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
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%-- 
--%><%@ taglib prefix="s" uri="http://www.springframework.org/tags" %><%-- 
--%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
      <m:menu index="0"/>
        <h2>Student :</h2>
        <form:form method="post" action="?a=u" modelAttribute="form">
        	<%-- <form:errors path="*" element="div"/> --%>
            <fieldset><table><tr><td>
                <form:label path="id">Student ID:</form:label></td><td>
                <form:input path="id" readonly="true"  /></td><td>
                <form:errors path="id"/></td></tr><tr><td>
                <form:label path="firstName">First Name:</form:label></td><td>
                <form:input path="firstName"/></td><td>
                <form:errors path="firstName"/></td></tr><tr><td>
                <form:label path="lastName">Last Name:</form:label></td><td>
                <form:input path="lastName"/></td><td>
                <form:errors path="lastName"/></td></tr><tr><td>
		 		Gender:</td><td>
                <form:label path="gender" for="gM">Male</form:label>
                <form:radiobutton path="gender" id="gM" value="M"/>
                <form:label path="gender" for="gF">Female</form:label>
                <form:radiobutton path="gender" id="gF" value="F"/></td><td>
                <form:errors path="gender"/></td></tr><tr><td>
                <form:label path="startDate">Start date:</form:label></td><td>
                <form:input path="startDate" type="date"/></td><td>
                <form:errors path="startDate"/></td></tr><tr><td colspan="2">
                 <button type="submit">Update</button></td></tr></table>
           </fieldset></form:form>
    </section><footer>
      </footer></body>
</html>
