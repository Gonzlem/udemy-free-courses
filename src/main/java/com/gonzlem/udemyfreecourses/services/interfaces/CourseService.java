package com.gonzlem.udemyfreecourses.services.interfaces;

import com.gonzlem.udemyfreecourses.utils.CoursesInfo;

public interface CourseService {
    // Database related
    void saveAllCourses(CoursesInfo courses);
    CoursesInfo findAllCourses(); // Will go and get the items from the database directly
    CoursesInfo findAllByOrderByDateAsc();
    CoursesInfo findAllByOrderByDateDesc();
    CoursesInfo findAllByTitleContains(String title);
}
