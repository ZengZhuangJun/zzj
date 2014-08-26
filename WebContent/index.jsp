<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@ include file="/WEB-INF/pages/common/header.jsp"%> 
<title>Insert title here</title>
</head>
<body>
	<%@ include file="/WEB-INF/pages/common/loading.jsp"%>
	<div class="top-bar-inner">
		<div class="top-bar-user">
			<a href="javascript:void(0);"> <img id="user-icon"
				src="<c:url value='/images/portrait.gif'/>" class="top-bar-icon">
			</a>
		</div>
		<div class="top-bar-seach">
			<form action="">
				<input placeholder="请输入搜索内容">
				<div class="seaech" onclick="alert(1)"></div>
			</form>
		</div>
		<div class="top-bar-logo">
			<span>吃点啥</span>
		</div>
		<div class="top-bar-links"></div>
	</div>
	<div class="content">
		<div class="cleft">
			<div>
			</div>
		</div>
		<div class="cleft">
			<div>
			</div>
		</div>
	</div>
</body>
</html>