<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE html 
     PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN"
     "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">

<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	<link rel="stylesheet" href="<c:url value="resources/styles/bootstrap/3.3.5/css/bootstrap.min.css" />" />
    <link rel="stylesheet" href="<c:url value="resources/styles/bootstrap/3.3.5/css/bootstrap-theme.min.css" />" />
    <link rel="stylesheet" href="<c:url value="resources/styles/pivotal.css" />" />
    
	<title>spring-microservices: Home</title>
</head>
<script>
function callhyponatemia()
{
	
	var sermSodium=document.getElementById("serumSodum").value;
	alert(sermSodium);
	 $.ajax({
		  url: "/hyponatemia",
		  type: "get", //send it through get method
		  data: { 
			  sermSodium: sermSodium		    
		  },
		  success: function(response) {
			  alert("success"+response);
		    //Do Something
		  },
		  error: function(xhr) {
		    //Do Something to handle error
		    alert("failure");
		  }
		}); 
	/*  $.post("/hyponatemia",
			    {
		 sermSodium: sermSodium
			    },
			    function(data, status){
			        alert("Data: " + data + "\nStatus: " + status);
			    }); */
	
	 /* $.get( "/hyponatemia", { sermSodium: sermSodium } )
	  .done(function( data ) {
	    alert( "Data Loaded: " + data );
	  }); */
	}
</script>
<body>

	<div class="container">
		<div class="row">
			<nav class="navbar navbar-inverse">
				<div class="container-fluid">
					<div class="navbar-header">
						<a title="Spring IO" href="http://www.spring.io"> 
							<img src="<c:url value="resources/images/spring-trans-dark.png"/>" height="50"/>
						</a>
					</div>
					<div>
						<ul class="nav navbar-nav navbar-right">
							<li>
								<a href="http://www.pivotal.io">
									<img alt="Pivotal" title="Pivotal" height="20" src="<c:url value="resources/images/pivotal-logo-600.png" />" />
								</a>
							</li>
						</ul>
					</div>
				</div>
			</nav>
		</div>
		
		<div class="row">
		<label>Please enter serumSodium value: </label>
		<input type="text" id="serumSodum"/>
		<input type="button" value="submit" onclick="callhyponatemia()"/>
			
			<!-- <h1>Accounts Web - Home Page</h1>

			<ul>
				<li><a href="/accountList">View Account List</a></li>
			</ul> -->
			
		</div>
		
	</div>
		
</body>
</html>