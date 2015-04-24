<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.Date,java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<title>Ajouter un nouveau jouet</title>

<jsp:include page="top.jsp" />

<H1> Ajouter un nouveau jouet </H1>

<div class="col-sm-6">
<form class="form-horizontal" method="GET" action="SauverJouet">
	<input type="hidden" name="type" value ="ajout" />
	
	<div class="form-group">
		<label for="id" class="col-sm-3 control-label">Numéro</label>
		<div class="col-sm-9">
			<div class="input-group">
				<div class="input-group-addon">N°</div>
				<input class="form-control" id="id" type="number" name="id" placeholder="Numéro" />
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="libelle" class="col-sm-3 control-label">Libellé</label>
		<div class="col-sm-9">
			<input class="form-control" id="libelle" type="text" name="libelle" placeholder="Libellé" />
		</div>
	</div>
	
	<hr>
	
	<div class="form-group">
		<label for="codecateg" class="col-sm-3 control-label">Catégorie</label>
		<div class="col-sm-9">
			<select class="form-control" id="codecateg" name="codecateg">
				<c:forEach items="${categories}" var="item" >
					<option value="${item.codecateg}">${item.libcateg}</option>
				</c:forEach>
			</select>
		</div>
	</div>
	
	<div class="form-group">
		<label for="codetranche" class="col-sm-3 control-label">Tranche d'âge</label>
		<div class="col-sm-9">
			<select class="form-control" id="codetranche" name="codetranche">
				<c:forEach items="${tranches}" var="item" >
					<option value="${item.codetranche}">${item.agemin} à ${item.agemax} ans</option>
				</c:forEach>
			</select>
		</div>
	</div>
	
	<hr>
	
	<div class="form-group">
		<label for="codecatalogue" class="col-sm-3 control-label">Catalogue</label>
		<div class="col-sm-9">
			<div class="input-group">
				<div class="input-group-addon">Année</div>
				<select class="form-control" id="codecatalogue" name="codecatalogue">
					<c:forEach items="${catalogues}" var="item" >
						<option value="${item.annee}">${item.annee}</option>
					</c:forEach>
				</select>
			</div>
		</div>
	</div>

	<div class="form-group">
		<label for="quantiteDistribution" class="col-sm-3 control-label">Quantité</label>
		<div class="col-sm-9">
			<div class="input-group">
				<input class="form-control" id="quantiteDistribution" type="number" name="quantiteDistribution" placeholder="Quantité" />
				<div class="input-group-addon"> unités</div>
			</div>
		</div>
	</div>

	<div class="form-group">
		<div class="col-sm-3"></div>
		<div class="col-sm-9">
			<button type="submit" class="btn btn-default">Ajouter</button>
		</div>
	</div>
</form>
</div>
<div class="col-sm-6"></div>

<jsp:include page="bottom.jsp" />
