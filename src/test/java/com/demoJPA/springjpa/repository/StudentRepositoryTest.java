package com.demoJPA.springjpa.repository;

import com.demoJPA.springjpa.entity.Guardian;
import com.demoJPA.springjpa.entity.Student;
import org.hibernate.id.GUIDGenerator;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentRepositoryTest {
    @Autowired
    private StudentRepository studentRepository;

    @Test
    public void saveStudent(){
        Student student = Student.builder()
                .emailId("ducnguyenqn22@gmail.com")
                .firstName("Nguyen")
                .lastName("Duc")
//                .guardianName("Mr A")
//                .guardianEmail("mra@gmail.com")
//                .guardianMobile("7777777777")
                .build();
        studentRepository.save(student);
    }
    @Test
    public void saveStudentWithGuardian(){
        Guardian guardian = Guardian.builder()
                            .email("mra@gmail.com")
                            .name("Mr A")
                            .mobile("777777")
                            .build();
        Student student = Student.builder()
                            .firstName("Nguyen")
                            .emailId("abc@gmail.com")
                            .lastName("ABC")
                            .guardian(guardian)
                            .build();
        studentRepository.save(student);

    }
    @Test
    public void getAllStudent(){
        List<Student> list = studentRepository.findAll();
        System.out.println(list);
    }
    @Test
    public void findByFirstName(){
        List<Student> students =
                studentRepository.findByFirstName("Nguyen");
        System.out.println(students);
    }
    @Test
    public void findByFirstNamebyEmail(){
        Student students =
                studentRepository.getStudentByEmailAddressNative("abc@gmail.com");
        System.out.println(students);
    }
    @Test
    public void findByFirstNamebyEmailNativeNamedParam(){
        Student students =
                studentRepository.getStudentByEmailAddressNativeNamedParam("abc2@gmail.com");
        System.out.println(students);
    }
}