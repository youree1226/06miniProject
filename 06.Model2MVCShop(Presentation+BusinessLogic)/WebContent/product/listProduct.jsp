<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
<%@ page import="java.util.*"  %>
<%@ page import="com.model2.mvc.service.domain.*" %>
<%@ page import="com.model2.mvc.common.*" %>
<%@page import="com.model2.mvc.common.util.CommonUtil"%> --%>

<%System.out.println("==============================");
System.out.println("listProduct.jsp - START"); %>

<%--
<%
	User user = (User)session.getAttribute("user");
	String menu = request.getParameter("menu");
	System.out.println("listProduct.jsp - menu : "+menu);
	
	List<Product> list= (List<Product>)request.getAttribute("list");
	Page resultPage=(Page)request.getAttribute("resultPage");
	Search search = (Search)request.getAttribute("search");
	
	String searchCondition = CommonUtil.null2str(search.getSearchCondition());
	String searchKeyword = CommonUtil.null2str(search.getSearchKeyword());
	
	System.out.println("listProduct.jsp - searchCondition : "+searchCondition);
	System.out.println("listProduct.jsp - searchKeyword : "+searchKeyword);
	
%> --%>


<html>
<head>
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<script type="text/javascript">

	function fncGetUserList(currentPage){
		document.getElementById("currentPage").value = currentPage;
	   	document.detailForm.submit();		
	}
	
	function fncGetProductList(currentPage, ascendingCondition){
		document.getElementById("currentPage").value = currentPage;
		document.getElementById("ascendingCondition").value = ascendingCondition;
	   	document.detailForm.submit();		
	}
	
	function fncGetProductList2(currentPage, onSale){
		document.getElementById("currentPage").value = currentPage;
		document.getElementById("onSale").value = onSale;
	   	document.detailForm.submit();		
	}
	
</script>
</head>

<body bgcolor="#ffffff" text="#000000">

<div style="width:98%; margin-left:10px;">
<%--
<%if(searchKeyword.equals("")){ %>
<form name="detailForm" action="/listProduct.do?menu=<%=menu%>" method="post">
<%}else{ %>
<form name="detailForm" action="/listProduct.do?menu=<%=menu%>
&searchCondition=<%=searchCondition %>&searchKeyword=<%=searchKeyword%>" method="post">
<%} %>
 --%>
 
 <%--
<c:if test="${empty search.searchKeyword}">
	<c:choose>
		<c:when test="${empty search.ascendingCondition}">
			<form name="detailForm" action="/listProduct.do?menu=${menu}" method="post">
		</c:when>
		<c:when test="${!empty search.ascendingCondition}">
			<form name="detailForm" action="/listProduct.do?menu=${menu}&ascendingCondition=${search.ascendingCondition}" method="post">
		</c:when>
	</c:choose>	
</c:if>
<c:if test="${!empty search.searchKeyword}">
	<c:choose>
		<c:when test="${empty search.ascendingCondition}">
			<form name="detailForm" action="/listProduct.do?menu=${menu}&searchCondition=${search.searchCondition}
			&searchKeyword=${search.searchKeyword}" method="post">
		</c:when>
		<c:when test="${!empty search.ascendingCondition}">
			<form name="detailForm" action="/listProduct.do?menu=${menu}&searchCondition=${search.searchCondition}
			&searchKeyword=${search.searchKeyword}&ascendingCondition=${search.ascendingCondition}" method="post">
		</c:when>
	</c:choose>
</c:if> --%>

<form name="detailForm" action="/listProduct.do?menu=${menu}" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left:10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">
							상품 관리
					</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif" width="12" height="37"/>
		</td>
	</tr>
</table>


<!-- <table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">

</table> -->


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
	<input type="hidden" id="onSale" name="onSale" value="${!empty search.onSale ? search.onSale : ''}"/>
	<td align="right" width="100">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">			
						<a href="javascript:fncGetProductList2('1','1');">판매중 상품</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
		<td align="right" width="100">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">		
						<a href="javascript:fncGetProductList2('1','2');">판매중 제외</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
		<td align="right">	
		가격범위검색
		<input type="text" name="lowPrice"  value="${search.lowPrice}" 
			 class="ct_input_g" style="width:70px; height:19px" />
		~
		<input type="text" name="highPrice"  value="${search.highPrice}" 
			 class="ct_input_g" style="width:70px; height:19px" />
		</td>
	
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript: fncGetUserList('1');">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>



