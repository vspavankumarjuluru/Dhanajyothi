<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Beneficiary Page</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>
<body>
<script type="text/javascript">
function onlyAlphabets(inputId) {

	  var regex = /^[a-zA-Z]*$/;
	  var inputtxt=document.getElementById(inputId)
	  document.getElementById("errMsg"+inputId).innerHTML='';
	  if(inputtxt.value.match(regex)){
	      return true;
	  } else {
		  document.getElementById("errMsg"+inputId).innerHTML="Enter only aplhabets";
		  document.getElementById(inputId).value='';
	      return false;
	  }	  
	}
	
function validateMaxLength(inputId,maxLen){
	document.getElementById("errMsg"+inputId).innerHTML="";
	 var inputtxt=document.getElementById(inputId);
	 alert(inputtxt.value.length);
	 alert(maxLen);
	 if(inputtxt.value.length!=maxLen){
		 alert(true);
			document.getElementById("errMsg"+inputId).innerHTML="Max length should be "+maxLen ;
			 document.getElementById(inputId).value='';
			return false;
		}
	 return true;
}

function validateMaxLengthForAcct(inputId,maxLen){
	document.getElementById("errMsg"+inputId).innerHTML="";
	 var inputtxt=document.getElementById(inputId);
	 if(inputtxt.value.length > maxLen){
			document.getElementById("errMsg"+inputId).innerHTML="Max length should be "+maxLen ;
			 document.getElementById(inputId).value='';
			return false;
		}
	 return true;
}
	
function onlyNumeric(inputId) {

	  var regex = /^[a-zA-Z]*$/;
	  var inputtxt=document.getElementById(inputId)
	  document.getElementById("errMsg"+inputId).innerHTML='';
	  if(!inputtxt.value.match(regex)){
	      return true;
	  } else {
		  document.getElementById("errMsg"+inputId).innerHTML="Enter only Numbers";
		  document.getElementById(inputId).value='';
	      return false;
	  }	  
}
</script>
	<div class="account-container">
		<%@include file="authheader.jsp" %>
		
		<div class="well lead">Add Beneficiary</div> <!--  -->
	 	<form:form method="POST" modelAttribute="beneficiary" class="form-horizontal">
			<%-- <form:input type="hidden" path="id" id="id"/> --%>
			<div>
			<c:choose>
				<c:when test="${error}">
				<span><Strong><font color="blue">${errordesc}</font></Strong></span>
				</c:when>
			</c:choose>
			</div>	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="benNickName">Payee Nick Name</label>
					<div class="col-md-7">
						<form:input type="text" path="benNickName" id="benNickName" maxlength="100" class="form-control input-sm"/>
						<div id="errMsgbenNickName" class="has-error">
							<form:errors path="benNickName" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="benName">Payee Name</label>
					<div class="col-md-7">
						<form:input type="text" path="benName" id="benName" maxlength="100" class="form-control input-sm" onBlur="onlyAlphabets(this.id)"/>
						<div id="errMsgbenName" class="has-error">
							<form:errors path="benName" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="benAccNumber">Payee Account Number</label>
					<div class="col-md-7">
						<form:input type="number" path="benAccNumber" id="benAccNumber" maxlength="13" class="form-control input-sm" onBlur="onlyNumeric(this.id);validateMaxLengthForAcct(this.id,'13');"/>
						<div id="errMsgbenAccNumber" class="has-error">
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
						<form:input type="text" path="benBank" id="benBank" maxlength="100" class="form-control input-sm" onBlur="onlyAlphabets(this.id)"/>
						<div id="errMsgbenBank" class="has-error">
							<form:errors path="benBank" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="benBankIfsc">IFSC Code</label>
					<div class="col-md-7">
						<form:input type="text" path="benBankIfsc" id="benBankIfsc" maxlength="11" onBlur="validateMaxLength(this.id,'11')" class="form-control input-sm"/>
						<div id="errMsgbenBankIfsc" class="has-error">
							<form:errors path="benBankIfsc" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			</c:when>
			</c:choose> 		
			
			
			<div>
			<c:choose>
				<c:when test="${benefAdd}">
				<span><Strong><font color="blue">Your beneficiary account has been saved.</font></Strong></span>
				</c:when>
			</c:choose>
			</div>				
			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${benefAdd}">
							<input type="submit" value="Save" class="btn btn-primary btn-sm" disabled /> or <a href="<c:url value='/list' />">Summary</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="save" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/list' />">Cancel</a>
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