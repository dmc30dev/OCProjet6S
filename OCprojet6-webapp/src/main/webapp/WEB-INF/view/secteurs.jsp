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
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
    <title>Secteurs</title>
</head>
<body>
<c:url value="/logout" var="logoutUrl"/>
<script>
    function formSubmit() {
        document.getElementById("logoutForm").submit();
    }
</script>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="${pageContext.request.contextPath}"><h2>Les amis de l'escalade</h2></a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavAltMarkup"
            aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
        <div class="navbar-nav">
            <a class="nav-item nav-link active" href="${pageContext.request.contextPath}/accueil">Accueil<span
                    class="sr-only">(current)</span></a>
            <a class="nav-item nav-link" href="${pageContext.request.contextPath}/sites">Sites</a>
        </div>
    </div>
    <c:if test="${pageContext.request.userPrincipal.name == null}">
        <a class="btn btn-outline-primary" href="${pageContext.request.contextPath}/login">Se connecter</a>
    </c:if>
    <c:if test="${pageContext.request.userPrincipal.name != null}">
        <div>Bonjour ${pageContext.request.userPrincipal.name}</div>
        &emsp;
        <form action="${logoutUrl}" method="post" id="logoutForm">
            <input type="hidden" name="${_csrf.parameterName}"
                   value="${_csrf.token}"/>
            <a class="btn btn-outline-primary" href="javascript:formSubmit()">Se déconnecter</a>
        </form>
    </c:if>
</nav>

<br><br>

<div>
    <h2>Page de présentation des différents secteurs d'un site</h2>
</div>


</body>
</html>
