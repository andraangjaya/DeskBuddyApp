package com.demo.deskbuddy.repository;

import com.demo.deskbuddy.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findAllByFirstNameOrLastNameContainingIgnoreCase(String firstName, String lastName);
}
