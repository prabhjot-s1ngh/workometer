<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">        
        <title>Leader Home Page</title>
    </head>
    <body>
        <jsp:include page="navAndHeader.jsp" />

        <div class="container">
            <h1>ALL PROJECTS</h1>
            <table class="table table-hover">
                <caption>List of projects.</caption>
                <thead class="table-dark">
                    <tr>
                        <th>ID</th>
                        <th>TITLE</th>
                        <th>DESCRIPTION</th>
                        <th>START DATE</th>
                        <th>
                            <c:if test="${role eq 'member'}">    
                                MEMBERS
                            </c:if>  
                            <c:if test="${role eq 'leader'}"> 
                                TASKS
                            </c:if>                       
                        </th>
                    </tr>
                </thead>
                <c:forEach items="${projects}" var="project">
                    <tr>
                        <td>${project.projectId}</td>
                        <td>${project.title}</td>
                        <td>${project.description}</td>                   
                        <td>${project.startDate}</td>
                        <td>
                            <c:if test="${role eq 'member'}">        
                                <a href="${contextPath}/PM/member/listProjectMembers/${project.projectId}">View</a>
                            </c:if>  
                            <c:if test="${role eq 'leader'}">        
                                <a href="${contextPath}/PM/leader/taskViewEditByProjectId/${project.projectId}">View</a>
                            </c:if>                             
                        </td>
                    </tr> 
                </c:forEach>
            </table>
            <form id="logout" action="<c:url value="/logout" />" method="post">
                <input type="hidden"                        
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <input type="submit" value="Log out" />
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
