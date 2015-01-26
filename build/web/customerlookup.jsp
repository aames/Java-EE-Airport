<%-- 
    Document   : EditCustomer
    Created on : 07-Jan-2014, 23:12:55
    Author     : Andrew
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Customer lookup</title>
    </head>
    <body>
        <form action="./EditServlet" method="POST">
            <h2>Search for a customer</h2>
            <input type="text" name="queryid" value="Customer id" />
            <input type="submit" name="actionid" value="Search Customers" />

            <h2>Edit a Selected Customer</h2>

            <table border ="1">

                <table border="1">
                    <tr>
                        <th>Customer ID</th>
                        <th>Forename</th>
                        <th>Surname</th>
                        <th>Passport ID</th>

                    <tr>      
                        <td> <input type="text" name="custid" value="${selectedcust.id}" /> </td>
                        <td> <input type="text" name="custfname" value="${selectedcust.firstname}" /></td>
                        <td> <input type="text" name="custsname" value="${selectedcust.getLastname()}" /></td>
                        <td> <input type="text" name="custpassport" value="${selectedcust.getPassportnumber()}" /></td>

                    </tr>
                </table>
                <table border="1">
                    <tr>
                        <th>Luggage ID</th>
                        <th>Flight ID</th>
                        <th>Address ID</th>
                    </tr>
                    <tr>
                        <td> <input type="text" name="custluggage" value="${selectedcust.luggage}" /> </td>
                        <td> <input type="text" name="custflight" value="${selectedcust.flight}" /> </td>
                        <td> <input type="text" name="custaddress" value="${selectedcust.address}" /> </td>
                    </tr>

                </table>
                <input type="submit" name="updateaction" value="Apply"/>

        </form>
        <br>
        <br>

        <h3>Additional information</h3>
        <table border="1">
            <tr>
                <th>Luggage id</th>
                <td> ${luggage.id}</td>
                <th>Flight Destination</th>
                <td> ${flight.destination}</td>
            <tr>      
                <th>Mass</th>
                <td> ${luggage.mass}</td>
                <th>Gate Name</th>
                <td> ${gate.name} </td>

            </tr>

        </table>
        <br>

        <table border = 1>
            <tr>
                <th>Address info</th>
            </tr>
            <tr>            
                <td>${address.nameornumber}</td>

            </tr>
            <tr>
                <td>${address.street}</td>
            </tr>
            <tr>
                <td>${address.towncity}</td>
            </tr>
            <tr>
                <td>${address.statecounty}</td>
            </tr>
            <tr>
                <td>${address.country}</td>
            </tr>
            <tr>
                <td>${address.postalcode}</td>
            </tr>
        </table>
        <h4>Weather at destination</h4> <br>
        ${weather}



    </body>
</html>
