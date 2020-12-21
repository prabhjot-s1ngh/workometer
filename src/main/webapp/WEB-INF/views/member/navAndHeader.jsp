
<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
    <div class="container">
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#Navbar">
            <span class="navbar-toggler-icon"></span>
        </button>                
        <!-- Brand -->
        <a class="navbar-brand" href="#">WORKOMETER</a>

        <!-- Links -->
        <div class="collapse navbar-collapse" id="Navbar">
            <ul class="navbar-nav">
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/PM/member">HOME</a>
                </li>                 
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/PM/member/viewTasks">TASKS</a>
                </li>     
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/PM/member/projectViewEdit">PROJECTS</a>
                </li>                
                <li class="nav-item">
                    <a class="nav-link" href="${contextPath}/PM/member/statsView">STATS</a>
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