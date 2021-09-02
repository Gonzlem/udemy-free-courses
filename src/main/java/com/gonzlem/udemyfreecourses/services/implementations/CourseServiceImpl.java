package com.gonzlem.udemyfreecourses.services.implementations;

import com.gonzlem.udemyfreecourses.repositories.CourseRepository;
import com.gonzlem.udemyfreecourses.services.interfaces.CourseService;
import com.gonzlem.udemyfreecourses.utils.CoursesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;


@Service
public class CourseServiceImpl implements CourseService {
    private final CourseRepository courseRepository;

    @Autowired
    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @Override
    public void saveAllCourses(CoursesInfo courses) {
        try {
            courses.getCourses().forEach(
                    course -> {
                        if (!courseRepository.existsByTitle(course.getTitle())) {
                            courseRepository.save(course);
                        }
                    }
            );
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public CoursesInfo findAllCourses() {
        return new CoursesInfo(courseRepository.findAll());
    }

    @Override
    public CoursesInfo findAllByOrderByDateAsc() {
        return new CoursesInfo(courseRepository.findAllByOrderByDateAsc());
    }

    @Override
    public CoursesInfo findAllByTitleContains(String title) {
        return new CoursesInfo(courseRepository.findAllByTitleContains(title));
    }
}
