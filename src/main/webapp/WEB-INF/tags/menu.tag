<%-- 
    Document   : menu
    Created on : Jun 14, 2016, 1:03:30 PM
    Author     : Mihai Manole <mihai.manole77@gmail.com>
--%><%@tag description="put the tag description here" pageEncoding="UTF-8"%><%--
The list of normal or fragment attributes can be specified here: 
--%><%@attribute name="index"%><%-- 
any content can be specified here e.g.: 
--%><%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %><%-- 
--%><%@taglib uri="http://www.springframework.org/tags" prefix="s"%><%-- 
--%><header>
        <img width="80px" height="80px">
        <hgroup>
          <h1>Test Admission</h1>
        </hgroup>
      </header><div style="text-align: right;"><a href="?lang=<s:message code="other-lang"/>"><%-- 
--%><s:message code="other-language"/></a></div>
        <nav id="nav_bar"><ul><c:forEach 
            items="${pages}" var="p" varStatus="s">
          <li><a href="../${p[0]}/"<c:if test="${s.index==index}"
              > class="current"</c:if>>${p[1]}</a></li></c:forEach>
        </ul></nav>
    <section>