<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>SB Account Opening Page</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
	<div class="account-container">
		<%@include file="authheader.jsp" %>
		<c:choose>
			<c:when test="${termdeposit}">
			<div class="well lead">Term Deposit Form</div>
			</c:when>
			<c:otherwise>
			<div class="well lead">SB Account Form</div> 
			</c:otherwise>
		</c:choose>		
	 	<form:form method="POST" modelAttribute="account" class="form-horizontal">
			<%-- <form:input type="hidden" path="id" id="id"/> --%>
			<div>
			<c:choose>
				<c:when test="${error}">
				<span><strong> <font color="Red">Deposit Amount should be greater than Rs.1000</font></strong> </span>
				</c:when>
				<c:when test="${errorfunds}">
				<span><strong> <font color="Red">There is no sufficient funds in your SB Account</font></strong> </span>
				</c:when>				
			</c:choose>
			</div>					
			<c:choose>
			<c:when test="${termdeposit}">			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="maturityAmt">DEPOSIT AMOUNT</label>
					<div class="col-md-7">
						<form:input type="text" path="maturityAmt" id="maturityAmt" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="maturityAmt" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="depoTenure">TERM OF DEPOSIT</label>
					<div class="col-md-7">
						<%-- <form:input type="text" path="depoTenure" id="depoTenure" class="form-control input-sm"/> --%>
						<form:select path="depoTenure" class="form-control input-sm">							
							<form:options items="${depoTenure}" />
						</form:select>
						<div class="has-error">
							<form:errors path="depoTenure" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			</c:when>
			<c:otherwise>
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="accountBalance">DEPOSIT AMOUNT</label>
					<div class="col-md-7">
						<form:input type="text" path="accountBalance" id="accountBalance" class="form-control input-sm"/>
						<div class="has-error">
							<form:errors path="accountBalance" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			</c:otherwise>			
			</c:choose>
			
			<div>
			<c:choose>
				<c:when test="${sbacc}">
				<span>SB Account has been created with account number # <strong>${accId}</strong></span>
				</c:when>
				<c:when test="${termacc}">
				<span><Strong><font color="blue">Term Deposit Account has been created.</font></Strong></span>
				</c:when>				
			</c:choose>
			</div>				
			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${sbacc}">
							<input type="submit" value="Open" class="btn btn-primary btn-sm" disabled /> or <a href="<c:url value='/list' />">Summary</a>
						</c:when>
						<c:when test="${termacc}">
							<input type="submit" value="Open" class="btn btn-primary btn-sm" disabled /> or <a href="<c:url value='/list' />">Summary</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Open" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
		
		<%-- <span class="well floatRight">
			Go to <a href="<c:url value='/list' />">Summary</a>
		</span> --%>
	</div>
</body>

</html>