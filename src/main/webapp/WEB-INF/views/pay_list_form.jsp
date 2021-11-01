<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

  <link href="css/bootstrap.min.css" rel="stylesheet">

</head>
<body>
	
<div class="container padding-bottom-2x" id="dc">
        <div class="row" style="width: 1300px;">
          <div class="col-lg-9 col-md-8 order-md-2">
            <h6 class="text-muted text-normal text-uppercase">결제 내역</h6>
            <hr class="margin-bottom-1x">
            <div class="table-responsive">
              <table class="table">
                <thead>
                  <tr style="width: 750px;">
                    <th>결제번호</th>
                    <th>결제날짜</th>
                    <th>결제금액</th>
                    <th>상세보기</th>
                    <th></th>
                  </tr>
                </thead>
                <tbody>
                  <c:forEach var="pay" items="${payList}" begin="0" end="${payList.size()}" step="1" varStatus="status">
                  <tr style="width: 750px;">
                    <td>${pay.pay_no}</td>
                    <td>${pay.pay_date}</td>
                    <td>${pay.pay_total }</td>
                    <td><button class="detailBtn" value="${pay.pay_no}">상세보기</button></td>
                    <c:forEach var="pay" items="${pay.payItemList}" begin="0" end="0" step="1" varStatus="status">
                    <td style="text-overflow: ellipsis; overflow: hidden;">[${pay.product.p_name}] 등</c:forEach></td>
                  </tr>
                  </c:forEach>
                </tbody>
              </table>
            </div>
		</div>
	</div>
</div>
<div id="productDetail" style="padding-left: 10px;">
</div>
    <!-- ##### jQuery (Necessary for All JavaScript Plugins) ##### -->
    <script src="js/jquery/jquery-2.2.4.min.js"></script>
    <!-- Popper js -->
    <script src="js/popper.min.js"></script>
    <!-- Bootstrap js -->
    <script src="js/bootstrap.min.js"></script>
    <!-- Plugins js -->
    <script src="js/plugins.js"></script>
    <!-- Active js -->
    <script src="js/active.js"></script>
	<!-- Custom -->
	<script src="js/custom/pay.js"></script>
	
</body>
</html>