<%-- 
    Document   : new
    Created on : May 30, 2016, 11:49:33 AM
    Author     : Mihai Manole <mihai.manole77@gmail.com>
--%><%@page contentType="text/html" pageEncoding="UTF-8"%><%-- 
--%><!DOCTYPE html>
<html>
    <head>
        <title>Insert a new course</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="../style/ex061.css">
        <link rel="stylesheet" href="../style/ex101.css">
    </head>
    <body><%-- 
--%><%@taglib prefix="m" uri="/WEB-INF/tlds/atag_lib" %><%-- 
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>--%>
      <m:menu index="1"/>
        <h2>A new course :</h2>
        <form method="post" action=".?a=i">
            <fieldset><table><tr><td>
                 <label for="pid">Course ID:</label></td><td>
		 <input type="text" name="id" id="pid" required pattern="^.{1,4}$"></td></tr><tr><td>
		 <label for="name">Name:</label></td><td>
		 <input type="text" name="name" id="name" required pattern="^.{1,30}$"></td></tr><tr><td colspan="2">
		 <button type="submit">Send</button></td></tr></table>
           </fieldset></form>
    </section><footer>
      </footer></body>
</html>
