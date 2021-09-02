package com.gonzlem.udemyfreecourses.configuration;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

@Configuration
public class SeleniumConfigurer {

    @PostConstruct
    void postConstruct() {
        System.setProperty("webdriver.chrome.driver", "E:\\Apps\\Projects\\udemy-free-courses\\src\\main\\resources\\chromedriver.exe");
    }

    @Bean
    public ChromeDriver driver() {
        // Configurations to run chrome in headless mode
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-gpu");

        return new ChromeDriver(options);
    }
}
