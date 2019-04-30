<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--x
<%@ page import="com.model2.mvc.service.domain.*" %><%-- --%>

<%-- 
<% 
	Purchase purchase = (Purchase)request.getAttribute("purchase"); 
	User user = (User)session.getAttribute("user");
	
	System.out.println("addPurchase.jsp - Purchase : "+purchase);
	System.out.println("addPurchase.jsp - User : "+user);%>--%>



<html>
<head>
<title>상품 구매 완료</title>
</head>

<body>

<form name="updatePurchase" action="/updatePurchaseView.do?tranNo=${purchase.tranNo }" method="post">

다음과 같이 구매가 되었습니다.

<table border=1>
	<tr>
		<td>물품번호</td>
		<td>${purchase.purchaseProd.prodNo}</td>
	</tr>
	<tr>
		<td>구매자아이디</td>
		<td>${purchase.buyer.userId}</td>
	</tr>
	<tr>
		<td>구매방법</td>
		<td>
			<c:choose>
				<c:when test="${purchase.paymentOption=='1'}">현금구매</c:when>
				<c:when test="${purchase.paymentOption=='2'}">신용구매</c:when>
			</c:choose>
		</td>
	</tr>
	<tr>
		<td>구매자이름</td>
		<td>${!empty purchase.receiverName ? purchase.receiverName : ''}</td>
	</tr>
	<tr>
		<td>구매자연락처</td>
		<td>${!empty purchase.receiverPhone ? purchase.receiverPhone : ''}</td>
	</tr>
	<tr>
		<td>구매자주소</td>
		<td>${!empty purchase.divyAddr ? purchase.divyAddr : ''}</td>
	</tr>
		<tr>
		<td>구매요청사항</td>
		<td>${!empty purchase.divyRequest ? purchase.divyRequest : ''}</td>
	</tr>
	<tr>
		<td>배송희망일자</td>
		<td>${!empty purchase.divyDate ? purchase.divyDate : ''}</td>
	</tr>
</table>

<input type='submit' value='수정하기'/>
<a href="/listPurchase.do"><input type='button' value='목록보기'/></a>
</form>

</body>
</html>