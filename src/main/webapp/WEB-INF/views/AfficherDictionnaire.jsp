<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" %>
<%@ page import="java.util.Date,java.text.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 

<title>Catalogue année ${anneecatalogue}</title>

<jsp:include page="top.jsp" />

<H1> Catalogue année ${anneecatalogue} </H1>

<table class="table table-bordered">
	<thead>
		<tr>
			<td>Libellé</td>
			<td>Quantité distribuée</td>
		</tr>
	</thead>

	<tbody>
		<c:forEach items="${dictionnaire}" var="item" >
			<tr>
				<td>${item.key.libcateg}</td>
				<td>${item.value}</td>
			</tr>
		</c:forEach>
		<tr>
			<td>Total</td>
			<td>${total}</td>
		</tr>
	</tbody>
</table>

<jsp:include page="bottom.jsp" />
