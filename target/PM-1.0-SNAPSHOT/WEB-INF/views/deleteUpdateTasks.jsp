<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>TASKS ${caption}</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body>
        <jsp:include page="navLeader.jsp" />

        <header class="jumbotron">
            <div class="container">
                <h1>TASKS ${caption}</h1>
            </div>
        </header>
        <div class="container">        

            <h3>${message}</h3>
            <table class="table table-hover">
                <caption>List of tasks.</caption>

                <thead class="table-dark">  
                <th>ID</th>
                <th>TITLE</th>
                <th>DESCRIPTION</th>
                <th>MEMBER</th>
                <th>START DATE</th>
                <th>DEADLINE</th>
                <th>END DATE</th>
                <th>UPDATE</th>
                <th>DELETE</th>
                </tr>
                </thead>
                <c:forEach items="${tasks}" var="task">
                    <tr>
                        <td>${task.taskId}</td>
                        <td>${task.title}</td>
                        <td>${task.description}</td>                    
                        <td>${task.employee.employeeId}</td>
                        <td>${task.startDate}</td>
                        <td>${task.deadline}</td>
                        <td>${task.endDate}</td>
                        <td><a href="${contextPath}/PM/leader/taskUpdate/${task.taskId}" >update</a></td>
                        <td><a href="${contextPath}/PM/leader/taskDelete/${task.taskId}">delete</a></td>
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