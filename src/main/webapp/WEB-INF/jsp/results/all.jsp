<%-- 
    Document   : all
    Created on : May 30, 2016, 11:49:33 AM
    Author     : Mihai Manole <mihai.manole77@gmail.com>
--%><%@page contentType="text/html" pageEncoding="UTF-8"%><%-- 
--%><!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>All results</title>
        <link rel="stylesheet" href="../style/ex061.css">
        <link rel="stylesheet" href="../style/ex101.css">
    </head>
    <body><%-- 
--%><%@taglib prefix="m" uri="/WEB-INF/tlds/atag_lib" %><%-- 
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <m:menu index="2"/>
	<h2>All results</h2>
    <table class="list results">
      <thead>
        <tr><th>Student\Course</th><c:forEach items="${courses}" var="course"
    ><th><c:out value="${course.name}"/></th></c:forEach></tr>
      </thead><tbody><c:forEach items="${students}" var="student">
        <tr><td><c:out value="${student.firstName}"
        /> <c:out value="${student.lastName}"
        /></td><c:forEach items="${courses}" var="course"
    ><td><a href="<c:url value='edit'><c:param 
        name='sid' value='${student.id}'/><c:param 
        name='cid' value='${course.id}'/><c:param 
        name='m' value='${results.next(student,course)}'/></c:url
    >"><c:out value="${results.result}"/></a></td></c:forEach></tr></c:forEach>
    </tbody></table>
    </section><footer>${msg}
      </footer></body>
</html>
