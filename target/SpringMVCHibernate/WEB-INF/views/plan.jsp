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
    <script src="<c:url value="/resources/js/jquery.dataTables.min.js"/>" ></script>
   
   <link href="<c:url value="/resources/css/jquery.dataTables.min.css" />"rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="<c:url value="/resources/font-awesome/css/font-awesome.min.css" />"rel="stylesheet" type="text/css">
    <script src="<c:url value="/resources/js/jquery-1.12.4.js"/>" ></script>
    <!-- Bootstrap Core JavaScript -->
    <script src="<c:url value="/resources/js/bootstrap.min.js"/>" ></script>
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
                            Gestion des plans d'étude
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-edit"></i> Gestion des plans d'étude
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->

<c:url var="addAction" value="/plan/add" ></c:url>

<form:form action="${addAction}" commandName="plan">
<table>
	<c:if test="${!empty plan.code}">
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
			<form:input cssStyle="width:220px"  path="code" class="form-control"/>
		</td> 
	</tr>

	<tr>
		<td>
			<form:label path="anneeUniversitaire">
				Annee universitaire: 
			</form:label>
		</td>
		<td><div class="dropdown">
		    <form:select cssStyle="width:220px"   class="dropdown" id="SelectList" items="${annees}" itemLabel="code" itemValue="code"  path="anneeUniversitaire.code" value="" >
		                    
		    </form:select></div>
		</td> 
	</tr>
	<tr>
		<td colspan="2">
			<c:if test="${!empty paln.code}">
				<input type="submit"
					value="<spring:message text="Edit Plan"/>" />
			</c:if>
			<c:if test="${empty plan.code}">
				<input type="submit"
					value="<spring:message text="Add Plan"/>" />
			</c:if>
		</td>
	</tr>
	
</table>	

</form:form>
<br>
<c:if test="${!empty plans}"> <div class="col-lg-20">
        	<div class="panel panel-default">
            <div class="panel-heading"><h3>Plans</h3><!-- <input type="text" id="myInput" onkeyup="myFunction()" placeholder="Recherche par code.."> --></div>
            <div class="panel-body">
	<!-- <table  id="myTable" class="table table-responsive table-bordered table-hover table-striped " > -->
	<table  id="myTable" class="table" >
	  <thead>
	   <tr>
	    <th width="80"> </th>
		<th width="80"> ID</th>
		<th width="120"> code</th>
		<th width="120">annee Universitaire</th>
		<th width="120">Unites d'enseignement</th>
		<th width="60">Edit</th>
		<th width="60">Delete</th>
	   </tr>
	  </thead>
	<tbody>
	<c:forEach items="${plans}" var="plan">
	<c:choose>
	  <c:when test="${!empty plan.uePlanRels}">
		<tr class="clickable" data-toggle="collapse" id="${plan.id}"  data-target=".${plan.id}">
		    <td><i class="glyphicon glyphicon-plus"></i></td>
			<td>${plan.id}</td>
			<td>${plan.code}</td>
			<td>${plan.anneeUniversitaire.code}</td>
			<td><a href="<c:url value='/viewUePl/${plan.id}' />" >Unites d'enseignement</a></td>
			<td><a href="<c:url value='/editPlan/${plan.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/removePlan/${plan.id}' />" >Delete</a></td>
		</tr>
	  </c:when> 
	  <c:otherwise>
		<tr id="${plan.id}" >
		    <td><i class="glyphicon glyphicon-plus"></i></td>
			<td>${plan.id}</td>
			<td>${plan.code}</td>
			<td>${plan.anneeUniversitaire.code}</td>
			<td><a href="<c:url value='/viewUePl/${plan.id}' />" >Unites d'enseignement</a></td>
			<td><a href="<c:url value='/editPlan/${plan.id}' />" >Edit</a></td>
			<td><a href="<c:url value='/removePlan/${plan.id}' />" >Delete</a></td>
		</tr>
	  </c:otherwise> 
	 </c:choose>
	 <c:forEach items="${plan.uePlanRels}" var="uePlanRel">
	  
		<tr class="collapse ${plan.id} " id="${uePlanRel.id}"  >
			<td></td>
			<td>ue: ${uePlanRel.uniteEnseignement.code}</td>
			<td>date debut: ${uePlanRel.dateDeb}</td>
			<td>date fin: ${uePlanRel.dateFin}</td>
		</tr>
		<c:forEach items="${uePlanRel.uniteEnseignement.eeUeRels}" var="eeUeRel">
	  
		  <tr class="collapse ${plan.id} " id="${eeUeRel.id}"  >
			<td></td>
			<td></td>
			<td>ee: ${eeUeRel.elementEnseignement.code}</td>
			<td>coef: ${eeUeRel.coef}</td>
		  </tr>
		   <c:forEach items="${eeUeRel.enseignantEeUeRels}" var="enseignantEeUeRel">
	  
		    <tr class="collapse ${plan.id} " id="${enseignantEeUeRel.id}"  >
			 <td></td>
			 <td></td>
			 <td></td>
			 <td>enseignant: ${enseignantEeUeRel.enseignant.code}</td>
		    </tr>
	       </c:forEach>
	    </c:forEach>
	 </c:forEach>
	 
	 
	</c:forEach>
	</tbody>
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