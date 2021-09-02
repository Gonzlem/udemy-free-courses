package com.gonzlem.udemyfreecourses.services.interfaces;

public interface ScrappingService<T> {
    T scrape(); // Returns the scraped items
}
