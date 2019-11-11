<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags"%>

<html>

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Users List</title>
	<link href="<c:url value='/static/css/bootstrap.css' />" rel="stylesheet"></link>
	<link href="<c:url value='/static/css/app.css' />" rel="stylesheet"></link>
</head>

<body>
<script type="text/javascript">
function enable(elementId) { 
 document.getElementById(elementId).style.display="block";
}
</script>
	<div class="generic-container">
		<%@include file="authheader.jsp" %>	
		<div class="panel panel-default">
			  <!-- Default panel contents -->
		  	<div class="panel-heading"><span class="lead">Menu </span></div>
			<table class="table table-hover">	    		
	    		<tbody>
	    		 <sec:authorize access="hasRole('USER')">
	    		<td><div class="dropdown"><a> Open new account</a>
	    		<div class="dropdown-content">
	    			<c:choose>
	    			<c:when test="${isAccountCreated}">
	    					<a href="#" />SB account
	    					<a href="<c:url value='/openTermAcc-${loggedinuser}' />">Term Deposit account</a> 
	    			</c:when>
	    			<c:otherwise>
	    			<a href="<c:url value='/openSBAcc-${loggedinuser}' />">SB account</a>
	    			<a href="#" />Term Deposit account
	    			</c:otherwise>
	    			</c:choose>		      		
	    		   	  		
				</div></div></td>
	    		<td><a href="<c:url value='/accBalance-${loggedinuser}' />"/>Check account balance</td>
	    		<td><div class="dropdown"><a href="#"> Funds transfer </a>
	    		<div class="dropdown-content">		
		      		<a href="<c:url value='/TransWithOwn-${loggedinuser}/bank/I' />">To Own Banks</a>
	    		   	<a href="<c:url value='/TransWithOwn-${loggedinuser}/bank/E' />">To External Banks</a>   		
				</div></div></td>
	    		<td><a href="<c:url value='/req-${loggedinuser}'/>"/>Request Chequebook</td>
	    		<td><a href="<c:url value='/viewstatements-${loggedinuser}' />"/> View monthly transaction statements</td>
	    		 </sec:authorize>
	    		 <sec:authorize access="hasRole('ADMIN')">
	    		<td><a href="#" onClick="enable('id1');"/> Approve User Registrations</td>
	    		</sec:authorize>
	    		</tbody>
	    	</table>
		</div>
		
		<div class="panel panel-default" id="id1"  style="display:none">
		<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Firstname</th>
				        <th>Lastname</th>
				        <th>Email</th>
				        <th>Request Id</th>
				        <th>Documents</th>
				        <sec:authorize access="hasRole('ADMIN') or hasRole('DBA')">
				        	<th width="100"></th>
				        </sec:authorize>
				        <sec:authorize access="hasRole('ADMIN')">
				        	<th width="100"></th>
				        </sec:authorize>
				        
					</tr> 
		    	</thead>
	    		<tbody>
				<c:forEach items="${users}" var="user">
					<tr>
						<td>${user.firstName}</td>
						<td>${user.lastName}</td>
						<td>${user.email}</td>
						<td>${user.id}</td>
						<td>
						<c:forEach items="${user.kycDocList}" var="kyc">
							<a href="<c:url value='/download-${kyc.id}'/>">${kyc.docDesc}</a></br>					
						</c:forEach>
						</td>
					    <sec:authorize access="hasRole('ADMIN')">					    						    	
					    	<td><a href="<c:url value='/app-user-${user.id}' />" class="btn btn-success custom-width">Approve</a></td>
							<td><a href="<c:url value='/rej-user-${user.id}' />" class="btn btn-danger custom-width">Reject</a></td>		
				        </sec:authorize>				       
					</tr>
				</c:forEach> 		
				 </tbody>
	    	</table>
	    	</div>
	    	<c:choose>
				<c:when test="${accbal}">
	    	<div class="panel panel-default" id="id2">
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Account Number</th>
				        <th>Account Type</th>
				        <th>Account Balance</th>				        
					</tr> 
		    	</thead>
	    		<tbody>				
					<tr>
						<td>${account.accountId}</td>
						<td>${account.accType}</td>
						<td>${account.accountBalance}</td>								       
					</tr>
				</tbody>
	    	</table>
	    	</div>
	    	</c:when>
			</c:choose>	
			
			<c:choose>
				<c:when test="${statement}">
	    	<div class="panel panel-default" id="id2">
			<table class="table table-hover">
	    		<thead>
		      		<tr>
				        <th>Date</th>
				        <th>Description</th>
				        <th>Debit, &#8377</th>
				        <th>Credit, &#8377</th>				        
					</tr> 
		    	</thead>
	    		<tbody>				
					<c:forEach items="${transactions}" var="transaction">
					<tr>
						<td>${transaction.transdt}</td>
						<td>${transaction.transdesc}</td>
						<td>${transaction.transamt}</td>
						<td>${transaction.transamt}</td>					   			       
					</tr>
				</c:forEach>
				</tbody>
	    	</table>
	    	</div>
	    	</c:when>
			</c:choose>	
	    		
		<%-- <sec:authorize access="hasRole('ADMIN')">
		 	<div class="well">
		 		<a href="<c:url value='/newuser' />">Add New User</a>
		 	</div>
	 	</sec:authorize> --%>
   	</div>
</body>
</html>