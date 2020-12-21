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
                <h1>MEMBERS</h1>
            </div>
        </header>
        <div class="container">        

            <h3>${message}</h3>
            <table class="table table-hover">
                <caption>List of members.</caption>

                <thead class="table-dark">  
            <tr>
                <th scope="col">ID</th>
                <th scope="col">NAME</th>
                <th scope="col">STATS</th>
            </tr>
            </thead>

            <c:forEach items="${employees}" var="employee">
                <tr>
                    <td>${employee.employeeId}</td>
                    <td>${employee.employeeName}</td>
                    <td><a href="statsView/${employee.employeeId}" >VIEW</a></td>
                </tr> 
            </c:forEach>
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