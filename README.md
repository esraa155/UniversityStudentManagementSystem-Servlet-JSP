University Student Management System
Overview
The University Student Management System is a web application built using Java Servlet and JSP. It allows users to manage student information by adding, searching, and deleting student records. The student data is stored in an XML file, making it easy to maintain and modify.

Features
Add Student: Users can add new student records with details such as student ID, first name, last name, gender, GPA, level, and address.
Search Student: Users can search for student records by GPA or first name.
Delete Student: Users can delete a student record by providing the student ID.
Project Structure
UniversityStudentManagementSystem
│
├── src
│   └── net
│       └── codejava
│           └── mavenproject1
│               ├── UniversityServlet.java
│
├── web
│   ├── index.jsp
│   └── WEB-INF
│       └── web.xml
│
├── university.xml
└── README.md
Prerequisites
Java Development Kit (JDK) 8 or higher
Apache Tomcat 8 or higher
Apache Maven
NetBeans IDE (or any other preferred IDE)
Setup Instructions
Clone the Repository
git clone [https://github.com/your-username/UniversityStudentManagementSystem.git]
cd UniversityStudentManagementSystem
Open the Project in NetBeans IDE

Open NetBeans IDE.
Go to File -> Open Project and select the cloned repository.
Build the Project

Right-click on the project in the Projects window and select Clean and Build.
Deploy the Project to Apache Tomcat

Ensure Apache Tomcat is installed and configured in NetBeans.
Right-click on the project and select Run.
Access the Application

Open a web browser and go to http://localhost:8080/UniversityStudentManagementSystem/.
Usage
Add Student

Navigate to the "Add Student" section on the main page.
Fill in the student details and click the "Add Student" button.
Search Student

Navigate to the "Search Student" section on the main page.
Enter the GPA or first name of the student and click the "Search" button.
Delete Student

Navigate to the "Delete Student" section on the main page.
Enter the student ID to delete and click the "Delete Student" button.
Contributing
Contributions are welcome! Please fork the repository and submit a pull request for any enhancements or bug fixes.

License
This project is licensed under the MIT License. See the LICENSE file for details.

Contact
For any questions or suggestions, please contact esrramhumd@gmail.com.
