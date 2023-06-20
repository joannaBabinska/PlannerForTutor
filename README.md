# PlannerForTutor
## Contents
* [About The Project](#about-the-project)
* [Built with](#built-with)
* [Getting Started](#getting-started)
* [Exploring PlannerForTutor](#exploring-plannerfortutor)
* [Roadmap](#roadmap)
* [Concat](#concat)
* [License](#license)

## About the project
The project was created for the needs of tutors. The application allows you to create a database of students, lessons and their management over time.

Selected functionalities:
* organization of the working day related to the schedule of classes, list of students, topics of lessons,
* possibility to add a holiday,
* configuration of working days,
* exclusion of work on public holidays (data downloaded from an external API),
* an overview of statistics on earnings and hours worked.

## Built with
* Java 17
* ApacheMaven
* MySQL
* Hibernate
* H2
* Lombok
* JUnit
* Spring Web
* Spring Data

## Getting Started
To get a local copy up and running follow these simple  steps.
1. Clone the repo
   ```sh
   git clone https://github.com/joannaBabinska/PlannerForTutor.git
   ```
2. Build project with maven
   ```sh
    mvn spring-boot:run
   ```

## Exploring PlannerForTutor
CRUD operation and available endpoints

### Student

| Method | Url            | Description       | Example of valid Request Body                                                                                                                                                 |
|--------|----------------|-------------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
 | GET    | /students/{id} | Get student by id |                                                                                                                                                                               |
| POST   | /students      | Save student      | { "id": 4,"firstName": "Janina","lastName": "Kowal","email": "janina@kowal.pl","phoneNumber": "855523369","dateOfBirth": "2007-11-01","schoolClass": "HIGH_SCHOOL_1TH_GRADE"} |
| PUT| /students/{id} | Changing a student with an id placed in the path variable to a student placed in the request body| {"firstName": "Joanna","lastName": "Nowik","email": "joanna@nowik.pl","phoneNumber": "698852236","dateOfBirth": "2004-12-01","schoolClass": "HIGH_SCHOOL_2TH_GRADE"}|
|PATCH| /students/{id}|Update student information with id placed in variable path using information in request body|{"schoolClass": "HIGH_SCHOOL_2TH_GRADE"}|

## RoadMap
## Concat
## License