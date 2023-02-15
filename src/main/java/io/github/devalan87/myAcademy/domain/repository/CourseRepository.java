package io.github.devalan87.myAcademy.domain.repository;

import io.github.devalan87.myAcademy.domain.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CourseRepository extends JpaRepository<Course, Integer> {

    Optional<Course> findByCode(String code);
    List<Course> findByNameContainingIgnoreCase(String name);

}
