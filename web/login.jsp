<%-- 
    Document   : login
    Created on : Dec 31, 2020, 11:03:52 AM
    Author     : abdom
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Courgette|Pacifico:400,700">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="myStyle/formstyle.css">
    </head>
    <body>

        <div class="myform" id="loginForm" >
            <form action="loginServlet" class="form-container" method="POST">
                <div class="form-header">
			<h2>zayBooking</h2>
		</div>
                
                <div class="form-group">
                    <label>Email Address</label>
                    <input type="email" class="form-control" name="email" required="required">
                </div>
                
                <div class="form-group">
                    <label>Password</label>
                    <input type="password" class="form-control" name="password" required="required">
                </div>
                
                <div class="form-group">
			<button type="submit" class="btn btn-primary btn-block btn-lg">Sign In</button>
		</div>

                <!--div class="form-group">
			<button type="button" class="btn btn-primary btn-block btn-lg" onclick="closeForm()">Close</button>
		</div-->
                <div class="text-center small">
                Don't have an account? <a href="signup.jsp">sign up here</a></div>
            </form>
            
        </div>


        <script>
            function openForm() {
                document.getElementById("loginForm").style.display = "block";
            }
            function closeForm() {
                document.getElementById("loginForm").style.display = "none";
            }
        </script>
    </body>

</html>
