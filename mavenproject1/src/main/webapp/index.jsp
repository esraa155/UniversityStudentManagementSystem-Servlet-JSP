<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
            <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>University Student Management</title>
            <script>
            function generateStudentForm() {
                var numStudents = document.getElementById("numStudents").value;
                var formContainer = document.getElementById("studentFormContainer");

                // Clear existing content
                //formContainer.innerHTML = "";

                // Generate input fields based on the number of students
                for (var i = 1; i <= numStudents; i++) {
                    var formGroup = document.createElement("div");
                    formGroup.className = "form-group";

                    formGroup.innerHTML = `
                        <label for="studentId${i}">Student ID ${i}:</label>
                        <input type="text" id="studentId${i}" name="studentId${i}" required><br>

                        <label for="firstName${i}">First Name ${i}:</label>
                        <input type="text" id="firstName${i}" name="firstName${i}" required><br>

                        <label for="lastName${i}">Last Name ${i}:</label>
                        <input type="text" id="lastName${i}" name="lastName${i}" required><br>

                        <label for="gender${i}">Gender ${i}:</label>
                        <input type="text" id="gender${i}" name="gender${i}" required><br>

                        <label for="gpa${i}">GPA ${i}:</label>
                        <input type="text" id="gpa${i}" name="gpa${i}" required><br>

                        <label for="level${i}">Level ${i}:</label>
                        <input type="text" id="level${i}" name="level${i}" required><br>

                        <label for="address${i}">Address ${i}:</label>
                        <input type="text" id="address${i}" name="address${i}" required><br><br>
                                      <input type="hidden" name="choice" value="1">
               <input type="submit" value="Add Student">
                    `;
 
                    formContainer.appendChild(formGroup);
                          
                }
              
               
            }

        </script>
    </head>
    <body>
            </form>
     <h2>Specify Number of Students</h2>
<form id="studentForm" method="post">
            <label for="numStudents">Number of Students:</label>
            <input type="number" id="numStudents" name="numStudents" required>
            <button type="button" onclick="generateStudentForm()">Generate Form</button>


        </form>
     <div id="studentFormContainer"></div>
     
         <h2>Add Student</h2>
    <form action="UniversityServlet" method="post">
        <label for="studentId">Student ID:</label>
        <input type="text" id="studentId" name="studentId" required><br>

        <label for="firstName">First Name:</label>
        <input type="text" id="firstName" name="firstName" required><br>

        <label for="lastName">Last Name:</label>
        <input type="text" id="lastName" name="lastName" required><br>

        <label for="gender">Gender:</label>
        <input type="text" id="gender" name="gender" required><br>

        <label for="gpa">GPA:</label>
        <input type="text" id="gpa" name="gpa" required><br>

        <label for="level">Level:</label>
        <input type="text" id="level" name="level" required><br>

        <label for="address">Address:</label>
        <input type="text" id="address" name="address" required><br>
        
        <input type="hidden" name="choice" value="1">
        <input type="submit" value="Add Student">
    </form>

    <h2>Search Student</h2>
    <form action="UniversityServlet" method="get">
        <label for="searchTerm">Search Term (GPA or First Name):</label>
        <input type="text" id="searchTerm" name="searchTerm" required><br>
        <input type="hidden" name="choice" value="2">
        <input type="submit" value="Search">
    </form>
 <h2>Delete Student</h2>
    <form action="UniversityServlet" method="post">
        <label for="studentIdToDelete">Student ID to Delete:</label>
        <input type="text" id="studentIdToDelete" name="studentIdToDelete" required><br>

        <input type="hidden" name="choice" value="3"> <!-- Add this hidden field for the choice -->
        <input type="submit" value="Delete Student">

    
    </body>
</html>









