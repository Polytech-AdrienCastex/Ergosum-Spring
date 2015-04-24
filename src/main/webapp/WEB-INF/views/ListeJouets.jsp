<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.Date,java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<title>Liste des jouets</title>

<jsp:include page="top.jsp" />

<H1> Liste des jouets </H1>

<form class="form-horizontal" method="GET" action="EffacerJouet">
<table class="table table-bordered">
	<thead>
		<tr>
			<td></td>
			<td>Actions</td>
			<td>Numéro</td>
			<td>Libellé</td>
			<td>Catégorie</td>
			<td>Tranche d'age</td>
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${mesJouets}" var="item" >
			<tr>
				<td>
					<input height="10" class="form-control" type="checkbox" name="id[]" value="${item.numero}" />
				</td>
				<td class="col-sm-1">
					<h4>
						<a href="ModifierJouet?id=${item.numero}"><span class="glyphicon glyphicon-edit" aria-hidden="true"></span></a>
						<a href="EffacerJouet?id[]=${item.numero}"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span></a>
					</h4>
				</td>
				<td>${item.numero}</td>
				<td>${item.libelle}</td>
				<td>${item.categorie.libcateg}</td>
				<td>${item.trancheage.agemin} à ${item.trancheage.agemax} ans</td>
			</tr>
		</c:forEach>
	</tbody>
	
	<tfoot>
		<tr>
			<td colspan="6">
				<button type="submit" class="btn btn-default">Supprimer</button>
			</td>
		</tr>
	</tfoot>
</table>
</form>

<jsp:include page="bottom.jsp" />
