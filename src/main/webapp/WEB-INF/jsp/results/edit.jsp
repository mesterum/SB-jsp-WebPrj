<%-- 
    Document   : edit
    Created on : May 30, 2016, 1:15:54 PM
    Author     : Mihai Manole <mihai.manole77@gmail.com>
--%><%@page contentType="text/html" pageEncoding="UTF-8"%><%-- 
--%><!DOCTYPE html>
<html>
    <head>
        <title>Modify a result</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../style/ex061.css">
        <link rel="stylesheet" href="../style/ex101.css">
    </head>
    <body><%-- 
--%><%@taglib prefix="m" uri="/WEB-INF/tlds/atag_lib" %><%-- 
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
      <m:menu index="2"/>
        <h2>Result :</h2>
        <form method="post" action="./">
            <fieldset><table><tr><td>
                <label for="sid">Student ID:</label></td><td>
                <input type="text" name="sid" id="sid" readonly value="${
                       param.sid}"></td></tr><tr><td>
                <label for="cid">Course ID:</label></td><td>
                <input type="text" name="cid" id="cid" readonly value="${
                       param.cid}"></td></tr><tr><td>
		<label for="Marks">Marks:</label></td><td>
		<input type="number" name="marks" id="Marks" min="0" max="100" step="1" required value="${
                       param.m}"></td></tr><tr><td<c:if test='${param.m=="-"}' var="ins"
                        > colspan="2">
                <button type="submit" name="a" value="i">Insert</button></c:if
                ><c:if test='${!ins}'>>
                <button type="submit" name="a" value="u">Update</button></td><td>
                <button type="submit" name="a" value="d">Delete</button></c:if
                ></td></tr></table>
           </fieldset></form>
    </section><footer>
      </footer></body>
</html>
