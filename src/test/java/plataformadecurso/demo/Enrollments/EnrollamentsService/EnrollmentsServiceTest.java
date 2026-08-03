package plataformadecurso.demo.Enrollments.EnrollamentsService;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import plataformadecurso.demo.Enrollments.DTO.RequesEnrollmentsDTO;
import plataformadecurso.demo.Enrollments.DTO.ResponseEnrollmentsDTO;
import plataformadecurso.demo.Enrollments.EnrollamentsEntity.EnrollmentEntity;
import plataformadecurso.demo.Enrollments.EnrollamentsRepository.EnrollmentsRepository;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EnrollmentsServiceTest {

    @Mock
    private EnrollmentsRepository enrollmentsRepository;

    @InjectMocks
    private EnrollmentsService enrollmentsService;

    @Test
    void shouldListAllEnrollments() {

        EnrollmentEntity enrollment = new EnrollmentEntity();

        when(enrollmentsRepository.findAll())
                .thenReturn(List.of(enrollment));

        List<ResponseEnrollmentsDTO> result =
                enrollmentsService.listAllResponses();

        assertEquals(1, result.size());

        verify(enrollmentsRepository).findAll();

    }

    @Test
    void shouldCreateEnrollment() {

        RequesEnrollmentsDTO request = mock(RequesEnrollmentsDTO.class);

        when(request.progress()).thenReturn(String.valueOf(50.0));
        when(request.enrolledAt()).thenReturn(LocalDateTime.now());

        EnrollmentEntity savedEntity = new EnrollmentEntity();

        when(enrollmentsRepository.save(any(EnrollmentEntity.class)))
                .thenReturn(savedEntity);

        ResponseEnrollmentsDTO response =
                enrollmentsService.createEnrollments(request);

        assertNotNull(response);

        verify(enrollmentsRepository)
                .save(any(EnrollmentEntity.class));

    }

    @Test
    void shouldUpdateEnrollment() {

        UUID id = UUID.randomUUID();

        EnrollmentEntity enrollment = new EnrollmentEntity();

        RequesEnrollmentsDTO request =
                mock(RequesEnrollmentsDTO.class);

        when(request.progress()).thenReturn(String.valueOf(80.0));
        when(request.enrolledAt()).thenReturn(LocalDateTime.now());

        when(enrollmentsRepository.findById(id))
                .thenReturn(Optional.of(enrollment));

        when(enrollmentsRepository.save(any(EnrollmentEntity.class)))
                .thenReturn(enrollment);

        ResponseEnrollmentsDTO response =
                enrollmentsService.updateEnrollments(id, request);

        assertNotNull(response);

        verify(enrollmentsRepository).findById(id);
        verify(enrollmentsRepository).save(enrollment);

    }

    @Test
    void shouldDeleteEnrollment() {

        UUID id = UUID.randomUUID();

        when(enrollmentsRepository.existsById(id))
                .thenReturn(true);

        enrollmentsService.deleteEnrollments(id);

        verify(enrollmentsRepository).deleteById(id);
    }

    @Test
    void shouldThrowExceptionWhenUpdatingNonexistentEnrollment() {

        UUID id = UUID.randomUUID();

        RequesEnrollmentsDTO request =
                mock(RequesEnrollmentsDTO.class);

        when(enrollmentsRepository.findById(id))
                .thenReturn(Optional.empty());

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> enrollmentsService.updateEnrollments(id, request)
                );

        assertEquals(
                "Enrollment with id " + id + " not found",
                exception.getMessage()
        );

    }

    @Test
    void shouldThrowExceptionWhenDeletingNonexistentEnrollment() {

        UUID id = UUID.randomUUID();

        when(enrollmentsRepository.existsById(id))
                .thenReturn(false);

        RuntimeException exception =
                assertThrows(
                        RuntimeException.class,
                        () -> enrollmentsService.deleteEnrollments(id)
                );

        assertEquals(
                "Enrollment with id " + id + " not found",
                exception.getMessage()
        );

    }
}