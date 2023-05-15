package com.demoJPA.springjpa.repository;

import com.demoJPA.springjpa.entity.Course;
import com.demoJPA.springjpa.entity.Student;
import com.demoJPA.springjpa.entity.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@SpringBootTest
class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;
    @Test
    public void printCourses(){
        List<Course> courses = courseRepository.findAll();
        System.out.println(courses);
    }
    @Test
    public void saveCourseWithTeacher(){
        Teacher teacher = Teacher.builder()
                            .firstName("Le")
                            .lastName("Thanh")
                            .build();
        Course course = Course.builder()
                        .title("C#")
                        .credit(4)
                        .teacher(teacher)
                        .build();
        courseRepository.save(course);
    }
    @Test
    public void findAllPagination(){
        Pageable firstPageWithThreeRecords =
                PageRequest.of(0,3);
        Pageable secondPageWithTwoRecords =
                PageRequest.of(1,2);
        List<Course> courses = courseRepository.findAll(firstPageWithThreeRecords).getContent();
        List<Course> courses1 = courseRepository.findAll(secondPageWithTwoRecords).getContent();
        long totalElements =
                courseRepository.findAll(firstPageWithThreeRecords)
                .getTotalElements();
        long totalPages =
                courseRepository.findAll(firstPageWithThreeRecords)
                .getTotalPages();
        System.out.println("totalPages = " + totalPages);
        System.out.println("totalElements " + totalElements);
        System.out.println("courses = " + courses);
        System.out.println("courses = " + courses1);
    }
    @Test
    public void findAllSorting(){
        Pageable pageSortingWithTitle =
                PageRequest.of(0,3, Sort.by("title"));
        List<Course> courses = courseRepository.findAll(pageSortingWithTitle).getContent();
        System.out.println("Courses" + courses);
        Pageable pageSortingWithCreditAsc =
                PageRequest.of(0,5,Sort.by("credit").ascending());
        List<Course> courses1 = courseRepository.findAll(pageSortingWithCreditAsc).getContent();
        System.out.println("Courses Sort by Credit Asc" + courses1);
        Pageable pageSortingWithCreditDsc =
                PageRequest.of(0,5,Sort.by("credit").descending());
        List<Course> courses2 = courseRepository.findAll(pageSortingWithCreditDsc).getContent();
        System.out.println("Courses Sort by Credit Dsc" + courses2);
    }
    @Test
    public void printfindByTitleContaining(){
        Pageable firstPageTenRecords =
                PageRequest.of(0, 10);
        List<Course> courses = courseRepository.findByTitleContaining("P",firstPageTenRecords).getContent();
        System.out.println("Courses = " + courses);
    }
    @Test
    public void saveCourseWithStudentAndTeacher(){
        Teacher teacher = Teacher.builder()
                .firstName("Lizze")
                .lastName("Morgan")
                .build();

        Student student = Student.builder()
                .firstName("Abhiskek")
                .lastName("Teo")
                .emailId("teo@gmail.com")
                .build();
        Course course = Course.builder()
                .title(null)
                .credit(12)
                .teacher(teacher)
                .build();
        course.addStudents(student);
        courseRepository.save(course);
    }
    @Test
    public void findTitleByCredit(){
        List<String> check = courseRepository.findTitleByCredit(4);
        System.out.println(check);
    }
    @Test
    public void findCourseById(){
        List<Course> check = courseRepository.findCourseById(Long.valueOf(2));
        System.out.println(check);
    }
}