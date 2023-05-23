package com.demoJPA.springjpa.repository;

import com.demoJPA.springjpa.entity.Teacher;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TeacherRepository extends MongoRepository<Teacher,Long> {
}
