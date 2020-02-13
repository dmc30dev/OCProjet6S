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

    <title>Page des sites</title>
</head>

<body>
<div class="container">
    <c:url value="/logout" var="logoutUrl"/>
    <header id="header">
        <nav class="navbar navbar-expand-lg navbar-dark fixed-top">
            <a class="navbar-brand" href="${pageContext.request.contextPath}"><h1>Les amis de l'escalade</h1></a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
                    aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item active">
                        <a class="nav-link" href="${pageContext.request.contextPath}/accueil">Home<span class="sr-only">(current)</span></a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="${pageContext.request.contextPath}/showSearchSitePage">Recherche</a>
                    </li>
                </ul>
            </div>
            <div class="clearfix">
                <form action="searchAll" method="post" class="form-inline mt-2 mt-md-0 mr-2 float-right"
                      id="inputSearchForm">
                    <div class="ui-widget">
                        <input class="form-control mr-sm-2" type="text" id="searchInput" name="motCle">
                    </div>
                    <button class="btn btn-outline-success my-2 mr-5" type="submit">Rechercher</button>
                </form>
            </div>
            <div class="clearfix">
                <c:if test="${pageContext.request.userPrincipal.name == null}">
            <span class="float-right">
            <a class="btn btn-outline-warning" href="${pageContext.request.contextPath}/signin">Creer un compte</a>
            &nbsp
            <a class="btn btn-outline-warning" href="${pageContext.request.contextPath}/login">Se connecter</a>
        </span>
                </c:if>
            </div>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <div>Bonjour ${pageContext.request.userPrincipal.name}</div>
                &emsp;
                <form action="${logoutUrl}" method="post" id="logoutForm">
                    <input type="hidden" name="${_csrf.parameterName}"
                           value="${_csrf.token}"/>
                    <a class="btn btn-outline-warning" href="javascript:lougOutFormSubmit()">Se déconnecter</a>
                </form>
            </c:if>
        </nav>
    </header>

    <div id="body">
        <div class="card">
            <div class="card-header">
                <h2 class="card-title">${site.nom}</h2>
                <h5 class="text-muted text-right">${site.ville.nom} - ${site.departement.nom}</h5>
            </div>
        </div>
        <div class="card-body">
            <div>
                <img src="..." class="card-img-top" alt="...">
            </div>
            <div id="descriptionBlock">
                <p>Description :</p>
                <div id="descriptionText">
                    ${site.description.description}
                </div>
            </div>
        </div>
        <div class="card-footer">
            <c:url var="secteurPage" value="/showSecteurPage">
                <c:param name="siteId" value="${site.id}"/>
            </c:url>
            <div class="row" id="buttonLinkBar">
                <div class="col-md-2">
                    <a class="btn btn-outline-warning" href="${secteurPage}">Voir les secteurs</a>
                </div>
                <div class="col-md-2">
                    <a class="btn btn-outline-warning" href="${pageContext.request.contextPath}/#">Voir les topos</a>
                </div>
                <div class="col-md-2">
                    <a class="btn btn-outline-warning" href="${pageContext.request.contextPath}/#">Ajouter un topo</a>
                </div>
                <div class="col-md-2">
                    <a class="btn btn-outline-warning" href="${pageContext.request.contextPath}/#">Voir les photos</a>
                </div>
                <div class="col-md-2">
                    <input class="btn btn-outline-warning" type="submit" onclick="showFormUpload(1)"
                           value="Ajouter une photo"><br/>
                </div>
                <div class="col-md-2">
                    <a class="btn btn-outline-warning" href="${pageContext.request.contextPath}/#">Enregistrer</a>
                </div>
            </div>
            <br>
            <form class="row" method="POST" action="uploadFile" id="formUpload" enctype="multipart/form-data">
                <input type="hidden" value="${site.id}" name="siteId">
                <div class="row col-md-12 no-gutters">
                    <div class="input-group col-md-7">
                        <div class="input-group-prepend">
                            <span class="input-group-text" id="inputGroupFileAddon">Image</span>
                        </div>
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" name="file " id="inputGroupFile"
                                   aria-describedby="inputGroupFileAddon">
                            <label class="custom-file-label" for="inputGroupFile">Choisir une image</label>
                        </div>
                    </div>
                    <div class="input-group col-md-5">
                        <div class="input-group-prepend offset-1">
                            <span class="input-group-text form-control" id="nameLabel">Nom : </span>
                        </div>
                        <input type="text" class="form-control" name="nomPhoto" aria-label="nomPhoto"
                               aria-describedby="nameLabel">
                    </div>
                    <button class="btn btn-outline-warning offset-10" id="btnUpload" type="submit"
                            onclick="showFormUpload(0)">Ajouter
                    </button>
                </div>
            </form>
            <br>
            <div>
                <c:if test="${ !empty uploadMsg}">
                    <div class="alert alert-success" role="alert">
                        <c:out value="${uploadMsg}"/>
                    </div>
                </c:if>
            </div>
        </div>
        <br><br>
        <div class="card">
            <div class="card-header>">
                <h3 class="card-title">Commentaires</h3>
            </div>
            <div class="card-body">
                <ul class="list-unstyled">
                    <li class="media">
                        <img src="..." class="mr-3" alt="...">
                        <div class="media-body">
                            <h5 class="mt-0 mb-1">titre commentaire</h5>
                            Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante
                            sollicitudin.
                            Cras
                            purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac
                            nisi
                            vulputate fringilla. Donec lacinia congue felis in faucibus.
                        </div>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <button type="button" class="btn btn-outline-warning">Modifier</button>
                            <button type="button" class="btn btn-outline-warning">Supprimer</button>
                        </sec:authorize>
                    </li>
                    <li class="media">
                        <img src="..." class="mr-3" alt="...">
                        <div class="media-body">
                            <h5 class="mt-0 mb-1">titre commentaire</h5>
                            Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante
                            sollicitudin.
                            Cras
                            purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac
                            nisi
                            vulputate fringilla. Donec lacinia congue felis in faucibus.
                        </div>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <button type="button" class="btn btn-outline-warning">Modifier</button>
                            <button type="button" class="btn btn-outline-warning">Supprimer</button>
                        </sec:authorize>
                    </li>
                    <li class="media">
                        <img src="..." class="mr-3" alt="...">
                        <div class="media-body">
                            <h5 class="mt-0 mb-1">titre commentaire</h5>
                            Cras sit amet nibh libero, in gravida nulla. Nulla vel metus scelerisque ante
                            sollicitudin.
                            Cras
                            purus odio, vestibulum in vulputate at, tempus viverra turpis. Fusce condimentum nunc ac
                            nisi
                            vulputate fringilla. Donec lacinia congue felis in faucibus.
                        </div>
                        <sec:authorize access="hasRole('ROLE_ADMIN')">
                            <button type="button" class="btn btn-outline-warning">Modifier</button>
                            <button type="button" class="btn btn-outline-warning">Supprimer</button>
                        </sec:authorize>
                    </li>
                </ul>
                <sec:authorize access="hasAnyRole({'ROLE_USER', 'ROLE_ADMIN'})">
                    <button type="button" class="btn btn-outline-warning">Ajouter un commentaire</button>
                </sec:authorize>
            </div>
        </div>
    </div>

    <!-- FOOTER -->
    <footer id="footer" class="page-footer">
        <p class="float-right"><a href="#">Back to top</a></p>
        <p>&copy; 2020 - Les amis de l'escalade &middot; <a href="#">Privacy</a> &middot; <a href="#">Terms</a>
        </p>
    </footer>
</div>
</body>


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
