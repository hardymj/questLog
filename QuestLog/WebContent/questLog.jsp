<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">	
	<meta name="google-signin-client_id" content="588304591419-b19s1r974cq51chts43qvkr335bsgm30.apps.googleusercontent.com">
	
	<title>Insert title here</title>
	<link rel="stylesheet" href="stylesheet.css" type="text/css" media="all" />
	<script src="https://apis.google.com/js/platform.js" async defer></script>
</head>
<body>

<div id="main">
	<div class="content">
		<div class="content-headers">
			<div class="g-signin2" data-onsuccess="onSignIn"></div>
			<a href="#" onclick="signOut();">Sign out</a>
			<a href="#" id="nqBut">New Quest</a>
		</div>
		
		<div class="dashboard-wrapper dash-top">
		
			<div id="newQuest" class="square" style="display: none;">
				<p class="circle-label">New Quest</p>
				<form id="quest" action="QuestLog" method="post">		
					<div><label for="title">Title</label><input name="title" type="text"></input></div>
					<div><label for="description">Description</label><input name="description" type="text"></input></div>
					<div><label for="user">User</label><input name="user" type="text"></input></div>
					<div><label for="value">Value</label><input name="value" type="text"></input></div>
					<input type="submit" name="action" value="Submit"/>	
				</form>
			</div>
			<div id="selectedQuest" class="square" style="display: none;">
				<p class="circle-label">Selected Quest</p>
				<form id="quest" action="QuestLog" method="post">		
					<div><label for="selectedTitle">Title</label><p id="selectedTitle"></p></div>
					<div><label for="selectedDescription">Description</label><p id="selectedDescription"></p></div>
					<div><label for="selectedUser">User</label><p id="selectedUser"></p></div>
					<div><label for="selectedValue">Value</label><p id="selectedValue"></p></div>
					<div> 
						<input type="button" name="newQuest" value="New Quest"/>
						<input type="button" name="upvote" value="Upvote"/>
					</div>
				</form>
			</div>
			
			<div id="currentQuests" class="square">
				<div class="circle-label">
					<p>Current Quests</p>
					<p id="totalQuests">${quests.size()}</p>
				</div>
				<table>
				<tr><th>Idea</th><th>Title</th><th>Description</th><th>User</th><th>Value</th><th>Votes</th></tr>
				<c:forEach var="quest" items="${quests}">
				<tr>
					<td class="id">${quest.id}</td>
					<td class="title">${quest.title}</td>
					<td class="description">${quest.description}</td>
					<td class="user">${quest.user}</td>
					<td class="value">${quest.value}</td>
					<td class="votes">	
						<div class="upvote topic">
						<c:choose>
							<c:when test="${quest.voted == 1}">
								<a class="upvote upvote-on"></a>
							</c:when>
							<c:otherwise>
								<a class="upvote"></a>
							</c:otherwise>
						</c:choose>						    
						<span class="count">${quest.votes}</span>
					    <c:choose>
							<c:when test="${quest.voted == -1}">
								<a class="downvote downvote-on"></a>
							</c:when>
							<c:otherwise>
								<a class="downvote"></a>
							</c:otherwise>
						</c:choose>					
						    <!--<a class="star"></a>-->
						</div>				
					</td>
				</tr>
				</c:forEach>
				</table>	
			</div>
        </div>
	</div>
</div>
</body>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
<script type="text/javascript" src="lib/jquery.upvote.js"></script>
<link rel="stylesheet" href="lib/jquery.upvote.css" type="text/css" media="all" />
<script>

signedInCheck = null;

$(function () {
	
	$('table tr').on("click", function () {		
		//$(selectedQuest).show();
		$(newQuest).hide();		
		$(selectedTitle).text($(this).find(".title").text());
		$(selectedDescription).text($(this).find(".description").text());
		$(selectedUser).text($(this).find(".user").text());
		$(selectedValue).text($(this).find(".value").text());
	});	
	
	signedIn();
	
	function signedIn(){
		
		$('.topic').each(function(){
			$(this).upvote();
		});
		
		$('.upvote-enabled').on("click", function () {
			var user=1;
			var voted=0;
			var email="";
			var questId=$(this).closest("tr").find(".id").text();
			
			if($(this).hasClass("upvote-on")){
				voted = 1;
			}
			else if($(this).hasClass("downvote-on")){
				voted = -1;
			}
			else{
				voted = 0;
			}			
			var auth2 = gapi.auth2.getAuthInstance();
			if (auth2.isSignedIn.get()) {
				var profile = auth2.currentUser.get().getBasicProfile();
				user = profile.getId();
				email = profile.getEmail();
			}
			jQuery.ajax({
				type: "GET",
				url: "UserVoted",
				dataType:"html",
				data:"quest="+questId+"&user="+user+"&voted="+voted+"&email="+email,
				success:function(response){
				},
				error:function(xhr,err){
				}
			});
		});
	}
	signedInCheck = signedIn;
	
	
	
})
	
function onSignIn(googleUser) {
	var profile = googleUser.getBasicProfile();
	signedInCheck();
	
	
	//now need to add to signOut()
	
}
	
function signOut() {
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
    	$(".upvote-enabled").unbind("click");    
    	$(".topic").unbind("upvote");
    	$(".upvote").removeClass("upvote-on");
    	$(".downvote").removeClass("downvote-on");
    });
  }
</script>
</html>