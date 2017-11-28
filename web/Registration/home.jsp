<%-- 
    Document   : userManagement
    Created on : 11 28, 17, 5:09:32 PM
    Author     : User
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@page import="java.util.ArrayList"%>
<%@page import="Objects.User"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.DriverManager"%>

<html>
    <head>
        <link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/Core/css_standard.css">
	 <script src="<%= request.getContextPath() %>/Core/jquery.js"></script>
	 <script src="<%= request.getContextPath() %>/Core/css_scripts.js"></script>


 	<script src="js/jquery-3.2.1.min.js"></script>
        
</head>
<body>
    <%@include file="navs.jsp"%>
    <div id = "css-id-body">
   <div class="slideshow-container">

  <div class="mySlides fade">
    <div class="numbertext">1 / 4</div>
    <img src="http://argohughes.com/wp-content/uploads/2013/01/homeowners-association.jpg" style="width: 800px; height: 350px;">
    <div class="text">St. Mary's Village</div>
  </div>

  <div class="mySlides fade">
    <div class="numbertext">2 / 4</div>
    <img src="https://3.bp.blogspot.com/-5-zn0wj30P4/WGKHJl_jSCI/AAAAAAAADUM/vcuG23uelrUiG8ePX2orgaoDsnLNNRUqACLcB/s1600/Design%2BVillage%2BPenang.JPG" style="width: 800px; height: 350px;">
    <div class="text">Parkings</div>
  </div>

  <div class="mySlides fade">
    <div class="numbertext">3 / 4</div>
    <img src="http://natickbasketball.com/wp-content/uploads/2015/07/ben-hem-court.jpg" style="width:800px; height: 350px;">
    <div class="text">Basketball Court</div>
  </div>

    <div class="mySlides fade">
    <div class="numbertext">4 / 4</div>
    <img src="http://www.harmonyreserve.com/wp-content/uploads/2015/01/Harmony-Club-House-3-23-160007.jpg" style="width:800px; height: 350px;">
    <div class="text">Grand Clubhouse</div>
  </div>
  </div>
  <br>

  <div style="text-align:center">
    <span class="dot"></span> 
    <span class="dot"></span> 
    <span class="dot"></span> 
    <span class="dot"></span> 
  </div>
  <div class="container">

<header>
   <h1>Announcement! </h1>
</header>
  
<nav>
  <ul>
    <li><a href="#">29/11/2017</a></li>
 
  </ul>
</nav>

<article>
  
    <p style="color:black">Stickers are now available for claiming. Please proceed to the office if you have questions</p>
</article>


</div>
    </div>
    <script>
  var slideIndex = 0;
  showSlides();

  function showSlides() {
      var i;
      var slides = document.getElementsByClassName("mySlides");
      var dots = document.getElementsByClassName("dot");
      for (i = 0; i < slides.length; i++) {
         slides[i].style.display = "none";  
      }
      slideIndex++;
      if (slideIndex > slides.length) {slideIndex = 1}    
      for (i = 0; i < dots.length; i++) {
          dots[i].className = dots[i].className.replace(" active", "");
      }
      slides[slideIndex-1].style.display = "block";  
      dots[slideIndex-1].className += " active";
      setTimeout(showSlides, 2000); // Change image every 2 seconds
  }
  </script>
</body>
<style>
    div.container {
    width: 800px;
    border: 1px solid gray;
    left: 20%;
    position: relative;
    top: 30px;
}

header, footer {
    padding: 1em;
    color: white;
    background-color: #1e453e;
    clear: left;
    text-align: center;
}

nav {
    float: left;
    max-width: 160px;
    margin: 0;
    padding: 1em;
}

nav ul {
    list-style-type: none;
    padding: 0;
}
   
nav ul a {
    text-decoration: none;
}

article {
    margin-left: 170px;
    border-left: 1px solid gray;
    padding: 1em;
    overflow: hidden;
}
  * {box-sizing:border-box}
  body {font-family: Verdana,sans-serif;}
  .mySlides {display:none}

  /* Slideshow container */
  .slideshow-container {
   
    position: relative;
    left: 20%;
    top: 20px;
   
    
  }

  /* Caption text */
  .text {
    color: #f2f2f2;
    font-size: 15px;
    padding: 8px 12px;
    position: absolute;
    bottom: 8px;
    width: 100%;
    text-align: center;
  }

  /* Number text (1/3 etc) */
  .numbertext {
    color: #f2f2f2;
    font-size: 12px;
    padding: 8px 12px;
    position: absolute;
    top: 0;
  }

  /* The dots/bullets/indicators */
  .dot {
    height: 15px;
    width: 15px;
    margin: 0 2px;
    background-color: #bbb;
    border-radius: 50%;
    display: inline-block;
    transition: background-color 0.6s ease;
  }

  .active {
    background-color: #717171;
  }

  /* Fading animation */
  .fade {
    -webkit-animation-name: fade;
    -webkit-animation-duration: 1.5s;
    animation-name: fade;
    animation-duration: 1.5s;
  }

  @-webkit-keyframes fade {
    from {opacity: .4} 
    to {opacity: 1}
  }

  @keyframes fade {
    from {opacity: .4} 
    to {opacity: 1}
  }

  /* On smaller screens, decrease text size */
  @media only screen and (max-width: 300px) {
    .text {font-size: 11px}
  }
  </style>



</html>
