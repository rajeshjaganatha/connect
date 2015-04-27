<%@ page import="com.thoughtfocus.leave.util.Month,java.util.*,java.io.*,java.sql.*,com.thoughtfocus.leave.*, java.util.Date, java.text.*" %>
<%@include file="taglib_includes.jsp"%>
<%-- TODO: CLEAN UP THE PAGE TAG ABOVE --%>
<%@ include file="calendarCommon.jsp" %>

<html>
<head>
  <title>Calendar</title>
   <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
  <script type="text/javascript" src="js/common.js"></script>
<script language="javaScript" type="text/javascript"
	src="js/calendar.js"></script>
<script language="javaScript" type="text/javascript"
	src="js/workingdays.js"></script>
<link href="css/table.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<link href="css/calendar.css" rel="stylesheet" type="text/css">
</head>

<body id="regular_page">
<div id="calendar_main_div">
<table border="1" id="calendar_table">
  <tr>
    <td width="50%" colspan="9" class="month_year_header">
      <%=monthName%>, <%=intYear%>
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
    <table id="today_tasks" border="1" style="border:2px;">
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
    <table id="day_tasks" border="1" style="border:2px;">
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
</table>

<%-- end of "calendar_div" --%>
</div>

<!-- navigation links -->
<%-- sorry, i don't know how to get this look without a table --%>
<table id="calendar_nav_table" >
  <tr>
    <td id="prev_link">
      <form method="get" >
        <input type="submit" name="PREV" value=" << ">
        <input type="hidden" name="month" value="<%=prevMonth%>">
        <input type="hidden" name="year" value="<%=prevYear%>">
      </form>
    </td>
    <td id="link_to_month_view">
      <form action="calendarMonthPrintView.do" method="get">
        <input type="submit" value="  Full-Screen Print View  " class="submit_button">
        <input type="hidden" name="month" value="<%=intMonth%>">
        <input type="hidden" name="year"  value="<%=intYear%>">
      </form>
    </td>
    <td id="next_link">
      <form method="get">
        <input type="submit" name="NEXT" value=" >> ">
        <input type="hidden" name="month" value="<%=nextMonth%>">
        <input type="hidden" name="year" value="<%=nextYear%>">
      </form>
    </td>
  </tr>
</table>
  <!-- navigation links end -->
  


</body>
</html>


