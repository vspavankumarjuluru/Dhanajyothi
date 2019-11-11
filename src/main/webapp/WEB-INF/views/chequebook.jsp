<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Request ChequeBook Page</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript">

function loadCharge() {
	
	 var data = $( "#pageid option:selected" ).val();	 
		 var charge = "0";
		 if(data.indexOf("20") != -1){
			 charge="100";
		 } else if (data.indexOf("50") != -1) {
			 charge="200";
		 } else if (data.indexOf("100") != -1) {
			 charge="300";
		 }
		 document.getElementById("charge").value=charge; 

}
</script>
	<div class="account-container">
		<%@include file="authheader.jsp" %>
		
		<div class="well lead">Request ChequeBook Form</div> <!--  -->
	 	<form:form method="POST" modelAttribute="servicerequest" class="form-horizontal">
			<%-- <form:input type="hidden" path="id" id="id"/> --%>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="page">No Of Pages</label>
					<div class="col-md-7">
						<form:select path="page" id="pageid" onChange="loadCharge();" class="form-control input-sm">
							<form:option value="NONE" label="--- Select ---" />
							<form:options items="${pages}" />
						</form:select>						
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="charge">Charges</label>
					<div class="col-md-7">
					<form:input type="text" path="charge" id="charge" class="form-control input-sm" readonly="true"/>
						<%-- <form:select path="charge" class="form-control input-sm">
							<form:option value="NONE" label="--- Select ---" />
							<form:options items="${charges}" />
						</form:select>	 --%>					
					</div>
				</div>
			</div> 		
			<div>
			<c:choose>
				<c:when test="${req}">
				<span><strong> <font color="blue">Your Request has been submitted and chequebook will be despatched within 7 working days.</font></strong></span>
				</c:when>
			</c:choose>
			</div>				
			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${req}">
							<input type="submit" value="Submit" class="btn btn-primary btn-sm" disabled /> or <a href="<c:url value='/list' />">Summary</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Submit" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list' />">Cancel</a>
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