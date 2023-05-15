package com.demoJPA.springjpa.controller;


import com.demoJPA.springjpa.entity.Teacher;
import com.demoJPA.springjpa.service.TeacherService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;
    @GetMapping
    public List<Teacher> getAllTeacher(){
        List<Teacher> teachers = teacherService.getAllTeacher();
        return teachers;
    }
    @GetMapping(value ="/{teacherId}")
    public Optional<Teacher> getTeacherbyId(@PathVariable Long teacherId){
        Optional<Teacher> isTeacher = teacherService.getTeacherById(teacherId);
        return isTeacher;
    }

}
