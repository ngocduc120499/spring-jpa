package com.demoJPA.springjpa.repository;

import com.demoJPA.springjpa.entity.CourseMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseMaterialRepository extends JpaRepository<CourseMaterial,Long> {
}
