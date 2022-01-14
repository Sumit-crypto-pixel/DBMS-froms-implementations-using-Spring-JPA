package com.springjpaproject.springdatajpa.repository;

import com.springjpaproject.springdatajpa.entity.Course;
import com.springjpaproject.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherRepositoryTest {
    @Autowired
   private TeacherRepository teacherRepository;
    @Test
    public void saveTeacher(){  // 2 courses will be taught by a teacher
        Course courseDBA = Course.builder()
                .title("DBA")
                .credit(7)
                .build();
        Course courseJava = Course.builder()
                .title("JAVA")
                .credit(11)
                .build();
        Teacher teacher =
                Teacher.builder()
                        .firstName("Qutub")
                        .lastName("Khan")
                        //.courses(List.of(courseDBA,courseJava))
                        .build();
        teacherRepository.save(teacher);
    }
}