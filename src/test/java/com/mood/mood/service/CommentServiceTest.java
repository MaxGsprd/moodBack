package com.mood.mood.service;

import com.mood.mood.dto.in.CommentForm;
import com.mood.mood.dto.out.CommentDetails;
import com.mood.mood.model.*;
import com.mood.mood.repository.CommentRepository;
import com.mood.mood.repository.EstablishmentRepository;
import com.mood.mood.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Optional.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class CommentServiceTest {

    @InjectMocks
    private CommentService service;

    @Mock
    private CommentRepository repositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private EstablishmentRepository establishmentRepository;

    @Mock
    private ModelMapper modelMapper;

    private Comment comment;
    private CommentDetails commentDetails;
    private User user;
    private Establishment establishment;
    private List<Comment> comments;
    private CommentForm form;

    @BeforeEach
    public void setUp() {
        this.comment = new Comment();
        this.comment.setId(1);
        this.comment.setContent("content");
        this.comment.setTitle("title");
        this.comment.setStatus(true);
        this.comment.setGroupType(1);
        this.comment.setCreatedDate(LocalDateTime.of(2022,04,02,12,30));


        this.user = new User();
        this.user.setId(1);
        this.user.setFirstname("John");
        this.user.setName("Doe");
        this.user.setBirthdate(LocalDate.of(1990, 4, 1));
        this.user.setEmail("john.doe@test.com");
        this.user.setPhone("01 23 45 67 89");
        this.user.setRole(new Role(1, "ROLE_USER"));
        this.user.setMood(new Category());
        this.user.addComment(this.comment);
        this.comment.setUser(this.user);

        this.commentDetails = new CommentDetails(this.comment.getTitle(), this.comment.getContent(), "",this.comment.getCreatedDate().toString(), this.user);


        this.establishment = new Establishment();
        this.establishment.setId(1);
        this.establishment.setName("Establsihment");
        this.establishment.addComment(this.comment);
        this.comment.setEstablishment(this.establishment);

        this.comments = new ArrayList<>();
        this.comments.add(this.comment);

        this.form = new CommentForm(
                "title form",
                "content form",
                1
        );
    }

    @Test
    void getAllComments() {
        this.comments.add(new Comment());

        when(repositoryMock.findAll()).thenReturn(comments);
        lenient().when(modelMapper.map(this.comment, CommentDetails.class)).thenReturn(this.commentDetails);


        List<CommentDetails> result = service.getAllComments();

        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getTitle(), this.commentDetails.getTitle());
    }

    @Test
    void getAllCommentsByEstablishmentId() {
        lenient().when(establishmentRepository.findById(1)).thenReturn(this.establishment);

        List<Comment> commentsEstablishment = this.comments.stream().filter(comment -> comment.getEstablishment().getId() == 1).collect(Collectors.toList());

        when(repositoryMock.findByEstablishment(this.establishment)).thenReturn(commentsEstablishment);
        lenient().when(modelMapper.map(this.comment, CommentDetails.class)).thenReturn(this.commentDetails);

        List<CommentDetails> result = service.getAllCommentsByEstablishmentId(1);

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getTitle(), this.comment.getTitle());
    }

    @Test
    void getAllCommentsByStatus() {
        List<Comment> commentsStatus = this.comments.stream().filter(comment -> comment.getStatus()).collect(Collectors.toList());

        when(repositoryMock.findByStatus(true)).thenReturn(commentsStatus);
        lenient().when(modelMapper.map(this.comment, CommentDetails.class)).thenReturn(this.commentDetails);

        List<CommentDetails> result = service.getAllCommentsByStatus(true);

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getTitle(), this.comment.getTitle());
    }

    @Test
    void getAllCommentsByUserId() {
        lenient().when(userRepositoryMock.findById(1)).thenReturn(of(this.user));

        List<Comment> commentsUser = this.comments.stream().filter(comment -> comment.getUser().getId() == 1).collect(Collectors.toList());

        when(repositoryMock.findByUser(this.user)).thenReturn(commentsUser);
        lenient().when(modelMapper.map(this.comment, CommentDetails.class)).thenReturn(this.commentDetails);

        List<CommentDetails> result = service.getAllCommentsByUserId(1);

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getTitle(), this.comment.getTitle());
    }

    @Test
    void getAllCommentsByGroupType() {
        List<Comment> commentsGroupType = this.comments.stream().filter(comment -> comment.getGroupType() == 1).collect(Collectors.toList());

        when(repositoryMock.findByGroupType(1)).thenReturn(commentsGroupType);
        lenient().when(modelMapper.map(this.comment, CommentDetails.class)).thenReturn(this.commentDetails);

        List<CommentDetails> result = service.getAllCommentsByGroupType(1);

        assertEquals(result.size(), 1);
        assertEquals(result.get(0).getTitle(), this.comment.getTitle());
    }

    @Test
    void createComment() throws Exception {
        lenient().when(userRepositoryMock.findById(1)).thenReturn(of(this.user));
        lenient().when(establishmentRepository.findById(1)).thenReturn(this.establishment);

        Comment newComment = new Comment();
        newComment.setContent(this.form.getContent());
        newComment.setTitle(this.form.getTitle());
        newComment.setEstablishment(this.establishment);
        newComment.setUser(this.user);

        Comment result = service.createComment(this.form, 1, 1);

        assertEquals(result.getTitle(), newComment.getTitle());
        assertEquals(result.getEstablishment(), this.establishment);
        assertEquals(result.getUser(), this.user);
    }

    @Test
    void deleteCommentById() throws Exception {
        when(repositoryMock.findById(1)).thenReturn(of(this.comment));

        service.deleteCommentById(1);

        verify(repositoryMock, times(1)).deleteById(eq(1));
    }

    @Test
    void updateComment() throws Exception {
        when(repositoryMock.findById(1)).thenReturn(of(this.comment));
        lenient().when(modelMapper.map(this.comment, CommentDetails.class)).thenReturn(this.commentDetails);

        CommentDetails result = service.updateComment(1, this.form);

        this.commentDetails.setTitle(this.form.getTitle());
        this.commentDetails.setContent(this.form.getContent());

        assertEquals(result.getTitle(), this.commentDetails.getTitle());
        assertEquals(result.getContent(), this.commentDetails.getContent());
    }

    @Test
    void getCommentRepository() {
        assertEquals(service.getCommentRepository(), this.repositoryMock);
    }

    @Test
    void getEstablishmentRepository() {
        assertEquals(service.getEstablishmentRepository(), this.establishmentRepository);
    }

    @Test
    void getUserRepository() {
        assertEquals(service.getUserRepository(), this.userRepositoryMock);
    }

    @Test
    void getModelMapper() {
        assertEquals(service.getModelMapper(), this.modelMapper);
    }

    @Test
    void getAllCommentsByCreatedDateLatest() {
        Comment latestComment = new Comment();
        latestComment = new Comment();
        latestComment.setId(1);
        latestComment.setContent("content");
        latestComment.setTitle("title latest");
        latestComment.setStatus(true);
        latestComment.setGroupType(1);
        latestComment.setUser(new User());
        latestComment.setCreatedDate(LocalDateTime.of(2022,04,02,15,30));


        CommentDetails latestCommentDetails = new CommentDetails(latestComment.getTitle(), latestComment.getContent(), "",latestComment.getCreatedDate().toString(), latestComment.getUser());

        this.comments.add(latestComment);

        this.comments.sort(new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return o2.getCreatedDate().compareTo(o1.getCreatedDate());
            }
        });

        when(repositoryMock.findAll(Sort.by(Sort.Direction.DESC, "createdDate"))).thenReturn(comments);
        lenient().when(modelMapper.map(this.comment, CommentDetails.class)).thenReturn(this.commentDetails);
        lenient().when(modelMapper.map(latestComment, CommentDetails.class)).thenReturn(latestCommentDetails);



        List<CommentDetails> result = service.getAllCommentsByCreatedDateLatest();

        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getTitle(), latestCommentDetails.getTitle());
    }

    @Test
    void getAllCommentsByCreatedDateOldest() {
        Comment oldestComment = new Comment();
        oldestComment.setId(1);
        oldestComment.setContent("content");
        oldestComment.setTitle("title latest");
        oldestComment.setStatus(true);
        oldestComment.setGroupType(1);
        oldestComment.setUser(new User());
        oldestComment.setCreatedDate(LocalDateTime.of(2022,04,02,10,30));


        CommentDetails oldestCommentDetails = new CommentDetails(oldestComment.getTitle(), oldestComment.getContent(), "",oldestComment.getCreatedDate().toString(), oldestComment.getUser());

        this.comments.add(oldestComment);

        this.comments.sort(new Comparator<Comment>() {
            @Override
            public int compare(Comment o1, Comment o2) {
                return o1.getCreatedDate().compareTo(o2.getCreatedDate());
            }
        });

        when(repositoryMock.findAll(Sort.by(Sort.Direction.DESC, "createdDate"))).thenReturn(comments);
        lenient().when(modelMapper.map(this.comment, CommentDetails.class)).thenReturn(this.commentDetails);
        lenient().when(modelMapper.map(oldestComment, CommentDetails.class)).thenReturn(oldestCommentDetails);



        List<CommentDetails> result = service.getAllCommentsByCreatedDateLatest();

        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getTitle(), oldestCommentDetails.getTitle());

    }
}