package com.springjpaproject.springdatajpa.repository;

import com.springjpaproject.springdatajpa.entity.Course;
import com.springjpaproject.springdatajpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void printCourse(){
        List<Course> courses = courseRepository.findAll();
        System.out.println("courses = "+ courses);
    }

    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder().
                firstName("Prateek").
                lastName("Chattopadhyay").
                build();
        Course course = Course.builder()
                .title("C++")
                .credit(13)
                .teacher(teacher)
                .build();
        courseRepository.save(course);
    }

    @Test
    public  void findAllPagination () {
        Pageable firstPageWithThreeRecords =
        PageRequest.of(0, 3);
        Pageable secondPageWithThreeRecords =
        PageRequest.of(1, 2);  // 2nd page with two records

        List<Course> courses =
                courseRepository.findAll(firstPageWithThreeRecords).getContent();

        long totalElements = courseRepository.findAll(firstPageWithThreeRecords)
                        .getTotalElements();

        long totalPages = courseRepository.findAll(firstPageWithThreeRecords)
                        .getTotalPages();

        System.out.println("totalPages = "+ totalPages);

        System.out.println("totalElements = "+ totalElements);

        System.out.println("courses = "+ courses);

    }
    @Test
    public void findAllSorting(){
        Pageable sortByTitle =
                PageRequest.of(
                        0,2,
                        Sort.by("title")
                );
          Pageable sortByCreditDesc =
                  PageRequest.of(
                          0,2,
                          Sort.by("credit").descending()
                  );
        Pageable sortByTitleAndCreditDesc =
                PageRequest.of(
                        0,2,
                        Sort.by("title").descending().descending()
                                .and(Sort.by("credit"))
                  );
          List<Course> courses = courseRepository.findAll(sortByTitle).getContent();
        System.out.println("courses = " + courses);
    }
}