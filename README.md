# Stack
- Java 17
- Spring Boot 3.5.5 (Embedded server and automatic configuration)
- Spring Web MVC (Endpoints @Controller/@RestController)
- Spring Data (For JPA repository)
- Spring Validation (For DTO validation)
- Spring Security (For session management, login)
- Thymeleaf (For providing front-end using back-end)
- Maven (Dependencies)
- Lombok (Avoiding boilerplate)
- H2 (Local database, to avoid separately starting a database)
- Flyway (Database migration instead of using database dump)

# Usage
1. Open CMD in projects' root folder.
2. Use following command to run it on Windows: ```mvnw.cmd spring-boot:run```, or Linux/Mac: ```./mvnw spring-boot:run```
3. Open ```http://localhost:8080``` in browser.
4. Automatic redirection will get you to ```http://localhost:8080/login```, instead go to ```http://localhost:8080/register```.
5. After successful registration either click the appeared button to go to login page, or manually go to ```http://localhost:8080/login```.
6. Login using same credentials as for registration.
7. Enter name,sector and agree to terms, press save.
# Check
1. In new tab go to: ```http://localhost:8080/h2-console```.
2. If credentials for H2 are not auto-filled, go to ```application.properties``` check ```# H2 Database connection settings``` section.
3. Run: ```SELECT * FROM USERS``` command to see your user.
4. In opened section tab, change section choice or name, press save again and run (STEP 3) again, you will see changed data. 