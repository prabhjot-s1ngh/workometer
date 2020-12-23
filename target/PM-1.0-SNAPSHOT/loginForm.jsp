<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

        <title>Login Page</title>
        <style>
            th, td {
                padding: 10px;
            }
            h5{
                color:red;
            }
            h6{
                color:darkblue;
            }            
            h4, h5,h6{
                text-align: center;
            }
            .jumbotron{
                margin:0px auto;
                background: #000000;
                color:floralwhite;
            }
        </style>
    </head>
    <body>

        <header class="jumbotron">
            <div class="d-flex justify-content-center">
                <h1>WORKOMETER</h1>
            </div>
        </header>
        </br>
        <h4>LOGIN PAGE</h4></br>
        <h6> [ Username: 5 ,Password: hello ]</h6>

        <div class="d-flex justify-content-center">
            <form action="<c:url value="/login" />" method="post">       
                <c:if test="${param.error != null}">        
                    <h5>
                        Invalid username and password.
                    </h5>
                </c:if>
                <c:if test="${param.logout != null}">       
                    <h5>
                        You have been logged out.
                    </h5>
                </c:if>
                <table>
                    <tr>
                        <td>                               
                            <label for="username">Username</label>
                        </td>
                        <td>
                            <input type="text" id="username" name="username"/>              
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <label for="password">Password</label>

                        </td>
                        <td>
                            <input type="password" id="password" name="password"/>  

                        </td>
                    </tr>
                    <tr>
                        <td colspan="2" style="text-align:center">
                            <input type="hidden"                        
                                   name="${_csrf.parameterName}"
                                   value="${_csrf.token}"/>  
                            <button type="submit" class="btn btn-dark">Log in</button>

                        </td>
                    </tr>
                </table>

            </form>                          
        </div>



        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>  

    </body>
</html>
