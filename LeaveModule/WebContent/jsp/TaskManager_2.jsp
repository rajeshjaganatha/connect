<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.io.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="taglib_includes.jsp"%>
<html>
<head>

<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Connect</title>
<script type="text/javascript" src="js/common.js"></script>
<script language="javaScript" type="text/javascript"
	src="js/calendar.js"></script>
<script language="javaScript" type="text/javascript"
	src="js/workingdays.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/calendar.css" rel="stylesheet" type="text/css">
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html"%>
</head>

<body>
	<div class="title">Leave</div>
	<a href="#" title="LogOut" onclick="javascript:go('logout.do');"
		class="button_example">LOGOUT</a>

	<h:form id="inputForm">
		<table>
			<tr style="width: 454px;">
			<td>Project</td>
			<td>			<h:selectOneMenu id="countries" 
			                 label="Countries"
				             value="#{inputBean.selectedCountry}">
				<f:selectItems value="#{inputBean.countries}" />

				<f:ajax event="change" 
				        execute="@this" 
				        render="states"
					    listener="#{inputBean.setStateOptions()}" />

			</h:selectOneMenu></td>
				
								<td>Date</td>
				<td><input type="text" name="date" id="date"
					style="width: 91px;"> <a href="#"
					onClick="setYears(2000, 2020); 
      
      showCalender(this, 'fromdate'); setTodate(); ">
						<img src="calender.png" alt="">
				</a></td>

								<td>Hours</td>
				<td><input type="text" name="hours" id="hours"></td>
				
				</tr>
				<tr>

				<td>Task</td>
				<td><select id="taskId" name="taskId">
						<option value="selectLeaveType">--Select Leave Type--</option>
						<c:forEach items="${projectList}" var="i">
							<option value="${i.projectName}"
								${i.projectId == projectId? 'selected' : ''}>${i.projectName}</option>
						</c:forEach>
				</select></td>

				<td>Desc</td>
				<td><input type="text" name="taskDesc" id="taskDesc"></td>
			</tr>
			<tr>
				<td><input type="button" onclick="getSubmitLeaveReport();"
					title="Submit" value="APPLY LEAVE" class="button_example" /></td>
			</tr>
			
		</table>
		<td style="color: red; font-size: large;" colspan="3">${ERROR_MSG}</td>
	</h:form>


	<!-- Calender Script  -->
	<table id="calenderTable">
		<tbody id="calenderTableHead">
			<tr>
				<td colspan="4" align="center"><select
					onChange="showCalenderBody(
                   createCalender(document.getElementById('selectYear').value,
	           this.selectedIndex, false));"
					id="selectMonth">
						<option value="0">Jan</option>
						<option value="1">Feb</option>
						<option value="2">Mar</option>
						<option value="3">Apr</option>
						<option value="4">May</option>
						<option value="5">Jun</option>
						<option value="6">Jul</option>
						<option value="7">Aug</option>
						<option value="8">Sep</option>
						<option value="9">Oct</option>
						<option value="10">Nov</option>
						<option value="11">Dec</option>
				</select></td>
				<td colspan="2" align="center"><select
					onChange="showCalenderBody(createCalender(this.value, 
	      document.getElementById('selectMonth').selectedIndex, false));"
					id="selectYear">
				</select></td>
				<td align="center"><a href="#" onClick="closeCalender();">
						<font color="#003333" size="+1">X</font>
				</a></td>
			</tr>
		</tbody>
		<tbody id="calenderTableDays">
			<tr style="">
				<td>Sun</td>
				<td>Mon</td>
				<td>Tue</td>
				<td>Wed</td>
				<td>Thu</td>
				<td>Fri</td>
				<td>Sat</td>
			</tr>
		</tbody>
		<tbody id="calender"></tbody>
	</table>
	<td><form:errors path="document_number" cssClass="error" /></td>
	<!-- End Calender Script  -->




</body>
</html>