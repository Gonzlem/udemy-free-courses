package com.gonzlem.udemyfreecourses.services.implementations;

import com.gonzlem.udemyfreecourses.entities.Course;
import com.gonzlem.udemyfreecourses.services.interfaces.ScrappingService;
import com.gonzlem.udemyfreecourses.utils.CoursesInfo;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class UdemyCourseScrapper implements ScrappingService<CoursesInfo> {
    private static final String URL_TO_SCRAPE = "https://www.discudemy.com/all";
    private final String PATTERN = "https://www.discudemy.com/go/";
    private final ChromeDriver driver;

    @Autowired
    public UdemyCourseScrapper(ChromeDriver driver) {
        this.driver = driver;
    }

    @Override
    public CoursesInfo scrape() {
        CoursesInfo coursesInfo;
        List<Course> courses;
        driver.get(URL_TO_SCRAPE);
        String html = driver.getPageSource();
        Document document = Jsoup.parse(html);

        // Go through each card and get the data to create our Course object
        courses = document.getElementsByClass("card")
                .stream()
                .map(element -> {
                            String BASE_URL = element.getElementsByClass("card-header").attr("href");
                            String title = element.getElementsByClass("card-header").text();
                            String description = element.getElementsByClass("description").text();
                            String language = element.getElementsByClass("ui green disc-fee label").text();
                            Date date = new Date();

                            return new Course(title, description, language, BASE_URL, date);
                        }
                ).collect(Collectors.toList());

        // If the course is an ad or is a malformed course, we delete it.
        courses.removeIf(course -> !course.getUrl().startsWith(PATTERN) || course.getLanguage().equalsIgnoreCase("ads"));
        // Populate our CoursesInfo object, with the recently scrapped data
        coursesInfo = new CoursesInfo(courses);
        // Update the links with the actual Udemy courses links
        updateCoursesLinks(coursesInfo);

        driver.close();

        return coursesInfo;
    }

    private void updateCoursesLinks(CoursesInfo courses) {
        // This method runs through each course, and filters the
        courses.getCourses()
                .forEach(course -> course
                        .setUrl(course.getUrl().replaceFirst(course.getUrl().split("/")[3], "go")) // To avoid having to go through each page twice, I replaced the link to be able to get the links by going through them only once
        );

        getActualUdemyLinks(courses);
    }

    private void getActualUdemyLinks(CoursesInfo courses) {
        courses.getCourses()
                .forEach(course -> {
                    driver.navigate().to(course.getUrl());
                    Document document = Jsoup.parse(driver.getPageSource());
                    course.setUrl(document.getElementsByClass("ui segment").select("a").attr("href")); // Get the actual udemy course link
                });
    }
}
