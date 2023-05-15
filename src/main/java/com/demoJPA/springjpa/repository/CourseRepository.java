package com.demoJPA.springjpa.repository;

import com.demoJPA.springjpa.entity.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionService;

@Repository
public interface CourseRepository extends JpaRepository<Course,Long> {
    Page<Course> findByTitleContaining(String title, Pageable pageable);

    @Query(value = "SELECT c FROM Course c WHERE c.title = ?1 ")
    Optional<Course> findByTitle(String title);

    @Query(value = "SELECT c.title FROM Course c WHERE c.credit = ?1")
    List<String> findTitleByCredit(int credit);

    @Query(value = "SELECT c FROM Course c WHERE c.courseId = ?1")
    List<Course> findCourseById(Long courseId);
}
