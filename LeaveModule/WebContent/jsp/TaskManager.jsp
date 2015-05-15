<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.thoughtfocus.leave.util.Month,java.util.*,java.io.*,java.sql.*,com.thoughtfocus.leave.*, java.util.Date, java.text.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@include file="taglib_includes.jsp"%>
<%@ include file="calendarCommon.jsp" %>
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

function getTask(){
				var projectId = document.getElementById("projectId").value;
				var date = document.getElementById("date").value;
				var hours = document.getElementById("hours").value;
				var taskId = document.getElementById("taskId").value;
				var taskDesc = document.getElementById("taskDesc").value;
				
				if(projectId == "selectProject"){
					alert("Please Select the Project");
					return false;
				}else if(date == ""){
					alert("Please Select date");
					return false;
				}else if(hours == ""){
					alert("Please Enter Hours");
					return false;
				}else if(taskId == "selectTask"){
					alert("Please Select Task");
					return false;
				}else if(taskDesc == ""){
					alert("Please Enter Description");
					return false;
				}
				else{
					document.forms[0].action="addTask.do";
					document.forms[0].submit();
				}
}

</script>
<body>
	<div class="title">TIME SHEET</div>
	<a href="#" title="LogOut" onclick="javascript:go('logout.do');"
		class="button_example">LOGOUT</a>
	<a href="#" title="holidaylist"
		onclick="javascript:go('holidaylist.do');" class="button_example">HOLIDAY
		LIST</a>
	<a href="#" title="leave"
		onclick="javascript:go('leave.do');" class="button_example">LEAVE</a>
		
		
<br>
<br>
	
	<form:form action="addTask.do" method="post"
		commandName="BookmarkList" modelAttribute="taskBean">
		<table>
			<tr style="width: 454px;">
			<td>Project</td>
				<td><select id="projectId" name="projectId" >
						<option value="selectProject">--Select Project--</option>
						<c:forEach items="${projectList}" var="i">
							<option value="${i.projectId}"
								${i.projectId == projectId? 'selected' : ''}>${i.projectName}</option>
						</c:forEach>
				</select></td>
				
								<td>Date</td>
				<td><input type="text" name="date" id="date"
					style="width: 91px;"> <a href="#"
					onClick="setYears(2000, 2020); 
      
      showCalender(this, 'date'); ">
						<img src="calender.png" alt="">
				</a></td>

								<td>Hours</td>
				<td><input type="text" name="hours" id="hours"></td>
				
				</tr>
				<tr>

				<td>Task</td>
				<td><select id="taskId" name="taskId">
						<option value="selectTask">--Select Task--</option>
						<c:forEach items="${taskList}" var="i">
							<option value="${i.taskId}"
								${i.taskId == taskId? 'selected' : ''}>${i.taskName}</option>
						</c:forEach>
				</select></td>

				<td>Desc</td>
				<td><input type="text" name="taskDesc" id="taskDesc"></td>
			</tr>
			<tr>
				<td><input type="button" onclick="getTask();"
				title="Submit" value="ADD TASK" class="button_example" /></td>
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


<div id="calendar_main_div">
<table border="1" id="calendar_table">
  <tr>
  <td rowspan="10" id="prev_link" >
      <form method="get" action="timesheet.do">
        <input style="height: 600px;" type="submit" name="PREV" value=" << ">
        <input type="hidden" name="month" value="<%=prevMonth%>">
        <input type="hidden" name="year" value="<%=prevYear%>">
      </form>
 </td>
    <td colspan="9" class="month_year_header">
      <%=monthName%>, <%=intYear%>
   </td>
           <td rowspan="10" id="next_link" >
      <form method="get" action="timesheet.do">
        <input style="height: 600px;" type="submit" name="NEXT" value=" >> ">
        <input type="hidden" name="month" value="<%=nextMonth%>">
        <input type="hidden" name="year" value="<%=nextYear%>">
      </form>
    </td>
  </tr>
 
  <tr class="week_header_row" >
    <th class="th_day_cell day">Sun</th>
    <th class="th_day_cell day">Mon</th>
    <th class="th_day_cell day">Tue</th>
    <th class="th_day_cell day">Wed</th>
    <th class="th_day_cell day">Thu</th>
    <th class="th_day_cell day">Fri</th>
    <th class="th_day_cell day">Sat</th>

  </tr>
<% {Month aMonth = Month.getMonth( Integer.parseInt(currentMonthString), Integer.parseInt(currentYearString) );
  	int [][] days = aMonth.getDays();
  	String tempDate = null;;
  	for( int i=0; i<aMonth.getNumberOfWeeks(); i++ )
  	{%>
    <tr class="week_data_row"  ><%
    for( int j=0; j<7; j++ )
    {
      if( days[i][j] == 0 )
      {
        %><td class="empty_day_cell" >&nbsp;</td><%
      }
      else
      {
        // this is "today"
        if(currentDayInt == days[i][j] && currentMonthInt == aMonth.getMonth() && currentYearInt == aMonth.getYear() ){
        	tempDate = aMonth.getYear()+"-"+(aMonth.getMonth()+1)+"-"+days[i][j];
          	SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    		Date date = null;
    		try {
    			date = formatter.parse(tempDate);
    		} catch (ParseException e1) {
    			e1.printStackTrace();
    		}
    		tempDate = formatter.format(date);%>
    		
	<c:set var="tempd" scope="session" value="<%=tempDate%>"/>
    <td class="today_cell" ><%=days[i][j]%>
    <table id="today_tasks" border="1" >
 	<c:forEach items="${taskSummary}" var="i" varStatus="loopStatus">
  	<c:if test="${fn:substring(i.taskDate,0,10) eq tempd}">
  	<tr>
  	<td><c:out value = "${i.projects.projectName}"/></td>
  	<td><c:out value = "${i.hours}"/></td>
  	</tr>
  	</c:if>
  	</c:forEach>
	</table>
 	</td><%}else{
        	String tempDate2 = aMonth.getYear()+"-"+(aMonth.getMonth()+1)+"-"+days[i][j];
          	SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
    		Date date = null;
    		try {
    			date = parser.parse(tempDate2);
    		} catch (ParseException e1) {
    			e1.printStackTrace();
    		}
    		tempDate2 = parser.format(date);%>
	<c:set var="tempd2" scope="session" value="<%=tempDate2%>"/>
    <td class="day_cell"><%=days[i][j]%>
    <table id="day_tasks" border="1" >
  	<c:forEach items="${taskSummary}" var="i" varStatus="loopStatus">
	<c:if test="${fn:substring(i.taskDate,0,10) == fn:substring(tempd2,0,10) }">
  	<tr>
  	<td><c:out value = "${i.projects.projectName}"/></td>
  	<td><c:out value = "${i.hours}"/></td>
  	</tr>
  	</c:if>
  	</c:forEach>
	</table>
    </td>
    <%}
      } // end outer if
    } // end for
    %>
    </tr>
  <%}
}
%>
 <tr>
    <td colspan="7" id="link_to_month_view">
      <form action="calendarMonthPrintView.do" method="get">
        <input style="width: 850px;" type="submit" value="  Full-Screen Print View  " class="submit_button">
        <input type="hidden" name="month" value="<%=intMonth%>">
        <input type="hidden" name="year"  value="<%=intYear%>">
      </form>
    </td>
  </tr>

</table>

<%-- end of "calendar_div" --%>
</div>
</body>
</html>