package com.demoJPA.springjpa.service;

import com.demoJPA.springjpa.entity.Course;
import com.demoJPA.springjpa.exceptions.BadRequestException;
import com.demoJPA.springjpa.repository.CourseRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;
    public List<Course> getAllCourse() {
        List<Course> courses = courseRepository.findAll();
        return  courses;
    }
    public Optional<Course> getCourseById(String id) {
        Optional<Course> course = courseRepository.findById(id);
        return course;
    }
    public Optional<Course> findByTitle(String title){
        Optional<Course> course = courseRepository.findByTitle(title);
        return course;
    }
    public void saveCourse(Course course){
        Optional<Course> checkTitle = findByTitle(course.getTitle());
        if (checkTitle.isPresent()){
            throw new BadRequestException("Course " + course.getTitle() + "taken");
        }
        courseRepository.save(course);
    }
        @Transactional
        public void updateCourse(String id, String title, Integer credit){
            Course course = courseRepository.findById(id)
                            .orElseThrow(()->new IllegalStateException("Course with id" + id + "does not exits "));
            if(title != null && title.length()>0 && !Objects.equals(course.getTitle(),title)){
                    course.setTitle(title);
            }
            if(credit != 0 && !Objects.equals(course.getCredit(),credit)){
                if(credit < 0){
                    throw new IllegalStateException("Credit has value greater 0");
                }
                    course.setCredit(credit);
            }
        }
    public void deleteCourse(String id){
        Optional<Course> exist = courseRepository.findById(id);
        if(exist.isEmpty()) {
            throw new BadRequestException("Course with id" + id + "does not exists");
        }
        courseRepository.deleteById(id);
    }

}
