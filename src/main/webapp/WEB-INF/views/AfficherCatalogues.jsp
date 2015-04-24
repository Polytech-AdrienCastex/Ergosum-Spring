<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.Date,java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<title>Affichage des catalogues</title>

<jsp:include page="top.jsp" />

<H1> Affichage des catalogues </H1>

<table class="table table-bordered">
	<thead>
		<tr>
			<td>Année</td>
			<td>Quantité affectée</td>
			<td>Quantité distribuée</td>
			<td>Différence</td>
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${mesCataloguesQuantites}" var="item" >
			<tr>
				<td><a href="AfficherDictionnaire?annee=${item.id}">Année ${item.id}</a></td>
				<td>${item.quantite}</td>
				<td>${item.quantiteDistribuee}</td>
				<td>${item.quantite - item.quantiteDistribuee}</td>
			</tr>
		</c:forEach>
	</tbody>
</table>

<jsp:include page="bottom.jsp" />
