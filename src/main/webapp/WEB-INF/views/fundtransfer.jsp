<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Fund Transfer Page</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.2/jquery.min.js"></script>
</head>
<body>
<script type="text/javascript">
function loadbeneficiaryDetails(elementId) {
	 var logge = '<c:out value="${dataMap}"/>';
	 var arr = logge.split(",");
	 var data = $( "#bennickid option:selected" ).val();	 
	 for(var i=0;i<arr.length;i++){
		 
		if(arr[i].indexOf(data+"=") != -1){
			var secSplit = arr[i].split("=");
			var str = secSplit[1].toString().replace("}", "");;
			var datastr = str.split("|");	
			document.getElementById("benName").value=datastr[0];
			document.getElementById("benAccNumber").value=datastr[1];
			document.getElementById("benBank").value=datastr[2];
			document.getElementById("benBankIfsc").value=datastr[3];
		}
		 
	 }

}
</script>
<sec:csrfMetaTags />
	<div class="fundtransfer-container">
		<%@include file="authheader.jsp" %>		
		<!-- <div class="well lead">SB Account Form</div>  -->
	 	<form:form method="POST" modelAttribute="beneficiarytrans" class="form-horizontal">
			<input type="hidden" id="id" value="${loggedinuser}"/>
			<input type="hidden" id="bank" value="${bank}"/>			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="benNickName">Payee Nick Name</label>
					<div class="col-md-7">
						<%-- <form:input type="text" path="benNickName" id="benNickName" class="form-control input-sm"/> --%>
						<form:select path="benNickName" class="form-control input-sm" id="bennickid" onChange="loadbeneficiaryDetails();">
							<form:option value="NONE" label="Select" />
							<form:options items="${bennicknames}" />
						</form:select><div class="beneficiary float-right"><a href="<c:url value='/beneficiary-${loggedinuser}/bank/${bank}' />">Add Beneficiary</a></div>												
						<div id="errorbennickid" class="has-error">
							<form:errors path="benNickName" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="benName">Payee Name</label>
					<div class="col-md-7">
						<form:input type="text" path="benName" id="benName" maxlength="100" class="form-control input-sm" readonly="true"/>
						<div id="errorbenName" class="has-error">
							<form:errors path="benName" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="benAccNumber">Payee Account Number</label>
					<div class="col-md-7">
						<form:input type="text" path="benAccNumber" id="benAccNumber" maxlength="13" class="form-control input-sm"  readonly="true"/>
						<div id="errorbenAccNumber" class="has-error">
							<form:errors path="benAccNumber" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<c:choose>
				<c:when test="${isext}">
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="benBank">Bank</label>
					<div class="col-md-7">
						<form:input type="text" path="benBank" id="benBank" class="form-control input-sm"  readonly="true"/>
						<div id="errorbenBank" class="has-error">
							<form:errors path="benBank" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="benBankIfsc">IFSC Code</label>
					<div class="col-md-7">
						<form:input type="text" path="benBankIfsc" id="benBankIfsc" class="form-control input-sm"  readonly="true"/>
						<div id="errorbenBankIfsc" class="has-error">
							<form:errors path="benBankIfsc" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			</c:when>
			</c:choose>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="transamt">Transfer Amount</label>
					<div class="col-md-7">
						<form:input type="number" path="transamt" min="0" id="transamt" class="form-control input-sm"/>
						<div id="errortransamt" class="has-error">
							<form:errors path="transamt" class="help-inline"/>
						</div>
					</div>
				</div>
			</div> 
						
			<div>
			<c:choose>
				<c:when test="${trans}">
				<span><Strong><font color="green">Your amount has been transfered successfully.</font></Strong></span>
				</c:when>
				<c:when test="${error}">
				<span><Strong><font color="red">${errordesc}</font></Strong></span>
				</c:when>
			</c:choose>
			</div>				
			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${trans}">
							<input type="submit" value="Transfer" class="btn btn-primary btn-sm" disabled /> or <a href="<c:url value='/list' />">Summary</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Transfer" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list' />">Cancel</a>
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