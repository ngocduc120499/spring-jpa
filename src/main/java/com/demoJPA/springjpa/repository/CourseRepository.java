package com.demoJPA.springjpa.repository;

import com.demoJPA.springjpa.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionService;

@Repository
public interface CourseRepository extends MongoRepository<Course,String> {
    Page<Course> findByTitleContaining(String title, Pageable pageable);

    //@Query(value = "SELECT c FROM Course c WHERE c.title = ?1 ")
    @Query("{title:'?0'}")
    Optional<Course> findByTitle(String title);

}
