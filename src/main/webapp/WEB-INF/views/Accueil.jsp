<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.Date,java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<title>Accueil</title>

<jsp:include page="top.jsp" />

<H1> Ergosum </H1>

<c:if test="${message != null}">
	<div class="alert alert-success">
		${message}
	</div>
</c:if>

<div class="col-lg-4">
	<div id="menu">
		<dl class="dl-horizontal">
		    <dt><a href="afficherJouets">Lister les jouets</a></dt>
		    <dd>aaaaaaaaaaaa.</dd>
		    <dt><a href="ajouterJouet">Ajouter un jouet</a></dt>
		    <dd>aaaaaaaaaaaa.</dd>
		    <dt><a href="listerCatalogue">Choix catalogue</a></dt>
		    <dd>aaaaaaaaaaaa.</dd>
		    <dt><a href="afficherCatalogues">Afficher catalogue</a></dt>
		    <dd>aaaaaaaaaaaa.</dd>
		    <dt><a href="afficherDictionnaire">Afficher dictionnaire</a></dt>
		    <dd>aaaaaaaaaaaa.</dd>
		</dl>
	</div>
</div>
<div class="col-lg-8">
	<blockquote>
		<h2>Nous sommes le <%= DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL).format(new Date()) %></h2>
	</blockquote>
</div>

<jsp:include page="bottom.jsp" />
