<%-- 
    Document   : Home
    Created on : Dec 31, 2020, 10:45:08 AM
    Author     : abdom
--%>
<%@page import="java.sql.Date"%>
<%@include  file="includes/head.jsp" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
            //String clientID = request.getSession().getAttribute("clientID").toString();
            if (request.getSession().getAttribute("clientID") == null || request.getSession().getAttribute("clientID").equals("")){ 
                response.sendRedirect("login.jsp");
            }else{
%>
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
        <link rel="stylesheet" href="myStyle/homestyle.css">
        
        <title>Home</title>
    </head>
    <body>
        <div class="searchForm" id="srch">
            <form action = "searchResults.jsp" onsubmit = "return check(this);" method="get">
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-8">
                        <label for="location"> Where are you going? </label>
                        <input type="search" class="form-control" onchange="update(this);" name="location" placeholder="Hurghada">
                        <p id='errorLocation' ></p>
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-4">
                        <label for="location"> number of adult </label>
                        <input type="number" min="1" class="form-control" name="numOfAdults" placeholder="" value="1">
                    </div>
                    <div class="col-md-4">
                        <label for="location"> number of children </label>
                        <input type="number" min="0" class="form-control" name="numOfChildren" placeholder="" value="0">
                    </div>
                </div>
                <div class="row">
                    <div class="col-md-2"></div>
                    <div class="col-md-4">
                        <label for="location"> Check-in </label>
                        <input type="date"  id="checkin" min='2021-01-11'  class="form-control" name="checkin" value="2021-01-14" placeholder="2-1-2021">
                    </div>
                    <div class="col-md-4">
                        <label for="location"> Check-out </label>
                        <input type="date" id="checkout" class="form-control" min='2021-01-11' name="checkout" value="2021-01-14" placeholder="3-1-2021">
                    </div>
                </div>
                <button type="submit" id="srchBtn"> Search </button>
            </form>

        </div>

    </body>
</html>
<script>
    var today = new Date();
    var dd = today.getDate();
    var mm = today.getMonth()+1; //January is 0!
    var yyyy = today.getFullYear();
    if(dd<10){
       dd='0'+dd
    } 
    if(mm<10){
        mm='0'+mm
    } 
    today = yyyy+'-'+mm+'-'+dd;
    document.getElementById("checkin").setAttribute("min", today);
    document.getElementById("checkin").value = today;
    var tomorrow = new Date();
    tomorrow.setDate(new Date().getDate()+1);
    dd = tomorrow.getDate();
    mm = tomorrow.getMonth()+1; //January is 0!
    yyyy = tomorrow.getFullYear();
    if(dd<10){
       dd='0'+dd
    } 
    if(mm<10){
        mm='0'+mm
    } 
    tomorrow = yyyy+'-'+mm+'-'+dd;
    document.getElementById("checkout").setAttribute("min", tomorrow);
    document.getElementById("checkout").value = tomorrow;
    function check(form){
        if (form.location.value === ""){
            var ele = document.getElementById('errorLocation');
            ele.innerHTML = "Enter town name";
            ele.style.color = "red";
            form.location.focus();
            return false;
        }
        return true;
    }
    function update(ele){
        if (ele.name === "location"){
            document.getElementById('errorLocation').innerHTML = "";
        }
    }
</script>
<% } %>