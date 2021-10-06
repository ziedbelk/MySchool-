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
    $(function () {
        $('#datetimepicker6').datetimepicker({format: 'dd/mm/yyyy'});
        $('#datetimepicker7').datetimepicker({format: 'dd/mm/yyyy'});
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
                    <li>
                        <a href="index.html"><i class="fa fa-fw fa-dashboard"></i> Dashboard</a>
                    </li>
					<li>
                        <a href="<c:url value='/seanceEnseignantUser' />"><i class="glyphicon glyphicon-eye-open"></i> Absences</a>
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
                            Gestion des seances
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-edit"></i> Gestion des seances
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->
<c:url var="addAction" value="/searchSeanceEnseignantUser" ></c:url>

<form:form action="${addAction}" commandName="absenceModel">
<table>
	
	<tr>
		<td>
			<form:label path="date">
				<spring:message text="date"/>
			</form:label>
		</td>
		<td>
		    <div class='input-group date' id='datetimepicker7'>
			   <form:input type='date' path="date" class="form-control"/>
			    <span class="input-group-addon">
                    <span class="glyphicon glyphicon-calendar"></span>
                </span>
			</div>
		</td> 
	</tr>
	
	<tr>
		<td>
			<form:label path="classe">
				Classe: 
			</form:label>
		</td>
		<td>
		    <form:select  cssStyle="width:220px"  id="SelectList" items="${classes}" itemLabel="nomClasse" itemValue="nomClasse"  path="classe.nomClasse" value="">
		                    
		    </form:select>
		</td> 
	</tr>
	<tr>
		<td colspan="2">
				<input type="submit"
					value="<spring:message text="Génerer"/>" />
		</td>
	</tr>
	
</table>	
</form:form>
<br>
<h3>Seances List</h3>
<c:if test="${!empty listSeances}"><div class="col-lg-10">
        	<div class="panel panel-default">
            <div class="panel-heading"><h3>Séances</h3><input type="text" id="myInput" onkeyup="myFunction()" placeholder="Recherche par nom.."></div>
            <div class="panel-body">
	<table id="myTable" class="table table-condensed" style="border-collapse:collapse;">
	<tr>
		<th width="80">ID</th>
		<th width="120">date</th>
		<th width="120">remarque</th>
		<th width="120">matiere</th>
		<th width="120">Classe</th>
		<th width="60">Confirmation</th>
		<th width="60">Date Confirmation</th>
		<th width="60">Confirmer</th>
		<th width="60">Suivi objectifs</th>
		<th width="120">Absence</th>
	</tr>
	<c:forEach items="${listSeances}" var="seance">
		<tr>
			<td>${seance.id}</td>
			<td>${seance.date}</td>
			<td>${seance.remarque}</td>
			<td>${seance.elementEnseignement.code}</td>
			<td>${seance.classe.nomClasse}</td>
			<td>${seance.confirmation}</td>
			<td>${seance.dateConfirmation}</td>
			<td><a href="<c:url value='/confirmerSeancefcEnseignantUser/${seance.id}' />" >Confirmer</a></td>
			<td><a href="<c:url value='/viewObjectifClasseRelEnseignantUser/${seance.id}'/>" >Suivi objectifs</a></td>
			<td><a href="<c:url value='/viewAbsencesEnseignantUser/${seance.id}'/>" >Absences</a></td>
		</tr>
	</c:forEach></tbody>
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