<%-- 
    Document   : edit
    Created on : May 30, 2016, 1:15:54 PM
    Author     : Mihai Manole <mihai.manole77@gmail.com>
--%><%@page contentType="text/html" pageEncoding="UTF-8"%><%-- 
--%><!DOCTYPE html>
<html>
    <head>
        <title>Modify a course</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../style/ex061.css">
        <link rel="stylesheet" href="../style/ex101.css">
    </head>
    <body><%-- 
--%><%@taglib prefix="m" uri="/WEB-INF/tlds/atag_lib" %><%-- 
--%><%@ taglib prefix="s" uri="http://www.springframework.org/tags" %><%-- 
--%><%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><%-- 
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <m:menu index="1"/>
        <h2>Course :</h2>
        <form:form method="post" action="?a=u" modelAttribute="form">
            <fieldset><table><tr><td>
                <form:label path="id">Course ID:</form:label></td><td>
                <form:input path="id" readonly="${!empty(form.id)}"  /></td><td>
                <form:errors path="id"/></td></tr><tr><td>
                <form:label path="Name">Name:</form:label></td><td>
                <form:input path="Name"/></td><td>
                <form:errors path="Name"/></td></tr><tr><td colspan="2">
                 <button type="submit">Update</button></td></tr></table>
           </fieldset></form:form>
    </section><footer>
      </footer></body>
</html>
