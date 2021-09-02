package com.gonzlem.udemyfreecourses.controllers;

import com.gonzlem.udemyfreecourses.services.interfaces.CourseService;
import com.gonzlem.udemyfreecourses.services.interfaces.ScrappingService;
import com.gonzlem.udemyfreecourses.utils.CoursesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/scrapper")
public class ScrappingController {
    private final ScrappingService scrappingService;
    private final CourseService courseService;

    @Autowired
    public ScrappingController(ScrappingService scrappingService, CourseService courseService) {
        this.scrappingService = scrappingService;
        this.courseService = courseService;
    }

    @RequestMapping
    public ResponseEntity scrape() {
        CoursesInfo courses = (CoursesInfo) scrappingService.scrape();
        courseService.saveAllCourses(courses);

        return ResponseEntity.ok().build();
    }
}