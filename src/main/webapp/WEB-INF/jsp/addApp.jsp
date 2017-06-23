<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.3/jquery.min.js"></script>
<script
	src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.4/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<script src="js/jquery-1.11.1.js" type="text/javascript"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<script>
$(document).ready(function() {

$('#department').change(function(event) {
	  
        var department = $("select#department").val();
        $.get('addApp.html', {
                department : department
        }, function(response) {
        console.log("hi");
        var select = $('#player');
        select.find('option').remove();
          $.each(response, function(index, value) {
        	 //console.log(value.id);
        	 console.log(value);
          $('<option>').val(value).text(value).appendTo(select);
      });
        });
        });
});
</script>

<style type="text/css">
body {
	font-family: 'Open Sans', sans-serif;
}

.popup-box-on {
	display: block !important;
}

.off {
	display: none;
}

.chat_box .chat_message_wrapper ul.chat_message>li+li {
	margin-top: 4px;
}

#search.open {
	opacity: 1;
	transform: translate(0px, 0px) scale(1, 1);
}

#search {
	background-color: #fff;
	height: 100%;
	left: 0;
	position: fixed;
	top: 0;
	transition: all 0.5s ease-in-out 0s;
	width: 100%;
	z-index: 2000;
}

#search .close {
	color: #be5254;
	font-size: 40px;
	opacity: 1;
	padding: 10px 17px;
	position: fixed;
	right: 15px;
	top: 15px;
}

button.close {
	background: transparent none repeat scroll 0 0;
	border: 0 none;
	cursor: pointer;
}

.close {
	float: right;
	font-weight: bold;
	line-height: 1;
	text-shadow: 0 1px 0 #fff;
}

#search input[type="text"] {
	background: rgba(0, 0, 0, 0) none repeat scroll 0 0;
	border: 0 none;
	color: #333;
	font-family: "Open Sans", sans-serif;
	font-size: 50px;
	font-weight: 300;
	margin: -51px auto 0;
	outline: medium none;
	padding-left: 30px;
	padding-right: 30px;
	position: absolute;
	text-align: center;
	top: 50%;
	width: 100%;
}

#search .btn {
	left: 50%;
	margin-top: 60px;
	padding: 10px 50px;
	position: absolute;
	top: 50%;
	transform: translateX(-50%);
}

nav {
	background: #be5254 none repeat scroll 0 0 !important;
	border: medium none !important;
	border-radius: 151px !important;
}

.s-icon a {
	background: #fff none repeat scroll 0 0 !important;
	border-radius: 40px !important;
	color: #be5254 !important;
	font-size: 26px;
	height: 44px;
	margin: 3px -12px 0 0;
	padding: 7px 0 0 !important;
	text-align: center;
	width: 44px;
}

.navbar-inverse .navbar-nav>li>a {
	color: #fff;
}

.navbar-inverse .navbar-brand {
	background: #fff none repeat scroll 0 0 !important;
	border-radius: 50px;
	color: #be5254 !important;
	height: auto;
	margin: 3px 0 2px -12px !important;
	padding: 12px 20px !important;
}

.btn-lg, .btn-group-lg>.btn {
	border-radius: 153px;
	font-size: 29px;
}

.btn-site {
	background: #be5254 none repeat scroll 0 0;
	color: #fff !important;
}
}
</style>

<nav class="navbar navbar-inverse">
<div class="container">
	<!-- Brand and toggle get grouped for better mobile display -->
	<div class="navbar-header">
		<h3 style="color: White">
			<center>Add Application</center>
		</h3>

	</div>
</div>
</nav>
</head>
<body>
<form:form modelAttribute="application">
<table class="table table-striped">
       <tr>
       <td> Select Favorite Sports: </td>
       <td>
        <select id="department" name="dep">
                <option>Select Departments</option>
                <c:forEach items="${departments}" var="d">
                <option value="${d.id}">${d.name}</option>
                </c:forEach>
                
        </select>
        </td>
        </tr>
        <tr>
        <td> 
        Select Favorite Player: </td>
        
        <td><select id="player" name="prog">
                <option>Select Player</option>
               
        </select>
        </td></tr>
        <tr>
        <td>
        Enter Term :  </td>
        <td>
        <input type="text" name="term" />
        </td></tr>
        
        </br>
        </br>
        </table>
        <input type="submit" name="save" value="Next" class="btn btn-lg btn-default btn-block"/>
        
        </form:form>
</body>
</html>