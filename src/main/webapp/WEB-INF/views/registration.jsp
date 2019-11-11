<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>User Registration Form</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
	<link rel="stylesheet" href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</head>

<body>
<script>
$( function() {
    $.datepicker.setDefaults({
        onClose:function(date, inst){
            $("#selectedDtaeVal").html(date);
        }
    });

    $( "#dob" ).datepicker({ dateFormat: 'dd/mm/yy' });
});

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
	
function checkDate(inputId) {
	document.getElementById("errMsg"+inputId).innerHTML="";
	date = document.getElementById(inputId).value;
    if (date == "") {
    	  document.getElementById("errMsg"+inputId).innerHTML="Please enter the DOB";
    	  document.getElementById(inputId).value='';
        return false;
    }
    else if (!date.match(/^(0[1-9]|[12][0-9]|3[01])[\- \/.](?:(0[1-9]|1[012])[\- \/.](19|20)[0-9]{2})$/)) {
    	document.getElementById("errMsg"+inputId).innerHTML='date format is wrong';
    	 document.getElementById(inputId).value='';
        return false;
    }
    
    var edate = date.split("/");
    var spdate = new Date();
    var sdd = spdate.getDate();
    var smm = spdate.getMonth();
    var syyyy = spdate.getFullYear();
    var today = new Date(syyyy,smm,sdd).getTime();
    var e_date = new Date(edate[2],edate[1],edate[0]).getTime();
    if(e_date > today)
     {
    	document.getElementById("errMsg"+inputId).innerHTML='Current or future date is not allowed';
   	 	document.getElementById(inputId).value='';
        return false;
     }
    
    return true;
    
}

function ValidateEmail(inputId) 
{
	document.getElementById("errMsg"+inputId).innerHTML="";
	 var inputtxt=document.getElementById(inputId)
 	if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(inputtxt.value))
  	{
    	return true;
  	}else{
  		document.getElementById("errMsg"+inputId).innerHTML="You have entered an invalid email address!";
  		 document.getElementById(inputId).value='';
  		return false;
  	}
}

function validateNumeric(inputId,maxLen) 
{
	document.getElementById("errMsg"+inputId).innerHTML="";
	 var inputtxt=document.getElementById(inputId)
	 var numbers = /^[0-9]+$/;
      if(!inputtxt.value.match(numbers)){
  		document.getElementById("errMsg"+inputId).innerHTML="Enter Numeric value only";	
  		 document.getElementById(inputId).value='';
  		return false;
  	}else{
  		if(inputtxt.value.length!=maxLen){
  			document.getElementById("errMsg"+inputId).innerHTML="Max length should be "+maxLen ;
  			 document.getElementById(inputId).value='';
  			return false;
  		}else{
  		return true;
  		}
  	}
}
function validateMaxLength(inputId,maxLen){
	document.getElementById("errMsg"+inputId).innerHTML="";
	 var inputtxt=document.getElementById(inputId)
	 if(inputtxt.value.length!=maxLen){
			document.getElementById("errMsg"+inputId).innerHTML="Max length should be "+maxLen ;
			 document.getElementById(inputId).value='';
			return false;
		}else{
		return true;
		}
}
function validateUserName(inputId,minLen,maxLen){
	document.getElementById("errMsg"+inputId).innerHTML="";
	 var inputtxt=document.getElementById(inputId)
	 if(inputtxt.value==""){
		 document.getElementById("errMsg"+inputId).innerHTML="Please enter the User Name ";
		 return false;
	 }else if(inputtxt.value.length < minLen){
			document.getElementById("errMsg"+inputId).innerHTML="The username must be between "+ minLen +" and "+maxLen ;
			 document.getElementById(inputId).value='';
			return false;
		}else{
		return true;
		}
}

function validatePassword(inputId){
	document.getElementById("errMsg"+inputId).innerHTML="";
	 var inputtxt=document.getElementById(inputId);
	 if (inputtxt.match(/[a-z]/g) && inputtxt.match(/[A-Z]/g) && inputtxt.match(/[0-9]/g) && inputtxt.match(/[^a-zA-Z\d]/g) && inputtxt.length >= 8) {
		 return true;
	 }else{
 		document.getElementById("errMsg"+inputId).innerHTML="Should contain atleast one capital letter, one number and one special character";
 		 document.getElementById(inputId).value='';
 		return false;
 	}
	 return true;
}

