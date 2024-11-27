<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>

<html lang="en">
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
		<title>Tennis Info</title>
		<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/assets/css/main.css" />
		<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous">
		<link href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css" rel="stylesheet">
	</head>
	<body>
		<jsp:include page="Common/header.jsp"/>
		<section class="container">
			<div class="p-3 pt-md-5 pb-md-4 text-center">
				<h2 class="my-4">TOP 10</h2>
			</div>
			<div class="row g-3 mb-5 text-cneter my-3">
				<c:forEach var="player" items="${players}">
				<div class="col-12 col-md-4">
					<div class="card">
							<div class="card-header"><h4>Rank ${player.rankS}</h4></div>
							<div class="card-body">
								<a href="/player/${player.playerId}">
									<img src="${player.image}" alt="Image">
								</a>
							</div>
							<div class="card-footer">
								<h3 class="card-title text-primary">${player.playerName}</h3>
								<ul class="list-unstyled my-4">
									<li>타이틀 (싱글) : ${player.crPrz}</li>
									<li>상금 : ${player.przYtdS}</li>
									<li>승/패(승률) : ${player.matchYtdWs}/${player.matchYtdLs}(${player.winRate}%)</li>
								</ul>
							</div>
						
					</div>
				</div>
				</c:forEach>
				
			</div>
		</section>
		<!-- Bootstrap JS -->
		<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	</body>

</html>