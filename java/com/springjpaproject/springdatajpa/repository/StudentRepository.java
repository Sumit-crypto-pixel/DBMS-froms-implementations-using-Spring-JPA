package com.springjpaproject.springdatajpa.repository;

import com.springjpaproject.springdatajpa.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
public List<Student> findByFirstName(String firstName); // we are defining our own method .
    //"findByFirstName" will do the task itself.

    List<Student> findByFirstNameContaining(String name); // first name with some characters contained.

    List<Student> findByLastNameNotNull();
    List<Student> findByGuardianName(String guardianName);

    // we will use Query annotation here.

    @Query("select s from Student s where s.emailId = ?1")  // JPQL query
    Student getStudentByEmailAddress(String emailId);

    @Query(   // native query
        value = "SELECT * FROM tbl_student s where s.email_address = ?1",
        nativeQuery = true
    )
    Student getStudentByEmailAddressNative(String emailId);


    @Query(   // native query with same implementation as above
            value = "SELECT * FROM tbl_student s where s.email_address = :emailId",
            nativeQuery = true
    )
    Student getStudentByEmailAddressNativeNamedParam(@Param("emailId") String emailId);

    // Now let's do updating stuff

    @Modifying
    @Transactional
    @Query(
            value = "update tbl_student set first_name = ?1 where email_address = ?2",
            nativeQuery = true
    )
      int updateStudentByEmailId(String firstName, String emailId);
}
