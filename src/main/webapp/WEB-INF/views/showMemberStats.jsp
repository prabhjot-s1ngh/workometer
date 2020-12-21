<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>MEMBERS</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    </head>
    <body>
        <jsp:include page="navLeader.jsp" />

        <header class="jumbotron">
            <div class="container">
                <h1>MEMBER STATS</h1>
            </div>
        </header>
        <div class="container">        

            <h3>${message}</h3>
            <table class="table table-hover">
                <caption>stats.</caption>

            <tr>
                <td>ID</td>
                <td>${id}</td>
            </tr>
            <tr>
                <td>NAME</td>
                <td>${name}</td>
            </tr>            
            <tr>
                <td>PENDING</td>
                <td>${pending}</td>
            </tr>
            <tr>
                <td>INBOUND</td>
                <td>${inbound}</td>
            </tr>
            <tr>
                <td>OUTBOUND</td>
                <td>${outbound}</td>
            </tr>
            <tr>
                <td>COMPLETED</td>
                <td>${completed}</td>
            </tr>
        </table>        
            </br>
            <form id="logout" action="<c:url value="/logout" />" method="post">
                <input type="hidden"                        
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <input type="submit" class="btn btn-dark"  class="btn btn-dark"  value="Log out" />
            </form>    
        </div>

        <jsp:include page="footer.jsp" /> 
        
    </body>
</html>
