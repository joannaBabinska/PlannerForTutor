# PlannerForTutor

## Contents

* [About The Project](#about-the-project)
* [Built with](#built-with)
* [Getting Started](#getting-started)
* [Exploring PlannerForTutor](#exploring-plannerfortutor)
* [Design Patterns](#design-patterns)
* [Roadmap](#roadmap)
* [Contact](#Contact)
* [License](#license)

## About the project

The project was created for the needs of tutors. The application allows you to create a database of students, lessons
and their management over time.

Selected functionalities:

* organization of the working day related to the schedule of classes, list of students, topics of lessons,
* possibility to add a holiday,
* configuration of working days,
* exclusion of work on public holidays (data downloaded from an external API),
* an overview of statistics on earnings and hours worked.

## Built with

* Java 17
* Apache Maven
* MySQL
* Hibernate
* H2
* Lombok
* JUnit
* Spring Web
* Spring Data
* RabbitMQ
* Swagger
* Docker
* Git

## Getting Started

#### With Maven:

To get a local copy up and running follow these simple steps.

1. Clone the repository
   ```sh
   git clone https://github.com/joannaBabinska/PlannerForTutor.git
   ```
2. Build project with maven
   ```sh
   mvn spring-boot:run
   ```

#### With Docker:

1. Run command:
   ```sh
   docker run --name planner -d -p 8080:8080 joannababinska/planner:latest
   ```

***
After launching the application, Swagger documentation available at the link http://localhost:8080/swagger-ui/index.html

## Exploring PlannerForTutor

CRUD operation and available endpoints

### Student

| Method | Url            | Description                                                                                        | Example of valid Request Body                                                                                                                                                                                         |
|--------|----------------|----------------------------------------------------------------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GET    | /students/{id} | Get student by id.                                                                                 |                                                                                                                                                                                                                       |
| POST   | /students      | Save new student.                                                                                  | {<br/> "id": 4,<br/>"firstName": "Janina",<br/>"lastName": "Kowal",<br/>"email": "janina@kowal.pl",<br/>"phoneNumber": "855523369",<br/>"dateOfBirth": "2007-11-01",<br/>"schoolClass": "HIGH_SCHOOL_1TH_GRADE"<br/>} |
| PUT    | /students/{id} | Changing a student with an id placed in the path variable to a student placed in the request body. | {<br/>"firstName": "Joanna",<br/>"lastName": "Nowik",<br/>"email": "joanna@nowik.pl",<br/>"phoneNumber": "698852236",<br/>"dateOfBirth": "2004-12-01",<br/>"schoolClass": "HIGH_SCHOOL_2TH_GRADE"<br/>}               |
| PATCH  | /students/{id} | Update student information with id placed in variable path using information in request body.      | {<br/>"schoolClass": "HIGH_SCHOOL_2TH_GRADE"<br/>}                                                                                                                                                                    |
| DELETE | /students/{id} | Delete student from all lesson reservation and from databases.                                     |                                                                                                                                                                                                                       |

### LessonReservation

| Method | Url                                                  | Description                                                                                                              | Example of valid Request Body                                                                                                                                                                                                                    |
|--------|------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| GET    | /lessons-reservation/{id}                            | Get lesson reservation by id, but without information about students enrolled in classes.                                |                                                                                                                                                                                                                                                  |
| GET    | /lessons-reservation/{id}/all                        | Get lesson reservation by id.                                                                                            |                                                                                                                                                                                                                                                  |
| GET    | /lessons-reservation/students?date=YYYY-mm-DD        | Get the list of students for the date that was entered as request param.                                                 |                                                                                                                                                                                                                                                  |
| POST   | /lessons-reservation                                 | Save a new lesson reservation, but without adding students to it.                                                        | {<br/>"lessonType": "MATURDAY_COURSE",<br/>"topic": "geometria anlityczna - funkcja liniowa",<br/>"startTime": "13:00:00",<br/>"endTime": "14:30:00",<br/>"reservationDate": "2023-01-05",<br/>"durationInMinutes": 90,<br/>"price": 160.0<br/>} |
| PUT    | /lessons-reservation/{id}                            | Changing a lesson reservation with an id placed in the path variable to a lesson reservation placed in the request body. | {<br/>"lessonType": "GROUP_TUTORING",<br/>"topic": "geometria anlityczna - funkcja liniowa",<br/>"startTime": "15:30:00",<br/>"endTime": "16:30:00",<br/>"reservationDate": "2023-01-05",<br/>"durationInMinutes": 90,<br/>"price": 150<br/>}    |
| PATCH  | /lessons-reservation/{id}                            | Update lesson reservation information with id placed in variable path using information in request body.                 | {<br/>"startTime": "13:00:00",<br/>"endTime": "15:00:00"<br/>}                                                                                                                                                                                   |
| PATCH  | /lessons-reservation/{id}/students                   | Create a new student and enroll him right away in classes.                                                               | {<br/>"firstName" : "Janina",<br/>"lastName": "Kowal",<br/>"email": "janina@kowal.pl",<br/>"phoneNumber": "855523369",<br/>"dateOfBirth": "2024-11-01",<br/>"schoolClass": "HIGH_SCHOOL_1TH_GRADE"<br/>}                                         |
| PATCH  | /lessons-reservation/{lessonId}/students/{studentId} | Enroll a student with an id placed in Path variable for a lesson reservation with an id placed in path variable.         |                                                                                                                                                                                                                                                  |
| DELETE | /lessons-reservation/{id}                            | Deletes lesson reservation, but does not remove students from the database who are attending these lessons.              |                                                                                                                                                                                                                                                  |
| DELETE | /{lessonId}/student/{studentId}                      | Deletes the student from the lesson reservation, but not from databases.                                                 |                                                                                                                                                                                                                                                  |    

### Vacation

| Method | Url       | Description            |
|--------|-----------|------------------------|
| Get    | /vacation | Get date with vacation |

### Statistics

| Method | Url                                                        | Description                                                                                                                                            |
|--------|------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------|
| GET    | /statistics/salary/perTerm?start=YYYY-mm-DD&end=YYYY-mm-DD | Get the sum of salary in the given period. If you don't specify the end of the period it will give you the salary amount up to today.                  |
| GET    | /statistics/salary/{date}                                  | Get the sum of salary in the given date.                                                                                                               |
| GET    | /statistics/salary/perMonth?month=Month&year=YYYY          | Get the salary for the given month. If you do not enter the month in the request parameter, the salary for the current month will be given.            |
| GET    | /statistics/hours/perMonth?month=Month                     | Get the total hours in the month indicated in Request parm.                                                                                            |
| GET    | /statistics/hours/perTerm?start=YYYY-mm-DD&end=YYYY-mm-DD  | Get the total of all hours in the specified period. If you don't specify the end of the period it will give you total of all hours amount up to today. |

## Design Patterns

### Singleton

> Spring Bean is a singleton by default.

### Strategy

> The project uses strategy to validate the input of lesson reservation data. The validation included:
>- working days of the week,
>- occurrence of public holidays,
>- planned vacation.

#### Builder

> Builder design pattern was used to create objects using Lombok.

## RoadMap

- [ ] Send notifications to participants
- [ ] Optimize the selection of free booking dates

## Contact

📫 babinskaasia52@gmail.com

## License

Distributed under the Mit License. [Check here][license-url] for more information.

[license-url]: https://github.com/joannaBabinska/PlannerForTutor/blob/main/LICENSE