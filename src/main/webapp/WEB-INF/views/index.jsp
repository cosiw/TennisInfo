<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<!--
	Strongly Typed by HTML5 UP
	html5up.net | @ajlkn
	Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
	<head>
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<title>Tennis Info</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1, user-scalable=no" />
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
		<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
		

	</head>
	<body class="homepage is-preload">
		<div id="page-wrapper">

			<!-- Header -->
				<section id="header">
					<div class="container">

						<!-- Logo -->
							<h1 id="logo"><a href="test">TENNIS INFO</a></h1>
							<p>테니스 대회 정보와 선수 랭킹 정보를 제공합니다.</p>

						<!-- Nav -->
							<nav id="nav">
								<ul>
									<li><a class="icon solid fa-home" href="test"><span>Introduction</span></a></li>
									<li>
										<a href="#" class="icon fa-chart-bar"><span>Dropdown</span></a>
										<ul>
											<li><a href="#">Lorem ipsum dolor</a></li>
											<li><a href="#">Magna phasellus</a></li>
											<li><a href="#">Etiam dolore nisl</a></li>
											<li>
												<a href="#">Phasellus consequat</a>
												<ul>
													<li><a href="#">Magna phasellus</a></li>
													<li><a href="#">Etiam dolore nisl</a></li>
													<li><a href="#">Phasellus consequat</a></li>
												</ul>
											</li>
											<li><a href="#">Veroeros feugiat</a></li>
										</ul>
									</li>
									<li><a class="icon solid fa-cog" href="left-sidebar.html"><span>Left Sidebar</span></a></li>
									<li><a class="icon solid fa-retweet" href="right-sidebar.html"><span>Right Sidebar</span></a></li>
									<li><a class="icon solid fa-sitemap" href="no-sidebar.html"><span>No Sidebar</span></a></li>
								</ul>
							</nav>

					</div>
				</section>

			<!-- Features -->
				<section id="features">
					<div class="container">
						<header>
							<h2>랭킹 정보</h2>
						</header>
						<div class="row aln-center">
							<c:forEach var="player" items="${players}">
									<div class="col-4 col-6-medium col-12-small">
										<section>
											<a href="#" class="image featured">
												<img src='${player.image}' alt="" />
											</a>
											<h3>${player.playerName}</h3>
											<p style="text-align: left;">
												현재랭킹: ${player.rank} </br>
												타이틀 (싱글): 0 </br>
												상금: ${player.przYtdS} </br>
												승/패 (승률): </p>
										</section>
									</div>
							</c:forEach>
						</div>
					</div>
				</section>

			<!-- Main -->
			<div class="container">
			  	<table class="ranking-table">
                        <thead>
                            <tr>
                                <th>Rank</th>
                                <th>Player</th>
                                <th>Country</th>
                                <th>Points</th>
                            </tr>
                        </thead>
                        <tbody id="rankingTableBody">
                            
                        </tbody>
                    </table>

			</div>

			<!-- Footer -->
				<section id="footer">
					<div class="container">
						<header>
							<h2>Questions or comments? <strong>Get in touch:</strong></h2>
						</header>
						<div class="row">
							<div class="col-6 col-12-medium">
								<section>
									<form method="post" action="#">
										<div class="row gtr-50">
											<div class="col-6 col-12-small">
												<input name="name" placeholder="Name" type="text" />
											</div>
											<div class="col-6 col-12-small">
												<input name="email" placeholder="Email" type="text" />
											</div>
											<div class="col-12">
												<textarea name="message" placeholder="Message"></textarea>
											</div>
											<div class="col-12">
												<a href="#" class="form-button-submit button icon solid fa-envelope">Send Message</a>
											</div>
										</div>
									</form>
								</section>
							</div>
							<div class="col-6 col-12-medium">
								<section>
									<p>Erat lorem ipsum veroeros consequat magna tempus lorem ipsum consequat Phaselamet
									mollis tortor congue. Sed quis mauris sit amet magna accumsan tristique. Curabitur
									leo nibh, rutrum eu malesuada.</p>
									<div class="row">
										<div class="col-6 col-12-small">
											<ul class="icons">
												<li class="icon solid fa-home">
													1234 Somewhere Road<br />
													Nashville, TN 00000<br />
													USA
												</li>
												<li class="icon solid fa-phone">
													(000) 000-0000
												</li>
												<li class="icon solid fa-envelope">
													<a href="#">info@untitled.tld</a>
												</li>
											</ul>
										</div>
										<div class="col-6 col-12-small">
											<ul class="icons">
												<li class="icon brands fa-twitter">
													<a href="#">@untitled</a>
												</li>
												<li class="icon brands fa-instagram">
													<a href="#">instagram.com/untitled</a>
												</li>
												<li class="icon brands fa-dribbble">
													<a href="#">dribbble.com/untitled</a>
												</li>
												<li class="icon brands fa-facebook-f">
													<a href="#">facebook.com/untitled</a>
												</li>
											</ul>
										</div>
									</div>
								</section>
							</div>
						</div>
					</div>
					<div id="copyright" class="container">
						<ul class="links">
							<li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
						</ul>
					</div>
				</section>

		</div>

		<!-- Scripts -->
			<!-- <script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/jquery.dropotron.min.js"></script>
			<script src="assets/js/browser.min.js"></script>
			<script src="assets/js/breakpoints.min.js"></script>
			<script src="assets/js/util.js"></script>
			<script src="assets/js/main.js"></script> -->

      <script>
        $(document).ready(function(){
			$.ajax({
				url: 'http://localhost:8085/rankList',  // 호출할 API URL
                type: 'GET',
                dataType: 'json',
                success: function(data) {
					var tableBody = $('#rankingTableBody');
					tableBody.empty();

					data.forEach(function(rank) {
                        var row = '<tr>' +
							'<td>' + rank.rank + '</td>' + 
							'<td>' + rank.playerName + '</td>' + 
							'<td>' + rank.country + '</td>' + 
							'<td>' + rank.livePt + '</td>' + 
							'</tr>';

							tableBody.append(row);
                    });
                },
                error: function(error) {
                    // 오류 처리
                    console.log(error);
                }
			})
        });
		console.log("hi : ", )
		// $(document).ready(function(){
		// 	$.ajax({
		// 		url: 'http://localhost:8085/api/rank/top',  // 호출할 API URL
        //         type: 'GET',
        //         dataType: 'json',
        //         success: function(data) {
		// 			let html = '';
		// 			console.log("data :", data);
		// 			data.forEach(rank => {
        //                 html += `
		// 				<section>
		// 				<a href="#" class="image featured">
        //                     <img src= ${rank.image} alt="" />
        //                 </a>
        //                 <h3>${rank.playerName}</h3>
        //                 <p style="text-align: left;">
        //                     현재랭킹: ${rank.rank} </br>
        //                     타이틀 (싱글): ${player.titles} </br>
        //                     상금: ${rank.przYtdS} </br>
        //                     승/패 (승률): ${rank.matchYtdWS}/${rank.matchYtdLS}(${winRate})</p>
        //             	</section>`;
        //             });
		// 			$('#player-info').html(html);
        //         },
        //         error: function(error) {
        //             // 오류 처리
        //             console.log(error);
        //         }
		// 	})
        // });
      </script>
	</body>
</html>