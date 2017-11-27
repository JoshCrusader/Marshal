<html>
<head>
	<title>Log in</title>
</head>
<body>
    
	<div class="loginBox">
            
		<div class="loginTitle" >
			<p><strong>Home Owners' Association</strong>
		</div>
		<div class="loginBody">
			
			<form method="post" action="loginServlet">
				<div class="inputBox">
					<input type="text" name= "uname" class="inputForm" placeholder="JoshCruzada">
                                                
                                </div>
				<div class="inputBox">
					<input type="password" name = "password" class="inputForm" placeholder="*******">
				</div>
                                
                                <div>
                                  <% 
                                        String mssg= (String) request.getAttribute("Invalid");
                                        if (mssg!= null){
                                            out.println("Username and password doesn't match. Please try again!");
                                        } 

                                     %>    
                                </div>
	  
				<div>
				
						<div align="center">
                                                    <button type="submit" name="submit" class="button">LOGIN</button>
                                                </div>	
	
				</div>
                            <center><a href="reg.jsp">Haven't signed up yet? Register now!</a></center>
			</form>
		</div>
	</div>
	
</body>
<script src="../jquery-3.2.1.min.js"></script>
<style>* {
	margin: 0;
	padding: 0;
}

body {
                background: #193D19;
}

.button {
    height: 30px;
    width: 100%;
    background-color: #001900;
    color: white;
    border: 0.5px solid #e5e5e5;
    padding: 5px 18px;
    text-align: center;
    text-decoration: none;
    display: inline-block;
    font-size: 20px;
    margin: 4px 2px;
    cursor: pointer;
    border-radius: 10px;
    font-family: Helvetica;
}
    
.button:hover {
    background-color: #e5e5e5;
    color: #000;
}

.loginBox {
    width: 360px;
    margin: 7% auto;
}

.loginTitle, .panel-title {

    font-size: 31px;
    text-align: center;
    margin-bottom: 25px;
    font-weight: 300;
    color: white;
}

.loginBody {
    background: #fff;
    padding: 20px;
    border-top: 0;
	border-radius: 10px;
    color: #666;
}

.loginText {
    margin: 0;
	font-size: 20px;
    text-align: center;
    padding: 0 20px 20px 20px;
}

.inputBox {
	margin-bottom: 15px;
}

.content-header {
	width: 75%;
	margin: auto;
	margin-bottom: 20px;
}
.panel-info {
    border-color: #bce8f1;
}

.panel {
    margin-bottom: 20px;
    background-color: #fff;
    border-radius: 4px;
    -webkit-box-shadow: 0 1px 1px rgba(0,0,0,.05);
    box-shadow: 0 1px 1px rgba(0,0,0,.05);
}

.panel-heading {
    padding: 10px 15px;
    border-bottom: 1px solid transparent;
    border-top-left-radius: 3px;
    border-top-right-radius: 3px;
	margin-right: -15px;
    margin-left: -15px;
}


.panel-title {
    margin-top: 0;
    margin-bottom: 0;
    font-size: 16px;
    color: inherit;
}

.inputForm {
    border-radius: 0;
    box-shadow: none;
    border-color: #d2d6de;
	display: block;
    width: 100%;
    height: 34px;
    padding: 6px 12px;
    font-size: 14px;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px
}

</style>
</html>