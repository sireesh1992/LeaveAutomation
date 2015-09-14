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
<h1>Citi Admin</h1>
		<div id="tabContainer">
			<div id="tabs">
				<ul>
					<li id="tabHeader_1">Home</li>
					<li id="tabHeader_2">Modify Database</li>
					<li id="tabHeader_3">Check Database</li>
					<li id="tabHeader_4">Check Leaves</li>
				</ul>
			</div>
			<div id="tabscontent">
				<div class="tabpage" id="tabpage_1">
					<ul>
<li><s:property value="num_active" /><br>Employees Active</li>
<li><s:property value="num_inactive" /><br>Employees Inactive</li>
<li>Date:<br><s:property value="date" /></li>
				</div>
				<div class="tabpage" id="tabpage_2">
				<h2>Add new Employee</h2>
				<s:form action="adminaddProcess">

			<label>SOEID:</label><input type="text" name="soeid" maxlength="10" />
			<label>First Name:</label><input type="text" name="fname"  maxlength="20" />
			<label>Last Name:</label><input type="text" name="lname"  maxlength="20" />
			<label>Title:</label><input type="text" name="title"  maxlength="10" /><br>
			<label>Gender:</label><input type="text" name="gender"  maxlength="10" />
			<label>Address:</label><input type="text" name="address"  maxlength="40" />
			<label>City:</label><input type="text" name="city"  maxlength="10" />
			<label>Region:</label><input type="text" name="region"  maxlength="10" /><br>
			<label>Pincode:</label><input type="text" name="pcode"  maxlength="10" />
			<label>Country:</label><input type="text" name="country"  maxlength="10" />
			<label>Birth Date:</label><input type="date" name="birthDate"  max=today/>
			<label>Hire Date:</label><input type="date" name="hireDate"  max=today /><br>
			<label>Homephone:</label><input type="text" name="hphone"  maxlength="10" />
			<label>Extension:</label><input type="text" name="ext"  maxlength="20" />
			<label>Reports To:</label><input type="text" name="mgr"  maxlength="10" />
			<label>Level:</label><input type="text" name="lvl" maxlength="10" /><br>
            
            <input type="submit" value="Add Employee">
            <input type="reset" value="Reset">
      		</s:form>
      		<h2>Delete Employee</h2>
					<s:form action="admindelProcess.action">

			<label>SOEID</label><input type="text" name="soeid" maxlength="10" />

             <input type="submit" value="Delete Employee">
            <input type="reset" value="Reset">
		</s:form>
				</div>
				<div class="tabpage" id="tabpage_3">
					<table border="1" style="width: 100%; padding:0">
			<thead style="background-color: black;color:white">
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
				<s:iterator value="adminlist">

					<tr>
						<td><s:property value="soeid" /></td>
						<td><s:property value="fname" /></td>
						<td><s:property value="lname" /></td>
						<td><s:property value="title" /></td>
						<td><s:property value="gender" /></td>
						<td><s:property value="address" /></td>
						<td><s:property value="city" /></td>
						<td><s:property value="region" />&nbsp;</td>
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
				</div>
				<div class="tabpage" id="tabpage_4">
					<table border="1" style="width: 100%; padding:0">
			<thead style="background-color: black; color:white">
				<tr>
					<th>SOEID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>CL_Available</th>
					<th>SL_Available</th>
					<th>ML_Available</th>
					<th>PML_Available</th>
				</tr>
			</thead>
			<tbody style="background-color: #0099FF; font-weight:bold">
				<s:iterator value="temp">

					<tr>
						<td><s:property value="soeid" /></td>
						<td><s:property value="fname" /></td>
						<td><s:property value="lname" /></td>
						<td><s:property value="cla" /></td>
						<td><s:property value="sla" /></td>
						<td><s:property value="mla" /></td>
						<td><s:property value="pmla" /></td>
					</tr>
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
       min-hieght: 500px;
	margin: auto auto;
	background: linear-gradient(to bottom, rgba(48,48, 48, 1),
		rgba(240,0,0,1));
color: #F0F0F0;
}

#wrapper>h1 {
	text-color: red;
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
		rgba(240,0,0,1));
	border-radius: 5;
	position: relative;
	left: 0%;
	top: 50%;
}

#tabs {
	height: 45;
width:auto;
}

#tabs>ul {
	font: 1em;
	list-style: none;
}

#tabs>ul>li {
	position: relative;
	left: -42;
	top: center;
        width:100;
	margin: 5 2px 0px 0;
	padding: auto auto;
	float: down;
	color: #FF6;
font-weight:bold;
	border-top-left-radius: 10px;
	border-top-right-radius: 0px;
	border-bottom-right-radius: 0px;
	border-bottom-left-radius: 10px;
	background: linear-gradient(to right,  rgba(0,15,56,0),rgba(240,0,0,1));
}

#tabs>ul>li:hover {
	background: linear-gradient(to right,  rgba(0,15,56,0),rgba(70,240,240,1));
	cursor: pointer;
	color: white;
weight:bold;
width:102;
}

#tabs>ul>li.tabActiveHeader {
	background: linear-gradient(to right,  rgba(0,0,0,0),rgba(216,216,216,1));
	cursor: pointer;
	width:105;
	font: bold 15px Arial;
	color: #FFF;
}

#tabscontent {
	border-top-left-radius: 0px;
	border-top-right-radius: 10px;
	border-bottom-right-radius: 10px;
	border-bottom-left-radius: 0px;
	padding: auto;
	background: linear-gradient(to left,  rgba(255,255,255,1),rgba(216,216,216,1)); /* old browsers */
	margin: auto;
min-height:300;
	color: #000;
position: relative;
left:45;
top:-40;
width:88.7%;
}

#tabpage_1> ul {
	font: 1em;
	list-style: none;
}
#tabpage_1> ul>li {
background: linear-gradient(to bottom, rgba(255,255,255,0), rgba(0,0,0,1));
float:left;
color:#FFFFFF;
font-weight:bold;
font-size:20;
position:relative;
left:1%;
margin:0 0 0 100;
padding:5%;
}

table, th, td {
    border: 1px solid yellow;
    border-collapse: collapse;
}
th, td {
    padding: 5px;
}
#tab_open{
position:fixed;
left:0;
}
</style>
</body>
</html>
