package com.mood.mood.service;

import com.mood.mood.dto.in.NoteForm;
import com.mood.mood.dto.out.NotesAverage;
import com.mood.mood.model.*;
import com.mood.mood.repository.EstablishmentRepository;
import com.mood.mood.repository.NoteRepository;
import com.mood.mood.repository.UserRepository;
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
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class NoteServiceTest {

    @InjectMocks
    private NoteService service;

    @Mock
    private NoteRepository repositoryMock;

    @Mock
    private UserRepository userRepositoryMock;

    @Mock
    private EstablishmentRepository establishmentRepository;

    @Mock
    private ModelMapper modelMapper;

    private NoteForm form;
    private User user;
    private Establishment establishment;
    private List<Note> notes;

    @BeforeEach
    public void setUp() {
        this.user = new User();
        this.user.setId(1);
        this.user.setFirstname("John");
        this.user.setName("Doe");
        this.user.setBirthdate(LocalDate.of(1990, 4, 1));
        this.user.setEmail("john.doe@test.com");
        this.user.setPhone("01 23 45 67 89");
        this.user.setRole(new Role(1, "ROLE_USER"));
        this.user.setMood(new Category());

        this.establishment = new Establishment();
        this.establishment.setId(1);
        this.establishment.setName("Establsihment");

        this.notes = Arrays.asList(
                new Note(0, 4, new Establishment(), this.user),
                new Note(2, 2, new Establishment(), new User()),
                new Note(3, 4, this.establishment, this.user),
                new Note(4, 2, this.establishment, new User())
        );

        this.form = new NoteForm(
                3
        );

    }

    @Test
    void noteEstablishment() throws Exception {
        lenient().when(userRepositoryMock.findById(1)).thenReturn(Optional.ofNullable(this.user));
        lenient().when(establishmentRepository.findById(1)).thenReturn(this.establishment);

        this.notes.get(0).setEstablishment(this.establishment);
        this.notes.get(0).setUser(this.user);

        this.notes.get(0).setValue(this.form.getValue());

        this.establishment.addNote(this.notes.get(0));

        Establishment result = service.noteEstablishment(this.form, 1, 1);

        assertEquals(result.getNotes().get(0), this.notes.get(0));
    }

    @Test
    void getNotesByValue() throws Exception {
        List<Note> notes4 = this.notes.stream().filter(note -> note.getValue() == 4).collect(Collectors.toList());
        when(repositoryMock.findByValue(4)).thenReturn(notes4);

        List<Note> result = service.getNotesByValue(4);

        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getId(), 0);
    }

    @Test
    void getNotesByEstablishment() throws Exception {
        List<Note> notesEstablishment = this.notes.stream().filter(note -> note.getEstablishment().getId() == 0).collect(Collectors.toList());
        when(repositoryMock.findAllByEstablishmentId(0)).thenReturn(notesEstablishment);

        List<Note> result = service.getNotesByEstablishment(0);

        assertEquals(result.size(), 2);
        assertEquals(result.get(0).getValue(), 4);
    }

    @Test
    void getNoteByEstablishmentAndUser() throws Exception {
        List<Note> notesEstablishmentUser = this.notes.stream().filter(note ->note.getEstablishment().getId() == 1 && note.getUser().getId() == 1).collect(Collectors.toList());
        when(repositoryMock.findByUserIdAndEstablishmentId(1, 1)).thenReturn(notesEstablishmentUser.get(0));

        Note result = service.getNoteByEstablishmentAndUser(1,1);

        assertEquals(result.getId(),3);
        assertEquals(result.getValue(), 4);
    }

    @Test
    void getEstablishmentAverage() throws Exception {
        List<Note> notesEstablishment = this.notes.stream().filter(note -> note.getEstablishment().getId() == 1).collect(Collectors.toList());
        when(repositoryMock.findAllByEstablishmentId(1)).thenReturn(notesEstablishment);

        float sum = 0;

        for (Note note : notesEstablishment) {
            sum += note.getValue();
        }

        NotesAverage average = new NotesAverage(sum/notesEstablishment.size());

        NotesAverage result = service.getEstablishmentAverage(1);

        assertEquals(result.getNote(), average.getNote());
    }

    @Test
    void deleteNoteById() throws Exception {
        service.deleteNoteById(0);

        verify(repositoryMock, times(1)).deleteById(eq(0));
    }

    @Test
    void getNoteRepository() {
        assertEquals(service.getNoteRepository(), this.repositoryMock);
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
}