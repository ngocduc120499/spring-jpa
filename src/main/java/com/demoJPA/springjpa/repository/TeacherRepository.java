package com.demoJPA.springjpa.repository;

import com.demoJPA.springjpa.entity.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TeacherRepository extends JpaRepository<Teacher,Long> {
}
