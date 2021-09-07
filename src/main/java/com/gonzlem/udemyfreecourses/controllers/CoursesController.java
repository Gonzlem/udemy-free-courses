package com.gonzlem.udemyfreecourses.controllers;

import com.gonzlem.udemyfreecourses.services.interfaces.CourseService;
import com.gonzlem.udemyfreecourses.utils.CoursesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/courses")
public class CoursesController {
    private final CourseService courseService;

    @Autowired
    public CoursesController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping("/all")
    public CoursesInfo findAllCourses() {
        return courseService.findAllCourses();
    }

    @GetMapping("/all/filter")
    public CoursesInfo findAllByOrderByDate(@RequestParam(value = "order") String order) {
        return order.equals("asc") ? courseService.findAllByOrderByDateAsc() : courseService.findAllByOrderByDateDesc();
    }

    @RequestMapping("/all/filter")
    @ResponseBody
    public CoursesInfo findAllByTitleContains(@RequestParam(value = "title") String title) {
        return courseService.findAllByTitleContains(title);
    }
}
