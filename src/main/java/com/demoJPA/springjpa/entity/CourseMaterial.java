package com.demoJPA.springjpa.entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString(exclude = "course")
@Document("course_material")
public class CourseMaterial {
    @Id
    private Long courseMaterialId;
    private String url;
    private Course course;
}
