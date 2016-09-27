<%-- 
    Document   : all
    Created on : May 30, 2016, 11:49:33 AM
    Author     : Mihai Manole <mihai.manole77@gmail.com>
--%><%@page contentType="text/html" pageEncoding="UTF-8"%><%-- 
--%><!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All students</title>
        <link rel="stylesheet" href="../style/ex061.css">
        <link rel="stylesheet" href="../style/ex101.css">
    </head>
    <body><%-- 
--%><%@taglib prefix="m" uri="/WEB-INF/tlds/atag_lib" %><%-- 
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <m:menu index="0"/>
	<h2>All students</h2>
<table class="list">
<thead><tr><th>Id</th><th>First Name</th><th>Last Name</th><th>Gender</th><th>Start date</th>
<th colspan="2">Actions</th></tr></thead><tbody><c:forEach items="${students}" var="student">
        <tr><td><c:out value="${student.id}"
        /></td><td><c:out value="${student.firstName}"
        /></td><td><c:out value="${student.lastName}"
        /></td><td><c:out value="${student.gender}"
        /></td><td><c:out value="${student.startDate}"
        /></td><td><a href="<c:url value='?a=e'>
           <c:param name='id' value='${student.id}'/>
        </c:url>">detail</a></td><td><a href="<c:url value='?a=d'>
            <c:param name='id' value='${student.id}'/>
        </c:url>">delete</a></td></tr></c:forEach>
</tbody></table>
        <form method="GET" action="new.jsp"><button type="submit">A new student</button></form>
    </section><footer>${msg}
      </footer></body>
</html>
