package io.github.devalan87.myAcademy.domain.repository;

import io.github.devalan87.myAcademy.domain.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    Optional<Course> findByCode(String code);
    List<Course> findByNameLike(String name);
    List<Course> findCourse(Course filter);

}
