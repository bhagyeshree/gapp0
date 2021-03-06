<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
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
		
<title>Login</title>

<style type="text/css">
body {
  background: url('http://i.imgur.com/Au8To6H.jpg') fixed;
  background-size: cover;
  padding: 0;
  margin: 0;
}

.wrap {
  width: 100%;
  height: 100%;
  min-height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 99;
}

p.form-title {
  font-family: 'Open Sans' , sans-serif;
  font-size: 20px;
  font-weight: 600;
  text-align: center;
  color: #FFFFFF;
  margin-top: 5%;
  text-transform: uppercase;
  letter-spacing: 4px;
}

form {
  width: 250px;
  margin: 0 auto;
}

form.login input[type="text"], form.login input[type="password"] {
  width: 100%;
  margin: 0;
  padding: 5px 10px;
  background: 0;
  border: 0;
  border-bottom: 1px solid #FFFFFF;
  outline: 0;
  font-style: italic;
  font-size: 12px;
  font-weight: 400;
  letter-spacing: 1px;
  margin-bottom: 5px;
  color: #FFFFFF;
  outline: 0;
}

form.login input[type="submit"] {
  width: 100%;
  font-size: 14px;
  text-transform: uppercase;
  font-weight: 500;
  margin-top: 16px;
  outline: 0;
  cursor: pointer;
  letter-spacing: 1px;
}

form.login input[type="submit"]:hover {
  transition: background-color 0.5s ease;
}

form.login .remember-forgot {
  float: left;
  width: 100%;
  margin: 10px 0 0 0;
}

form.login .forgot-pass-content {
  min-height: 20px;
  margin-top: 10px;
  margin-bottom: 10px;
}

form.login label, form.login a {
  font-size: 12px;
  font-weight: 400;
  color: #FFFFFF;
}

form.login a {
  transition: color 0.5s ease;
}

form.login a:hover {
  color: #2ecc71;
}

.pr-wrap {
  width: 100%;
  height: 100%;
  min-height: 100%;
  position: absolute;
  top: 0;
  left: 0;
  z-index: 999;
  display: none;
}

.show-pass-reset {
  display: block !important;
}

.pass-reset {
  margin: 0 auto;
  width: 250px;
  position: relative;
  margin-top: 22%;
  z-index: 999;
  background: #FFFFFF;
  padding: 20px 15px;
}

.pass-reset label {
  font-size: 12px;
  font-weight: 400;
  margin-bottom: 15px;
}

.pass-reset input[type="email"] {
  width: 100%;
  margin: 5px 0 0 0;
  padding: 5px 10px;
  background: 0;
  border: 0;
  border-bottom: 1px solid #000000;
  outline: 0;
  font-style: italic;
  font-size: 12px;
  font-weight: 400;
  letter-spacing: 1px;
  margin-bottom: 5px;
  color: #000000;
  outline: 0;
}

.pass-reset input[type="submit"] {
  width: 100%;
  border: 0;
  font-size: 14px;
  text-transform: uppercase;
  font-weight: 500;
  margin-top: 10px;
  outline: 0;
  cursor: pointer;
  letter-spacing: 1px;
}

.pass-reset input[type="submit"]:hover {
  transition: background-color 0.5s ease;
}

.posted-by {
  position: absolute;
  bottom: 26px;
  margin: 0 auto;
  color: #FFF;
  background-color: rgba(0, 0, 0, 0.66);
  padding: 10px;
  left: 45%;
}
 
</style> 



</head>


<div class="wrap">
                <p class="form-title">Sign In</p>


<body>

<div class="container">

	<form:form modelAttribute="user">
	
	
		
		<div class="form-group">
		
			 <input class="form-control" type="text" name="email" placeholder = "Username"/>
		</div>		
		<div class="form-group">
		 	 <input class="form-control" type="password" name="password" placeholder = "Password"/>
		</div>	
		
		<input class="btn btn-lg btn-default btn-block" type="submit" name="register" value="Login">
		
		
	</form:form>
</body>
</html>
