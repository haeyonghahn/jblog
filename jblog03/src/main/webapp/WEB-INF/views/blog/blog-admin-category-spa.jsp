<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>JBlog</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/jblog.css">
<link rel="stylesheet"
	href="https://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script type="text/javascript"
	src="${pageContext.request.contextPath }/assets/js/jquery/jquery-3.4.1.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/ejs/ejs.js"></script>
<script>

var listItemTemplate = new EJS({
	url: "${pageContext.request.contextPath }/assets/js/ejs/list-item-template.ejs"
});

var listTemplate = new EJS({
	url: "${pageContext.request.contextPath }/assets/js/ejs/list-template.ejs"
});

$(function() {
	// 카테고리 리스트
	$.ajax({
		url: "${pageContext.request.contextPath }/${authUser.id}/api/category/list",
		async: true,
		type: 'get',
		dataType: 'json',
		data: '',
		success: function(response) {
			
			if(response.result != "success") {
				console.error(response.message);
				return;
			}
			
			// rendering
			// $.each(response.data, function(index, vo) {
			//	render(vo);
			// });
			response.data.pageContext = "${pageContext.request.contextPath }";
			
			
			var html = listTemplate.render(response);
			$(".admin-cat").append(html);
		},
		error : function(xhr, status, e) {
			console.error(status + ":" + e);
		}
	});
	
	// 카테고리 추가
	$("#admin-category-add").submit(function() {
		event.preventDefault();
		
		var vo = {};
		vo.name = $("#input-name").val();
		vo.description = $("#input-desc").val();
		
		$(function() {
			$.ajax({
				url: "${pageContext.request.contextPath }/${authUser.id}/api/category/add",
				aync: true,
				type: 'post',
				dataType: 'json',
				contentType : 'application/json',
				data: JSON.stringify(vo),
				success: function(response) {
					if(response.result != "success") {
						console.error(response.message);
						return;
					}
					
					var no = $(".admin-cat tr").last().index();
					response.data.number = no + 1;
					response.data.pageContext = "${pageContext.request.contextPath }";
					
					var html = listItemTemplate.render(response.data);
					
					$(".admin-cat tr:last").after(html);
					$('#admin-category-add')[0].reset();
				},
				error : function(xhr, status, e) {
					console.error(status + ":" + e);
				}
			});
		});
	});
	
	// Live event : 존재하지 않는 element의 이벤트 핸들러를 미리 세팅해 놓는 것
	// 카테고리 삭제
	$(document).on("click", ".admin-cat td a", function(event) {
		event.preventDefault();
		
		var no = $(this).data('no')
		
		$(function() {
			$.ajax({
				url: "${pageContext.request.contextPath }/${authUser.id }/api/category/delete/" + no,
				aync: true,
				type: 'delete',
				dataType: 'json',
				contentType : 'application/json',
				data: '',
				success: function(response) {
					console.log(response.data);
					
					if(response.data != -1) {
						$(".admin-cat tr[data-no=" + response.data + "]").remove();
					} 
				},
				error : function(xhr, status, e) {
					console.error(status + ":" + e);
				}
			});
		});	
	});
});
</script>
</head>
<body>
	<div id="container">
		<c:import url="/WEB-INF/views/includes/blogheader.jsp"></c:import>
		<div id="wrapper">
			<div id="content" class="full-screen">
				<c:import url="/WEB-INF/views/includes/blogadminmenu.jsp"></c:import>
				<table class="admin-cat">
					<tr>
						<th>번호</th>
						<th>카테고리명</th>
						<th>포스트 수</th>
						<th>설명</th>
						<th>삭제</th>
					</tr>
					
				</table>
				<h4 class="n-c">새로운 카테고리 추가</h4>
				<form action="" method="post" id="admin-category-add">
					<table id="admin-cat-add">
						<tr>
							<td class="t">카테고리명</td>
							<td><input type="text" name="name" id="input-name"></td>
						</tr>
						<tr>
							<td class="t">설명</td>
							<td><input type="text" name="desc" id="input-desc"></td>
						</tr>
						<tr>
							<td class="s">&nbsp;</td>
							<td><input type="submit" value="카테고리 추가"></td>
						</tr>
					</table>
				</form>
			</div>
		</div>
		<c:import url="/WEB-INF/views/includes/blogfooter.jsp"></c:import>
	</div>
</body>
</html>