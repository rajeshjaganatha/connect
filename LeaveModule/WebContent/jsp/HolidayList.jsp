<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ page import="java.io.*,java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="taglib_includes.jsp"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connect</title>
<script language="javaScript"  type="text/javascript" src="js/common.js"></script> 
<link href="css/table.css" rel="stylesheet" type="text/css"/>
<link href="css/style.css" rel="stylesheet" type="text/css"/>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>



</head>
<body>
<div class="title">Leave</div>

<a href="#" title="LogOut" onclick="javascript:go('logout.do');" class="button_example">LOGOUT</a>
<a href="javascript:history.go(-1)">Go Back</a>

<br>
<br>
<table cellspacing="1" cellpadding="4" border="1">
	<c:choose>
		<c:when  test="${!empty holidayList}">
			<tr>
			<td>Serial Number 	<c:out value="${i.holidayId}"/></td>
			<td>Holiday Name	<c:out value="${i.holidayName}"/></td>
			<td>Date			<c:out value="${i.date}"/> </td>
			<td>Day				<c:out value="${i.day}"/> </td>
			</tr>		
		
			<c:forEach items="${holidayList}" var="i"  varStatus="loopStatus">
			<tr>
			<td><c:out value="${i.holidayId}"/></td>
			<td><c:out value="${i.holidayName}"/></td>
			<td><c:out value="${i.date}"/> </td>
			<td><c:out value="${i.day}"/> </td>
			</tr>

			</c:forEach> 
			
    		</c:when> 
		<c:otherwise>
			<td colspan="5" align="center" class="rowodd">
				<c:out value="No Records Found"></c:out>
			</td>
		</c:otherwise>
	</c:choose>
		</table>		    
</body>
</html>