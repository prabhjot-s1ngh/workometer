
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#Navbar">
            <span class="navbar-toggler-icon"></span>
        </button>                
        <!-- Brand -->
        <a class="navbar-brand" href="#">
            WORKOMETER
        </a>

        <!-- Links -->
        <div class="collapse navbar-collapse" id="Navbar">
            <ul class="navbar-nav">
                
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/PM/admin">HOME</a>
                </li>                 
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        USER
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${contextPath}/PM/admin/employeeAddForm"">ADD</a>
                        <a class="dropdown-item" href="${contextPath}/PM/admin/employeeViewEdit">EDIT</a>
                        <a class="dropdown-item" href="${contextPath}/PM/admin/employeeViewEdit">VIEW</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                        PROJECT
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${contextPath}/PM/admin/projectAddForm"">ADD</a>
                        <a class="dropdown-item" href="${contextPath}/PM/admin/projectViewEdit">EDIT</a>
                        <a class="dropdown-item" href="${contextPath}/PM/admin/projectViewEdit">VIEW</a>
                    </div>
                </li>     
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/PM/admin/memberViewEdit">MEMBER</a>
                </li>                

                <li class="nav-item">
                    <form>
                        <button type="button" onclick="logout()" class="btn btn-link btn-logout">Logout</button>
                    </form>                          
                </li>
            </ul>
            <div>
            </div>
            </nav>
            <header class="jumbotron">
                <div class="container">
                    <h1>WELCOME ${name} </h1>
                </div>
            </header>