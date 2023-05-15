package com.demoJPA.springjpa.repository;

import com.demoJPA.springjpa.entity.Course;
import com.demoJPA.springjpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class   TeacherRepositoryTest {
    @Autowired
    private TeacherRepository teacherRepository;
    //To use OneToMany
    @Test
    public void saveTeacher(){
        Course course = Course.builder()
                        .title("JAVA")
                        .credit(5).build();
        Course course1 = Course.builder()
                .title(".Net")
                .credit(6).build();
        List<Course> list = new ArrayList<>();
        list.add(course);
        list.add(course1);
        Teacher teacher = Teacher.builder()
                            .firstName("Nguyen")
                            .lastName("Duc")
//                            .courses(list)
                            .build();
        teacherRepository.save(teacher);
    }
}