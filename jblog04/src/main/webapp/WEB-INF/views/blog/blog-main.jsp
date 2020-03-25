<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%
	pageContext.setAttribute("newLine", "\n");
%>	
	
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<Link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blogheader.jsp"></c:import>
		<div id="wrapper">
			<div id="content">
				<div class="blog-content">
					<h4>${postVoSingle.title }</h4>
					<p>
						${fn:replace(postVoSingle.contents, newLine , "<br>") }
					<p>
				</div>
				<ul class="blog-list">
					<c:forEach items="${listPost }" var="vo">
						<li><a href="${pageContext.request.contextPath }/${blogVo.id }/${categoryNo }/${vo.no }">${vo.title }</a><span>${vo.regDate }</span></li>
					</c:forEach>
				</ul>
			</div>
		</div>

		<div id="extra">
			<div class="blog-logo">
				<img
					src="${pageContext.request.contextPath}${blogVo.logo }">
			</div>
		</div>

		<div id="navigation">
			<h2>카테고리</h2>
			<ul>
				<c:forEach items="${listCategory }" var="categoryVo">
				<li><a href="${pageContext.request.contextPath }/${blogVo.id }/${categoryVo.no }">${categoryVo.name }</a></li>
				</c:forEach>
			</ul>
		</div>
		<c:import url="/WEB-INF/views/includes/blogfooter.jsp"></c:import>
	</div>
</body>
</html>