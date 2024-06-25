/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package net.codejava.mavenproject1;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.xml.sax.SAXException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;


/**
 *
 * @author Dell
 */
@WebServlet("/UniversityServlet")

public class UniversityServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Document document;
    private static final String XML_FILE_PATH = "C:\\Users\\Dell\\Documents\\NetBeansProjects\\mavenproject1\\university.xml";


    @Override
    public void init() throws ServletException {
        super.init();
        document = createUniversityXMLDocument();
        getServletContext().setAttribute("document", document);
    }
private Document createUniversityXMLDocument() {
    try {
        DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
        Document document = docBuilder.newDocument();

        Element rootElement = document.createElement("University");
        document.appendChild(rootElement);

        return document;
    } catch (ParserConfigurationException e) {
        e.printStackTrace();
        return null;
    }
}



    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

private void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    
        response.setContentType("text/html;charset=UTF-8");

        // Retrieve the document from the servlet context
        Document document = (Document) getServletContext().getAttribute("document");

        // Main menu
     if (document == null) {
        // If the document doesn't exist, create a new one
        document = createUniversityXMLDocument();
        getServletContext().setAttribute("document", document);
    }   
     
     String choiceParam = request.getParameter("choice");
    if (choiceParam != null && !choiceParam.isEmpty()) 
    {
             int choice = Integer.parseInt(choiceParam);

        switch (choice) {
            case 1:

                    addStudent(document, request,XML_FILE_PATH);
               
                
                //addStudent(document, request,XML_FILE_PATH);
                saveUniversityXMLDocument(document,XML_FILE_PATH);
                response.sendRedirect("index.jsp"); // Redirect to index.jsp after adding a student
                break;
            case 2:
                searchAndRetrieveData(document, request, response);
                break;
            case 3:
                deleteStudentRecord(document, request,XML_FILE_PATH);
                saveUniversityXMLDocument(document,XML_FILE_PATH);
                response.sendRedirect("index.jsp"); // Redirect to index.jsp after deleting a student
                break;
            case 4:
                response.getWriter().println("Goodbye!");
                break;

        }
        }
    else
    {
        response.getWriter().println("Invalid choice. Please select a valid option.");
    }
        
    }

    
private static void saveUniversityXMLDocument(Document document, String filePath) {
    try {
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);
        StreamResult result = new StreamResult(new File(filePath));
        transformer.transform(source, result);
        System.out.println("XML document saved to " + filePath);
    } catch (TransformerException e) {
        e.printStackTrace();
    }
}

    private static Document loadUniversityXMLDocument(File xmlFile) throws org.xml.sax.SAXException {
        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
            Document document = docBuilder.parse(xmlFile);
            document.getDocumentElement().normalize();
            return document;
            } 
        catch (ParserConfigurationException | IOException e)
        {
            //e.printStackTrace();
            return null;
        }
    }

    private void addStudent(Document document, HttpServletRequest request,String filePath) {
        Element studentElement = document.createElement("Student");

        String studentId = request.getParameter("studentId");
        studentElement.setAttribute("ID", studentId);

        createAndAppendElement(document, studentElement, "FirstName", request.getParameter("firstName"));
        createAndAppendElement(document, studentElement, "LastName", request.getParameter("lastName"));
        createAndAppendElement(document, studentElement, "Gender", request.getParameter("gender"));
        createAndAppendElement(document, studentElement, "GPA", request.getParameter("gpa"));
        createAndAppendElement(document, studentElement, "Level", request.getParameter("level"));
        createAndAppendElement(document, studentElement, "Address", request.getParameter("address"));

        document.getDocumentElement().appendChild(studentElement);
            document.getDocumentElement().appendChild(studentElement);
            saveUniversityXMLDocument(document, filePath);
            System.out.println("Student data added.");
    }

    private void createAndAppendElement(Document document, Element parentElement, String tagName, String textContent) {
        Element element = document.createElement(tagName);
        element.appendChild(document.createTextNode(textContent));
        parentElement.appendChild(element);
    }

    private void searchAndRetrieveData(Document document, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String searchTerm = request.getParameter("searchTerm");

        NodeList students = document.getElementsByTagName("Student");
        for (int i = 0; i < students.getLength(); i++) {
            Element student = (Element) students.item(i);
            String gpa = student.getElementsByTagName("GPA").item(0).getTextContent();
            String firstName = student.getElementsByTagName("FirstName").item(0).getTextContent();

            if (gpa.equals(searchTerm) || firstName.equals(searchTerm)) {
                response.getWriter().println("Student found: ");
                response.getWriter().println("Student ID: " + student.getAttribute("ID"));
                response.getWriter().println("First Name: " + firstName);
                response.getWriter().println("Last Name: " + student.getElementsByTagName("LastName").item(0).getTextContent());
                response.getWriter().println("Gender: " + student.getElementsByTagName("Gender").item(0).getTextContent());
                response.getWriter().println("GPA: " + gpa);
                response.getWriter().println("Level: " + student.getElementsByTagName("Level").item(0).getTextContent());
                response.getWriter().println("Address: " + student.getElementsByTagName("Address").item(0).getTextContent());
                return;
            }
        }

        response.getWriter().println("Student with GPA or First Name '" + searchTerm + "' not found.");
    }

    private void deleteStudentRecord(Document document, HttpServletRequest request,String filePath) {
        String studentIdToDelete = request.getParameter("studentIdToDelete");

        NodeList students = document.getElementsByTagName("Student");
        for (int i = 0; i < students.getLength(); i++) {
            Element student = (Element) students.item(i);
            String id = student.getAttribute("ID");
            if (id.equals(studentIdToDelete)) {
                student.getParentNode().removeChild(student);
                saveUniversityXMLDocument(document, filePath);
                System.out.println("Student with ID " + studentIdToDelete + " deleted.");
                
                return;
            }
        }

        System.out.println("Student with ID " + studentIdToDelete + " not found.");
    }
}
