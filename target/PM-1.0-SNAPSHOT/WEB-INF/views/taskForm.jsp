<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">                
        <title>Task Form</title>
        <style>
            th, td {
                padding: 4px;
            }
        </style> 
    </head>
    <body>
        <jsp:include page="navLeader.jsp" />

        <header class="jumbotron">
            <div class="container">
                <h1>ADD TASK</h1>
            </div>
        </header>
        <div class="container">        

            <h3>${message}</h3>
            <form:form action="taskAdd" method="POST" modelAttribute="task">

                <table>
                    <tr>
                        <td>Task ID</td>
                        <td><form:input path="taskId"  ></form:input></td>
                        <td><form:errors path="taskId"/></td>
                    </tr>
                    <tr>
                        <td>Task Name</td>
                        <td><form:input path="title"></form:input></td>
                        <td><form:errors path="title"/></td>
                    </tr>
                    <tr>
                        <td>Task Member</td>
                        <td>
                            <form:select path="employee.employeeId">
                                <form:options items="${employees}" itemValue="employeeId" itemLabel="employeeName"></form:options>
                            </form:select>                        
                        </td>
                        <td><form:errors path="employee.employeeId"/>
                        </td>
                    </tr>
                    <tr>
                        <td>Project</td>
                        <td>
                            <form:select path="project.projectId">
                                <form:options items="${projects}" itemValue="projectId" itemLabel="title"></form:options>
                            </form:select>                        
                        </td>
                        <td><form:errors path="project.projectId" /></td>
                    </tr>
                    <tr>
                        <td>Task Description</td>
                        <td><form:input path="description"></form:input>
                            </td>
                            <td><form:errors path="description"/></td>
                    </tr>
                    <tr>
                        <td>Start Date</td>
                        <td><form:input type="date" path="startDate"></form:input></td>
                        <td><form:errors path="startDate"/></td>
                    </tr>
                    <tr>
                        <td>Deadline</td>
                        <td><form:input type="date" path="deadline"></form:input></td>
                        <td><form:errors  path="deadline"/></td>
                    </tr>
                    <%--
                    <tr>
                        <td>End Date</td>
                        <td><form:input type="date" path="endDate"></form:input></td>
                        <td><form:errors path="endDate"/></td>
                    </tr>--%>
                </table>
                <input type="submit" class="btn btn-dark"  class="btn btn-dark"  class="btn btn-dark"  value="Add">
            </form:form>
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