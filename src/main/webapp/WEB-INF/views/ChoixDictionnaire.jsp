<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.Date,java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<title>Choisir un catalogue</title>

<jsp:include page="top.jsp" />

<H1> Choix du catalogue </H1>

<div class="col-sm-6">
<form class="form-horizontal" method="GET" action="AfficherDictionnaire">
	<div class="form-group">
		<label for="annee" class="col-sm-2 control-label">Année</label>
		<div class="col-sm-10">
			<select class="form-control" id="annee" name="annee">
				<c:forEach items="${catalogues}" var="item" >
					<option value="${item.annee}">Catalogue ${item.annee} [${item.quantiteDistribuee}]</option>
				</c:forEach>
			</select>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-2"></div>
		<div class="col-sm-10">
			<button type="submit" class="btn btn-default">Afficher</button>
		</div>
	</div>
</form>
</div>
<div class="col-sm-6"></div>

<jsp:include page="bottom.jsp" />
