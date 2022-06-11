package br.com.katho.argonaut.service;

import br.com.katho.argonaut.dto.StudentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface StudentService {

    Optional<StudentDTO> create(StudentDTO dto);
    Optional<StudentDTO> getById(Long id);
    Page<StudentDTO> getAll(Pageable pageable);
}
