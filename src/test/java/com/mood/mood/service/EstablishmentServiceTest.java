package com.mood.mood.service;

import com.mood.mood.dto.in.EstablishmentForm;
import com.mood.mood.dto.in.LocalisationForm;
import com.mood.mood.dto.out.CommentDetails;
import com.mood.mood.dto.out.EstablishmentDetails;
import com.mood.mood.dto.out.NotesAverage;
import com.mood.mood.model.*;
import com.mood.mood.repository.CategoryRepository;
import com.mood.mood.repository.CommentRepository;
import com.mood.mood.repository.EstablishmentRepository;
import com.mood.mood.repository.NoteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

@ExtendWith(MockitoExtension.class)
class EstablishmentServiceTest {

    @InjectMocks
    EstablishmentService service;

    @Mock
    EstablishmentRepository repositoryMock;

    @Mock
    CommentRepository commentRepositoryMock;
    @Mock
    CommentService commentServiceMock;

    @Mock
    NoteRepository noteRepositoryMock;
    @Mock
    NoteService noteServiceMock;

    @Mock
    CategoryRepository categoryRepository;
    @Mock
    ModelMapper modelMapper;

    private Establishment establishment;
    private Comment comment;
    private Note note;
    private Category category;
    private List<Note> notes;
    private EstablishmentForm form;
    private LocalisationForm localisationForm;

    @BeforeEach
    public void setUp() {
        this.establishment = new Establishment();
        this.establishment.setId(1);
        this.establishment.setName("Name");
        this.establishment.setDescription("Desc");
        this.establishment.setLocalisation(new Localisation());
        this.establishment.setStatus(true);

        this.comment = new Comment();
        this.comment.setId(1);
        this.comment.setTitle("Bien");
        this.comment.setContent("Content");

        this.note = new Note();
        this.note.setId(1);
        this.note.setValue(4);

        this.category = new Category(
                0,
                "CHILL",
                "desc"
        );

        this.establishment.setCategory(this.category);

        this.notes = Arrays.asList(
                new Note(3, 4, this.establishment, new User()),
                new Note(4, 2, this.establishment, new User())
        );

        this.establishment.setNotes(this.notes);
        this.establishment.setComments(Arrays.asList(this.comment));

        this.localisationForm = new LocalisationForm();
        this.localisationForm.setAddressNumber("29");
        this.localisationForm.setAddressName("Rue Saint Germain");
        this.localisationForm.setPostalCode("91760");
        this.localisationForm.setCity("Itteville");

        /*this.form = new EstablishmentForm(
                "Name form",
                "description form",
                0
        );*/
        this.form = new EstablishmentForm();
        this.form.setName("Name form");
        this.form.setDescription("description form");
        this.form.setLocalisationForm(this.localisationForm);
        this.form.setCategory(0);
    }

    @Test
    void getEstablishmentById() {
        when(repositoryMock.findById(1)).thenReturn(this.establishment);
        lenient().when(noteServiceMock.notesAverage(this.establishment.getNotes())).thenReturn(new NotesAverage(3));

        EstablishmentDetails result = service.getEstablishmentById(1);

        assertEquals(result.getName(), "Name");
        assertEquals(result.getNote().getNote(), 3);
    }

    @Test
    void getAllEstablishmentsByCategoryId() {
        when(repositoryMock.findByCategoryId(0)).thenReturn(Arrays.asList(this.establishment));
        lenient().when(noteServiceMock.notesAverage(this.establishment.getNotes())).thenReturn(new NotesAverage(3));

        List<EstablishmentDetails> result = service.getAllEstablishmentsByCategoryId(0);

        assertEquals(result.get(0).getName(), "Name");
        assertEquals(result.get(0).getNote().getNote(), 3);
    }

    @Test
    void getAllEstablishments() {
        when(repositoryMock.findAll()).thenReturn(Arrays.asList(this.establishment));
        lenient().when(noteServiceMock.notesAverage(this.establishment.getNotes())).thenReturn(new NotesAverage(3));

        List<EstablishmentDetails> result = service.getAllEstablishments();

        assertEquals(result.get(0).getName(), "Name");
        assertEquals(result.get(0).getNote().getNote(), 3);
    }

    @Test
    void getEstablishmentByNameLike() throws Exception {
        when(repositoryMock.findByNameLikeIgnoreCase("na")).thenReturn(Arrays.asList(this.establishment));
        lenient().when(noteServiceMock.notesAverage(this.establishment.getNotes())).thenReturn(new NotesAverage(3));

        List<EstablishmentDetails> result = service.getEstablishmentByNameLike("na");

        assertEquals(result.get(0).getName(), "Name");
        assertEquals(result.get(0).getNote().getNote(), 3);
    }

    @Test
    void createEstablishment() throws Exception {
        lenient().when(repositoryMock.save(this.establishment)).thenReturn(this.establishment);
        lenient().when(categoryRepository.findById(0)).thenReturn(this.category);

        Establishment result = service.createEstablishment(this.form);

        assertEquals(result.getName(), "Name form");
    }

    @Test
    void deleteEstablishmentById() throws Exception {
        when(repositoryMock.findById(1)).thenReturn(this.establishment);

        service.deleteEstablishmentById(1);

        verify(repositoryMock, times(1)).deleteById(eq(1));
    }

    @Test
    void updateEstablishment() throws Exception {
        when(repositoryMock.findById(1)).thenReturn(this.establishment);
        lenient().when(categoryRepository.findById(0)).thenReturn(this.category);

        this.establishment.setName(this.form.getName());
        this.establishment.setDescription(this.form.getDescription());
        this.establishment.setCategory(this.category);

        EstablishmentDetails result = service.updateEstablishment(1, this.form);

        assertEquals(result.getName(), this.establishment.getName());
        assertEquals(result.getCategory().getTitle(), this.establishment.getCategory().getTitle());
    }

    @Test
    void getEstablishmentsByStatus() throws Exception {
        when(repositoryMock.findByStatus(true)).thenReturn(Arrays.asList(this.establishment));
        lenient().when(noteServiceMock.notesAverage(this.establishment.getNotes())).thenReturn(new NotesAverage(3));

        List<EstablishmentDetails> result = service.getEstablishmentsByStatus(true);

        assertEquals(result.get(0).getName(), this.establishment.getName());
        assertEquals(result.get(0).getNote().getNote(), 3);
        assertEquals(result.get(0).getCategory().getTitle(), this.establishment.getCategory().getTitle());

    }

    @Test
    void getEstablishmentRepository() {
        assertEquals(service.getEstablishmentRepository(), this.repositoryMock);
    }

    @Test
    void getCommentRepository() {
        assertEquals(service.getCommentRepository(), this.commentRepositoryMock);
    }

    @Test
    void getCommentService() {
        assertEquals(service.getCommentService(), this.commentServiceMock);
    }

    @Test
    void getCategoryRepository() {
        assertEquals(service.getCategoryRepository(), this.categoryRepository);
    }

    @Test
    void getNoteRepository() {
        assertEquals(service.getNoteRepository(), this.noteRepositoryMock);
    }

    @Test
    void getNoteService() {
        assertEquals(service.getNoteService(), this.noteServiceMock);
    }

    @Test
    void getModelMapper() {
        assertEquals(service.getModelMapper(), this.modelMapper);
    }
}