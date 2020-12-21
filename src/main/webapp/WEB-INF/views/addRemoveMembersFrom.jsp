<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">                
        <style>
            th, td {
                padding: 5px;
            }
        </style>         
        <title>Update Member</title>
    </head>
    <body onload="disableEndate()">
        <jsp:include page="navAdmin.jsp" />

        <header class="jumbotron">
            <div class="container">
                <h1>UPDATE MEMBER</h1>
            </div>
        </header>
        <div class="container">
            <h3>${message}</h3>
            <form:form action="${contextPath}/PM/admin/taskUpdate" method="POST" modelAttribute="task">

                <table>
                    <tr>
                        <td>Task ID</td>
                        <td><form:input path="taskId" readonly="true" ></form:input></td>
                        <td><form:errors path="taskId"/></td>
                    </tr>
                    <tr>
                        <td>Task Name</td>
                        <td><form:input path="title" readonly="true"></form:input></td>
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
                        <td><form:input path="project.projectId"  readonly="true"></form:input></td>
                        <td><form:errors path="project.projectId" /></td>
                    </tr>
                    <tr>
                        <td>Task Description</td>
                        <td><form:input path="description" readonly="true"></form:input>
                            </td>
                            <td><form:errors path="description"/></td>
                    </tr>

                    <tr>
                        <td>Start Date</td>
                        <td><form:input type="date" path="startDate" readonly="true"></form:input></td>
                        <td><form:errors path="startDate"/></td>
                    </tr>
                    <tr>
                        <td>Deadline</td>
                        <td><form:input type="date" path="deadline" readonly="true"></form:input></td>
                        <td><form:errors  path="deadline"/></td>
                    </tr>
                    <tr>
                        <td>End Date</td>
                        <td><form:input type="date" id="endDate" path="endDate" readonly="true"></form:input></td>
                        <td><form:errors path="endDate"/></td>
                    </tr>

                </table>

                <input type="submit" class="btn btn-dark"  value="Update">
            </form:form>

            <script>
                function disableEndate() {
                    if (document.getElementById("endDate").value === "") {
                        document.getElementById("endDate").disabled = true;
                    }
                }
            </script>      
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
