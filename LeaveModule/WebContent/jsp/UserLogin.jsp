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
<link href="css/table.css" rel="stylesheet" type="text/css" />
<link href="css/style.css" rel="stylesheet" type="text/css" />
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
</head>
<body>
	<div class="title">HRMS Portal</div>
	<form:form action="validateUser.do" method="post"
		commandName="BookmarkList" modelAttribute="queryBean">
		<fieldset>
			<legend>
				<b>User Login</b>
			</legend>
			<table class="tabledefault">
				<tr>
					<td style="color: red; font-size: large;" colspan="3">${ERROR_MSG}</td>
				</tr>
				<tr>
					<td style="font-weight: bold;">Login Here</td>
					<td height="10"></td>
				</tr>
				<tr>
					<td width="100" align="left">User Name</td>
					<td width="100" align="left"><form:input path="userName" /></td>
					<td align="left"><form:errors path="userName"
							cssStyle="color:red"></form:errors></td>

				</tr>
				<tr>
					<td width="100" align="left">Password</td>
					<td width="70" align="right"><form:password path="password" /></td>
					<td align="left"><form:errors path="password"
							cssStyle="color:red"></form:errors></td>
				</tr>

				<tr>
					<td><input type="submit" title="Submit" value="SUBMIT"
						class="button_example" /></td>
				</tr>

			</table>
		</fieldset>
	</form:form>
</body>
</html>