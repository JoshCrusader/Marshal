<!DOCTYPE html>
<html>
<head>    
<center><h1 class="title" style="color:white">Registration of Account</h1></center>
<link rel="stylesheet" type="text/css" href="reg.css">
<script src='jquery-3.2.1.min.js'></script>
</head>
<body>

<div class="container">
				<form action="Servlet" method="POST">
					<div class="info" id="pInfo">
						<h2><strong> Personal Information</strong> </h2>	
					</div>       
							<div id="fNameRow">
								<label>First Name</label><br>
								<input type="text" class="field" name="fName" id="fName" placeholder="Enter First Name Here.." required>
							</div>
							<div id="mNameRow">
								<label>Middle Name</label><br>
								<input type="text" class="field" name="mName" id="mName" placeholder="Enter Middle Name Here.." required>
							</div>
							<div id="lNameRow">
								<label>Last Name</label><br>
								<input type="text" class="field" name="lName" id="lName" placeholder="Enter Last Name Here.."  required>
							</div>
							<div id="birthRow">
								<label>Birth Date</label>
                                <input type="date" name="bDate" id="bDate" placeholder="09-20-17" class="field" style="width:375px"required><br>
							</div>
							<div id="occRow">
							<label>Occupation</label>
                                 <input type="text" name="occupation" id="occupation" placeholder="Enter your occupation" class="field" style="width:377px" required><br>	
                            </div>
							<div id="telNoRow">
								  <label>Telephone Number:</label><br>
                                  <input type="number" name="tNum" id="tNum" placeholder="8537011" class="field" style="width:375px" min= "7" max="7" required><br>
                            </div>
                            <div id= "mNoRow">  
                                  <label>Mobile Number:</label>
                                  <input type="number" name="mNum" id="mNum" placeholder="09176492934" class="field" min= "10" max="10" style="width:375px" required><br>
                            </div>                
             		<div class="info" id="aInfo">
						<h2><strong> Address</strong> </h2>	
					</div>      		
							<div id="bNoRow">
								<label>Block No.</label>
								<input type="text" name="bNo" id="bNo" placeholder="Enter your block no" class="field" required><br>
							</div>
							<div id="lNoRow">
								<label>Lot No.</label>
								<input type="text" name="lNo" id="lNo" placeholder="Enter your lot number" class="field" required><br>
                                                                 
                                                        </div>
							<div id="sRow">
								<label>Street</label>
								<input type="text" name="street" id="street" placeholder="Enter your street name" class="field" required><br>
                                                                    <% 
                                                                        String mssg3= (String) request.getAttribute("existingBno");
                                                                        if (mssg3!= null){
                                                                            out.println("<font color='red'> Your homeowner must have an account first.</font>");
                                                                        } 

                                                                     %>  
							</div>
					<div class="info" id="v">
						<h2><strong> Vehicles</strong> </h2>	
					</div>

					<div id= "label">
						<label style="position: relative; left: -0.5px; top: -19.5em">Plate Number: </label>
						<label style="position: relative; left: 13.5em; top: -19.5em">Car Model </label>
					</div>
                        <div id="moreV">
                                 <!--<label style="position: relative; left: -10px; top: -19em">Plate Number: </label>!-->
                                 <input type="text" name="v1" id="v1" placeholder="Enter Vehicle Plate Number" class="vehicles" style="width:300px">
                                 <!--<label style="position: relative; left: 7em; top: -19em">Car Model: </label>!-->
                                 <input type="text" name="c1" id="c1" placeholder="Car Model" class="vehicles" style="width:300px">
                                 <div id="b">
                                     <button type="button" onclick="moreV()">Add More Vehicle </button>
                                 </div>
                        </div>	
                   <div class="info" id="accInfo">
                   		<h2><strong> Account Information</strong> </h2>	
                   </div>
                    	<div id="em">
	                            <label>Email</label>
	                            <input type="email" name="email" id="email" placeholder="Enter Email Address Here.." class="field" style="width:375px" required><br>
	                    </div>
                   		<div id="uName">
                                <label>Username</label>
                                <input type="text" name="username" id="username" placeholder="Enter Email Address Here.." class="field" style="width:375px" required><br>
                                 <% 
                                    String mssg= (String) request.getAttribute("Existing");
                                    if (mssg!= null){
                                        out.println("<font color='red'>Username already exists</font>");
                                    }
                                 %>    
                             </div>
                       <div id="pass">
                                <label>Password</label>
                                <input type="password" name="pw" id="pw" placeholder="Enter Password Here.." class="field" style="width:375px" required><br>
                       </div>
                       <div id="rpass">
                                <label>Re-Type Password</label>
                                <input type="password" name="pw2" id="pw2" placeholder="Re-type Password Here..." class="field" style="width:375px" required><br>
                                <% 
                                        String mssg2= (String) request.getAttribute("Invalid");
                                        if (mssg2!= null){
                                            out.println("<font color='red'> Password doesn't match. Please try again! </font>");
                                        } 
                                 %>    
                       </div>
                                 
                       <div id ="user">
                                <label>User Type</label><br>
                                <select name="usertype" id="usertype" style="width:375px; height: 22px" >
                                    <option id="usertype" value=1>Home Owner
                                    <option id="usertype"  value=2>System Administrator
                                    <option id="usertype"  value=3> Security 
                                    <option id="usertype"  value=4> Board Member
                                </select><br>
                       </div>
                       <div id="la">
                                <label>Living As</label><br>
                                   <select name="livingAs" id="livingAs" style="width:375px; height: 22px">
                                     <option value=1>Home Owner</option>
                                     <option value=2>Home Member</option>
                                     <option value=3>Kasambahay</option>
                                   </select>
                       </div>

                       <div id ="isRenting"style="position: relative;left: -10px; top:-28em">
                           <input type = "radio" name = "rent" id="rent" value=0 style="display: none" checked>
                           <input type = "radio" name = "rent" id="rent" value=1 style="display: none">
                       </div>
                    <div class="rc">                               
					<a href=#> Read Terms and Conditions </a>
					</div>
					<div align="center" style="position: relative;top:-26em">
					<button type="submit" class="button">Submit</button>					
					</div>
                                 
				</form> 

