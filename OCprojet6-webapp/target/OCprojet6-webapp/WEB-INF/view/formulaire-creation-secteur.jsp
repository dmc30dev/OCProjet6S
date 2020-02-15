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

    <title>Page de creation de secteur</title>
</head>

<%@include file="header.jsp" %>

    <div id="body">
        <div class="container-fluid">
            <div class="card">
                <div class="card-title"><h2>Ajouter un nouveau secteur </h2></div>
                <form action="creationSecteur" method="post">
                    <div class="row col-md-12 no-gutters">
                        <div class="input-group col-md-5">
                            <div class="input-group-prepend">
                                <c:set var="site" value="${site}"/>
                                <input id="siteId" name="siteId" type="hidden" value="${site.id}">
                                <span class="input-group-text" id="siteNom">Nom du site : </span>
                            </div>
                            <input type="text" name="siteNom" disabled="disabled" value="${site.nom}" required
                                   class="form-control"
                                   aria-label="siteNom"
                                   aria-describedby="siteNom">
                        </div>
                        <div class="input-group col-md-7">
                            <div class="input-group-prepend">
                                <span class="input-group-text" id="secteurNom">Nom du secteur : </span>
                            </div>
                            <input type="text" name="secteurNom" required class="form-control" aria-label="secteurNom"
                                   aria-describedby="secteurNom">
                        </div>
                        <div class="row col-lg-12 no-gutters">
                            <div class="input-group">
                                <div class="input-group-prepend">
                                    <span class="input-group-text" id="description">Description : </span>
                                </div>
                                <textarea class="form-control" name="description" aria-label="With textarea"></textarea>
                            </div>
                        </div>
                    </div>
                    <br><br>
                    <div class="text-right">
                        <button type="submit" class="btn btn-outline-warning">Ajouter</button>
                    </div>
                </form>
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
