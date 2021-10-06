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
       var firstOpen = true;
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
                    <li >
                        <a href="<c:url value='/annees' />"><i class="glyphicon glyphicon-star"></i> Annees universitaires</a>
                    </li>
					<li >
                        <a href="<c:url value='/plans' />"><i class="glyphicon glyphicon-road"></i> Plans d'etudes</a>
                    </li>
					<li >
                        <a href="<c:url value='/unites' />"><i class="glyphicon glyphicon-th-large"></i> Unites d'enseignement</a>
                    </li>
					<li >
                        <a href="<c:url value='/elements' />"><i class="glyphicon glyphicon-th"></i> Elements d'enseignement</a>
                    </li>
					<li >
                        <a href="<c:url value='/classes' />"><i class="glyphicon glyphicon-tower"></i> Classes</a>
                    </li>
					 <li >
                        <a href="<c:url value='/seances' />"><i class="glyphicon glyphicon-list-alt"></i> Seances</a>
                    </li>
                    <li >
                        <a href="<c:url value='/enseignants' />"><i class="glyphicon glyphicon-user"></i> Personnels</a>
                    </li>
                    <li >
                        <a href="<c:url value='/etudiants' />"><i class="glyphicon glyphicon-user"></i> Etudiants</a>
                    </li>
					<li >
                        <a href="<c:url value='/absences' />"><i class="glyphicon glyphicon-eye-open"></i> Absences</a>
                    </li>
					<li >
                        <a href="<c:url value='/searchSeance' />"><i class="glyphicon glyphicon-eye-open"></i> Absences par seance</a>
                    </li>
                    <li >
                        <a href="javascript:;" data-toggle="collapse" data-target="#demo"><i class="glyphicon glyphicon-stats"></i> Statistiques <i class="fa fa-fw fa-caret-down"></i></a>
                        <ul id="demo" class="collapse">
                            
                            <li>
                                <a href="<c:url value='/stats' />"><i class="glyphicon glyphicon-signal"></i> Etudiants/Niveau</a>
                            </li>
                             <li>
                                <a href="<c:url value='/statsAbsences' />"><i class="glyphicon glyphicon-signal"></i> Absences/Niveau</a>
                            </li>
                        </ul>
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
                            Gestion des annees universitaires
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-edit"></i> Gestion des annees universitaires
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->
<c:url var="addAction" value="/chapitre/add" ></c:url>

<form:form action="${addAction}" commandName="chapitre">
<table>
	<c:if test="${!empty chapitre.titre}">
	<tr>
		<td>
			<form:label path="id">
				<spring:message text="ID"/>
			</form:label>
		</td>
		<td>
			<form:input cssStyle="width:220px"  path="id" readonly="true" size="8"  disabled="true" />
			<form:hidden path="id" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="titre">
				<spring:message text="titre"/>
			</form:label>
		</td>
		<td>
			<form:input cssStyle="width:220px"  path="titre" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="nbrHeure">
				<spring:message text="nbrHeure"/>
			</form:label>
		</td>
		<td>
			<form:input cssStyle="width:220px"  path="nbrHeure" />
		</td>
	
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
			<c:if test="${!empty chapitre.titre}">
				<input type="submit"
					value="<spring:message text="Edit chapitre"/>" />
			</c:if>
			<c:if test="${empty chapitre.titre}">
				<input type="submit"
					value="<spring:message text="Add chapitre"/>" />
			</c:if>
		</td>
	</tr>
	
</table>	

</form:form>
<br>
<c:if test="${!empty chapitres}"><div class="col-lg-10">
        	<div class="panel panel-default">
            <div class="panel-heading"><h3>Chapitres</h3></div>
            <div class="panel-body">
	<table class="tg"><thead>
	<tr>
		<th width="80"> ID</th>
		<th width="120"> titre</th>
		<th width="120">nbrHeure</th>
		<th width="60">Sauvegarder cours</th>
		<th width="60">Telecharger cours</th>
		<th width="60">Editer</th>
		<th width="60">Supprimer</th>
	</tr></thead>  <tbody>
	<c:forEach items="${chapitres}" var="chapitre">
		<tr>
			<td>${chapitre.id}</td>
			<td>${chapitre.titre}</td>
			<td>${chapitre.nbrHeure}</td>
		<%-- 	<td>${unite.classe.nomClasse}</td> --%>
		<td>
		<form method="POST" action="${pageContext.request.contextPath}/upload/${chapitre.id}" enctype="multipart/form-data">
          <input type="file" name="file" /><br/>
          <input type="submit" value="Submit" />
          </form>
		</td>
		<td><a href="<c:url value='/download/${chapitre.id}' />" >Telecharger</a></td>
			<td><a href="<c:url value='/editChapitre/${chapitre.id}' />" >Editer</a></td>
			<td><a href="<c:url value='/removeChapitre/${chapitre.id}' />" >Supprimer</a></td>
		</tr>
	</c:forEach>  </tbody>
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