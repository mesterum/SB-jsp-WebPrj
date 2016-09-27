<%-- 
    Document   : edit
    Created on : May 30, 2016, 1:15:54 PM
    Author     : Mihai Manole <mihai.manole77@gmail.com>
--%><%@page contentType="text/html" pageEncoding="UTF-8"%><%-- 
--%><!DOCTYPE html>
<html>
    <head>
        <title>Modify a student</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../style/ex061.css">
        <link rel="stylesheet" href="../style/ex101.css">
    </head>
    <body><%-- 
--%><%@taglib prefix="m" uri="/WEB-INF/tlds/atag_lib" %><%-- 
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <m:menu index="0"/>
        <h2>Student :</h2>
        <form method="post" action="?a=u">
            <fieldset><table><tr><td>
                 <label for="pid">Student ID:</label></td><td>
                <input type="text" name="id" id="pid" readonly value="${
                       student.id}"></td></tr><tr><td>
		 <label for="fName">First Name:</label></td><td>
		 <input type="text" name="fName" id="fName" required pattern="^.{1,20}$" value="${
                       student.firstName}"></td></tr><tr><td>
		 <label for="lName">Last Name:</label></td><td>
		 <input type="text" name="lName" id="lName" required pattern="^.{1,20}$" value="${
                       student.lastName}"></td></tr><tr><td>
		 Gender:</td><td>
                 <label for="gM">Male</label><input type="radio" name="gender" id="gM" value="M" <c:if
                   test='${student.gender=="M"}'>checked</c:if>>
		 <label for="gF">Female</label><input type="radio" name="gender" id="gF" value="F" <c:if
                   test='${student.gender=="F"}'>checked</c:if>></td></tr><tr><td>
                 <label for="creationD">Start date:</label></td><td>
		 <input type="date" name="startD" id="creationD" value="${
                       student.startDate}" required></td></tr><tr><td colspan="2">
                 <button type="submit">Update</button></td></tr></table>
           </fieldset></form>
    </section><footer>
      </footer></body>
</html>
