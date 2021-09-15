package com.gonzlem.udemyfreecourses.controllers;

import com.gonzlem.udemyfreecourses.services.interfaces.CourseService;
import com.gonzlem.udemyfreecourses.services.interfaces.ScrappingService;
import com.gonzlem.udemyfreecourses.utils.CoursesInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/scrapper")
public class ScrappingController {
    private final ScrappingService<CoursesInfo> scrappingService;
    private final CourseService courseService;

    @Autowired
    public ScrappingController(ScrappingService<CoursesInfo> scrappingService, CourseService courseService) {
        this.scrappingService = scrappingService;
        this.courseService = courseService;
    }

    @RequestMapping
    public CoursesInfo scrape() {
        try {
            CoursesInfo courses = scrappingService.scrape();
            return courses;
        } catch (Exception exc) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Error while trying to scrape the courses.", exc);
        }
    }

    @RequestMapping("/persist")
    public CoursesInfo scrapeAndPersist() {
            CoursesInfo courses = scrappingService.scrape();
            courseService.saveAllCourses(courses);
            return courses;
    }
}
