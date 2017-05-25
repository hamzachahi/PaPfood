<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="liens.jsp"%>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Toutes les factures</title>
</head>
<body class="home1">
	<%@include file="header.jsp"%>

	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="restaurent-menu-title">
					<h2 class="primery-title">Espace Administrateur</h2>
					<h3 class="secondery-title">Toutes les factures</h3>
				</div>
			</div>
		</div>
	</div>


	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="page-inner padding-top-xlg">
					<div class="booking-form" align="center">
						<div class="table-responsive">

							<table class="table table-hover">
								<thead>
									<tr>
										<th>ID facture</th>
										<th>Code facture</th>
										<th>Au nom de</th>
										<th>Créee le</th>
										<th>Prix total</th>
										<th>Adresse</th>
									</tr>
								</thead>

								<tbody>
									<c:forEach var="invoice" items="${requestScope['allInvoices']}">
										<tr>
											<td><a href="facture?id=${invoice.id }">${invoice.id }</a></td>
											<td>${invoice.codeInvoice }</td>
											<td>${invoice.personName }</td>
											<td>${invoice.creationDate }</td>
											<td>${invoice.totalPrice }&euro;</td>
											<td>${invoice.personAddress }</td>
										</tr>
									</c:forEach>

								</tbody>

							</table>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="footer.jsp"%>
</body>
</html>