package com.springjpaproject.springdatajpa.repository;

import com.springjpaproject.springdatajpa.entity.Guardian;
import com.springjpaproject.springdatajpa.entity.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("Sahil@gmail.com")
                .firstName("Sahil")
                .lastName("Kumar")
                //.guardianName("Sanjay")
               // .guardianEmail("sanjay@gmail.com")
               // .guardianMobile("998364372")
                .build();

        studentRepository.save(student);
    }
@Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                .email("sanjay@gmail.com")
                .name("Sanjay")
                .mobile("92848472923702")
                .build();
         Student student = Student.builder()
                 .firstName("Sumit")
                 .emailId("Sumit@gmail.com")
                 .lastName("Kumar")
                 .guardian(guardian)
                 .build();

         studentRepository.save(student);
    }
    @Test
    public void printAllStudent(){
        List<Student> studentList = studentRepository.findAll();
        System.out.println("studentList = "+ studentList);
    }

    @Test
    public void printStudentByFirstName(){
       List<Student> students =
               studentRepository.findByFirstName("Sumit");
        System.out.println("students = " + students);
    }

    @Test
    public void printStudentByFirstNameContaining(){
        List<Student> students =
                studentRepository.findByFirstNameContaining("mi");
        System.out.println("students = " + students);
    }

    @Test
    public void printStudentBasedOnGuardianName(){
       List<Student> students =
       studentRepository.findByGuardianName("Sanjay");
        System.out.println("students = " + students);
    }

    @Test
    public void printGetStudentByEmailAddress(){
        Student student = studentRepository.getStudentByEmailAddress("Sumit@gmail.com");
        System.out.println("student = "+ student);

    }
    @Test  // native    query
    public void getStudentByEmailAddressNative(){
        Student student = studentRepository.getStudentByEmailAddressNative("kumar@gmail.com");
        System.out.println("student = "+ student);
    }

     @Test
    public void getStudentByEmailAddressNativeNamedParam(){
         Student student = studentRepository.getStudentByEmailAddressNativeNamedParam("kumar@gmail.com");
         System.out.println("student = "+ student);
    }
    @Test
    public void updateStudentByEmailId(){
        studentRepository.updateStudentByEmailId(
                "Falana",
                "kumar@gmail.com"

        );
    }
}