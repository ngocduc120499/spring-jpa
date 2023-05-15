package com.demoJPA.springjpa.service;

import com.demoJPA.springjpa.entity.Teacher;
import com.demoJPA.springjpa.repository.TeacherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    public List<Teacher> getAllTeacher(){
        List<Teacher> listTeacher = teacherRepository.findAll();
        return listTeacher;
    }
    public Optional<Teacher> getTeacherById(Long teacherId){
        Optional<Teacher> teacher = teacherRepository.findById(teacherId);
        return teacher;
    }
}
