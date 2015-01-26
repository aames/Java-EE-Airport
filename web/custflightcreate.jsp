<%-- 
    Document   : custflightcreate
    Created on : 13-Jan-2014, 12:02:11
    Author     : Andrew
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" isELIgnored="false" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Create new records!</h1>
        <form action="./CustomerFlight" method="POST">

            <h1>Customer</h1>
            <input type="text" name="customerid" value="Customer ID" />
            <input type="text" name="customerfname" value="Forename" />
            <input type="text" name="customersname" value="Surname" />
            <br>
            <h1>Flight</h1>
            <input type="text" name="flightid" value="Flight ID" />
            <input type="text" name="flightdestination" value="Destination" />
            <br>

            <h1>Address</h1>
            <br><input type="text" name="addressid" value="Address ID" />
            <br><input type="text" name="addressnameornumber" value="House Name or Number" />
            <br><input type="text" name="addressstreet" value="Street" />
            <br><input type="text" name="addresstown" value="Town or City" />
            <br><input type="text" name="addressstate" value="State or County" />
            <br><input type="text" name="addresscountry" value="Country" />
            <br><input type="text" name="addresspostcode" value="Post or Zip code" />

            <br>
            <h1>Gate</h1>
            <input type="text" name="gateid" value="Gate ID" />
            <input type="text" name="gatename" value="Gate Name" />
            <br>
            <h1>Luggage</h1>
            <input type="text" name="luggageid" value="Luggage ID" />
            <input type="text" name="luggagemass" value="mass e.g. 2.9" />

            <br><br>
            <input type="submit" value="CreateAll" name="create" />

        </form>
        <table border="1">
            <tr>
                <th>Customer ID</th>
                <th>Forename</th>
                <th>Surname</th>

            <tr>      
                <td> <input type="text" name="custid" value="${selectedcust.id}" /> </td>
                <td> <input type="text" name="custfname" value="${selectedcust.firstname}" /></td>
                <td> <input type="text" name="custsname" value="${selectedcust.getLastname()}" /></td>

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

    </body>
</html>
