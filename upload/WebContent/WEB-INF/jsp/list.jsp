<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/3.3.7/css/bootstrap.min.css"/>
<script src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<title>Insert title here</title>
<style type="text/css">
	#images{
		width: 50px;
		height: 50px;
	}
</style>
</head>
<body>
	<table class="table table-bordered table-hover">
		<tr>
        	<th>名字</th>
            <th>基因序列</th>
        </tr>
		<c:forEach items="${list}" var="product" >
	        <tr>
	        	<th>${product.name }</th>
				<c:if test="${product.head != null}">
					<th>${product.head}<span style="color: red">${product.ten}</span>${product.end}</th>
				</c:if>
				<c:if test="${product.head == null}">
					<th>${product.gene}</th>
				</c:if>
	        </tr>
	    </c:forEach>
    </table>
</body>
</html>