package com.demoJPA.springjpa.repository;

import com.demoJPA.springjpa.entity.CourseMaterial;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CourseMaterialRepository extends MongoRepository<CourseMaterial,Long> {
}
