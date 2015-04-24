<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.Date,java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<title>Erreur</title>

<jsp:include page="top.jsp" />

<div class="panel panel-danger" >
	<div class="panel-heading">
		<h3><span class="glyphicon glyphicon-warning-sign" aria-hidden="true"></span> Une erreur est survenue!</h3>
	</div>
	<div class="panel-body">
		${MesErreurs}
	</div>
</div>

<jsp:include page="bottom.jsp" />
