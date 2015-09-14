<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
<head>
<title>Input Details</title>

<style>
body {
	font-family: Calibri, Tahoma, Arial;
}
</style>
</head>

<body>

	<div id="wrapper">
		<div id="tabContainer">
			<div id="tabs">
				<ul>
					<li id="tabHeader_1">Profile</li>
					<li id="tabHeader_2">Leave Application</li>

					<li id="tabHeader_3">Manager Access</li>
					<li id="tabHeader_4">Reports</li>
				</ul>
			</div>
			<div id="tabscontent">
				<div class="tabpage" id="tabpage_1">
					<h2>Profile</h2>
					<table border="1" style="width: 100%" id="First_Page">
						<tbody style="background-color: #00AAFF">
							<tr>
								<td>SOEID</td>
								<td><s:property value="obj1.soeid" /></td>
							</tr>
							<tr>
								<td>First Name</td>
								<td><s:property value="obj1.fname" /></td>
							</tr>
							<tr>
								<td>Last Name</td>
								<td><s:property value="obj1.lname" /></td>
							</tr>
							<tr>
								<td>Title</td>
								<td><s:property value="obj1.title" /></td>
							</tr>
							<tr>
								<td>Gender</td>
								<td><s:property value="obj1.gender" /></td>
							</tr>
							<tr>
								<td>Address</td>
								<td><s:property value="obj1.address" /></td>
							</tr>
							<tr>
								<td>City</td>
								<td><s:property value="obj1.city" /></td>
							</tr>
							<tr>
								<td>Region</td>
								<td><s:property value="obj1.region" /></td>
							</tr>
							<tr>
								<td>Pincode</td>
								<td><s:property value="obj1.pcode" /></td>
							</tr>
							<tr>
								<td>Country</td>
								<td><s:property value="obj1.country" /></td>
							</tr>
							<tr>
								<td>Home Phone</td>
								<td><s:property value="obj1.hphone" /></td>
							</tr>
							<tr>
								<td>Extension</td>
								<td><s:property value="obj1.ext" /></td>
							</tr>
							<tr>
								<td>Manager</td>
								<td><s:property value="obj1.mgr" /></td>
							</tr>
							<tr>
								<td>Level</td>
								<td><s:property value="obj1.lvl" /> <s:hidden name="level" /></td>
							</tr>

						</tbody>
					</table>
				</div>

				<div class="tabpage" id="tabpage_2">
					<s:form action="application.action">

						<table border="1" style="width: 100%">
							<tbody style="background-color: #20E2FF">
								<tr>
									<td>SOEID</td>
									<td><s:property value="username" /></td>
								</tr>
								<tr>
									<td>Number of Casual Leaves available</td>
									<td><s:property value="cl" /></td>
								</tr>
								<tr>
									<td>Number of Sick Leaves available</td>
									<td><s:property value="sl" /></td>
								</tr>
								<tr>
									<td>Number of Mandatory Leaves available</td>
									<td><s:property value="ml" /></td>
								</tr>
								<tr>
									<td>Number of Paternity/Maternity Leaves available</td>
									<td><s:property value="pml" /></td>
								</tr>
							</tbody>
						</table>
						<s:textfield label="Number of days:" name="num_days" maxlength="2" />
						<br>

						<label>Start Date: </label>
						<input type="date" name="st_date" min=today required>
						<br>
