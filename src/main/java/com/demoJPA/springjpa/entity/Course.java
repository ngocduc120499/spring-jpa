package com.demoJPA.springjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document("course")
public class Course {
    @Id
    private String courseId;
    @NotNull(message = "Title can't be null")
    private String title;
    private Integer credit;
    @JsonIgnore
    private CourseMaterial courseMaterial;

    private Teacher teacher;


    @JsonIgnore
    private List<Student> students;
    public void addStudents(Student student){
        if(students == null) students = new ArrayList<>();
        students.add(student);
    }
}
