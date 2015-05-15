	<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
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
<div class="title">LEAVE SUMMARY</div>

<a href="#" title="LogOut" onclick="javascript:go('logout.do');" class="button_example">LOGOUT</a>
<a href="javascript:history.go(-1)">Go Back</a>

<br>
<br>
<table cellspacing="1" cellpadding="4" border="1">
	<c:choose>
		<c:when  test="${!empty leaveSummary}">
			<tr>
			<td>Leave Type		<c:out value="${i.leavetype.leaveType}"/></td>
			<td>From Date		<c:out value="${i.fromDate}"/> </td>
			<td>To Date			<c:out value="${i.toDate}"/> </td>
			<td>Leave Availed	<c:out value="${i.leaveAvailed}"/> </td>
			</tr>		
		
			<c:forEach items="${leaveSummary}" var="i"  varStatus="loopStatus">
			<tr>
			<td><c:out value="${i.leavetype.leaveType}"/></td>
    		<td><c:out value="${fn:substring(i.fromDate, 0, 10)}"/> </td>
			<td><c:out value="${fn:substring(i.toDate, 0, 10)}"/> </td>
			<td><c:out value="${i.leaveAvailed}"/> </td>
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