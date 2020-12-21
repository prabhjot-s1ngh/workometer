<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="<c:url value="/resources/styles.css"/>">
        <title>Admin Login Page</title>
    </head>
    <body>
        <jsp:include page="navAndHeader.jsp" />

        <div class="container">
            <h3>ALL PROJECTS</h3>
            <table class="table table-hover">
                <caption>List of projects.</caption>
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>TITLE</th>
                        <th>DESCRIPTION</th>
                        <th>LEADER ID</th>                
                        <th>START DATE</th>
                        <th>END DATE</th>
                        <th>UPDATE</th>
                        <th>DELETE</th>

                    </tr>
                </thead>
                <c:forEach items="${projects}" var="project">
                    <tr>
                        <td>${project.projectId}</td>
                        <td>${project.title}</td>
                        <td>${project.description}</td>                   
                        <td>${project.employee.employeeId}</td>
                        <td>${project.startDate}</td>
                        <td>${project.endDate}</td>
                        <td><a href="${contextPath}/PM/admin/projectUpdate/${project.projectId}" >update</a></td>
                        <td><a href="${contextPath}/PM/admin/projectDelete/${project.projectId}">delete</a></td>
                    </tr> 
                </c:forEach>
            </table>            
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