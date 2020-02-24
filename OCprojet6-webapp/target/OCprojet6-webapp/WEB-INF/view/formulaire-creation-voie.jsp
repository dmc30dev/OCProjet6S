<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet"
          href="//code.jquery.com/ui/1.10.4/themes/smoothness/jquery-ui.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css">

    <title>Page de creation de voie</title>
</head>

<%@include file="header.jsp" %>

<div id="body">
    <div class="container-fluid">
        <c:set var="siteId" value="${siteId}"/>
        <c:set var="secteur" value="${secteur}"/>
        <div class="card">
            <div class="card-header">
                <c:url var="voiePage" value="/showVoiePage">
                    <c:param name="siteId" value="${siteId}"/>
                    <c:param name="secteurId" value="${secteur.id}"/>
                </c:url>
                <h2 class="card-title"><a href="${voiePage}">${secteur.nom}</a></h2>
            </div>
            <div class="card-body">
                <h5>Ajouter une nouvelle voie :</h5>
                <form action="creationVoie" method="post">
                    <input id="siteId" name="siteId" type="hidden" value="${siteId}">
                    <input id="secteurId" name="secteurId" type="hidden" value="${secteur.id}">
                    <div class="row col-md-12 no-gutters">
                        <div class="input-group mb-1">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="voieNom">Nom de la voie : </span>
                            </div>
                            <input type="text" name="nom" class="form-control" aria-label="Nom de la voie"
                                   aria-describedby="Nom de la voie" required>
                        </div>
                    </div>
                    <div class="row col-md-12 no-gutters">
                        <div class="input-group col-md-4">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="description">Hauteur : </span>
                            </div>
                            <input type="number" name="hauteur" class="form-control" aria-label="Hauteur de la voie"
                                   aria-describedby="Hauteur de la voie">
                        </div>
                        <div class="input-group col-md-4 ml-2">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="cotation">Cotation : </span>
                            </div>
                            <select class="custom-select" name="cotationId" id="cotations"                                 aria-label="Cotation de la voie"
                                    aria-describedby="Cotation de la voie">
                                <option selected disabled></option>
                                <c:forEach var="cotation" items="${cotations}">
                                    <option value="${cotation.id}">${cotation.valeur}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <br><br>
                    <div class="text-right">
                        <button type="submit" class="btn btn-warning">Ajouter</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<!-- FOOTER -->
<footer id="footer" class="page-footer">
    <p class="float-right"><a href="#">Back to top</a></p>
    <p>&copy; 2020 - Les amis de l'escalade &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a>
    </p>
</footer>


<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
        integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
        integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
<script rel="script" src="${pageContext.request.contextPath}/resources/js/webappJsFunctions.js"></script>

</html>
>
