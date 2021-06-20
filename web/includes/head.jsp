<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <title></title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Courgette|Pacifico:400,700">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="../myStyle/headstyle.css">
    </head>
    <body>
        <div class="container" >
            <div class="jumbotron">
                <h1> zayBooking </h1>
                <p> Making your movement easier and more comfortable </p>
            </div>
        </div>
        
        <nav class="navbar navbar-expand-sm navbar-dark bg-dark" role="navigation">
            <a class="navbar-brand" href="Home.jsp" > zayBooking </a>
            <div class="collapse navbar-collapse" id="exCollapsingNavbar">
                <ul class="nav navbar-nav">
                    <li class="nav-item"><a href="Home.jsp" class="nav-link">Home</a></li>
                    <li class="nav-item"><a href="profile.jsp" class="nav-link">Profile</a></li>
                    <!--li class="nav-item"><a href="#" class="nav-link">Service</a></li>
                    <li class="nav-item"><a href="#" class="nav-link">More</a></li-->
                </ul>
                <ul class="nav navbar-nav flex-row justify-content-between ml-auto">
                    <li class="nav-item order-2 order-md-1"><a href="#" class="nav-link" title="settings"><i class="fa fa-cog fa-fw fa-lg"></i></a></li>
                    <li class="nav-item order-2 order-md-2">
                        <input type="button"  class="btn btn-outline-secondary" onclick="location.href='logout'" value="logout">                        
                    </li>
                </ul>
            </div>       
        </nav>
    </body>
</html>
