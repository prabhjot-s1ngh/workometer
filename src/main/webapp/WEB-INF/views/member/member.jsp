<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">        
        <title>Member Home Page</title>
    </head>
    <body>
        <jsp:include page="navAndHeader.jsp" />

        <div class="container">
            <h1>TASKS</h1>
            <table class="table table-hover">
                <caption>List of tasks.</caption>
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>TITLE</th>
                        <th>DESCRIPTION</th>
                        <th>START DATE</th>
                        <th>DEADLINE</th>
                        <th>END DATE</th>
                        <th>COMPLETE</th>
                    </tr>
                </thead>
                <c:forEach items="${tasks}" var="task">
                    <tr>
                        <td>${task.taskId}</td>
                        <td>${task.title}</td>
                        <td>${task.description}</td>                    
                        <td>${task.startDate}</td>
                        <td>${task.deadline}</td>
                        <td>${task.endDate}</td>
                        <td>
                            <c:choose>
                                <c:when test="${task.endDate != null}">
                                    completed.
                                </c:when>
                                <c:otherwise>
                                    <a href="${contextPath}/PM/member/taskFinish/${task.taskId}" >DONE</a>
                                </c:otherwise>
                            </c:choose>          
                        </td>
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
        <footer class="footer">
            <div class="container">
                <div class="row justify-content-center">             
                    <div class="col-auto">
                        <p>© Copyright 2020 Workometer</p>
                    </div>
                </div>
            </div>
        </footer>             
        <script>
            function logout() {
                document.getElementById("logout").submit();
            }
        </script>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>                       
    </body>
</html>
