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

<div class="col-lg-6">
	<div id="menu">
		<dl class="dl-horizontal">
		    <dt><a href="AfficherJouets">Afficher les jouets</a></dt>
		    <dd>Affiche la liste des tous les jouets ainsi que leur catégorie et leur tranche d'âge.</dd>
		    <dt><a href="ListerCatalogue">Afficher les catalogues</a></dt>
		    <dd>Afficher la liste des catalogues à partir d'une année spécifique et sur une place d'un certain nombre d'années.</dd>
		    <dt><a href="ListerDictionnaire">Afficher un dictionnaire</a></dt>
		    <dd>Afficher les informations relatives à un catalogue en particulier.</dd>
		    <dt><hr></dt>
		    <dd><hr></dd>
		    <dt><a href="AjouterJouet">Ajouter un jouet</a></dt>
		    <dd>Permet d'ajouter un nouveau jouet à un catalogue.</dd>
		</dl>
	</div>
</div>
<div class="col-lg-6">
	<blockquote>
		<h2>Bienvenue</h2>
		<h5>Nous sommes le <%= DateFormat.getDateTimeInstance(DateFormat.FULL,DateFormat.FULL).format(new Date()) %></h5>
	</blockquote>
</div>

<jsp:include page="bottom.jsp" />
