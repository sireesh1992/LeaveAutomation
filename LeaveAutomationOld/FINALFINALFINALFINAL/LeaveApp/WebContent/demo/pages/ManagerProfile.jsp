<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	pageContext.setAttribute("newLineChar", "\n");
%>


<html>
<head>
<title>Confirmed Details</title>
<style>
body {
	font-family: Calibri, Tahoma, Arial;
	background-color: #d0e4fe;
}

h2 {
	font-size: 40px;
	color: black;
	text-align: center;
}

body {
	background: #fafafa url(http://jackrugile.com/images/misc/noise-diagonal.png);
	color: #444;
	font: 100%/30px 'Helvetica Neue', helvetica, arial, sans-serif;
	text-shadow: 0 1px 0 #fff;
}

strong {
	font-weight: bold; 
}

em {
	font-style: italic; 
}

table {
	background: #f5f5f5;
	border-collapse: separate;
	box-shadow: inset 0 1px 0 #fff;
	font-size: 12px;
	line-height: 24px;
	margin: 30px auto;
	text-align: left;
	width: 800px;
}	

th {
	background: url(http://jackrugile.com/images/misc/noise-diagonal.png), linear-gradient(#777, #444);
	border-left: 1px solid #555;
	border-right: 1px solid #777;
	border-top: 1px solid #555;
	border-bottom: 1px solid #333;
	box-shadow: inset 0 1px 0 #999;
	color: #fff;
  font-weight: bold;
	padding: 10px 15px;
	position: relative;
	text-shadow: 0 1px 0 #000;	
}

th:after {
	background: linear-gradient(rgba(255,255,255,0), rgba(255,255,255,.08));
	content: '';
	display: block;
	height: 25%;
	left: 0;
	margin: 1px 0 0 0;
	position: absolute;
	top: 25%;
	width: 100%;
}

th:first-child {
	border-left: 1px solid #777;	
	box-shadow: inset 1px 1px 0 #999;
}

th:last-child {
	box-shadow: inset -1px 1px 0 #999;
}

td {
	border-right: 1px solid #fff;
	border-left: 1px solid #e8e8e8;
	border-top: 1px solid #fff;
	border-bottom: 1px solid #e8e8e8;
	padding: 10px 15px;
	position: relative;
	transition: all 300ms;
}

td:first-child {
	box-shadow: inset 1px 0 0 #fff;
}	

td:last-child {
	border-right: 1px solid #e8e8e8;
	box-shadow: inset -1px 0 0 #fff;
}	

tr {
	background: url(http://jackrugile.com/images/misc/noise-diagonal.png);	
}

tr:nth-child(odd) td {
	background: #f1f1f1 url(http://jackrugile.com/images/misc/noise-diagonal.png);	
}

tr:last-of-type td {
	box-shadow: inset 0 -1px 0 #fff; 
}

tr:last-of-type td:first-child {
	box-shadow: inset 1px -1px 0 #fff;
}	

tr:last-of-type td:last-child {
	box-shadow: inset -1px -1px 0 #fff;
}	

tbody:hover td {
	color: transparent;
	text-shadow: 0 0 3px #aaa;
}

tbody:hover tr:hover td {
	color: #444;
	text-shadow: 0 1px 0 #fff;
}

</style>
</head>

<body>
<table><tr><td style="text-align:right"><a href="http://localhost:8080/LeaveApp/demo/input.action">Logout</a></td></tr>
</table > 
	<h2>Profile</h2>

	

		<table>
			<tr>
				<th>Name</th>
				<th>SOEID</th>
				<th>Team</th>
				<th>Contact</th>
				<th>Designation</th>
				<th>Address</th>
				<th>Company</th>
				<th>Casual Leaves Taken</th>
				<th>Casual Leaves Available</th>
				<th>Sick Leaves Taken</th>
				<th>Sick Leaves Available</th>
				<th>Mandatory Leaves Taken</th>
				<th>Mandatory Leaves Available</th>
				<th>Maternity Leaves Taken</th>
				<th>Maternity Leaves Available</th>
			</tr>

			<tr>
				<td><s:property value="name" /></td>
				<td><s:property value="SOEID" /></td>
				<td><s:property value="team" /></td>
				<td><s:property value="contact" /></td>
				<td><s:property value="designation" /></td>
				<td><s:property value="address" /></td>
				<td><s:property value="company" /></td>
				<td><s:property value="casualLeavesTaken" /></td>
				<td><s:property value="casualLeavesAvailable" /></td>
				<td><s:property value="sickLeavesTaken" /></td>
				<td><s:property value="sickLeavesAvailable" /></td>
				<td><s:property value="mandatoryLeavesTaken" /></td>
				<td><s:property value="mandatoryLeavesAvailable" /></td>
				<td><s:property value="maternityLeavesTaken" /></td>
				<td><s:property value="maternityLeavesAvailable" /></td>

			</tr>
		</table>	

	<h2>Leave Application Status</h2>
	<table>
		<tr>
			<th>ID</th>
			<th>SOEID</th>
			<th>category</th>
			<th>startDate</th>
			<th>endDate</th>
			<th>noOfDays</th>
			<th>status</th>
		</tr>
		<s:iterator value="leaveStatusQueue">
			<s:form theme="simple">
				<tr>
					<td><s:property value="leaveID" /> <s:hidden name="leaveID" /></td>
					<td><s:property value="SOEID" /> <s:hidden name="SOEID" /></td>
					<td><s:property value="category" /> <s:hidden name="category" /></td>
					<td><s:property value="startDate" /> <s:hidden
							name="startDate" /></td>
					<td><s:property value="endDate" /> <s:hidden name="endDate" /></td>
					<td><s:property value="noOfDays" /> <s:hidden name="noOfDays" /></td>
					<td><s:property value="status" /> <s:hidden name="status" /></td>
				</tr>
			</s:form>
		</s:iterator>
	</table>
	
	
	<s:form action="applyleave">
		
		<s:submit value="Apply for leave"></s:submit>
	</s:form>
	<!-- leave Approval Queue -->
	

	
	<s:form action="leaveApproval">
		<s:submit name="ShowLeaveApprovalQueue"
			value="Show Leave Approval Queue"></s:submit>
	</s:form>


	<s:form action="ApplyOnBehalf">
		<s:submit name="ApplyForMember" value="Apply for Team Member"></s:submit>
	</s:form>


	<s:form action="selfReport">
		<s:submit name="showReport" value="Show Leave Report" />
	</s:form>
	
	<s:form action="changePassword">
	<s:submit name="PasswordChange" value="Change Password"/>
	</s:form>
	
	
<% 
response.setHeader("Pragma","no-cache"); 
response.setHeader("Cache-Control","no-store"); 
response.setHeader("Expires","0"); 
response.setDateHeader("Expires",-1); 
%> 
</body>
</html>



