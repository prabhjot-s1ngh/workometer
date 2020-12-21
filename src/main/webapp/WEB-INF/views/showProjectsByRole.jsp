<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Projects</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">

    </head>
    <body>
        <c:if test="${role eq 'member'}">    
            <jsp:include page="navMember.jsp" />
        </c:if>  
        <c:if test="${role eq 'leader'}"> 
            <jsp:include page="navLeader.jsp" />
        </c:if>          

        <header class="jumbotron">
            <div class="container">
                <h1>PROJECTS</h1>
            </div>
        </header>
        <div class="container">        

            <h3>${message}</h3>
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
                                TASKS</th>
                            <th>Members</th>
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
                            </td>
                        </c:if>  
                        <c:if test="${role eq 'leader'}">        
                        <a href="${contextPath}/PM/leader/taskViewEditByProjectId/${project.projectId}">View</a>
                        </td>

                        <td>
                            <a href="${contextPath}/PM/leader/listProjectMembers/${project.projectId}">View</a>

                        </td>
                    </c:if>                             
                    </tr>
                </c:forEach>
            </table>
            </br>
            <form id="logout" action="<c:url value="/logout" />" method="post">
                <input type="hidden"                        
                       name="${_csrf.parameterName}"
                       value="${_csrf.token}"/>
                <input type="submit" class="btn btn-dark"  class="btn btn-dark"  class="btn btn-dark"  value="Log out" />
            </form>    
        </div>

        <jsp:include page="footer.jsp" /> 
    </body>
</html>