package io.github.devalan87.myAcademy.api.controller;

import io.github.devalan87.myAcademy.domain.entity.Course;
import io.github.devalan87.myAcademy.exception.CourseAlreadyRegistered;
import io.github.devalan87.myAcademy.exception.CourseNotFoundException;
import io.github.devalan87.myAcademy.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/course")
public class CourseController {

    @Autowired
    CourseService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Course createCourse(@RequestBody Course course) {
        try {
            return service.saveCourse(course);
        } catch (Throwable t) {
            throw new CourseAlreadyRegistered(t.getMessage());
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Course editCourse(@PathVariable Integer id, @RequestBody Course course) {
        return service.getCourse(id).map( c -> {
            course.setId(c.getId());
            return service.saveCourse(course);
        } ).orElseThrow(CourseNotFoundException::new);
    }

    @PutMapping("/deprecate/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Course deprecateCourse(@PathVariable Integer id) {
        return service.deprecateCourse(true, id);
    }

    @PutMapping("/reconsider/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Course reconsiderCourse(@PathVariable Integer id) {
        return service.deprecateCourse(false, id);
    }

    @PutMapping("/disable/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Course disableCourse(@PathVariable Integer id) {
        return service.disableCourse(true, id);
    }

    @PutMapping("/enable/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Course enableCourse(@PathVariable Integer id) {
        return service.disableCourse(false, id);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Course getCourse(@PathVariable Integer id) {
        return service.getCourse(id).get();
    }

    @GetMapping("/code/{code}")
    @ResponseStatus(HttpStatus.OK)
    public Course getCourseByCode(@PathVariable String code) {
        return service.getCourseByCode(code).get();
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Course> listCourses() {
        return service.listCourses();
    }

    @GetMapping("/name/{name}")
    public List<Course> findByName(@PathVariable String name) {
        return service.findCourseByName(name);
    }

    @GetMapping("/search")
    public List<Course> searchCourse(@RequestBody Course filter) {
        return service.findCourse(filter);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Hello, Course";
    }

}
