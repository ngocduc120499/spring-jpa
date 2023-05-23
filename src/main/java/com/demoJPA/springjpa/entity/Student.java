package com.demoJPA.springjpa.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
//@Table(
//        name="tbl_student",
//        uniqueConstraints = @UniqueConstraint(
//                name = "emailid_unique",
//                columnNames = "email_address"
//        )
//)
@Document("student")
public class Student {

    @Id
    private String studentId;
    private String firstName;
    private String lastName;

    private String emailId;
    private Guardian guardian;
}
