package br.com.katho.argonaut.service;

import br.com.katho.argonaut.dto.StudentDTO;
import br.com.katho.argonaut.persistence.model.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class StudentServiceTest {

    @Autowired
    private StudentService service;

    StudentDTO request;

    @BeforeEach
    public void setUp() {
        request = new StudentDTO();
        request.setName("Katho");
        request.setEmail("pkatho@gmail.com");
        request.setIdentityNumber("123456789");
        request.setPostalCode("1235788");
        request.setAddressLine("Avenida Jabaquara, 1469");
        request.setStudentSince(LocalDate.now());
        request.setBirthDate(LocalDate.now());
        request.setCellphone(14997133470L);
        request.setMonthlyBill(150.00);
        request.setLastPayDate(LocalDate.now());
    }

    //@Test
    public void shouldCreateStudent() {

        Optional<StudentDTO> response = service.create(request);

        assertNotNull(response.get());
        assertEquals(request.getName(), response.get().getName());
        assertEquals(request.getEmail(), response.get().getEmail());
        assertEquals(request.getIdentityNumber(), response.get().getIdentityNumber());
        assertEquals(request.getPostalCode(), response.get().getPostalCode());
        assertEquals(request.getAddressLine(), response.get().getAddressLine());
        assertEquals(request.getStudentSince(), response.get().getStudentSince());
        assertEquals(request.getBirthDate(), response.get().getBirthDate());
        assertEquals(request.getCellphone(), response.get().getCellphone());
        assertEquals(request.getMonthlyBill(), response.get().getMonthlyBill());
        assertEquals(request.getLastPayDate(), response.get().getLastPayDate());
    }

    //@Test
    public void shouldCreateStudentWithoutPaymentInformation() {
        request.setMonthlyBill(0.0);
        request.setLastPayDate(null);

        Optional<StudentDTO> response = service.create(request);

        assertNotNull(response.get());
        assertEquals(request.getName(), response.get().getName());
        assertEquals(request.getEmail(), response.get().getEmail());
        assertEquals(request.getIdentityNumber(), response.get().getIdentityNumber());
        assertEquals(request.getPostalCode(), response.get().getPostalCode());
        assertEquals(request.getAddressLine(), response.get().getAddressLine());
        assertEquals(request.getStudentSince(), response.get().getStudentSince());
        assertEquals(request.getBirthDate(), response.get().getBirthDate());
        assertEquals(request.getCellphone(), response.get().getCellphone());
        assertEquals(response.get().getMonthlyBill(), 0.0);
        assertNull(response.get().getLastPayDate());
    }

}
