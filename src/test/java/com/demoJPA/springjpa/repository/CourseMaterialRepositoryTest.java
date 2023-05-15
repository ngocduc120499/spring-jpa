package com.demoJPA.springjpa.repository;

import com.demoJPA.springjpa.entity.Course;
import com.demoJPA.springjpa.entity.CourseMaterial;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Repository;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CourseMaterialRepositoryTest {
    @Autowired
    private CourseMaterialRepository repository;
    @Test
    public void SaveCourseMaterial(){
        Course course = Course.builder()
                        .title("Python")
                        .credit(6)
                        .build();
        CourseMaterial courseMaterial =
                CourseMaterial.builder()
                .url("www.python.com")
                .course(course)
                .build();
        repository.save(courseMaterial);
    }
    @Test
    public void printAllCourseMaterials(){
        List<CourseMaterial> courseMaterials =repository.findAll();
        System.out.println(courseMaterials);
    }


}