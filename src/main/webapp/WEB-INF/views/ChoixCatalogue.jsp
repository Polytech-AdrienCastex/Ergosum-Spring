<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.Date,java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<title>Choisir des catalogues</title>

<jsp:include page="top.jsp" />

<H1> Choix des catalogues </H1>

<div class="col-sm-6">
<form class="form-horizontal" method="GET" action="AfficherCatalogues">
	<div class="form-group">
		<label for="anneeDebut" class="col-sm-2 control-label">Année</label>
		<div class="col-sm-10">
			<select class="form-control" id="anneeDebut" name="anneeDebut">
				<c:forEach items="${catalogues}" var="item" >
					<option value="${item.annee}">Catalogue ${item.annee} [${item.quantiteDistribuee}]</option>
				</c:forEach>
			</select>
		</div>
	</div>

	<div class="form-group">
		<label for="nbAnnees" class="col-sm-2 control-label">Nombre d'années</label>
		<div class="col-sm-10">
			<div class="input-group">
				<input class="form-control" id="nbAnnees" type="number" name="nbAnnees" placeholder="Nombre d'années" />
				<div class="input-group-addon"> année(s)</div>
			</div>
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