</div>	
</body>

<script>
    
    $(document).ready(function(){
        $("#livingAs").change(function(event){
        if(document.getElementById("livingAs").value == 2){
            document.getElementById("isRenting").innerHTML = "Renting: <input type = 'radio' name = 'rent' id='rent' value=0 checked> Yes\n\
                                                              <input type = 'radio' name = 'rent' id='rent' value=1> No";
        }
        });
    });
    
    function moreV(){
            $("#moreV").append("<input type='text' name='v1' placeholder='Enter Vehicle Plate Number' class='vehicles'  style= 'width:300px'>\n\
                              <input type='text' name='c1' id='c1' placeholder='Car Model' class='vehicles'  style= 'width:300px'>");
    }
</script>
                         
<style>
body{
    background-color: #193D19;
}
.container{
    margin-left: 20%;
    width: 60%;
    background: #ededed;
    border: solid 0.5px #e9e9e9 ;
    border-radius: 10px;
    padding: 20px ;  
    
}

.field{
    width: 250px;
    box-shadow: none;
    border-color: #d2d6de;
    display: block;
    font-size: 14px;
    line-height: 1.42857143;
    color: #555;
    background-color: #fff;
    background-image: none;
    border: 1px solid #ccc;
    border-radius: 4px;
}

.info{
    position: relative;
    top:-1em;
    border-bottom: solid 0.5px;
    border-spacing:0;
}

#v{
    position: relative;
    top: -20em;
}
#aInfo{
    position: relative;
    top:-13em;
}

#fNameRow{
    position: relative;  
    left: -10px;
}

#mNameRow{
    position: relative;
    top: -42px;   
    left: 15.5em;
}

#lNameRow{
    position: relative;
    top: -84px;   
    left: 31.5em;
}

#birthRow{
    position: relative;  
    left: -10px;
    top: -4.5em;
}

#occRow{
    position: relative;  
    left: 23.5em;
    top: -8.3em;
}


#telNoRow{
    position: relative;  
    left: -10px;
    top: -8.6em;
}

#mNoRow{
    position: relative;  
    left: 23.5em;
    top: -12.39em;
}

#bNoRow{
    position: relative;  
    left: -10px;
    top: -12em;
}

#lNoRow{
    position: relative;  
    left: 15.5em;
    top: -15.8em;
}

#sRow{
    position: relative;  
    left: 31.5em;
    top: -19.55em;
}

.vehicles{
    position: relative;
    top: -23em;
}

#b{
    position:relative;
    top: -20.5em;
    left: 39em;
}

#accInfo{
    position: relative;
    top: -20em;
}

#uName{
    position: relative;

}

#em{
    position: relative;
    top: -19em;
    left: -10px;
}

#uName{
    position: relative;
    top: -22.7em;
    left: 23.5em;
}

#pass{
    position: relative;
    left: -10px;
    top: -23em;
}

#rpass{
    position: relative;
    left: 23.5em;
    top: -26.8em;
}

#user{
    position: relative;
    left:-10px;
    top: -26.8em;

}
#la{
    position: relative;
    left: 23.5em;
    top: -29.3em;
}
.rc{
    position: relative;
    top: -27em;
    left: -10px;   
}

.button {
    height: 30px;
    width: 100px;
    background-color: #161616;
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
</style>

</html>

