<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="description" content="">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- The above 4 meta tags *must* come first in the head; any other head content must come *after* these tags -->

    <!-- Title  -->
    <title>3조 요리도감</title>

    <!-- Favicon  -->
    <link rel="icon" href="img/core-img/yoridogam-logo.png">

    <!-- Core Style CSS -->
    <link rel="stylesheet" href="css/core-style.css">
    <link rel="stylesheet" href="style.css">

</head>

<body>
    <!-- Search Wrapper Area Start -->
    <div class="search-wrapper section-padding-100">
        <div class="search-close">
            <i class="fa fa-close" aria-hidden="true"></i>
        </div>
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <div class="search-content">
                        <form action="#" method="get">
                            <input type="search" name="search" id="search" placeholder="Type your keyword...">
                            <button type="submit"><img src="img/core-img/search.png" alt=""></button>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Search Wrapper Area End -->

    <!-- ##### Main Content Wrapper Start ##### -->
    <div class="main-content-wrapper d-flex clearfix">

        <!-- Mobile Nav (max width 767px)-->
        <div class="mobile-nav">
            <!-- Navbar Brand -->
            <div class="amado-navbar-brand">
                <a href="home"><img src="img/core-img/logo.png" alt=""></a>
            </div>
            <!-- Navbar Toggler -->
            <div class="amado-navbar-toggler">
                <span></span><span></span><span></span>
            </div>
        </div>

        <!-- Header Area Start -->
        <header class="header-area clearfix">
            <!-- Close Icon -->
            <div class="nav-close">
                <i class="fa fa-close" aria-hidden="true"></i>
            </div>
            <!-- Logo -->
            <div class="logo">
                <a href="home"><img src="img/core-img/logo.png" alt=""></a>
            </div>
            <!-- Amado Nav -->
            <nav class="amado-nav">
                <ul>
         
                <c:choose>
                <c:when test ="${!empty(sTeacherId)}" >
                	<li><a href="teacher_detail" style="color: blue;">[${sTeacherId_name}]</a></li>
                </c:when>
                <c:otherwise>
                	<li><a href="member_detail" style="color: blue;">[${sUserId_name}]</a></li>                
                </c:otherwise>
                </c:choose>
                    <li class="active"><a href="home">메인</a></li>
                    <li><a href=".html">온라인 강의</a></li>
                    <li><a href=".html">오프라인 강의</a></li>
                    <li><a href=".html">내 주변 강의</a></li>
                    <li><a href="notice_list">공지사항</a></li>
                    <li><a href="inquiry_list">문의게시판</a></li>
                </ul>
            </nav>
            <!-- Button Group -->
            <div class="amado-btn-group mt-30 mb-100">
            <c:choose>
            <c:when test ="${empty(sUserId)&&empty(sTeacherId)}" >
                <a href="member_login_form" class="btn amado-btn mb-15">회원로그인</a>
                <a href="teacher_login_form" class="btn amado-btn mb-15">강사로그인</a>
                <a href="#" class="btn amado-btn active">New this week</a>
           	</c:when>
           	<c:otherwise>
           		   <a href="member_logout_action" class="btn amado-btn mb-15">로그아웃</a>
           	</c:otherwise>
           	
           	</c:choose>
            </div>
            <!-- Cart Menu -->
            <div class="cart-fav-search mb-100">
                <a href="cart_list_form" class="cart-nav"><img src="img/core-img/cart.png" alt=""> 장바구니 <span>(0)</span></a>
                <a href="#" class="fav-nav"><img src="img/core-img/favorites.png" alt=""> 관심목록</a>
                <a href="#" class="search-nav"><img src="img/core-img/search.png" alt=""> 검색</a>
            </div>
            <!-- Social Button -->
            <div class="social-info d-flex justify-content-between">
                <a href="#"><i class="fa fa-pinterest" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-instagram" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-facebook" aria-hidden="true"></i></a>
                <a href="#"><i class="fa fa-twitter" aria-hidden="true"></i></a>
            </div>
        </header>