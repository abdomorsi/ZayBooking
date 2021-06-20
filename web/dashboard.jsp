<%-- 
    Document   : dashboard
    Created on : Jan 12, 2021, 2:34:23 PM
    Author     : abdom
--%>
<%@page import="Processes.Hotel"%>
<%@page import="Processes.HotelDB"%>
<%@page import="Processes.Review"%>
<%@page import="Processes.ClientDB"%>
<%@page import="Processes.Client"%>
<%@page import="java.sql.Date"%>
<%@page import="Processes.ReservationDB"%>
<%@page import="Processes.Reservation"%>
<%@page import="java.util.ArrayList"%>

<%
    if(request.getSession().getAttribute("adminID") == null || request.getSession().getAttribute("adminID").equals("")){
        out.print("kkkkkkkkkkkk");
        response.sendRedirect("login.jsp");
    }else{
    int adminID = Integer.parseInt(request.getSession().getAttribute("adminID").toString());
    int hotelID = new HotelDB().getHotelAdmin(adminID);
    boolean current = true;
    if(request.getParameter("fromDate") != null && request.getParameter("toDate") != null)
        current = false;
    Hotel hotel = new HotelDB().getHotel(hotelID);
    ArrayList<Reservation> reservations = new ArrayList<Reservation>();
    if(current)
        reservations = new ReservationDB().viewCurrentReservation(hotelID);
    else
        reservations = new ReservationDB().viewReservationUsingData(hotelID,
                Date.valueOf(request.getParameter("fromDate")),
                Date.valueOf(request.getParameter("toDate")));
    Client client = null;
    if(request.getParameter("customerID") != null)
        client = new ClientDB().getClient(Integer.parseInt(request.getParameter("customerID")));
    ArrayList<Review> reviews = new HotelDB().getReviews(hotelID); 
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Courgette|Pacifico:400,700">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="myStyle/dashboardStyle.css">
        <title>Dashboard</title>
          <script>
            function check()
                {
                var id = document.getElementById("cancelid").value;
                if(id == "") {
                alert("enter a value")    
                return false;}
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST", "AdminCancelReservation?id="+id+"&hotelID="+<%=hotelID%>, true);
                xmlhttp.send();
                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState==4 && xmlhttp.status==200)
                    {
                        alert(this.responseText);
                }             
            }
        }
          function check2()
                {
                var id = document.getElementById("checkinid").value;
                if(id == "") {
                alert("enter a value")    
                return false;}
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST", "checkInReservation?id="+id+"&hotelID="+<%=hotelID%>, true);
                xmlhttp.send();
                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState==4 && xmlhttp.status==200)
                    {
                        alert(this.responseText);
                }             
            }
        }
          function check3()
                {
                var id = document.getElementById("checkoutid").value;
                if(id == "") {
                alert("enter a value")    
                return false;}
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST", "checkOutReservation?id="+id+"&hotelID="+<%=hotelID%>, true);
                xmlhttp.send();
                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState==4 && xmlhttp.status==200)
                    {
                        alert(this.responseText);
                }             
            }
        }
          function check4()
                {
                var id = document.getElementById("paymentid").value;
                if(id == "") {
                alert("enter a value")    
                return false;}
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST", "paymentReservation?id="+id+"&hotelID="+<%=hotelID%>, true);
                xmlhttp.send();
                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState==4 && xmlhttp.status==200)
                    {
                        alert(this.responseText);
                }             
            }
        }
           function addAjax()
                {
                var id = document.getElementById("roomID").value;
                var kind = document.getElementById("roomKind").value;
                var num = document.getElementById("roomNumOfRooms").value;
                var des = document.getElementById("roomDescription").value;
                var child = document.getElementById("roomNumOfChildren").value;
                var adult = document.getElementById("roomNumberOfAdults").value;
                var facilities = document.getElementById("roomFacilities").value;
                var price = document.getElementById("roomPrice").value;
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST", "AddRoom?id="+id+"&kind?="+kind+"&num="
                +num+"&des="+des+"&child="+child+"&adult="+adult+"&facilities="+facilities
                +"&price="+price+"&hotelID="+<%= hotelID%>, true);
                xmlhttp.send();
                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState==4 && xmlhttp.status==200)
                    {
                        alert(this.responseText);
                }             
            }
        }
        
             function addPhotoAjax()
                {
                    
                var source = document.getElementById("addsource").value;
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST", "AddPhoto?id="+<%= hotelID%>+"&photo="+source, true);
                xmlhttp.send();
                xmlhttp.onreadystatechange = function ()
                {
                    if (xmlhttp.readyState==4 && xmlhttp.status==200)
                    {
                        alert(this.responseText);
                }             
            }             
            }
            function deletePhotoAjax()
            {
                 var source = document.getElementById("deletesource").value;
                var xmlhttp = new XMLHttpRequest();
                xmlhttp.open("POST", "DeletePhoto?id="+<%= hotelID%>+"&photo="+source, true);
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
    </head>
    <body>
        <div class="head-db">
            <h2> Admin Dashboard </h2>
            <h3> Hotel </h3>
            <p> powered by <strong> zayBooking </strong> </p>
        </div>
        <div class="container-fluid row">
            <div class="side-menu col-2">
                <button class="tablink" onclick="openPage('home', this)" id="defaultOpen"><i class="fa fa-fw fa-home"></i> Home</button>
                <button class="tablink" onclick="openPage('services', this)"><i class="fa fa-fw fa-wrench"></i>services</button>
                <button class="tablink" id="customeropen" onclick="openPage('customers', this)"><i class="fa fa-fw fa-user"></i> Clients</button>
                <button class="tablink" onclick="openPage('feedback', this)"><i class="fa fa-fw fa-envelope"></i> Feedback</button>
                <button class="tablink" onclick="window.location.href='logoutAdmin'"><i class="fa fa-sign-out"></i> Logout</button>
                <button class="tablink" id="rate">Rate <%= hotel.getRate()%></button>
            </div>
            <div class="content col-9" id="home">
                <div class="show-table">
                    <table id="reservation-table">
                        <h3> Reservation  </h3>
                        <thead>
                            <tr>
                                <th>reservation id</th>
                                <th>room id</th>
                                <th>client id</th>
                                <th>checked in</th>
                                <th>paid</th>
                            </tr>
                        </thead>
                        <tbody>
                            <%
                                for(Reservation reservation1:reservations)
                                {
                                %>
                            <tr>
                                <td><%=reservation1.getReservationID()%></td>
                                <td><%=reservation1.getRooms_roomID()%></td>
                                <td><%=reservation1.getClient_clientID()%></td>
                                <td><%=reservation1.getIsCheckedIn()==1?"yes":"no"%></td>
                                <td><%=reservation1.getPayment()==1?"yes":"no"%></td>
                            </tr>
<%}%>
                        </tbody>

                    </table>

                </div>

                <div class="inline-form">
                    <!--
<form action="#" method="GET">-->
                        <div class="form-item"> 
                            <h4> cancel Reservation </h4>
                            <div class="col-60">
                                <input type="text" onclick=""id="cancelid" name="reservationID" class="form-control" placeholder="Reservation-ID">
                            </div>
                            <div class="col-40">
                                <button type="submit" onclick="return check();"class="btn">Cancel</button>
                            </div>
                        </div>
                  <!-- </form>-->
                </div>
                <div class="inline-form">
                    <!--
<form action="#" method="GET">-->
                        <div class="form-item"> 
                            <h4> checkin Reservation </h4>
                            <div class="col-60">
                                <input type="text" onclick=""id="checkinid" name="reservationID" class="form-control" placeholder="Reservation-ID">
                            </div>
                            <div class="col-40">
                                <button type="submit" onclick="return check2();"class="btn">check in</button>
                            </div>
                        </div>
                  <!-- </form>-->
                </div>
                        <div class="inline-form">
                    <!--
<form action="#" method="GET">-->
                        <div class="form-item"> 
                            <h4> checkout Reservation </h4>
                            <div class="col-60">
                                <input type="text" onclick=""id="checkoutid" name="reservationID" class="form-control" placeholder="Reservation-ID">
                            </div>
                            <div class="col-40">
                                <button type="submit" onclick="return check3();"class="btn">check out</button>
                            </div>
                        </div>
                  <!-- </form>-->
                </div>
                        <div class="inline-form">
                    <!--
<form action="#" method="GET">-->
                        <div class="form-item"> 
                            <h4> confirm Payment Reservation </h4>
                            <div class="col-60">
                                <input type="text" onclick=""id="paymentid" name="reservationID" class="form-control" placeholder="Reservation-ID">
                            </div>
                            <div class="col-40">
                                <button type="submit" onclick="return check4();"class="btn">Confirm Payment</button>
                            </div>
                        </div>
                  <!-- </form>-->
                </div>
                <div class="inline-form" id="show-reservation-inperiod">
                    <form action="#dashboard.jsp" method="GET">
                        <div class="form-item"> 
                            <h4> Show Reservation </h4>
                            <div class="col-25">
                                <input type="date" name="fromDate" class="form-control" required>
                            </div>
                            <div class="col-25">
                                <input type="date" name="toDate" class="form-control" required>
                            </div>
                            <div class="col-25">
                                <button type="submit" onclick="myFunction('reservation-table-inperiod')" class="btn">Show</button>
                            </div>
                        </div>
                    </form>  
                </div>

                <div class="show-table" id="reservation-table-inperiod">
                    <table>
                        <h3> Reservation  </h3>
                        <thead>
                            <tr>
                                <th>first</th>
                                <th>Second</th>
                                <th>third</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <td>valueFirst</td>
                                <td>valueSecond</td>
                                <td>valueSecond</td>
                            </tr>

                        </tbody>

                    </table>

                </div>


            </div>

            <div class="content col-9" id="customers"> 
                <div class="show-table">
                    <table id="customers-table">
                        <h3> Customers  </h3>
                        <thead>
                            <tr>
                                <th>Name</th>
                                <th>Email</th>
                                <th>phone</th>
                            </tr>
                        </thead>
                        <tbody>
                               <%if(client!= null){%>
                            <tr>
                                <td><%= client.getUserName() %></td>
                                <td><%= client.getEmail()%></td>
                                <td><%= client.getPhoneNumber()%></td>
                            </tr>
<%}%>
                        </tbody>

                    </table>

                </div>

                <div class="inline-form">
                    <form action="#dashboard.jsp" method="GET">
                        <div class="form-item"> 
                            <h4> Find Customer </h4>
                            <div class="col-60">
                                <input type="text" name="customerID" class="form-control" placeholder="Customer-ID">
                            </div>
                            <div class="col-40">
                                <button type="submit" onclick="myFunction('userInfo')" class="btn">Find</button>
                            </div>
                        </div>
                    </form>  
                </div>
                
                <!--div class="user-info">
                    <div class="show-table" id="userInfo">
                    <table>
                        <thead>
                            <tr>
                                <th>first</th>
                                <th>Second</th>
                                <th>third</th>
                            </tr>
                        </thead>
                        <tbody>

                            <tr>
                                <td>valueFirst</td>
                                <td>valueSecond</td>
                                <td>valueSecond</td>
                            </tr>

                        </tbody>

                    </table>

                </div>
                </div-->
                
                

            </div>

            <div class="content col-9" id="feedback">
               
                <h3> Reviews </h3>
                <%
                    for(Review review: reviews)
                    {
                %>
                <div class="textBox">
                    <div class="comment">
       
                        <p>
                            <%= review.getComment()%>
                        </p>
                        <strong> <%= review.getRating()%> </strong>
                    </div>
                </div>
                <%}%>                
            </div>
               <div class="content col-9" id="services">
                    <div class="inline-form">
                        <div class="form-item"> 
                            <h4> Add Photo </h4>
                            <div class="col-60">
                                <input type="text" id ="addsource" name="photoPath" class="form-control" placeholder="your path..">
                            </div>
                            <div class="col-40">
                                <button type="submit" onclick="return addPhotoAjax();"class="btn">ADD</button>
                            </div>
                        </div>
                </div>

                <div class="inline-form">
                        <div class="form-item"> 
                            <h4> Delete Photo </h4>
                            <div class="col-60">
                                <input type="text" id="deletesource" name="photopath" class="form-control" placeholder="photo-path..">
                            </div>
                            <div class="col-40">
                                <button type="submit" onclick="return deletePhotoAjax();"class="btn">Delete</button>
                            </div>
                        </div>
                </div>
                <div class="form-x">
                    
                        <h4> UPDATE / ADD room information </h4>
                        
                        <div class="form-item">
                            <div class="col-30">
                                <label  for="roomID" class="form-label"> Room ID </label>
                            </div>
                            <div class="col-60">
                                <input type="text" id="roomID" name="roomID" class="form-input">
                            </div>
                        </div>

                        <div class="form-item">
                            <div class="col-30">
                                <label  for="kind" class="form-label"> Kind </label>
                            </div>
                            <div class="col-60">
                                <input type="text" id="roomKind" name="kind" class="form-input">
                            </div>
                        </div>

                        <div class="form-item">
                            <div class="col-30">
                                <label  for="numOfRooms" class="form-label">Number Of Rooms</label>
                            </div>
                            <div class="col-60">
                                <input type="number" id="roomNumOfRooms"name="numOfRooms" class="form-input">
                            </div>
                        </div>

                        <div class="form-item">
                            <div class="col-30">
                                <label  for="price" class="form-label"> Price </label>
                            </div>
                            <div class="col-60">
                                <input type="number" id="roomPrice"name="" class="form-input">
                            </div>
                        </div>

                        <div class="form-item">
                            <div class="col-30">
                                <label  for="nAva" class="form-label"># facilities</label>
                            </div>
                            <div class="col-60">
                                <input type="text" id="roomFacilities" name="roomFacilities" class="form-input">
                            </div>
                        </div>

                        <div class="form-item">
                            <div class="col-30">
                                <label  for="desc" class="form-label"> Description </label>
                            </div>
                            <div class="col-60">
                                <input type="text" id="roomDescription" name="" class="form-input">
                            </div>
                        </div>

                        <div class="form-item">
                            <div class="col-30">
                                <label  for="noc" class="form-label"> # of Child </label>
                            </div>
                            <div class="col-60">
                                <input type="number" id="roomNumOfChildren" name="numOfChildren" class="form-input">
                            </div>
                        </div>

                        <div class="form-item">
                            <div class="col-30">
                                <label  for="noa" class="form-label"># of Adults</label>
                            </div>
                            <div class="col-60">
                                <input type="number" id="roomNumberOfAdults" name="numOfAdults" class="form-input">
                            </div>
                        </div>
                        <div class="form-item">
                            <div class="col-25">
                                <button type="submit" onclick="return updateAjax();" class="btn">Update</button>
                            </div>

                            <div class="col-25">
                                <button type="submit" onclick="return addAjax();" class="btn">ADD</button>
                            </div>
                        </div>
                    
                </div>

                <div class="form-x">
                        <h4> update HOTEL information </h4>
                        <div class="form-item">
                            <div class="col-30">
                                <label  for="conInfo" class="form-label"> Contact Info </label>
                            </div>
                            <div class="col-60">
                                <input type="text"  id="contactinfo" name="cntactInfo" class="form-input">
                            </div>
                        </div>

                        <div class="form-item">
                            <div class="col-30">
                                <label  for="hf"   class="form-label"> Hotel Facilities </label>
                            </div>
                            <div class="col-60">
                                <input type="text" id="hotelFacilities" name="hotelFacilties" class="form-input">
                            </div>
                        </div>

                        <div class="form-item">
                            <div class="col-30">
                                <label  for="loc"  class="form-label">Location</label>
                            </div>
                            <div class="col-60">
                                <input type="text" id="location" name="location" class="form-input">
                            </div>
                        </div>

                        <div class="form-item">
                            <div class="col-30">
                                <label  for=""  class="form-label"> Description </label>
                            </div>
                            <div class="col-60">
                                <input type="text" id="hotelDescription" name="" class="form-input">
                            </div>
                        </div>

                        <div class="form-item">
                            <div class="col-25">
                                <button type="submit" onclick="return updateHotelAjax();"class="btn">Update</button>
                            </div>
                        </div>
                </div>

            </div>

        </div>

        <script>
            function myFunction(divID) {
                var x = document.getElementById(divID);
                if (x.style.display === "block") {
                    x.style.display = "none";
                } else {
                    x.style.display = "block";
                }
            }

            function openPage(pageName, elmnt) {
                // Hide all elements with class="tabcontent" by default */
                var i, tabcontent, tablinks;
                tabcontent = document.getElementsByClassName("content");
                for (i = 0; i < tabcontent.length; i++) {
                    tabcontent[i].style.display = "none";
                }

                document.getElementById(pageName).style.display = "block";

                // Add the specific color to the button used to open the tab content
            }

// Get the element with id="defaultOpen" and click on it
            <% if (request.getParameter("customerID") == null){%>
            document.getElementById("defaultOpen").click();
            <% }else {
                    %>
                       document.getElementById("customeropen").click();
                       <% } %>
        </script>
    </body>
</html>
<% } %>
