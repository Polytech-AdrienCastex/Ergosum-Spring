<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.Date,java.text.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta charset="utf-8">
	    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	    <meta name="viewport" content="width=device-width, initial-scale=1">
    
		<!-- Bootstrap -->
	    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
	    <link href="resources/css/site.css" rel="stylesheet">
	</head>
	<body>
	
	
	
	
	
		<nav class="navbar  navbar-inverse">
			<div class="container-fluid">
				<div class="navbar-header">
					<button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
						<span class="sr-only">Toggle navigation</span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
						<span class="icon-bar"></span>
					</button>
					<a class="navbar-brand" href="Accueil">Ergosum</a>
				</div>

				<div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
					<ul class="nav navbar-nav">
						<li	${action == 'Accueil' ? 'class="active"' : ''}><a href="Accueil">Accueil</a></li>
						
						<li	class="dropdown ${action == 'AfficherJouets' || action == 'AjouterJouet' ? 'active' : ''}">
				            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Jouets <span class="caret"></span></a>
				            <ul class="dropdown-menu" role="menu">
					            <li	${action == 'AfficherJouets' ? 'class="active"' : ''}><a href="AfficherJouets">Afficher</a></li>
					            <li	${action == 'AjouterJouet' ? 'class="active"' : ''}><a href="AjouterJouet">Ajouter</a></li>
				            </ul>
				        </li>
						
						<li	${action == 'ListerCatalogue' || action == 'AfficherCatalogues' ? 'class="active"' : ''}><a href="ListerCatalogue">Catalogues</a></li>
						<li	${action == 'ListerDictionnaire' || action == 'AfficherDictionnaire' ? 'class="active"' : ''}><a href="ListerDictionnaire">Dictionnaire</a></li>
					</ul>
				</div><!-- /.navbar-collapse -->
			</div><!-- /.container-fluid -->
		</nav>
	
		<div class="jumbotron">
			<div class="container">
