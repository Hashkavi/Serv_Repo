<%@page import="com.service"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Service Management</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.6.0.min.js"></script>
<script src="Components/service.js"></script>
</head>
<body> 
<div class="container"><div class="row"><div class="col-6"> 
<h1>Items Management V10.1</h1>
<form id="formservice" name="formservice" method="post" action="service.jsp">
 Service code: 
 <input id="serviceCode" name="serviceCode" type="text" 
 class="form-control form-control-sm">
 <br> Topic: 
 <input id="topic" name="topic" type="text" 
 class="form-control form-control-sm">
 <br> Description: 
 <input id="decription" name="decription" type="text" 
 class="form-control form-control-sm">
 <br> Date: 
 <input id="date" name="date" type="text" 
 class="form-control form-control-sm">
 <br>
 <input id="btnSave" name="btnSave" type="button" value="Save" 
 class="btn btn-primary">
 
</form>
<div id="alertSuccess" class="alert alert-success"></div>
<div id="alertError" class="alert alert-danger"></div>
<br>
<div id="divserviceGrid">
 <%
 service itemObj = new service(); 
 out.print(itemObj.readService()); 
 %>
</div>
</div> </div> </div> 
</body>
</html>