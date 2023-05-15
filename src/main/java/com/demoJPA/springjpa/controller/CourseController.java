package com.demoJPA.springjpa.controller;

import com.demoJPA.springjpa.entity.Course;
import com.demoJPA.springjpa.entity.Teacher;
import com.demoJPA.springjpa.exceptions.BadRequestException;
import com.demoJPA.springjpa.service.CourseService;
import net.bytebuddy.build.HashCodeAndEqualsPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getAllCourse(){
        List<Course> courses = courseService.getAllCourse();
        return courses;
    }
    @GetMapping(value = "{courseId}")
    public Optional<Course> getCourseById(@PathVariable Long courseId){
        Optional<Course> course = courseService.getCourseById(courseId);
        return course;
    }

    @PostMapping
    public void saveCourse(@RequestBody Course course ){
        courseService.saveCourse(course);
    }
    @PutMapping(value = "/{courseId}")
    public void updateCourse(@PathVariable Long courseId,
                       @RequestParam(required = false) String title,
                       @RequestParam(required = false) int credit){
        courseService.updateCourse(courseId,title,credit);
    }
    @DeleteMapping(path = "{courseId}")
    public void deleteCourse(@PathVariable("courseId") Long courseId){
        courseService.deleteCourse(courseId);
    }



}
