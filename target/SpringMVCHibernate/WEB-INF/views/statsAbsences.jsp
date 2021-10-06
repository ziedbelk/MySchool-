<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
     <script type="text/javascript" src="<c:url value="/resources/js/canvasjs.min.js"/>"></script>
     <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
        <script type="text/javascript">
       /*  window.onload = function () {

        	var chart = new CanvasJS.Chart("chartContainer", {
        		animationEnabled: true,
        		theme: "light2", //"light1", "dark1", "dark2"
        		title:{
        			text: "Division of Products Sold in 2nd Quarter"             
        		},
        		axisY:{
        			interval: 10,
        			suffix: "%"
        		},
        		toolTip:{
        			shared: true
        		},
        		data:[{
        			type: "stackedBar100",
        			toolTipContent: "{label}<br><b>{name}:</b> {y} (#percent%)",
        			showInLegend: true, 
        			name: "Absences",
        			dataPoints: [
        				{ y: ${fn:escapeXml(level1)}, label: "1" },
        				{ y: ${fn:escapeXml(level2)}, label: "2" },
        				{ y: ${fn:escapeXml(level3)}, label: "3" },
        				{ y: ${fn:escapeXml(level4)}, label: "4" },
        				{ y: ${fn:escapeXml(level5)}, label: "5" }
        			]
        			},
        			{
        				type: "stackedBar100",
        				toolTipContent: "<b>{name}:</b> {y} (#percent%)",
        				showInLegend: true, 
        				name: "Presences",
        				dataPoints: [
        					{ y: ${fn:escapeXml(level1B)}, label: "1" },
            				{ y: ${fn:escapeXml(level2B)}, label: "2" },
            				{ y: ${fn:escapeXml(level3B)}, label: "3" },
            				{ y: ${fn:escapeXml(level4B)}, label: "4" },
            				{ y: ${fn:escapeXml(level5B)}, label: "5" }
        				]
        			}]
        	});
        	chart.render();

        	} */
        	
        	 google.charts.load('current', {'packages':['bar']});
            google.charts.setOnLoadCallback(drawChart);

            function drawChart() {
              var data = google.visualization.arrayToDataTable([
                ['Niveau', 'presences', 'absences'],
                ['1', ${fn:escapeXml(level1)}, ${fn:escapeXml(level1B)}],
                ['2', ${fn:escapeXml(level2)}, ${fn:escapeXml(level2B)}],
                ['3', ${fn:escapeXml(level3)}, ${fn:escapeXml(level3B)}],
                ['4', ${fn:escapeXml(level4)}, ${fn:escapeXml(level4B)}],
                ['5', ${fn:escapeXml(level5)}, ${fn:escapeXml(level5B)}]
              ]);

              var options = {
                chart: {
                  title: 'Absences & Presences par niveau',
                  subtitle: '',
                }
              };

              var chart = new google.charts.Bar(document.getElementById('columnchart_material'));

              chart.draw(data, google.charts.Bar.convertOptions(options));
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
                            Statistiques étudiants par niveau
                        </h1>
                        <ol class="breadcrumb">
                            <li>
                                <i class="fa fa-dashboard"></i>  <a href="index.html">Dashboard</a>
                            </li>
                            <li class="active">
                                <i class="fa fa-edit"></i> Statistiques étudiants par niveau
                            </li>
                        </ol>
                    </div>
                </div>
                <!-- /.row -->
                <c:url var="addAction" value="/searchStatsAbsences" ></c:url>

<form:form action="${addAction}" commandName="absenceModel">
<table>

	<tr>
		<td>
			<form:label path="anneeUniversitaire">
				Année Universitaire: 
			</form:label>
		</td>
		<td>
		    <form:select  id="SelectList" items="${annees}" itemLabel="code" itemValue="code"  path="anneeUniversitaire.code" value="">
		                    
		    </form:select>
		</td> 
	</tr>
	<tr>
		<td colspan="2">
				<input type="submit"
					value="<spring:message text="search Seances"/>" />
		</td>
	</tr>
	
</table>	
</form:form>
                

    </div>
         
                  
                </div>
                <!-- /.row -->

            </div>
            <!-- /.container-fluid -->
             <!-- <div id="chartContainer" style="height: 300px; width: 90%;"> -->
               <div id="columnchart_material" style="width: 800px; height: 500px;">
	</div>

        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

</body>