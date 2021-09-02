# udemy-free-courses
Web service that provides a scrapping tool to retrieve the latest Udemy paid courses for free. 

## explanation
The scrapping service is intended to be launched daily, to update the database. The way it works now is not the fastest, as it goes through every page on a given web that publishes this Udemy courses on a daily basis, and retrieves the information from there.

### Endpoints
POST: "/scrapper" => Retrieves the information from the pages and adds the data to the database.
GET: "/courses/all" => Retrieves all the courses currently contained on the database.
GET: "/courses/all/date/asc" => Retrieves all the courses currently contained on the database, ordered by date.
GET: "/courses/all/title?{string}" => Retrieves all the courses filtered by title that contains the word/character passed on the query string.
