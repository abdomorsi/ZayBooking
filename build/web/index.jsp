<%-- 
    Document   : signup
    Created on : Dec 30, 2020, 1:23:21 AM
    Author     : abdom
--%>

<html>
    <head>        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://www.google.com/recaptcha/api.js"></script>
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Roboto|Courgette|Pacifico:400,700">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.0/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="myStyle/formstyle.css">
        <style>
            .Captcha{
                border-radius: 3px;
                border: none;
                margin: 3px;
                width: 44%;
                padding: .375rem .75rem;
                box-shadow: none;
                
            }
            .Captcha:focus {
                border-color: #00cb82;
            }
            .CaptchaView{
                webkit-user-select: none;  /* Chrome all and Safari all */
                moz-user-select: none;     /* Firefox all */
                ms-user-select: none;      /* Internet Explorer 10 and later */
                user-select: none;          /* Likely future */
            }
            .CaptchaBtn{
                background: #d39e00;
                border: none;
            }
        </style>
        <title> Register </title>
    </head>
    <body onload="Captcha();">
        <div class="myform">
            <form action="signupServlet" onsubmit="return(check(this));" method="POST">
		<div class="form-header">
			<h2>zayBooking</h2>
			<p>Fill out this form to Sign up!</p>
		</div>
                <div class="form-group">
			<label>Username</label>
                        <input type="text" class="form-control" name="username" required="required">
                </div>

                <div class="form-group">
            		<label>Email Address</label>
                        <input type="email" class="form-control" onchange="return checkEmail();" id="email" name="email"  required="required">
                        <p id="error"></p>
                </div>
                
                <div class="form-group">
                        <label for="phone">Phone Number</label>
                        <input type="tel" class="form-control" id="phone" name="phone" placeholder="01*********" required>
                </div>
                
		
                <div class="form-group">
                    <label>Captche</label><br>
                    <input type="text" class="Captcha CaptchaView" id="mainCaptcha" readonly=""/>
                    <span style="cursor:pointer;" ><i class="fa fa-refresh" onclick="Captcha();" style="font-size:20px; "></i> </span>
                    <input type="text" class="Captcha" id="txtInput"/> 
		</div>
                <div class="form-group">
			<label class="form-check-label"><input type="checkbox" required="required"> I accept the <a href="#">Terms of Use</a> &amp; <a href="#">Privacy Policy</a></label>
		</div>
                
		<div class="form-group">
                    <button type="submit" onclick="return (ValidCaptcha());" class="btn btn-primary btn-block btn-lg">Sign Up</button>
		</div>
                <div class="text-center small">
                    Already have an account? <a href="login.jsp">Login here</a></div>
                </div>
        </form>
        

    </body>
</html>


<script type="text/javascript">
    function Captcha(){
        var alpha = new Array('A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z','a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z');
        var i;
        for (i=0;i<6;i++){
          var a = alpha[Math.floor(Math.random() * alpha.length)];
          var b = alpha[Math.floor(Math.random() * alpha.length)];
          var c = alpha[Math.floor(Math.random() * alpha.length)];
          var d = alpha[Math.floor(Math.random() * alpha.length)];
          var e = alpha[Math.floor(Math.random() * alpha.length)];
          var f = alpha[Math.floor(Math.random() * alpha.length)];
          var g = alpha[Math.floor(Math.random() * alpha.length)];
         }
       var code = a + ' ' + b + ' ' + ' ' + c + ' ' + d + ' ' + e + ' '+ f + ' ' + g;
       document.getElementById("mainCaptcha").value = code
     }
    function ValidCaptcha(){
         var string1 = removeSpaces(document.getElementById('mainCaptcha').value);
         var string2 = removeSpaces(document.getElementById('txtInput').value);
         if (string1 == string2){
           return true;
         }
         else{
           alert("wrong capatch");
           return false;
         }
     }
    function removeSpaces(string){
       return string.split(' ').join('');
     }
    
    function check(form){
        if (isNaN(form.phone.value)){
            alert("phone must be only numbers");
            return false;
        }
        return true;
    }

    function checkEmail(){
        var name = document.getElementById("email").value;
        var xmlhttp = new XMLHttpRequest();
        xmlhttp.open("GET","signupServlet?email="+name,true);
        xmlhttp.send();
        xmlhttp.onreadystatechange=function()
        {
            if (xmlhttp.readyState==4 && xmlhttp.status==200)
            {
                if(xmlhttp.responseText == "found")
                {
                    alert("this email exists");
                    document.getElementById("email").value = "";
                    return false;
                }
            }
        }
    }
</script>  