Leave Category: <select name="category">
							<option value="Casual">Casual</option>
							<option value="Sick">Sick</option>
							<option value="Mandatory">Mandatory</option>
							<option value="Paternity/Maternity">Paternity/Maternity</option>
						</select>

						<br>
						<input type="submit" value="Apply" />

						<input type="reset" value="Reset" />
					</s:form>
				</div>
				<div class="tabpage" id="tabpage_3">
					<h2>Leave Request Processing</h2>

					<h2>Leaves to be approved</h2>
					<table border="1" style="width: 100%">
						<thead style="background-color:black; color:white">
							<tr>
								<th>Leave ID</th>
								<th>SOEID</th>
								<th>Number of Days</th>
								<th>Start_Date</th>
								<th>Category</th>
								<th>Comments</th>
								<th>Status</th>
								<th>Accept/Reject</th>
							</tr>
						</thead>

						<tbody style="background-color: #0099FF; font-weight:bold">
							<s:iterator value="mlistapp">
								<s:form theme="simple" action="updateLeaves.action">
									<tr>
										<td><s:property value="leave_id" /> <s:hidden
												name="leave_id" /></td>
										<td><s:property value="soeid_emp" /> <s:hidden
												name="soeid_emp" /></td>
										<td><s:property value="num_days" /> <s:hidden
												name="num_days" /></td>
										<td><s:property value="st_date" /></td>
										<td><s:property value="category" /> <s:hidden
												name="category" /></td>
										<td><s:property value="comments" /></td>
										<td><s:property value="stat" /></td>
										<td><s:submit name="A_Rbut" value="Accept" /> <s:submit
												name="A_Rbut" value="Reject" /></td>
									</tr>
								</s:form>
							</s:iterator>
						</tbody>

					</table>

					<h2>Leaves Approved</h2>
					<table border="1" style="width: 100%">
						<thead style="background-color: black; color:white">
							<tr>
								<th>Leave ID</th>
								<th>SOEID</th>
								<th>Number of Days</th>
								<th>Start_Date</th>
								<th>Category</th>
								<th>Comments</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody style="background-color: #0099FF; font-weight:bold">
							<s:iterator value="mlistapa">
								<s:form theme="simple">
									<tr>
										<td><s:property value="leave_id" /> <s:hidden
												name="leave_id" /></td>
										<td><s:property value="soeid_emp" /> <s:hidden
												name="soeid_emp" /></td>
										<td><s:property value="num_days" /> <s:hidden
												name="num_days" /></td>
										<td><s:property value="st_date" /></td>
										<td><s:property value="category" /> <s:hidden
												name="category" /></td>
										<td><s:property value="comments" /></td>
										<td><s:property value="stat" /></td>
									</tr>
								</s:form>
							</s:iterator>

						</tbody>
					</table>

					<h2>Leaves Rejected</h2>
					<table border="1" style="width: 100%">
						<thead style="background-color: black; color:white">
							<tr>
								<th>Leave ID</th>
								<th>SOEID</th>
								<th>Number of Days</th>
								<th>Start_Date</th>
								<th>Category</th>
								<th>Comments</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody style="background-color: #0099FF; font-weight:bold">
							<s:iterator value="mlistapr">
								<s:form theme="simple">
									<tr>
										<td><s:property value="leave_id" /> <s:hidden
												name="leave_id" /></td>
										<td><s:property value="soeid_emp" /> <s:hidden
												name="soeid_emp" /></td>
										<td><s:property value="num_days" /> <s:hidden
												name="num_days" /></td>
										<td><s:property value="st_date" /></td>
										<td><s:property value="category" /> <s:hidden
												name="category" /></td>
										<td><s:property value="comments" /></td>
										<td><s:property value="stat" /></td>
									</tr>
								</s:form>
							</s:iterator>

						</tbody>
					</table>

					<h2>Leaves Cancelled</h2>
					<table border="1" style="width: 100%">
						<thead style="background-color: black; color:white">
							<tr>
								<th>Leave ID</th>
								<th>SOEID</th>
								<th>Number of Days</th>
								<th>Start_Date</th>
								<th>Category</th>
								<th>Comments</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody style="background-color: #0099FF; font-weight:bold">
							<s:iterator value="mlistapc">
								<s:form theme="simple">
									<tr>
										<td><s:property value="leave_id" /> <s:hidden
												name="leave_id" /></td>
										<td><s:property value="soeid_emp" /> <s:hidden
												name="soeid_emp" /></td>
										<td><s:property value="num_days" /> <s:hidden
												name="num_days" /></td>
										<td><s:property value="st_date" /></td>
										<td><s:property value="category" /> <s:hidden
												name="category" /></td>
										<td><s:property value="comments" /></td>
										<td><s:property value="stat" /></td>
									</tr>
								</s:form>
							</s:iterator>

						</tbody>
					</table>

					<h2>Employees under me:</h2>
					<s:form theme="simple" action="appbehalf.action">
						<table border="1" style="width: 100%">
							<thead style="background-color: black; color:white">
								<tr>

									<th>SOEID</th>
									<th>First Name</th>
									<th>Last Name</th>
									<th>Title</th>
									<th>Gender</th>
									<th>Address</th>
									<th>City</th>
									<th>Region</th>
									<th>Pincode</th>
									<th>Country</th>
									<th>Home Phone</th>
									<th>Extension</th>
									<th>Manager</th>
									<th>Employee Status</th>
									<th>Level</th>
								</tr>
							</thead>
							<tbody style="background-color: #0099FF; font-weight:bold">
								<s:iterator value="l_Emp">

									<tr>
										<td><s:property value="soeid" /></td>
										<td><s:property value="lname" /></td>
										<td><s:property value="fname" /></td>
										<td><s:property value="title" /></td>
										<td><s:property value="gender" /></td>
										<td><s:property value="address" /></td>
										<td><s:property value="city" /></td>
										<td><s:property value="region" /></td>
										<td><s:property value="pcode" /></td>
										<td><s:property value="country" /></td>
										<td><s:property value="hphone" /></td>
										<td><s:property value="ext" /></td>
										<td><s:property value="mgr" /></td>
										<td><s:property value="emp_stat" /></td>
										<td><s:property value="lvl" /></td>
									</tr>
								</s:iterator>
							</tbody>
						</table>
						<h2>Apply On behalf</h2>
							<label>Select SOEID</label>
							<s:select headerKey="-1" headerValue="--- Please select ---"
								list="uemps" name="title" label="Title" />

							<label>Number of days</label> <input type="number"
								name="bnum_days" min=1 required /> <br> <label>
								Start date</label> <input type="date" name="bst_date" min=today required />

							<label>Leave Category</label> <select name="bcategory">
								<option value="Casual">Casual</option>
								<option value="Sick">Sick</option>
								<option value="Mandatory">Mandatory</option>
								<option value="Paternity/Maternity">Paternity/Maternity</option>
							</select> <br> <label>Comments</label>
							<s:textarea name="bcomments" cols="20" rows="4" />
						<s:submit />
					</s:form>
				</div>
				<div class="tabpage" id="tabpage_4">
					<div class="tab_open">Leave Status</div>
					<h2>Leaves Applied with pending status</h2>
					<table border="1" style="width: 100%">
						<thead style="background-color: black; color:white">
							<tr>
								<th>Leave ID</th>
								<th>SOEID of Employee</th>
								<th>Number of Days</th>
								<th>Start_Date</th>
								<th>Category</th>
								<th>Comments</th>
								<th>Status</th>
								<th></th>
							</tr>
						</thead>
						<tbody style="background-color: #0099FF; font-weight:bold">
							<s:iterator value="mlist">
								<s:form theme="simple" action="updateLeaves.action">
									<tr>
										<td><s:property value="leave_id" /> <s:hidden
												name="leave_id" /></td>
										<td><s:property value="soeid_emp" /> <s:hidden
												name="soeid_emp" /></td>
										<td><s:property value="num_days" /> <s:hidden
												name="num_days" /></td>
										<td><s:property value="st_date" /></td>
										<td><s:property value="category" /> <s:hidden
												name="category" /></td>
										<td><s:property value="comments" /></td>
										<td><s:property value="stat" /></td>
										<td><s:submit name="A_Rbut" value="Cancel" /></td>
									</tr>
								</s:form>
							</s:iterator>
						</tbody>
					</table>
					<h2>Leaves Applied with Accepted status</h2>
					<table border="1"  style="width: 100%">
						<thead style="background-color: black">
							<tr>
								<th>Leave ID</th>
								<th>SOEID of Employee</th>
								<th>Number of Days</th>
								<th>Start_Date</th>
								<th>Category</th>
								<th>Comments</th>
								<th>Status</th>
								<th></th>
							</tr>
						</thead>
						<tbody style="background-color: #0099FF; font-weight:bold">
							<s:iterator value="lrep">
								<s:form theme="simple" action="updateLeaves.action">
									<tr>
										<td><s:property value="leave_id" /> <s:hidden
												name="leave_id" /></td>
										<td><s:property value="num_days" /> <s:hidden
												name="num_days" /></td>
										<td><s:property value="st_date" /></td>
										<td><s:property value="category" /> <s:hidden
												name="category" /></td>
										<td><s:property value="comments" /></td>
										<td><s:property value="stat" /></td>
										<td><s:submit name="A_Rbut" value="Cancel" /></td>

									</tr>
								</s:form>
							</s:iterator>
						</tbody>
					</table>
					<h2>Leaves Applied with Rejected status</h2>
					<table border="1" style="width: 100%">
						<thead style="background-color: black; color:white">
							<tr>
								<th>Leave ID</th>
								<th>SOEID of Employee</th>
								<th>Number of Days</th>
								<th>Start_Date</th>
								<th>Category</th>
								<th>Comments</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody style="background-color: #0099FF; font-weight:bold">
							<s:iterator value="rrep">
								<s:form theme="simple">
									<tr>
										<td><s:property value="leave_id" /> <s:hidden
												name="leave_id" /></td>
										<td><s:property value="num_days" /> <s:hidden
												name="num_days" /></td>
										<td><s:property value="num_days" /> <s:hidden
												name="num_days" /></td>
										<td><s:property value="st_date" /></td>
										<td><s:property value="category" /> <s:hidden
												name="category" /></td>
										<td><s:property value="comments" /></td>
										<td><s:property value="stat" /></td>

									</tr>
								</s:form>
							</s:iterator>
						</tbody>
					</table>

					<h2>Leaves Applied with Cancelled status</h2>
					<table border="1"  style="width: 100%">
						<thead style="background-color: black; color:white">
							<tr>
								<th>Leave ID</th>
								<th>SOEID of Employee</th>
								<th>Number of Days</th>
								<th>Start_Date</th>
								<th>Category</th>
								<th>Comments</th>
								<th>Status</th>
							</tr>
						</thead>
						<tbody style="background-color: #0099FF; font-weight:bold">
							<s:iterator value="crep">
								<s:form theme="simple">
									<tr>
										<td><s:property value="leave_id" /> <s:hidden
												name="leave_id" /></td>
										<td><s:property value="num_days" /> <s:hidden
												name="num_days" /></td>
										<td><s:property value="num_days" /> <s:hidden
												name="num_days" /></td>
										<td><s:property value="st_date" /></td>
										<td><s:property value="category" /> <s:hidden
												name="category" /></td>
										<td><s:property value="comments" /></td>
										<td><s:property value="stat" /></td>

									</tr>
								</s:form>
							</s:iterator>
						</tbody>
					</table>

				</div>
			</div>
		</div>
	</div>
	<script>
		window.onload = function() {

			// get tab container
			var container = document.getElementById("tabContainer");
			var tabcon = document.getElementById("tabscontent");
			//alert(tabcon.childNodes.item(1));
			// set current tab
			var navitem = document.getElementById("tabHeader_1");
			var flag = document.getElementById("lvl");
			if (flag == 0) {
				document.getElementById("tabHeader_3").display = "none";
			}
			//store which tab we are on
			var ident = navitem.id.split("_")[1];
			//alert(ident);
			navitem.parentNode.setAttribute("data-current", ident);
			//set current tab with class of activetabheader
			navitem.setAttribute("class", "tabActiveHeader");

			//hide two tab contents we don't need
			var pages = tabcon.getElementsByTagName("div");
			for (var i = 1; i < pages.length; i++) {
				pages.item(i).style.display = "none";
			}
			;

			//this adds click event to tabs
			var tabs = container.getElementsByTagName("li");
			for (var i = 0; i < tabs.length; i++) {
				tabs[i].onclick = displayPage;
			}
		}

		// 	var emp=document.getElementByName("level");
		// 	document.getElementById("tabHeader_4").style.display="none";
		// 	int x=parseInt(emp);
		// 	if(x==0){
		// 		document.getElementById("tabpage_3").style.display="none";
		// 	}
		// on click of one of tabs
		function displayPage() {
			var current = this.parentNode.getAttribute("data-current");
			//remove class of activetabheader and hide old contents
			document.getElementById("tabHeader_" + current).removeAttribute(
					"class");
			document.getElementById("tabpage_" + current).style.display = "none";

			var ident = this.id.split("_")[1];
			//add class of activetabheader to new active tab and show contents
			this.setAttribute("class", "tabActiveHeader");
			document.getElementById("tabpage_" + ident).style.display = "block";
			this.parentNode.setAttribute("data-current", ident);
		}
		$('li').mouseover(function() {
			if ($('li ul li:hover').length) {
				$('li ul li:hover').css('background', 'red');
			} else {
				$('li:hover').css('background', 'red');
			}
		});
		$('li').mouseout(function() {
			$(this).css('background', 'transparent');
		});
	</script>
	<style>
