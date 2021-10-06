<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>Etudiant Page</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
	
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">


    <!-- Bootstrap Core CSS -->
     <link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="<c:url value="/resources/css/sb-admin.css" />"rel="stylesheet">
    <link href="<c:url value="/resources/style.css" />"rel="stylesheet">

    <!-- Morris Charts CSS -->
    <link href="<c:url value="/resources/css/plugins/morris.css" />"rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css" />"rel="stylesheet" type="text/css">
    
     <script src="<c:url value="/resources/js/jquery-1.12.4.js"/>" ></script>
     <script src="<c:url value="/resources/js/jquery.dataTables.min.js"/>" ></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" ></script>
     <script src="<c:url value="/resources/js/bootstrap-datetimepicker.js"/>" ></script>
     <link href="<c:url value="/resources/css/bootstrap-datetimepicker.min.css" />"rel="stylesheet" type="text/css">
     
      <script type="text/javascript" src="<c:url value="/resources/js/canvasjs.min.js"/>"></script>
      
    <script type="text/javascript">
    function myFunction() {
    	  // Declare variables 
    	  var input, filter, table, tr, td, i;
    	  input = document.getElementById("myInput");
    	  filter = input.value.toUpperCase();
    	  table = document.getElementById("myTable");
    	  tr = table.getElementsByTagName("tr");

    	  // Loop through all table rows, and hide those who don't match the search query
    	  for (i = 0; i < tr.length; i++) {
    	    td = tr[i].getElementsByTagName("td")[1];
    	    if (td) {
    	      if (td.innerHTML.toUpperCase().indexOf(filter) > -1) {
    	        tr[i].style.display = "";
    	      } else {
    	        tr[i].style.display = "none";
    	      }
    	    } 
    	  }
    	}
    
    $(function () {
        $('#datetimepicker6').datetimepicker({format: 'mm/dd/yyyy'});
        $('#datetimepicker7').datetimepicker({format: 'mm/dd/yyyy'});
    });
  </script>
</head>
<body>
  <div id="wrapper">

<!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="index.html"  >
                
                     <img style="background-color:white;"src="<c:url value="/resources/images/esprit.png" />"  alt="Smiley face" height="35" width="75">
                </a>
                
            </div>
            <!-- Top Menu Items -->
            <ul class="nav navbar-right top-nav">
              
       
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i> ${login} <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                        <li>
                            <a href="#"><i class="fa fa-fw fa-user"></i> Profile</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-envelope"></i> Inbox</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-fw fa-gear"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="<c:url value='/logout' />"><i class="fa fa-fw fa-power-off"></i> Log Out</a>
                        </li>
                    </ul>
                </li>
            </ul>
            <!-- Sidebar Menu Items - These collapse to the responsive navigation menu on small screens -->
            <div class="collapse navbar-collapse navbar-ex1-collapse">
                <ul class="nav navbar-nav side-nav">
                    <li >
                        <a href="index.html"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                    </li>
                  
					<li >
                        <a href="<c:url value='/seanceEnseignantUser' />"><i class="glyphicon glyphicon-eye-open"></i> Absences par seance</a>
                    </li>
                  
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>
    <div id="page-wrapper">
            <div class="container-fluid">
               <div class="row">
                    <div class="col-lg-10">
                     <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Gestion des absences
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-edit"></i> Gestion des absences
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

<br>
<h3>Etudiants List</h3>
<c:if test="${!empty listEtudiants}"><div class="col-lg-10">
        	<div class="panel panel-default">
            <div class="panel-heading"><h3>Etudiants:</h3><input type="text" id="myInput" onkeyup="myFunction()" placeholder="nom.."></div>
            <div class="panel-body">
	<table id="myTable"  class="table table-condensed" style="border-collapse:collapse;">
	<tr>
		<th width="80"> ID</th>
		<th width="120"> nom</th>
		<th width="120">Prenom</th>
		<th width="120">Absent</th>
		<th width="60">marquer absent</th>
		<th width="60">marquer present</th>
	</tr>
	<c:forEach items="${listEtudiants}" var="etudiant">
	   <c:choose>
       <c:when test="${etudiant.absent==true}">
        <tr class="danger">
			<td>${etudiant.id}</td>
			<td>${etudiant.nom}</td>
			<td>${etudiant.prenom}</td>
			<td>${etudiant.absent}</td>
			<td><a href="<c:url value='/markAbsentFromSeanceEnseignantUser/${etudiant.id}' />"  >marquer absent</a></td>
			<td><a href="<c:url value='/markPresentFromSeanceEnseignantUser/${etudiant.id}'  />" >marquer present</a></td>
		</tr>
       </c:when>    
       <c:otherwise>
         <tr class="success">
			<td>${etudiant.id}</td>
			<td>${etudiant.nom}</td>
			<td>${etudiant.prenom}</td>
			<td>${etudiant.absent}</td>
			<td><a href="<c:url value='/markAbsentFromSeanceEnseignantUser/${etudiant.id}' />"  >marquer absent</a></td>
			<td><a href="<c:url value='/markPresentFromSeanceEnseignantUser/${etudiant.id}'  />" >marquer present</a></td>
		</tr>
       </c:otherwise>
       </c:choose>
		
	</c:forEach>
	</table>
</div>
        
          </div> 
        
      </div>
</c:if>
                   </div>
                  
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->


</body>
</html>