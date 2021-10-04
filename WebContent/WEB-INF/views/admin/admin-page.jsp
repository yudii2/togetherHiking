<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>AdminLTE 3 | Dashboard</title>
<!-- bootstrap -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/css/bootstrap.min.css" integrity="sha384-B0vP5xmATw1+K9KRQjQERJvTumQW0nPEzvF6L/Z6nronJ3oUOFUFpCjEUQouq2+l" crossorigin="anonymous">
<!-- Google Font: Source Sans Pro -->
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+KR:300,400,500,700,900&display=swap">

<!-- Ionicons -->
<link rel="stylesheet" href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css">
<!-- Tempusdominus Bootstrap 4 -->
<link rel="stylesheet" href="/resources/css/admin/tempusdominus-bootstrap-4.min.css">
<!-- iCheck -->
<link rel="stylesheet" href="/resources/css/admin/icheck-bootstrap.min.css">
<!-- jsGrid -->
<link rel="stylesheet" href="/resources/css/admin/jsgrid.min.css">
<link rel="stylesheet" href="/resources/css/admin/jsgrid-theme.min.css">
<!-- Theme style -->
<link rel="stylesheet" href="/resources/css/admin/adminlte.min.css">




<!-- Font Awesome -->
<script src="https://kit.fontawesome.com/6fd6b71dc1.js" crossorigin="anonymous"></script>
</head>
<body class="hold-transition sidebar-mini layout-fixed">
	<div class="wrapper">

		<!-- Preloader -->
		<div class="preloader flex-column justify-content-center align-items-center">
			<img class="animation__shake" src="dist/img/AdminLTELogo.png" alt="AdminLTELogo" height="60" width="60">
		</div>

		<!-- Navbar -->
		<nav class="main-header navbar navbar-expand navbar-white navbar-light">
			<!-- Left navbar links -->
			<ul class="navbar-nav">
				<li class="nav-item"><a class="nav-link" data-widget="pushmenu" href="#" role="button"><i class="fas fa-bars"></i></a></li>
				<li class="nav-item d-none d-sm-inline-block"><a href="/index" class="nav-link">Home</a></li>
				<li class="nav-item d-none d-sm-inline-block"><a href="/mountain/search-seoul" class="nav-link">Mountain</a></li>
				<li class="nav-item d-none d-sm-inline-block"><a href="/schedule/calendar" class="nav-link">Schedule</a></li>
				<li class="nav-item d-none d-sm-inline-block"><a href="/board/board-page" class="nav-link">Board</a></li>
				
			</ul>

			<!-- Right navbar links -->
			<ul class="navbar-nav ml-auto">
	
			</ul>
		</nav>
		<!-- /.navbar -->

		<!-- Main Sidebar Container -->
		<aside class="main-sidebar sidebar-dark-primary elevation-4">
			<!-- Brand Logo -->
			<a href="/admin/home" class="brand-link"> <img src="/resources/img/산행동행로고.jpg" alt="AdminLTE Logo" class="brand-image img-circle elevation-3" style="opacity: .8"> <span class="brand-text font-weight-light" style="font-size: 18px; font-weight: 600">관리자 페이지</span>
			</a>

			<!-- Sidebar -->
			<div class="sidebar">
				<!-- Sidebar user panel (optional) -->
				<div class="user-panel mt-3 pb-3 mb-3 d-flex">

					<div class="info">
						<a href="/index">메인페이지로 이동하기</a>
					</div>
				</div>



				<!-- SidebarSearch Form -->
				<div class="form-inline">
					<div class="input-group" data-widget="sidebar-search">
						<input class="form-control form-control-sidebar" type="search" placeholder="Search" aria-label="Search">
						<div class="input-group-append">
							<button class="btn btn-sidebar">
								<i class="fas fa-search fa-fw"></i>
							</button>
						</div>
					</div>
				</div>

				<!-- Sidebar Menu -->
				<nav class="mt-2">
					<ul class="nav nav-pills nav-sidebar flex-column" data-widget="treeview" role="menu" data-accordion="false">
						<!-- Add icons to the links using the .nav-icon class
               with font-awesome or any other icon font library -->
						<li class="nav-item menu-open"><a href="/admin/home" class="nav-link active"> <i class="nav-icon fas fa-tachometer-alt"></i>
								<p>
									관리자 <i class="right fas fa-angle-left"></i>
								</p>
						</a>
							<ul class="nav nav-treeview">
								<li class="nav-item"><a href="/admin/new-schedule" class="nav-link"> <i class="far fa-circle nav-icon"></i>
										<p>NEW 모임 신청 내역</p>
								</a></li>
								<!--            <li class="nav-item">
                <a href="/member/admin/reject-schedule" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>모임 신청 반려 내역</p>
                </a>
              </li>
              <li class="nav-item">
                <a href="/member/admin/total-board" class="nav-link">
                  <i class="far fa-circle nav-icon"></i>
                  <p>자유게시판 관리</p>
                </a>
              </li> -->
							</ul></li>



					</ul>
				</nav>
				<!-- /.sidebar-menu -->
			</div>
			<!-- /.sidebar -->
		</aside>

		<!-- Content Wrapper. Contains page content -->
		<div class="content-wrapper">
			<!-- Content Header (Page header) -->
			<div class="content-header">
				<div class="container-fluid">
					<div class="row mb-2">
						<div class="col-sm-6">
							<h1 class="m-0" style="font-family: 'Noto Sans KR', sans-serif; font-size: 24px;">산행동행 관리자</h1>
						</div>
						<!-- /.col -->
						
						<!-- /.col -->
					</div>
					<!-- /.row -->
				</div>
				<!-- /.container-fluid -->
			</div>
			<!-- /.content-header -->

			<!-- Main content -->
			<section class="content">
				<div class="container-fluid">
					<!-- Small boxes (Stat box) -->
					<div class="row">
						<!--  <div class="col-lg-3 col-6">
            small box
            <div class="small-box bg-info">
              <div class="inner">
                <h3>150</h3>

                <p>전체 모임 관리</p>
              </div>
              <div class="icon">
                <i class="ion ion-bag"></i>
              </div>
              <a href="/member/admin/total-schedule" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div> -->
						<!-- ./col -->
						<div class="col-lg-3 col-6">
							<!-- small box -->
							<div class="small-box bg-success">
								<div class="inner">
									<h3>
										53<sup style="font-size: 20px">%</sup>
									</h3>

									<p>NEW 모임 신청 내역</p>
								</div>
								<div class="icon">
									<i class="ion ion-stats-bars"></i>
								</div>
								<a href="/admin/new-schedule" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
							</div>
						</div>
						<!-- ./col -->
						<!--  <div class="col-lg-3 col-6">
            small box
            <div class="small-box bg-warning">
              <div class="inner">
                <h3>44</h3>

                <p>모임 신청 반려 내역</p>
              </div>
              <div class="icon">
                <i class="ion ion-person-add"></i>
              </div>
              <a href="/member/admin/reject-schedule" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          ./col
          <div class="col-lg-3 col-6">
            small box
            <div class="small-box bg-danger">
              <div class="inner">
                <h3>65</h3>

                <p>자유게시판 관리</p>
              </div>
              <div class="icon">
                <i class="ion ion-pie-graph"></i>
              </div>
              <a href="/member/admin/total-board" class="small-box-footer">More info <i class="fas fa-arrow-circle-right"></i></a>
            </div>
          </div>
          ./col
        </div> -->
						<!-- /.row -->
					</div>
			</section>

			<!-- Content Wrapper. Contains page content -->

			<!-- Content Header (Page header) -->
			<section class="content-header">
				<div class="container-fluid"></div>
				<div class="row mb-2">
						<div class="col-sm-6">
							<h1 style="font-family: 'Noto Sans KR', sans-serif; font-size: 24px;"></h1>
						</div>
						<div class="col-sm-6">
							<ol class="breadcrumb float-sm-right">
								<li class="breadcrumb-item"><a href="/admin/new-schedule">Click</a></li>
								<li class="breadcrumb-item active">신청내역 확인</li>
							</ol>
						</div>
					</div>
				
				<!-- /.container-fluid -->
			</section>

			<!-- Main content -->
			<section class="content">
				<div class="card">
					<div class="card-header">
						<h3 class="card-title">Click More info</h3>
					</div>
					<!-- /.card-header -->
					<div class="card-body">
						<!-- <div id="jsGrid1"></div> -->
					</div>
					<!-- /.card-body -->
				</div>
				<!-- /.card -->
			</section>
			<!-- /.content -->
		</div>
		<!-- /.content-wrapper -->




		<!-- Main Footer -->
		<footer class="main-footer">
			<!-- To the right -->
			<div class="float-right d-none d-sm-inline">Anything you want</div>
			<!-- Default to the left -->
			<strong>Copyright &copy; 2014-2021 <a href="https://adminlte.io">AdminLTE.io</a>.
			</strong> All rights reserved.
		</footer>
	</div>
	<!-- ./wrapper -->




	<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
	<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-Piv4xVNRyMGpqkS2by6br4gNJ7DXjqk09RmUpJ8jgGtD7zP9yug3goQfGII0yAns" crossorigin="anonymous"></script>

	<script src="/resources/js/admin/jsgrid.min.js"></script>
	<script src="/resources/js/admin/db.js"></script>
	<script src="/resources/js/admin/demo.js"></script>
	<!-- AdminLTE App -->
	<script src="/resources/js/admin/adminlte.min.js"></script>

	<script>
		$(function() {
			$("#jsGrid1").jsGrid({
				height : "100%",
				width : "100%",

				sorting : true,
				paging : true,

				data : db.clients,

				fields : [ {
					name : "Name",
					type : "text",
					width : 150
				}, {
					name : "Age",
					type : "number",
					width : 50
				}, {
					name : "Address",
					type : "text",
					width : 200
				}, {
					name : "Country",
					type : "select",
					items : db.countries,
					valueField : "Id",
					textField : "Name"
				}, {
					name : "Married",
					type : "checkbox",
					title : "Is Married"
				} ]
			});
		});
	</script>
</body>
</html>