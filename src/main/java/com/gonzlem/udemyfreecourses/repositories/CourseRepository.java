package com.gonzlem.udemyfreecourses.repositories;

import com.gonzlem.udemyfreecourses.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    boolean existsByTitle(String title);
    List<Course> findAllByOrderByDateAsc();
    List<Course> findAllByOrderByDateDesc();
    List<Course> findAllByTitleContains(String title);
}
