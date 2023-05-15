package com.demoJPA.springjpa.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Course {
    @Id
    @SequenceGenerator(
            name = "course_sequence",
            sequenceName = "course_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "course_sequence"
    )
    private Long courseId;
    @NotNull(message = "Title can't be null")
    private String title;
    private Integer credit;
    @JsonIgnore
    @OneToOne(
            mappedBy = "course"
    )
    private CourseMaterial courseMaterial;

    @ManyToOne(
            cascade = CascadeType.ALL

    )
    @JoinColumn(
            name = "teacher_Id",
            referencedColumnName = "teacherId"
    )
    private Teacher teacher;

    public Course(Long courseId) {
        this.courseId = courseId;
    }

    @JsonIgnore
    @ManyToMany(
            cascade = CascadeType.ALL,
                fetch = FetchType.EAGER
    )
    @JoinTable(
           name = "student_course_map",
            joinColumns =  @JoinColumn(
                    name = "course_id",
                    referencedColumnName = "courseId"
            ),
            inverseJoinColumns = @JoinColumn(
                    name = "student_id",
                    referencedColumnName = "studentId"
            )
    )

    private List<Student> students;
    public void addStudents(Student student){
        if(students == null) students = new ArrayList<>();
        students.add(student);
    }
}
