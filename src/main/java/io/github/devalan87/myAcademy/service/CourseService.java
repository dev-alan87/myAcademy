package io.github.devalan87.myAcademy.service;

import io.github.devalan87.myAcademy.domain.entity.Course;
import io.github.devalan87.myAcademy.exception.CourseAlreadyRegistered;

import java.util.List;
import java.util.Optional;

public interface CourseService {

    Course saveCourse(Course course) throws CourseAlreadyRegistered;
    Course disableCourse(Boolean disabled, Integer id);
    Course deprecateCourse(Boolean deprecated, Integer id);
    Optional<Course> getCourse(Integer id);
    Optional<Course> getCourseByCode(String code);
    List<Course> listCourses();
    List<Course> findCourseByName(String name);
    List<Course> findCourse(Course filter);

}
