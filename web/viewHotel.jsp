<%-- 
    Document   : viewHotel
    Created on : Jan 13, 2021, 9:18:10 AM
    Author     : abdom
--%>
<%@include  file="includes/head.jsp" %>
<%@page import="Processes.Review"%>
<%@page import="java.sql.Date"%>
<%@page import="Processes.Room"%>
<%@page import="Processes.RoomDB"%>
<%@page import="Processes.HotelPhotoDB"%>
<%@page import="java.util.ArrayList"%>
<%@page import="Processes.HotelDB"%>
<%@page import="Processes.Hotel"%>
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
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Courgette|Pacifico:400,700">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" type="text/css" href="myStyle/headstyle.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="myStyle/viewHotelStyle.css">
        <title>Hotel Name</title>
    </head>
    <body>
        <%
            String id = request.getParameter("ID");
            if(id == null){
                //response.sendRedirect("Home.jsp");
                if(request.getSession().getAttribute("hotelID") != null || !request.getSession().getAttribute("hotelID").equals("") ){
                    id = request.getSession().getAttribute("hotelID").toString();
                }
            }
            Hotel hotel = new Hotel();
            HotelDB hotelDB = new HotelDB();
            RoomDB roomDB = new RoomDB();
            
            HotelPhotoDB hotelPhotoDB = new HotelPhotoDB();
            hotel = hotelDB.getHotel(Integer.parseInt(id));
            int numOfChildren = Integer.parseInt(session.getAttribute("numOfChildren").toString());
            int numOfAdults = Integer.parseInt(session.getAttribute("numOfAdults").toString());
            Date checkin = Date.valueOf(session.getAttribute("checkin").toString());
            Date checkout = Date.valueOf(session.getAttribute("checkout").toString());
            ArrayList<Room> res = roomDB.getHotelRooms(hotel.getId());
            out.print(res.size());
            ArrayList <String> photos = hotelPhotoDB.getHotelPhoto(hotel.getId());
            //String loc = "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d18709.871244279006!2d31.21589931271423!3d30.03963376741791!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x145840cd3ef57809%3A0xd7f50b4221f71eb0!2sFour%20Seasons%20Hotel%20Cairo%20at%20Nile%20Plaza!5e0!3m2!1sen!2seg!4v1610528076340!5m2!1sen!2seg";
            String loc = hotel.getLocation();
            //out.print(loc.length());
            session.setAttribute("hotelID", hotel.getId());
            ArrayList <Review> reviews = hotelDB.getReviews(hotel.getId());
        %>
        <div class="head-db">
            <h2> <%= hotel.getHotelName() %> </h2>
            <p> <strong> <%=hotel.getStars()%>stars </strong> </p>
        </div>
        <div class="main">
            <div class="container-fluid">
                <div class="row">
                    <div class="col-sm-9 col-md-6 col-lg-8">
                    </div>
                    <div class="col-sm-3 col-md-6 col-lg-4">
                        <div class="location">
                            <div class="card-body card-body-cascade text-center">
                                <div id="map-container-google-8" class="z-depth-1-half map-container-5" style="height:200px">
                                    <iframe src = "<%= loc %>" width="600" height="450" frameborder="0" style="border:0;" allowfullscreen="" aria-hidden="false" tabindex="0"></iframe>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>  
                                
                <div class="gallery">
                    <div class="row-gallery">
                        <% for(int i = 0 ; i < photos.size() ; i++) { %>
                            <div class="column-img">
                                <img src="<%= photos.get(i) %>" alt="our Facilities" onclick="myFunction(this);">
                            </div>
                        <%  if ( i == 4) break; } %>
                    </div>       
      
                    <div class="show-img">
                        <span onclick="this.parentElement.style.display = 'none'" class="closebtn">&times;</span>
                        <img id="expandedImg" style="width:100%">
                        <div id="imgtext"> </div>
                    </div>
                </div>
                        
                <% for(Room room : res){ 
                    String photo = "hhotel.jpg";
                    String kind = room.getKind();
                    double price = room.getPrice();
                    String discreption = room.getDescription();
                    String facilities = room.getFacilities();
                %>
                <div class="hotel-rooms">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="room-pic">
                                <img src="img/room/rooms-1.jpg" alt="">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="room-text">
                                <div class="room-title">
                                    <h2><%= kind %></h2>
                                    <div class="room-price">
                                        <span>From</span>
                                        <h2><%= price %></h2>
                                        <sub>/night</sub>
                                    </div>
                                       
                                </div>
                                         <div>
                                        <a href="Booking?id=<%= room.getRoomID()%>" class="primary-btn">Book Now <i class="lnr lnr-arrow-right"></i></a>
                                        </div>
                                <div class="room-desc">
                                    <p><%= discreption %></p>
                                </div>
                                <div class="room-features">
                                    <div class="room-info last">
                                        <span><%= facilities %></span>
                                        
                                    </div>
                                </div>
                                        
                                <div class="room-features">
                                    <div class="room-info">
                                        <span>Num Of Children: <%= room.getNumOfChildren() %></span>
                                    </div>
                                    <div class="room-info">
                                        <span>Num Of Adults: <%= room.getNumOfAdults()%></span>
                                    </div>
                                    
                                </div>
                                
                            </div>
                        </div>
                    </div>
                </div>
                <% } %>
                <div class="txtarea">
                    <form action="addComment" onsubmit="return check(this);"method="get">
                        <div class="row">
                            <div class="cmnt" >
                                <textarea name="comment"  rows="7" cols="100" placeholder="Hey... Write your comment!" id="comment"></textarea>
                            </div>
                            
                            <div class="userRate"> 
                                Rate us: <input type="number" step="0.01" min="0" max="5" value="0.0" name="rate" placeholder="4.5">
                            </div>
                            
                            <div class="sendbtn" >
                                <input type="submit" class="btn" value="Submit">
                            </div>
                            <p id = "error" class="text-warning" ></p>

                        </div>
                    </form>
                    
                </div>
                <div class="txtarea">
                    
                    <h4 style="color: white; padding: 10px;"> Reviews : </h4>
                    <br>
                    <div class="textBox" >
                        <% for (Review r : reviews) { %>
                        <div class="row" style="margin-top: 40px">
                            <div class="cmnt" >
                                <!--strong>name</strong-->
                                <p style="color: white;">
                                        "<%= r.getComment() %>"
                                </p>
                                
                            </div>
                        </div>
                    <% } %>

                        <!--div class="row">
                            <div class="cmnt" >
                                <strong> Suhail </strong>
                                <p style="color: white;">
                                    "We stayed at Hotel Arthur on our trip to Helsinki and it was great. Location 
                                    was perfect for walking to everything! Staff was helpful and breakfast was great.
                                    Room was perfect for my daughter and myself. The only thing was our room faced the
                                    main road and the traffic was noisy all night. Would definite stay here again!"
                                </p>
                                
                            </div>
                        </div>
                    
                        <div class="row">
                            <div class="cmnt" >
                                <p>
                                    "Good relaxing break! Friendly, welcoming staff. Very good location for city centre and local transport. Overall an enjoyable stay. The hotel is old and could do with some renovation, but it seems like this issue is being addressed."                        </p>
                                <strong> Sarah </strong>
                            </div>
                        </div-->


                </div>
                
                <div class="hotel-rating">
                    <div class="row" id="hr-r">
                        <span class="heading">User Rating</span>

                        <span class="<%=hotel.getRate()>=1?"fa fa-star checked":"fa fa-star unchecked"%>"></span>
                        <span class="<%=hotel.getRate()>=2?"fa fa-star checked":"fa fa-star unchecked"%>"></span>
                        <span class="<%=hotel.getRate()>=3?"fa fa-star checked":"fa fa-star unchecked"%>"></span>
                        <span class="<%=hotel.getRate()>=4?"fa fa-star checked":"fa fa-star unchecked"%>"></span>
                        <span class="<%=hotel.getRate()>=5?"fa fa-star checked":"fa fa-star unchecked"%>"></span>
                        <p><%=hotel.getRate()%></p>
                        <hr style="border:3px solid #f1f1f1">       
                    </div>
                </div>

            </div>



        </div>

        <script>
            function myFunction(imgs) {
                var expandImg = document.getElementById("expandedImg");
                var imgText = document.getElementById("imgtext");
                expandImg.src = imgs.src;
                imgText.innerHTML = imgs.alt;
                expandImg.parentElement.style.display = "block";
            }
            function check(form){
                if (form.comment.value.length == 0 && form.rate.value == 0.0)
                {
                    document.getElementById("error").innerHTML = "must add comment or rate";
                    return false;
                }else if (form.comment.value.length > 500)
                {
                    document.getElementById("error").innerHTML = "length of comment must be less than 500 character.";
                    return false;
                }
                document.getElementById("error").innerHTML = "";
                return true;
            }
        </script>

    </body>
</html>
<% } %>
<%-- 
    <div class="col-md-6 mb-4">

    <!--Card-->
    <div class="card card-cascade narrower">

      <!--Card image-->
      <div class="view view-cascade gradient-card-header blue-gradient">
        <h5 class="mb-0">Regular map</h5>
      </div>
      <!--/Card image-->

      <!--Card content-->
      <div class="card-body card-body-cascade text-center">

        <!--Google map-->
        <div id="map-container-google-8" class="z-depth-1-half map-container-5" style="height: 300px">
          <iframe src="https://maps.google.com/maps?q=Barcelona&t=&z=13&ie=UTF8&iwloc=&output=embed"
            frameborder="0" style="border:0" allowfullscreen></iframe>
        </div>

      </div>
      <!--/.Card content-->

    </div>
    <!--/.Card-->

  </div>
--%>