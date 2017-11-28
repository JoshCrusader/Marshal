var ontoggle = false;
function sidebarFunction(){
	if ($(".css-hamburger").hasClass("active")){
			$(".css-hamburger").removeClass("active");
			$("#sidebartarget").removeClass("active");
			ontoggle = false
		}
		else{
			$("#sidebartarget").addClass("active");
			$(".css-hamburger").addClass("active");
			ontoggle = true;
		}
}
$(document).ready(function(){
	$(".css-darkness").on("click",function(){
		sidebarFunction();
	})
	$(".css-hamburger").on("click",function(){
		sidebarFunction();
		
	});

});