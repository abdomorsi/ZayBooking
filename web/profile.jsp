<%-- 
    Document   : profile
    Created on : Jan 9, 2021, 1:30:26 PM
    Author     : abdom
--%>
<%@include  file="includes/head.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Courgette|Pacifico:400,700">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="myStyle/headstyle.css">
        <link rel="stylesheet" href="myStyle/profileStyle.css">
        <title> My profile </title>
        <script>
        <%
            //String clientID = request.getSession().getAttribute("clientID").toString();
            if (request.getSession().getAttribute("clientID") == null || request.getSession().getAttribute("clientID").equals("")){ %>
                alert("you must login first");
             <% response.sendRedirect("login.jsp");
            }
        %>
        </script>
    </head>
    <body>
        <div class="container-p">
            <div class="row" >
                <div class="updForm" style="background: white;">                
                    <h2> update your information </h2>
                    <div class="form-item">
                        <div class="col-25">
                            <label for="fname">Username</label>
                        </div>
                        <div class="col-75">
                            <input type="text" id="fname" class="form-control" name="firstname" placeholder="Your name..">
                        </div>
                    </div>
                    <div class="form-item">
                        <div class="col-25">
                            <label for="mail">Email</label>
                        </div>
                        <div class="col-75">
                            <input type="text" class="form-control" onchange = "checkEmail();" id="email" name="email" placeholder="Your email..">
                        </div>
                    </div>
                    <div class="form-item">
                        <div class="col-25">
                            <label for="fname">Phone Number</label>
                        </div>
                        <div class="col-75">
                            <input type="text" id="phone" class="form-control" onchange="return checkNum();" name="phone" placeholder="Your phone..">
                        </div>
                    </div>
                    <div class="form-item">
                        <input type="submit" onclick="return check();" value="Submit">
                    </div>
                </div>
            </div>
            <div class="row">
                <div class="updForm" style="background: white;">                    
                    <!--form action="" method="get"-->
                        <h2> Cancel Reservation </h2>
                        <div class="form-item">
                            <div class="col-25">
                                <label for="rid">Reservation ID</label>
                            </div>
                            <div class="col-75">
                                <input type="text" id="rid" name="reservationID" placeholder="Reservation ID..">
                            </div>
                        </div>
                        <div class="form-item">
                            <input type="submit" onclick="return check4();" value="Submit">
                        </div>
                    <!--/form-->
                </div>
            </div>
        </div>
    </body>
</html>

<script>
    function check()
    {
        var name = document.getElementById("fname").value;
        var email = document.getElementById("email").value;
        var phone = document.getElementById("phone").value;
        if (name.length === 0 && email.length === 0 && phone.length === 0){
           alert("all field can't be empty");
           return false;
       }
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("POST", "updateProfile?name=" + name + "&email=" + email + "&phone=" + phone, true);
        // xhttp.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xmlhttp.send();
        xmlhttp.onreadystatechange = function ()
        {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
            {
                document.getElementById("fname").value = "";
                document.getElementById("phone").value = "";
                document.getElementById("email").value = "";
                alert("success");
                return true;
            }
        }
    }
    function checkEmail() {
        var name = document.getElementById("email").value;

        if (/^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/.test(name) == false)
         {
            document.getElementById("email").value = "";
            alert("You have entered an invalid email address!");
            document.getElementById("email").value = "";
            return (false)
         }
           

        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("GET", "signupServlet?email=" + name, true);
        xmlhttp.send();
        xmlhttp.onreadystatechange = function ()
        {
            if (xmlhttp.readyState == 4 && xmlhttp.status == 200)
            {
                if (xmlhttp.responseText == "found")
                {
                    alert("this email exists");
                    document.getElementById("email").value = "";
                }
            }
        }
    }
    function checkNum(){
        var phone = document.getElementById("phone").value;
        if (phone.length === 0 ) return true;
        if (isNaN(phone)){
            alert("phone must be only numbers");
            document.getElementById("phone").value = "";
            return false;
        }
        return true;
    }
    function check4()
    {
        var id = document.getElementById("rid").value;
        if(id == "") {
        alert("enter a value")    
        return false;}
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("POST", "ClientCancelReservation?id="+id, true);
        xmlhttp.send();
        xmlhttp.onreadystatechange = function ()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                alert(this.responseText);
            }             
        }
    }
</script>
