<%-- 
    Document   : all
    Created on : May 30, 2016, 11:49:33 AM
    Author     : Mihai Manole <mihai.manole77@gmail.com>
--%><%@page contentType="text/html" pageEncoding="UTF-8"%><%-- 
--%><!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All courses</title>
        <link rel="stylesheet" href="../style/ex061.css">
        <link rel="stylesheet" href="../style/ex101.css">
    </head>
    <body><%-- 
--%><%@taglib prefix="m" uri="/WEB-INF/tlds/atag_lib" %><%-- 
--%><%@ taglib prefix="s" uri="http://www.springframework.org/tags" %><%-- 
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <m:menu index="1"/>
	<h2>All courses</h2>
<table class="list">
<thead><tr><th>Id</th><th>Name</th>
<th colspan="2">Actions</th></tr></thead><tbody><c:forEach items="${courses}" var="course">
        <tr><td><c:out value="${course.id}"
        /></td><td><c:out value="${course.name}"
        /></td><td><a href="<c:url value='?a=e'>
           <c:param name='id' value='${course.id}'/>
        </c:url>">detail</a></td><td><a href="<c:url value='?a=d'>
            <c:param name='id' value='${course.id}'/>
        </c:url>">delete</a></td></tr></c:forEach>
</tbody></table>
        <form method="GET" action="."><input type="hidden" name="a" value="i">
        <button type="submit">A new course</button></form>
    </section><footer>${msg}
      </footer></body>
</html>
