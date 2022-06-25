package br.com.katho.argonaut.service;

import br.com.katho.argonaut.dto.StudentDTO;
import br.com.katho.argonaut.persistence.model.Student;
import br.com.katho.argonaut.persistence.repository.StudentRepository;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.loader.FixtureFactoryLoader;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.*;

@SpringBootTest
@Sql(executionPhase = BEFORE_TEST_METHOD, scripts = {"classpath:populate-database.sql"}) //EXECUTA O populate-database.sql
public class StudentServiceTest {

    @Autowired
    private StudentService service;

    @Autowired
    private StudentRepository repository;

    //@BeforeEach //SOBE A CADA "CHAMADA" DO MÉTODO
    @BeforeAll //SOBE "ALL" QUE TIVER NO BASEPACKAGE
    public void setUp() {
        //AQUI SÓ ESTAMOS SUBINDO O TEMPLATE
        FixtureFactoryLoader.loadTemplates("br.com.katho.argonaut.fixture");
    }

    @Test
    public void shouldCreateStudent() {
        //AQUI CONSUMIMOS O TEMPLATE
        StudentDTO request = Fixture.from(StudentDTO.class).gimme("valid");

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

    @Test
    public void shouldCreateStudentWithoutPaymentInformation() {
        StudentDTO request = Fixture.from(StudentDTO.class).gimme("valid");

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

    @Test
    public void shouldGetStudentById() {
        //Possibilidade 1: popular o banco de teste e fazer um find geral
        Student student = repository.findAll().get(0);
        Optional<StudentDTO> response = service.getById(student.getId());

        assertNotNull(response.get());
        assertEquals(student.getName(), response.get().getName());
        assertEquals(student.getEmail(), response.get().getEmail());
        assertEquals(student.getIdentityNumber(), response.get().getIdentityNumber());
        assertEquals(student.getPostalCode(), response.get().getPostalCode());
        assertEquals(student.getAddressLine(), response.get().getAddressLine());
        assertEquals(student.getStudentSince(), response.get().getStudentSince());
        assertEquals(student.getBirthdate(), response.get().getBirthDate());
        assertEquals(student.getCellphone(), response.get().getCellphone());
        assertEquals(student.getMonthlyBill(), response.get().getMonthlyBill());
        assertEquals(student.getLastPayDate(), response.get().getLastPayDate());


        //Possibilidade 2: fazer um template da entidade e consumir
//        Student student = Fixture.from(StudentDTO.class).gimme("valid-entity");
//        service.getById(student.getId());
    }

    @Test
    public void shouldGetAll() {
        Page<StudentDTO> responses = service.getAll(Pageable.ofSize(10));
        assertNotNull(responses.getContent());
        assertThat(responses.getContent().size(), greaterThan(0));
    }

    @Test
    public void shouldGetAllByStatusActive() {
        List<StudentDTO> responses = service.getByStatus(true);
        assertNotNull(responses);
        assertEquals(responses.size(), 1);
    }

    @Test
    public void shouldGetAllByStatusInactive() {
        List<StudentDTO> responses = service.getByStatus(false);
        assertNotNull(responses);
        assertEquals(responses.size(), 1);
    }

    @Test
    public void shouldGetByStatusEmptyList() {
        service.delete(1L);
        List<StudentDTO> responses = service.getByStatus(true);
        assertNotNull(responses);
        assertEquals(responses.size(), 0);
    }

}