function checkFileSelected(inputid, dobid, addid, aadharid){
	
	var panupload = document.getElementById(inputid).files.length;
	var dobupload = document.getElementById(dobid).files.length;
	var addupload = document.getElementById(addid).files.length;
	var aadharupload = document.getElementById(aadharid).files.length;
	
	if(panupload ==0 && dobupload ==0 && addupload==0 && aadharupload==0){
		document.getElementById("errMsg"+inputid).innerHTML="Please upload all the documents.";
		document.getElementById(inputid).value='';
		return false;
	} else if(panupload ==0 || dobupload ==0 || addupload==0 || aadharupload==0){
		document.getElementById("errMsg"+inputId).innerHTML="Please upload all the documents.";
		document.getElementById(inputid).value='';
		return false;
	}
	
	return true;
	
}
	 
</script>
 	<div class="register-container">
		<%-- <%@include file="authheader.jsp" %> --%>

		<div class="well lead">Customer Registration Form</div>
	 	<form:form method="POST" modelAttribute="users" class="form-horizontal" enctype="multipart/form-data">
			<form:input type="hidden" path="id" id="id"/>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="firstName">First Name <span style="color:red;">*</span></label>
					<div class="col-md-7">
						<form:input type="text" path="firstName" id="firstName" maxlength="100" onFocus="onlyAlphabets(this.id)" class="form-control input-sm" />
						<div id="errMsgfirstName"class="has-error">
							<form:errors path="firstName" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="lastName">Last Name <span style="color:red;">*</span></label>
					<div class="col-md-7">
						<form:input type="text" path="lastName" id="lastName" maxlength="100" onBlur="onlyAlphabets(this.id)" class="form-control input-sm" />
						<div id="errMsglastName" class="has-error">
							<form:errors path="lastName" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="dob">DOB <span style="color:red;">*</span></label>
					<div class="col-md-7">
						<form:input type="text" id="dob" path="dob" class= "form-control date" name = "dob" onChange="checkDate(this.id)"/>
						<div id="errMsgdob" class="has-error">
							<form:errors path="dob" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
	
			<%-- <div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="ssoId">SSO ID</label>
					<div class="col-md-7">
						<c:choose>
							<c:when test="${edit}">
								<form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" disabled="true"/>
							</c:when>
							<c:otherwise>
								<form:input type="text" path="ssoId" id="ssoId" class="form-control input-sm" />
								<div class="has-error">
									<form:errors path="ssoId" class="help-inline"/>
								</div>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div> --%>			
	
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="email">Email <span style="color:red;">*</span></label>
					<div class="col-md-7">
						<form:input type="text" path="email" id="email" maxlength="50" onBlur="ValidateEmail(this.id)" class="form-control input-sm" />
						<div id="errMsgemail" class="has-error">
							<form:errors path="email" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="addline1">Address Line 1 <span style="color:red;">*</span></label>
					<div class="col-md-7">
						<form:input type="text" path="addline1" id="addline1" maxlength="200" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="addline1" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="addline2">Address Line 2</label>
					<div class="col-md-7">
						<form:input type="text" path="addline2" id="addline2" maxlength="200" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="addline1" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="city">City <span style="color:red;">*</span></label>
					<div class="col-md-7">
						<form:input type="text" path="city" id="city" maxlength="50" onBlur="onlyAlphabets(this.id)" class="form-control input-sm" />
						<div id="errMsgcity" class="has-error">
							<form:errors path="city" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="state">State <span style="color:red;">*</span></label>
					<div class="col-md-7">
						<form:input type="text" path="state" id="state" maxlength="50" onBlur="onlyAlphabets(this.id)"  class="form-control input-sm" />
						<div id="errMsgstate" class="has-error">
							<form:errors path="state" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="pin">PIN <span style="color:red;">*</span></label>
					<div class="col-md-7">
						<form:input type="text" path="pin" id="pin" maxlength="6" onBlur="validateNumeric(this.id,'6')" class="form-control input-sm" />
						<div id="errMsgpin" class="has-error">
							<form:errors path="state" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="mobileNumber">Mobile Number <span style="color:red;">*</span></label>
					<div class="col-md-7">
						<form:input type="text" path="mobileNumber" id="mobileNumber" maxlength="10" onBlur="validateNumeric(this.id,'10')" class="form-control input-sm" />
						<div  id="errMsgmobileNumber" class="has-error">
							<form:errors path="mobileNumber" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="aadharId">Aadhar</label>
					<div class="col-md-7">
						<form:input type="text" path="aadharId" id="aadharId" maxlength="16" onBlur="validateNumeric(this.id,'16')" class="form-control input-sm" />
						<div id="errMsgaadharId" class="has-error">
							<form:errors path="aadharId" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="pan">Pan</label>
					<div class="col-md-7">
						<form:input type="text" path="pan" id="pan" maxlength="10" onBlur="validateMaxLength(this.id,'10')" class="form-control input-sm" />
						<div id="errMsgpan"class="has-error">
							<form:errors path="pan" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="username">Login User Name <span style="color:red;">*</span></label>
					<div class="col-md-7">
						<form:input type="text" path="username" id="username" onBlur="validateUserName(this.id,'5','15')"  class="form-control input-sm" />
						<div id="errMsgusername" class="has-error">
							<form:errors path="pan" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="password">Password <span style="color:red;">*</span></label>
					<div class="col-md-7">
						<form:input type="password" path="password" id="password" onBlur="validatePassword(this.id)" class="form-control input-sm" />
						<div id="errMsgpassword"class="has-error">
							<form:errors path="password" class="help-inline"/>
						</div>
					</div>
				</div>
			</div>
			
			<div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" >Supporting Documents <span style="color:red;">*</span></label>
					<div class="col-md-7">
						<table>
						<tbody>
						<tr><label class="col-md-3 control-lable" >Date Of Birth</label> <input type="file" id="dobf" name="files" class="form-control input-sm" accept="image/jpeg,image/gif,image/png,application/pdf,image/x-eps"/><br/></tr>						
						<tr><label class="col-md-3 control-lable" >Proof of Address</label> <input type="file" id="addf" name="files" class="form-control input-sm" accept="image/jpeg,image/gif,image/png,application/pdf,image/x-eps" /><br/></tr>						
						<tr><label class="col-md-3 control-lable" >Aadhar</label> <input type="file" id="aadharf" name="files" class="form-control input-sm" accept="image/jpeg,image/gif,image/png,application/pdf,image/x-eps"/><br/></tr>						
						<tr><label class="col-md-3 control-lable" >PAN</label> <input type="file" name="files" id="panf" class="form-control input-sm" accept="image/jpeg,image/gif,image/png,application/pdf,image/x-eps" /><br/></tr>						
						<div id="errMsgpanf"class="has-error" />
						</tbody>						
						</table>
						<div>
							<c:choose>
								<c:when test="${error}">
									<span><strong> <font color="Red">Please	upload all required documents.</font></strong> </span>
								</c:when>
							</c:choose>
						</div>
					</div>
				</div>
			</div>
	
			<%-- <div class="row">
				<div class="form-group col-md-12">
					<label class="col-md-3 control-lable" for="userProfiles">Roles</label>
					<div class="col-md-7">
						<form:select path="userProfiles" items="${roles}" multiple="true" itemValue="id" itemLabel="type" class="form-control input-sm" />
						<div class="has-error">
							<form:errors path="userProfiles" class="help-inline"/>
						</div>
					</div>
				</div>
			</div> --%>
	
			<div class="row">
				<div class="form-actions floatRight">
					<c:choose>
						<c:when test="${edit}">
							<input type="submit" value="Update" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/login' />">Cancel</a>
						</c:when>
						<c:otherwise>
							<input type="submit" value="Register" class="btn btn-primary btn-sm"/> or <a href="<c:url value='/login' />">Cancel</a>
						</c:otherwise>
					</c:choose>
				</div>
			</div>
		</form:form>
	</div>
</body>
</html>