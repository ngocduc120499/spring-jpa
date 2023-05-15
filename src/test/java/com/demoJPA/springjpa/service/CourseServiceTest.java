package com.demoJPA.springjpa.service;

import com.demoJPA.springjpa.entity.Course;
import com.demoJPA.springjpa.entity.Teacher;
import com.demoJPA.springjpa.exceptions.BadRequestException;
import com.demoJPA.springjpa.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.useDefaultDateFormatsOnly;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.BDDMockito.given;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CourseServiceTest {
    @Mock
    private CourseRepository courseRepository;
    private CourseService courseService;
    private Teacher teacher;
    private Course course;

    @BeforeEach
    void setUp() {
        courseService = new CourseService(courseRepository);
         teacher = Teacher.builder()
                .firstName("Le")
                .lastName("Thanh")
                .build();
         course = Course.builder()
                .title("C#")
                .credit(4)
                .teacher(teacher)
                .build();
    }


    @Test
    void canGetAllCourses(){
        //when
        courseService.getAllCourse();
        //then
        Mockito.verify(courseRepository).findAll();
    }

    @Test
    void canAddCourse(){
        //Given
        Teacher teacher = Teacher.builder()
                .firstName("Le")
                .lastName("Thanh")
                .build();
        Course course = Course.builder()
                .title("C#")
                .credit(4)
                .teacher(teacher)
                .build();
        //When
        courseService.saveCourse(course);

        //Then
        ArgumentCaptor<Course> courseArgumentCaptor = ArgumentCaptor.forClass(Course.class);

        Mockito.verify(courseRepository).save(courseArgumentCaptor.capture());

        Course capturedCapture = courseArgumentCaptor.getValue();

        assertThat(capturedCapture).isEqualTo(course);

    }

    @Test
    void willThrowWhenTitleIsTaken() {
        // given
        //Given
        Optional<Course> course = Optional.ofNullable(Course.builder()
                .title("C#")
                .credit(4)
                .teacher(teacher)
                .build());

        given(courseRepository.findByTitle(Mockito.anyString()))
                .willReturn(course);

        // when
        // then
        assertThatThrownBy(() -> courseService.saveCourse(course.get()))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Course " + course.get().getTitle() + "taken");

        verify(courseRepository, never()).save(Mockito.any());

    }

    @Test
    void canDeleteCourse() {
        Long id = 2L;
        Optional<Course> course = Optional.ofNullable(Course.builder().courseId(id)
                .title("C#")
                .credit(4)
                .teacher(teacher)
                .build());
        given(courseRepository.findById(id)).willReturn(course);
        courseService.deleteCourse(id);
        verify(courseRepository).deleteById(id);
    }

    @Test
    void willThrowWhenCourseDoesNotExist() {

        //Given
        Long id = 1L;
        // when
        given(courseRepository.findById(id)).willReturn(Optional.empty());


        // then
        assertThatThrownBy(() -> courseService.deleteCourse(id))
                .isInstanceOf(BadRequestException.class)
                .hasMessageContaining("Course with id" + id + "does not exists");

        verify(courseRepository, never()).deleteById(Mockito.any());

    }
    @Test
    void canUpdateCourse(){
        Long id = 2L;
        String newTitle = "New Title";
        int newCredit = 3;
        Optional<Course> course = Optional.ofNullable(Course.builder().courseId(id)
                .title("C#")
                .credit(4)
                .teacher(teacher)
                .build());
        given(courseRepository.findById(id)).willReturn(course);

        courseService.updateCourse(id,newTitle,newCredit);

        verify(courseRepository,times(1)).findById(id);

        assertThat(course.get().getTitle()).isEqualTo(newTitle);
        assertThat(course.get().getCredit()).isEqualTo(newCredit);

    }

    @Test
    void willThrowWhenCreditHasValueGreaterZero(){
        Long id = 2L;
        String newTitle = "New Title";
        int newCredit = -1;
        Optional<Course> course = Optional.ofNullable(Course.builder().courseId(id)
                .title("C#")
                .credit(4)
                .teacher(teacher)
                .build());
        given(courseRepository.findById(id)).willReturn(course);

        assertThatThrownBy(() -> courseService.updateCourse(id,newTitle,newCredit))
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("Credit has value greater 0");

        verify(courseRepository,times(1)).findById(id);
//        assertThat(course.get().getTitle()).isEqualTo(newTitle);
//        assertThat(course.get().getCredit()).isEqualTo(newCredit);

    }
    @Test
    void CanGetCourseById(){
        // Arrange
        Long id = 1L;

        Course course = new Course();
        course.setCourseId(id);
        course.setTitle("Title");
        course.setCredit(1);

        when(courseRepository.findById(id)).thenReturn(Optional.of(course));

        // Act
        Optional<Course> expectResult = courseService.getCourseById(id);

        // Assert
        assertThat(course).isEqualTo(expectResult.get());

    }

}