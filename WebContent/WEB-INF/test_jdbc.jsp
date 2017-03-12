<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Tests JDBC</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/inc/form.css"/>" />
<!-- BOOTSTRAP CORE STYLE  -->
<link href="assets/css/bootstrap.css" rel="stylesheet" />
<!-- FONT AWESOME STYLE  -->
<link href="assets/css/font-awesome.css" rel="stylesheet" />
<!-- ANIMATE STYLE  -->
<link href="assets/css/animate.css" rel="stylesheet" />
<!-- FLEXSLIDER STYLE  -->
<link href="assets/css/flexslider.css" rel="stylesheet" />
<!-- CUSTOM STYLE  -->
<link href="assets/css/style.css" rel="stylesheet" />
</head>
<body>
	<h1>Tests JDBC</h1>

	<c:forEach items="${ messages }" var="message" varStatus="boucle">
		<p>${ boucle.count }.${ message }</p>
	</c:forEach>
</body>
</html>