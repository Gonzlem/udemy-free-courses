package com.gonzlem.udemyfreecourses.utils;

import com.gonzlem.udemyfreecourses.entities.Course;

import java.util.List;
import java.util.Objects;

public class CoursesInfo {
    private List<Course> courses;

    public CoursesInfo(List<Course> courses) {
        this.courses = courses;
    }

    public List<Course> getCourses() {
        return courses;
    }

    public void setCourses(List<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "CoursesInfo{" +
                "courses=" + courses +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CoursesInfo that = (CoursesInfo) o;
        return Objects.equals(courses, that.courses);
    }

    @Override
    public int hashCode() {
        return Objects.hash(courses);
    }
}
