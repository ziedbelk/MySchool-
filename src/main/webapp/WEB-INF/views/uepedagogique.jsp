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
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown"><i class="fa fa-user"></i>  ${login} <b class="caret"></b></a>
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
                    <li>
                        <a href="<c:url value='/uepedagogique' />"><i class="glyphicon glyphicon-star"></i>Unites Pedagogiques</a>
                    </li>
					
                  
                </ul>
            </div>
            <!-- /.navbar-collapse -->
        </nav>
    <div id="page-wrapper">
            <div class="container-fluid">
               <div class="row">
                    <div class="col-lg-6">
                     <!-- Page Heading -->
                <div class="row">
                    <div class="col-lg-12">
                        <h1 class="page-header">
                            Gestion des unit?s P?dagogiques
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-edit"></i> Gestion des unit?s p?dagogiques
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

<c:url var="addAction" value="/unitePedagogique/add" ></c:url>

<form:form action="${addAction}" commandName="unite">
<table>
	<c:if test="${!empty unite.code}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input cssStyle="width:220px" path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="code">
				<spring:message text="code"/>
			</form:label>
		</td>
		<td>
			<form:input cssStyle="width:220px" path="code" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="uniteEnseignement">
				Unite enseignement: 
			</form:label>
		</td>
		<td>
		    <form:select cssStyle="width:220px"  id="SelectList" items="${listElements}" itemLabel="code" itemValue="code"  path="uniteEnseignement.code" value="">
		                    
		    </form:select>
		</td> 
	</tr>
		
	<%-- <tr>
		<td>
			<form:label path="classe">
				classe: 
			</form:label>
		</td>
		<td>
		    <form:select  id="SelectList" items="${listecahiers}" itemLabel="nomClasse" itemValue="nomClasse"  path="classe">
		                    
		    </form:select>
		</td> 
	</tr> --%>
	<tr>
		<td colspan="2">
			<c:if test="${!empty unite.code}">
				<input type="submit"
					value="<spring:message text="Edit unite "/>" />
			</c:if>
			<c:if test="${empty unite.code}">
				<input type="submit"
					value="<spring:message text="Add unite "/>" />
			</c:if>
		</td>
	</tr>
	
</table>	

</form:form>
<br>
<c:if test="${!empty unites}">
	<div class="panel panel-default">
            <div class="panel-heading"><h3>Unit?s P?dagogiques</h3><!-- <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Recherche par code.."> --></div>
            <div class="panel-body">
	<!-- <table  id="myTable" class="table table-responsive table-bordered table-hover table-striped " > -->
	<table  id="myTable" class="table" >
	<tr>
		<th width="80"> ID</th>
		<th width="120">Code</th>
		<th width="120">Unite Enseignement</th>
		<th width="120">Supprimer</th>
		<th width="150">Enseignants</th>
	</tr>
	<c:forEach items="${unites}" var="unite">
		<tr>
			<td>${unite.id}</td>
			<td>${unite.code}</td>
			<td>${unite.uniteEnseignement.code}</td>
		<%-- 	<td>${unite.classe.nomClasse}</td> --%>
		    <td><a href="<c:url value='/removeUnitePedagogique/${unite.id}' />" >Supprimer</a></td>
			<td><a href="<c:url value='/viewUp/${unite.id}' />" >Enseignants</a></td>
		</tr>
	</c:forEach>
	</table>
	</div>
        
          </div> 
</c:if>   </div>
                  
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->


<script src="<c:url value="/resources/js/jquery-1.12.4.js"/>" ></script>
     <script src="<c:url value="/resources/js/jquery.dataTables.min.js"/>" ></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" ></script>
</body>