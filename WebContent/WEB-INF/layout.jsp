<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
<script src="assets/js/jquery.min.js"></script>
<!-- Custom Theme files -->
<!--theme-style-->
<link href="assets/css/style.css" rel="stylesheet" type="text/css" media="all" />
<!--//theme-style-->
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta name="keywords"
	content="Youth Fashion Responsive web template, Bootstrap Web Templates, Flat Web Templates, Android Compatible web template, 
Smartphone Compatible web template, free webdesigns for Nokia, Samsung, LG, SonyEricsson, Motorola web design" />
<script type="application/x-javascript">
	 addEventListener("load", function() { setTimeout(hideURLbar, 0); }, false); function hideURLbar(){ window.scrollTo(0,1); } 
</script>
<link href='//fonts.googleapis.com/css?family=Raleway' rel='stylesheet'
	type='text/css'>
<link href='//fonts.googleapis.com/css?family=Poiret+One'
	rel='stylesheet' type='text/css'>
<!-- start menu -->
<script src="assets/js/bootstrap.min.js"></script>
<script src="assets/js/simpleCart.min.js">
	
</script>
<!-- slide -->
<script src="assets/js/responsiveslides.min.js"></script>
<script>
	$(function() {
		$("#slider").responsiveSlides({
			auto : false,
			speed : 500,
			namespace : "callbacks",
			pager : true,
		});
	});
</script>
<!-- animation-effect -->
<link href="assets/css/animate.min.css" rel="stylesheet">
<script src="assets/js/wow.min.js"></script>
<script>
	new WOW().init();
</script>
<!-- //animation-effect -->
</head>
<body>
	<%@ include file="/WEB-INF/header.jsp" %>


	<h1>${param.title}</h1>

	<!--  <%@ include file="/WEB-INF/${param.content}.jsp" %>  -->

	<%@ include file="/WEB-INF/footer.jsp" %>


</body>
</html>