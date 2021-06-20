<%-- 
    Document   : searchResults
    Created on : Jan 3, 2021, 7:20:55 AM
    Author     : abdom
--%>
<%@page import="Processes.filter"%>
<%@page import="Processes.HotelView"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.sql.Date"%>
<%@page import="Processes.HotelDB"%>
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
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Courgette|Pacifico:400,700">
        <link href='https://fonts.googleapis.com/css?family=ABeeZee' rel='stylesheet'>
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <link rel="stylesheet" type="text/css" href="myStyle/headstyle.css">
        <link rel="stylesheet" type="text/css" href="myStyle/resultsStyle.css">
        <title>JSP Page</title>

    </head>
    <body>
        <%
            String town ;
            Date checkIn;
            Date checkOut ; 
            int numOfAdults;
            int numOfChildren ;
            HotelDB hotelDB = new HotelDB();
            ArrayList <HotelView> hotels = new ArrayList<>();
            town = request.getParameter("location");
            hotelDB = new HotelDB();
            
            String price = "";
            String Rate = "";
            String Distance = "";
            String breakfast = "";
            String dinner = "";
            String lunch = "";
            String star = "";
            if (town == null){
                price = request.getParameter("price");
                Rate = request.getParameter("rate");
                Distance = request.getParameter("distance");
                breakfast = request.getParameter("breakfast");
                dinner = request.getParameter("dinner");
                lunch = request.getParameter("lunch");
                star = request.getParameter("rating-filter");
                //hotels = hotelDB.
                //if (hotels.size() > 0)
                town = session.getAttribute("town").toString();
                checkIn = Date.valueOf(session.getAttribute("checkin").toString());
                checkOut = Date.valueOf(session.getAttribute("checkout").toString());
                numOfAdults = Integer.parseInt(session.getAttribute("numOfAdults").toString());
                numOfChildren = Integer.parseInt(session.getAttribute("numOfChildren").toString());
//                session.removeAttribute("town");
//                session.removeAttribute("checkin");
//                session.removeAttribute("checkout");
//                session.removeAttribute("numOfAdults");
//                session.removeAttribute("numOfChildren");
                ArrayList<HotelView> tem = hotelDB.search(town, checkIn, checkOut, numOfAdults, numOfChildren);
                filter f = new filter();
                hotels = f.filteration(tem, price, Rate, Distance , breakfast ,dinner , lunch ,star);
                System.out.println(town + " " + checkIn + " " + checkOut + " " + numOfAdults + " " + numOfChildren);
                System.out.println(price + " ," + Rate + " ," + Distance + ", " + breakfast + " ," + dinner + " ," + lunch + " ," + star);
                
            }else{
                checkIn = Date.valueOf(request.getParameter("checkin"));
                checkOut = Date.valueOf(request.getParameter("checkout")); 
                numOfAdults = Integer.parseInt(request.getParameter("numOfAdults"));
                numOfChildren = Integer.parseInt(request.getParameter("numOfChildren"));
                System.out.println(town + " " + checkIn + " " + checkOut + " " + numOfAdults + " " + numOfChildren);
                hotels = hotelDB.search(town, checkIn, checkOut, numOfAdults, numOfChildren);
                session.setAttribute("town", town);
                session.setAttribute("checkin", checkIn);
                session.setAttribute("checkout", checkOut);
                session.setAttribute("numOfAdults", numOfAdults);
                session.setAttribute("numOfChildren", numOfChildren);

            }
            
            
            //System.out.println(hotels.get(0).getHotel().getAdminId());
            //HotelView hotelView = new HotelView();
        %>
        <div class="container-sr">
            <article>
                <h5> Filter </h5>
                <div class="filter-Box">
                    <form action="searchResults.jsp" method="GET">
                        <div class="filter">
                            <div class="form-group">
                                <label for="price">What is you budget?</label>
                                <input type="number" name="price" min="0"  class="form-control text-center" id="price" >
                            </div>
                        </div>
                        <div class="filter">
                            <div class="form-group">
                                <label for="rate">Hotel Rate</label>
                                <input type="number" name="rate" min="0" max="5" placeholder="0.0" step="0.1"  class="form-control text-center" >
                            </div>
                        </div>
                        <div class="filter">
                            <div class="form-group">
                                <label for="distance">Distance By km</label>
                                <input type="number" name="distance" min="0" placeholder="0.0" step="1.0"  class="form-control text-center" >
                            </div>
                        </div>
                        <div class="filter">
                            <h6> Meals </h6>
                            <div class="form-check">
                                <label class="form-check-label" for="check1">
                                    <input type="checkbox" class="form-check-input" id="check1" name="breakfast" value="breakfast" >Breakfast
                                </label>
                            </div>
                            <div class="form-check ">
                                <label class="form-check-label" for="check1">
                                    <input type="checkbox" class="form-check-input  " id="check1" name="dinner" value="dinner" >Dinner
                                </label>
                            </div>
                            <div class="form-check ">
                                <label class="form-check-label" for="check2">
                                    <input type="checkbox" class="form-check-input" id="check2" name="lunch" value="lunch">Lunch
                                </label>
                            </div>
                            
                        </div>
                        <div class="filter">
                            <h6> Hotel's Stars </h6>
                            <div class="rating-filter"> <input type="radio" name="rating-filter" value="5" id="5"><label for="5">☆</label> <input type="radio" name="rating-filter" value="4" id="4"><label for="4">☆</label> <input type="radio" name="rating-filter" value="3" id="3"><label for="3">☆</label> <input type="radio" name="rating-filter" value="2" id="2"><label for="2">☆</label> <input type="radio" name="rating-filter" value="1" id="1"><label for="1">☆</label>
                            </div>
                        </div>
                        <button type="submit" class="btn ">Apply</button>
                    </form>    
                </div>
            </article>
            <div class="show">
                <div class="container-fluid px-4 py-5 mx-auto">
                    <% for(HotelView hotel : hotels){ %>  
                        <div class="hotel-card">
                            <div class="row d-flex justify-content-center">
                                <div class="hotelImage">
                                    <% /*String imgsrc = "https://i.imgur.com/3pcJdqF.jpg";
                                       String Cost = "1880";
                                       String HotelName = "Park Inn by Radisson Berlin Alexanderplatz";*/
                                       String imgsrc = hotel.getHotel().getSrc();
                                       String description = hotel.getHotel().getDescription();
                                       Double Cost = hotel.getExpectedPrice();
                                       String HotelName = hotel.getHotel().getHotelName();
                                       double rate = hotel.getHotel().getRate();
                                       //String isAvailable = "";
                                       double distance = hotel.getHotel().getDistance();
                                       boolean isAvaliable = hotel.getIsAvailable();
                                       String meal = "";
                                       if(hotel.getHotel().getBreakfast() > 0)
                                           meal += "Breakfast ";
                                       if(hotel.getHotel().getDinner()> 0)
                                           meal += "Dinner ";
                                       if(hotel.getHotel().getLunch() > 0)
                                           meal += "Lunch ";
                                       System.out.println();
                                       /*if ()
                                           isAvailable = "Available";
                                        else
                                           isAvailable = "Not available";
                                       */
                                    %>
                                    <img class="h-image" src= "<%= (imgsrc)%>" >
                                </div>
                                <div class="col-12">
                                    <div class="row px-3 mt-4 mb-3">
                                        <p class="rating mb-0 px-2 mr-3"><strong> <%= rate %> </strong></p>
                                        <!--p class="text mb-0 mr-2 grade"><strong>Very Good</strong></p-->
                                        <p class="text mb-0 mr-2">&middot;</p>
                                    </div>
                                    <div class="row px-3">
                                        <h3 class="h-name"> 
                                        <span style="cursor:pointer;" > 
                                            <a  href="viewHotel.jsp?ID=<%=(hotel.getHotel().getId())%>" style="color: black;"> 
                                                <%= (HotelName) %> 
                                            </a> 
                                        </span>
                                        </h3>
                                    </div>
                                    <div class="info">
                                    <div class="row px-3 mb-2 mt-2">
                                        <span class="fa fa-star text-warning mr-1"></span> <span class="fa fa-star text-warning mr-1"></span> <span class="fa fa-star text-warning mr-1"></span> <span class="fa fa-star text-warning mr-1"></span> </div>
                                    <!--div class="row px-3">
                                        <h5 class="mb-1">1 bedroom &middot; 1 living &middot; 2 beds</h5>
                                    </div-->
                                    <div class="row px-3">
                                        <h5 class="mb-1"><%= (description) %></h5>
                                    </div>
                                    <div class="row px-3">
                                        <p class="mb-4"> <%= meal %> </p>
                                    </div>
                                    <div class="row px-3">
                                        <p class="mb-4"> <%= town %> &middot; <%= distance %> km from center</p>
                                    </div>
                                    <div class="row px-3">
                                        <% if (isAvaliable) { %>
                                            <h5 class="text-success mb-1"> Available</h5>
                                        <% }else{ %>
                                            <h5 class="text-warning mb-1"> Not Available</h5>
                                        <% } %>
                                    </div>
                                    </div>
                                    <div class="line"></div>
                                    <div class="row px-3 mb-4">
                                        <h2 class="text-success mb-1 font-weight-bold"> Expected Price : <%= (Cost)%></h2>
                                        
                                    </div>
                                    
                                </div>
                            </div>
                        </div>
                    <% } %>
                          
                </div>
            </div>

        </div>

    </body>
</html>
<% } %>