<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="<c:url value="/resources/styles.css" />">

        <title>Tasks</title>
    </head>
    <body>
        <jsp:include page="navAdmin.jsp" />

        <header class="jumbotron">
            <div class="container">
                <h1>Tasks</h1>
            </div>
        </header>
        <div class="container">        

            <h3>${message}</h3>
            <table class="table table-hover ">
                <caption>List of tasks.</caption>

                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>TITLE</th>
                        <th>DESCRIPTION</th>
                        <th>MEMBER</th>
                        <th>PROJECT</th>
                        <th>UPDATE</th>

                    </tr>
                </thead>
                <c:forEach items="${tasks}" var="task">
                    <tr>
                        <td>${task.taskId}</td>
                        <td>${task.title}</td>
                        <td>${task.description}</td>                    
                        <td>${task.employee.employeeName}</td>
                        <td>${task.project.title}</td>
                        <td><a href="taskUpdate/${task.taskId}" >update</a></td>
                    </tr> 
                </c:forEach>
            </table>
            </br>
            <form id="logout" action="<c:url value="/logout" />" method="post">
                <input type="hidden"                        
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <input type="submit" class="btn btn-dark"  value="Log out" />
            </form>    
        </div>

        <jsp:include page="footer.jsp" /> 
    </body>
</html>