<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
<input type="hidden" id="ascendingCondition" name="ascendingCondition" value="${!empty search.ascendingCondition ? search.ascendingCondition : ''}"/>
	<tr>	
		<td align="right" width="100">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
					
						<a href="javascript:fncGetProductList('1','1');">가격낮은순</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
		
		<td align="right" width="100">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList('1','2');">가격높은순</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
		
		<td align="right" width="100">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList('1','3');">이름순</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
		
		<td align="right" width="150">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetProductList('1','4');">조회수높은순</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
		
		<td align="right" width="100">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="/listProduct.do?menu=${menu}">초기화</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>

		<td align="right">		
		<%-- <select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0" <%= (searchCondition.equals("0") ? "selected" : "")%>>상품번호</option>
				<option value="1" <%= (searchCondition.equals("1") ? "selected" : "")%>>상품명</option>
				<option value="2" <%= (searchCondition.equals("2") ? "selected" : "")%>>상품가격</option>
			</select>--%>	
			<select name="searchCondition" class="ct_input_g" style="width:80px">
				<option value="0"  ${ !empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품번호</option>
				<option value="1"  ${ !empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>상품명</option>
				<%--<option value="2"  ${ !empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>상품가격</option> --%>
			</select>
			<input type="text" name="searchKeyword"  value="${search.searchKeyword}" 
			 class="ct_input_g" style="width:200px; height:19px" />
		</td>	
	
		<td align="right" width="70">
			<table border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="17" height="23">
						<img src="/images/ct_btnbg01.gif" width="17" height="23">
					</td>
					<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top:3px;">
						<a href="javascript:fncGetUserList('1');">검색</a>
					</td>
					<td width="14" height="23">
						<img src="/images/ct_btnbg03.gif" width="14" height="23">
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>


<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<%-- --%><td colspan="11" >전체   ${resultPage.totalCount} 건수,	현재  ${resultPage.currentPage} 페이지</td>
	</tr>
	<tr>
		<td class="ct_list_b" width="100">No</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">조회수</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="200">상품명</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">가격</td>
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">등록일</td>	
		<td class="ct_line02"></td>
		<td class="ct_list_b" width="100">현재상태</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>
	<%-- <%
		for(int i=0; i<list.size(); i++) {
			Product product = list.get(i);
	%>		
	<tr class="ct_list_pop">
		<td align="center"><%= i + 1 %></td>
		<td></td>
		<%if(menu.equals("manage")){ %>
			<td align="left"><a href="/updateProductView.do?prodNo=<%=product.getProdNo()%>&menu=<%=menu%>"><%= product.getProdName() %></a></td>		
	
		<%}else{%>
			<td align="left"><a href="/getProduct.do?prodNo=<%=product.getProdNo()%>&menu=<%=menu%>"><%= product.getProdName() %></a></td>
		<%}%>		
		
		<td></td>
			<td align="left"><%= product.getPrice() %></td>
			<td></td>
			<td align="left"><%= product.getManuDate() %></td>
			<td></td>
			<td align="left">
			
		
			<%if(product.getProTranCode() != null){%>
				<%if(user.getRole().equals("user")){ %>
					재고없음
				<%}else if(user.getRole().equals("admin")){%>
					<%if(product.getProTranCode().equals("1")){%>
						구매완료 &nbsp;<a href="/updateTranCodeByProd.do?prodNo=<%=product.getProdNo()%>&tranCode=2
						&page=<%=resultPage.getCurrentPage()%>">배송하기</a>
					<%}else if(product.getProTranCode().equals("2")){%>
						배송중
					<%}else if(product.getProTranCode().equals("3")){%>
						배송완료
					<%}%>
				<%}%>
			<%}else{%>
				판매중
			<%}%>
		</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	<%}  %>	--%>
	<c:set var="i" value="0" />
	<c:forEach var="product" items="${list}">
		<c:set var="i" value="${ i+1 }" />	
	<tr class="ct_list_pop">
		<td align="center">${ i }</td>
		<td></td>
		<td align="center">${product.view}</td>
		<td></td>
		<c:choose>
			<c:when test="${menu=='manage'}">
				<td align="left"><a href="/updateProductView.do?prodNo=${product.prodNo}&menu=manage">${product.prodName}</a></td>		
			</c:when>
			<c:otherwise>
				<td align="left"><a href="/getProduct.do?prodNo=${product.prodNo}&menu=search">${product.prodName}</a></td>
			</c:otherwise>
		</c:choose>
		<td></td>
		<td align="center">${product.price}</td>
		<td></td>
		<td align="center">${product.manuDate}</td>
		<td></td>
		<td align="center">
			<c:if test="${!empty product.proTranCode}">
				<c:choose>	
					<c:when test="${user.role=='admin'}">
						<c:if test="${product.proTranCode=='1'}">
							구매완료 &nbsp;<a href="/updateTranCodeByProd.do?prodNo=${product.prodNo}&tranCode=2
							&page=${resultPage.currentPage}">배송하기</a>
						</c:if>
						<c:if test="${product.proTranCode=='2'}">
							배송중
						</c:if>
						<c:if test="${product.proTranCode=='3'}">
							배송완료
						</c:if>
					</c:when>
					<c:when test="${user.role=='user'}">	
						재고없음
					</c:when>
				</c:choose>
			</c:if>
			<c:if test="${empty product.proTranCode}">
				  판매중
			</c:if>
		</td>	
	</tr>
	<tr>
		<td colspan="11" bgcolor="D6D7D6" height="1"></td>
	</tr>	
	</c:forEach>
</table>


<!-- PageNavigation Start... -->
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top:10px;">
	<tr>
		<td align="center">
		<input type="hidden" id="currentPage" name="currentPage" value=""/>
			<%--/////////////////////// EL / JSTL 적용으로 주석 처리 //////////////////////// 		    
				<%if( resultPage.getCurrentPage() <= resultPage.getPageUnit() ){ %>
					◀ 이전
			<% }else{ %>
				<a href="javascript:fncGetUserList('<%=resultPage.getCurrentPage()-1%>')">◀ 이전</a>
			<% } %>

			<%for(int i=resultPage.getBeginUnitPage(); i<= resultPage.getEndUnitPage(); i++){%>
				<a href="javascript:fncGetUserList('<%=i %>');"><%=i %></a>
			<% 	}  %>
	
			<% if( resultPage.getEndUnitPage() >= resultPage.getMaxPage() ){ %>
					이후 ▶
			<% }else{ %>
				<a href="javascript:fncGetUserList('<%=resultPage.getEndUnitPage()+1%>')">이후 ▶</a>				
			<% } %>--%>
			
			<jsp:include page="../common/pageNavigator.jsp"/>	
			
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->

</form>

</div>
</body>
</html>


<%System.out.println("listProduct.jsp - END"); %>