* {
	margin: 10;
	padding: 10;
}

body {
	font: .8em "Lucida Sans Unicode", "Lucida Grande", sans-serif;
}

h2 {
	margin-bottom: 10px;
}

#wrapper {
	width: auto;
	margin: auto auto 0;
	background: linear-gradient(to bottom, rgba(48,48, 48, 1),
		rgba(20, 170, 255, 1));
}

#wrapper h1 {
	text-color: blue;
	text-align: center;
	margin-bottom: 20px;
}

#wrapper a {
	display: block;
	font-size: 1.2em;
	padding-top: aut;
	color: #101010;
	text-decoration: none;
	text-align: center;
}

#tabContainer {
	width: auto;
	padding: 2;
	background-color: linear-gradient(to bottom, rgba(48,48, 48, 1),
		rgba(20, 170, 255, 1));
	border-radius: 5;
	position: relative;
	left: 0%;
	top: 60%;
}

#tabs {
	height: 45;
}

#tabs>ul {
	font: 1em;
	list-style: none;
}

#tabs>ul>li {
	position: relative;
	left: 0;
	top: 0;
	margin: 0 2px 60px 0;
	padding: auto auto;
	float: left;
	font-weight: bold;
	color: #FF0;
	border-top-left-radius: 0px;
	border-top-right-radius: 10px;
	border-bottom-right-radius: 0px;
	border-bottom-left-radius: 0px;
	background: #00AAFF; /* old browsers */
	background: linear-gradient(to bottom, rgba(0, 0, 0, 0),
		rgba(20, 170, 255, 1));
}

#tabs>ul>li:hover {
	background: linear-gradient(to bottom, rgba(0, 0, 0, 0),
		rgba(255, 170, 0, 1));
	cursor: pointer;
	color: black;
}

#tabs>ul>li.tabActiveHeader {
	background: linear-gradient(to bottom, rgba(0, 0, 0, 0),
		rgba(216, 216, 216, 1));
	cursor: pointer;
	height: 25;
	font: bold 15px Arial;
	color: #FFF;
}

#tabscontent {
	border-top-left-radius: 10px;
	border-top-right-radius: 10px;
	border-bottom-right-radius: 10px;
	border-bottom-left-radius: 10px;
	padding: auto;
	background: linear-gradient(to top,  rgba(255,255,255,1),rgba(216,216,216,1)); /* old browsers */
	margin: auto;
	color: #323;
}

table, th, td {
	border: 1px solid yellow;
	border-collapse: collapse;
}

th, td {
	padding: 1px;
}

#tab_open {
	position: fixed;
	left: 0;
}
</style>
</body>
</html>
