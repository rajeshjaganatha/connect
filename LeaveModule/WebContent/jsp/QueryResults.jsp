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
</head>

<script type="text/javascript">
function getSubmitLeaveReport(){
				var leaveTypeId = document.getElementById("leaveTypeId").value;
				var todate = document.getElementById("todate").value;
				var fromdate = document.getElementById("fromdate").value;
				if(leaveTypeId == "selectLeaveType"){
					alert("Please Select the Leave Type");
					return false;
				}else if(todate == ""){
					alert("Please Select To date");
					return false;
				}else if(fromdate == ""){
					alert("Please Select From date");
					return false;
				}
				else{
					document.forms[0].action="applyLeave.do";
					document.forms[0].submit();
				}
}
</script>

<body>
	<div class="title">LEAVE</div>

	<a href="#" title="LogOut" onclick="javascript:go('logout.do');"
		class="button_example">LOGOUT</a>
	<a href="#" title="holidaylist"
		onclick="javascript:go('holidaylist.do');" class="button_example">HOLIDAY
		LIST</a>
	<a href="#" title="leavesummary"
		onclick="javascript:go('leavesummary.do');" class="button_example">LEAVE
		SUMMARY</a>
	<a href="#" title="home" onclick="javascript:go('timesheet.do');"
		class="button_example">HOME</a>

	<br>
	<br>
	<table cellspacing="1" cellpadding="4" border="1">
		<c:choose>
			<c:when test="${!empty queryResults}">
				<tr>
					<td>Serial Number <c:out value="${i.leaveTypeId}" /></td>
					<td>Leave Type <c:out value="${i.leaveType}" /></td>
					<td>Allotment <c:out value="${i.allotment}" /></td>
					<td>Availed <c:out value="${i.availed}" />
					</td>
					<%--<td>Available		<c:out value="${i.available}"/> </td> --%>
				</tr>

				<c:forEach items="${queryResults}" var="i" varStatus="loopStatus">
					<tr>
						<td><c:out value="${i.leaveTypeId}" /></td>
						<td><c:out value="${i.leaveType}" /></td>
						<td><c:out value="${i.allotment}" /></td>
						<td><c:out value="${i.availed}" /></td>
						<%-- <td><c:out value="${i.available}"/> </td> --%>
					</tr>

				</c:forEach>

			</c:when>
			<c:otherwise>
				<td colspan="5" align="center" class="rowodd"><c:out
						value="No Records Found"></c:out></td>
			</c:otherwise>
		</c:choose>
	</table>

	<br>
	<form:form action="applyLeave.do" method="post"
		commandName="BookmarkList" modelAttribute="leaveBean">
		<table>
			<tr style="width: 454px;">

				<td>Leave Type*</td>
				<td><select id="leaveTypeId" name="leaveTypeId">
						<option value="selectLeaveType">--Select Leave Type--</option>
						<c:forEach items="${queryResults}" var="i">
							<option value="${i.leaveTypeId}"
								${i.leaveTypeId == leaveTypeId? 'selected' : ''}>${i.leaveType}</option>
						</c:forEach>
				</select></td>


				<td>Leave Duration*</td>
				<td><input type="text" name="fromdate" id="fromdate"
					style="width: 91px;"> <a href="#"
					onClick="setYears(2000, 2020); 
      
      showCalender(this, 'fromdate'); setTodate(); ">
						<img src="calender.png" alt="">
				</a></td>

				<td><input type="text" name="todate" id="todate"
					style="width: 91px;"> <a href="#"
					onClick="setYears(2000, 2020); 
      showCalender(this, 'todate');">
						<img src="calender.png" alt="">
				</a></td>

				<td>No. of Days</td>
				<td width="23%"><input type="text" size="3" name="leaveAvailed"
					id="leaveAvailed" class="leaveBean" align="right" value="0"
					readonly="readonly" style="background: silver; z-index: 5;">
					<div class="label" id="halfDayImage"></div></td>
			</tr>
			
			<tr>
				<td><input type="button" onclick="getSubmitLeaveReport();"
					title="Submit" value="APPLY LEAVE" class="button_example" /></td>
			</tr>
		</table>
		<td style="color: red; font-size: large;" colspan="3">${ERROR_MSG}</td>
	</form:form>


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
	<form:errors path="document_number" cssClass="error" />
	<!-- End Calender Script  -->




</body>
</html>