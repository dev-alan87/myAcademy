package io.github.devalan87.myAcademy.service.impl;

import io.github.devalan87.myAcademy.domain.entity.Course;
import io.github.devalan87.myAcademy.domain.repository.CourseRepository;
import io.github.devalan87.myAcademy.exception.CourseAlreadyRegistered;
import io.github.devalan87.myAcademy.exception.CourseNotFoundException;
import io.github.devalan87.myAcademy.service.CourseService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl
        implements CourseService {

    @Autowired
    private CourseRepository repository;

    @Override
    @Transactional
    public Course saveCourse(Course course)
            throws CourseAlreadyRegistered {
        return repository.save(course);
    }

    @Override
    public Course disableCourse(Boolean disabled, Integer id) {
        Course course = getCourse(id).get();
        course.setEnabled(!disabled);
        return saveCourse(course);
    }

    @Override
    public Course deprecateCourse(Boolean deprecated, Integer id) {
        Course course = getCourse(id).get();
        course.setDeprecated(deprecated);
        return saveCourse(course);
    }

    @Override
    public Optional<Course> getCourse(Integer id) {
        return Optional.ofNullable(repository.findById(id)
                .orElseThrow(CourseNotFoundException::new));
    }

    @Override
    public Optional<Course> getCourseByCode(String code) {
        return Optional.ofNullable(repository.findByCode(code)
                .orElseThrow(CourseNotFoundException::new));
    }

    @Override
    public List<Course> listCourses() {
        return repository.findAll();
    }

    @Override
    public List<Course> findCourseByName(String name) {
        return repository.findByNameContainingIgnoreCase(name);
    }

    @Override
    public List<Course> findCourse(Course filter) {
        ExampleMatcher matcher = ExampleMatcher
                .matching().
                withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);
        Example<Course> example = Example.of(filter, matcher);
        return repository.findAll(example);
    }

}
