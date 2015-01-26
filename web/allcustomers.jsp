<%-- 
    Document   : index
    Created on : 07-Jan-2014, 00:45:34
    Author     : Andrew
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customers</title>
    </head>
    <body>
        <h1>Customer details</h1>
        <table border ="1">
            <tr>
                <th>Customer ID</th>
                <th>Forename</th>
                <th>Surname</th>
                <th>Passport ID</th>

            </tr>
            <form action="./AllCustomerServlet" method="POST">
                <c:forEach var="cust" items="${customers}">
                    <tr>      
                        <td> <input type="text" name="cust.id" value="${cust.id}" /> </td>
                        <td> <input type="text" name="cust.firstname" value="${cust.firstname}" /></td>
                        <td> <input type="text" name="cust.lastname" value="${cust.getLastname()}" /></td>
                        <td> <input type="text" name="cust.lastname" value="${cust.getPassportnumber()}" /></td>

                    </tr>
                </c:forEach>
        </table> 
        <br>
        <br>
        <h4>Add a Customer</h4>
        <table border ="1">
            <th>Customer ID</th>
                <th>Forename</th>
                <th>Surname</th>
                <th>Passport ID</th>
            <tr>      
                <td> <input type="text" name="custid" value="Unique ID" /> </td>
                <td> <input type="text" name="custfname" value="First name" /> </td>
                <td> <input type="text" name="custsname" value="Last name" /> </td>
                <td> <input type="text" name="custpassport" value="Passport number" /> </td>
                <td> <input type="submit" name="action" value="Create"  /> </td>
                </form>
            </tr>
        </table> 


    </body>
</html>
