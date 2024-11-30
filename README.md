### 💻 About the project
The project uploads the file requested in the test

### ⚙️ Features
- [x] Load-data:
  - [x] Upload specified csv file and store in in-memory database;

- [x] Form:
  - [x] Fetch all data;
  - [x] Fetch by code;
  - [x] Delete all data;
    
- [x] Security:
  - [x] Only authorized users can upload or view file data;
     
### 🛠 Technologies and standards used
- MVC Standard Architecture;
- Spring 3.1.0
- Java 17;
- Spring Security;
- Spring Data;
- Maven;
- Flyway;
- h2;
- Lombok;
- Swagger;

  ### 🧭 API Documentation

  1 - Before running the application, you need to download the .csv file to your directory and put the file path in the source application.yml, in the parameters.path environment variable.
  ![image](https://github.com/user-attachments/assets/4805a283-a8ab-40f4-9c52-bf36e7fa4f11)

  2 - When uploading the project, you first need to create a user in the POST user api. You can use swagger http://localhost:8080/swagger-ui/index.html#/ or you can use postman, with the curl below:
  Roles 1 and 2 give access to all functionalities, with role 1 giving permission to upload and delete(MANAGE_FORM), and role 2 giving permission to access GETs(CONSULT_FORM)

  curl -X 'POST' \
  'http://localhost:8080/user' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "username": "admin",
  "password": "admin",
  "rolesId": [
    1,2
  ]}'

3 - If you are accessing via swagger, you will need to add the username and password created in the 'Authorize' button as in the image below.

![image](https://github.com/user-attachments/assets/84639649-0de6-42a6-b2c7-32c58fa20312)
