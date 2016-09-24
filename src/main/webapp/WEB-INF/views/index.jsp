<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<html>
  <head>
    <meta charset="utf-8">
    <link rel="shortcut icon" href="resources/img/favicon.png">

    <title>Home</title>

    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/bootstrap-theme.css" rel="stylesheet">

    <link href="resources/css/elegant-icons-style.css" rel="stylesheet" />
    <link href="resources/css/font-awesome.min.css" rel="stylesheet" />    
    <link href="resources/assets/fullcalendar/fullcalendar/bootstrap-fullcalendar.css" rel="stylesheet" />
    <link href="resources/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.css" rel="stylesheet" type="text/css" media="screen"/>
    <link rel="resources/stylesheet" href="css/owl.carousel.css" type="text/css">
	<link href="resources/css/jquery-jvectormap-1.2.2.css" rel="stylesheet">

	<link rel="stylesheet" href="resources/css/fullcalendar.css">
	<link href="resources/css/widgets.css" rel="stylesheet">
    <link href="resources/css/style.css" rel="stylesheet">
    <link href="resources/css/style-responsive.css" rel="stylesheet" />
	<link href="resources/css/xcharts.min.css" rel=" stylesheet">	
	<link href="resources/css/jquery-ui-1.10.4.min.css" rel="stylesheet">
	
	<link rel="stylesheet" href="resources/lib/morris.js-0.5.1/morris.css">  	
	<style type="text/css">
		.container
		{
		  padding-right: 15px;
		  padding-left: 15px;
		  margin-right: auto;
		  margin-left: auto;
		}	
		@media (min-width: 768px) {
		  .container {
		    width: 750px;
		  }
		}
		@media (min-width: 992px) {
		  .container {
		    width: 970px;
		  }
		}
		@media (min-width: 1200px) {
		  .container {
		    width: 1170px;
		  }
		}
	</style>
  </head>

  <body>

  <section class="container">
      
      <header class="header dark-bg">
            <a href="index" class="logo">Stat <span class="lite">Foot</span></a>

            <div class="top-nav notification-row">                
                <ul class="nav pull-right top-menu">
	                <li>
	                    <a data-toggle="dropdown" class="dropdown-toggle" href="#">
	                        <span class="username logo">${username}</span>
	                    </a>
	                </li>
	                <li>
	                	<sec:authorize access="hasRole('ROLE_ADMIN')">
							<a href="admin">
		                        <span class="username logo">add match</span>
		                    </a>
						</sec:authorize>
	                </li>
	                <li>
						<a href="logout">
	                        <span class="username logo">deconnexion</span>
	                    </a>
	                </li>
                </ul>
            </div>
      </header>      

      <!--main content start-->
      <section >
          <section class="wrapper">            
              
            <div class="row">
				<c:forEach items="${matchs}" var="match" varStatus="loop">
					<div class="col-lg-3 col-md-3 col-sm-12 col-xs-12">
						<div class="info-box ${colors.get(loop.index)}">
							<div class="count"><a href="${match.id}" style="color: white;">${match.firstTeam} Vs. ${match.secondTeam}</a></div>
						</div>			
					</div>
				</c:forEach>
			</div>
			
			<div class="row">
				<div class="col-md-9">	
					<div class="panel panel-default">
						<div class="panel-heading" style="height: 34px;" >
							<h2><i class="fa fa-flag-o red"></i><strong>Statistic by Country</strong></h2>
							<div class="panel-actions">
								<a onclick="location.reload()" class="btn-setting"><i class="fa fa-rotate-right"></i></a>
								<a href="#" class="btn-minimize"><i class="fa fa-chevron-up"></i></a>
							</div>
						</div>
					
						<div class="panel-body" style="min-height: 350px;">
							<table class="table bootstrap-datatable countries">
								<thead>
									<tr>
										<th></th>
										<th>Country</th>
										<th>${match.firstTeam}</th>
										<th>${match.secondTeam}</th>
										<th>Resultat</th>
									</tr>
								</thead>   
								<tbody>	
									<c:forEach var="statistic" items="${match.statistics}" >						
										<tr>
											<td><img src="resources/img/Germany.png" style="height:18px; margin-top:-2px;"></td>
											<td>${statistic.name}</td>
											<td>${statistic.firstCount}</td>
											<td>${statistic.secondCount}</td>
											<td>
												<div class="progress thin">
													<div class="progress-bar progress-bar-danger" role="progressbar" aria-valuenow="73" aria-valuemin="0" aria-valuemax="100" style="width: ${statistic.pourcentageFirstCount()}%"></div>
													<div class="progress-bar progress-bar-warning" role="progressbar" aria-valuenow="27" aria-valuemin="0" aria-valuemax="100" style="width: ${statistic.pourcentageSecondCount()}%"></div>
												</div>
												<!-- <span class="sr-only">50%</span>  -->
											</td>
										</tr>
									</c:forEach> 
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-md-3">
					<ul class="today-datas">
						<li>
		                  <div><span id="todayspark1" class="spark"></span></div>
		                  <div class="datas-text">11,500 visitors/day</div>
		                </li>
		                <li>
		                  <div><span id="todayspark2" class="spark"></span></div>
		                  <div class="datas-text">15,000 Pageviews</div>
		                </li>
		                <li>
		                  <div><span id="todayspark3" class="spark"></span></div>
		                  <div class="datas-text">30.55% Bounce Rate</div>
		                </li>
		                <li>
		                  <div><span id="todayspark4" class="spark"></span></div>
		                  <div class="datas-text">$16,00 Revenue/Day</div>
		                </li> 
	              </ul>
              </div>
			</div>
				
			<div class="row" >
               
                  <div class="col-lg-9">
                      <div class="col-lg-6">
                           <section class="panel">
                               <header class="panel-heading">${match.firstTeam == null ? 'Team 1' : match.firstTeam }</header>
                               <div class="panel-body text-center">
                                   <div id="graphA" style="min-height: 350px;"></div>
                               </div>
                           </section>
                       </div>

                        <!-- Bar -->
                       <div class="col-lg-6">
                           <section class="panel">
                               <header class="panel-heading">${match.secondTeam == null ? 'Team 2' : match.secondTeam }</header>
                               <div class="panel-body text-center">
                                   <div id="graphB" style="min-height: 350px;"></div>
                               </div>
                           </section>
                       </div>
                  </div>
                   
				
				<div class="col-md-3">
					<div class="social-box facebook">
						<i class="fa fa-facebook"></i>
						<ul>
							<li>
								<strong>256k</strong>
								<span>friends</span>
							</li>
							<li>
								<strong>359</strong>
								<span>feeds</span>
							</li>
						</ul>
					</div><!--/social-box-->
				</div><!--/col-->
				<div class="col-md-3">
					
					<div class="social-box twitter">
						<i class="fa fa-twitter"></i>
						<ul>
							<li>
								<strong>1562k</strong>
								<span>followers</span>
							</li>
							<li>
								<strong>2562</strong>
								<span>tweets</span>
							</li>
						</ul>
					</div>			
				</div>
            </div> 

          <div class="row">
				 <div class="col-lg-9">
                      <div class="col-lg-6">
                           <section class="panel">
                               <header class="panel-heading">${match.firstTeam == null ? 'Team 1' : match.firstTeam }</header>
                               <div class="panel-body text-center">
                                   <div id="graphC" style="min-height: 350px;"></div>
                               </div>
                           </section>
                       </div>

                        <!-- Bar -->
                       <div class="col-lg-6">
                           <section class="panel">
                               <header class="panel-heading">${match.secondTeam == null ? 'Team 2' : match.secondTeam }</header>
                               <div class="panel-body text-center">
                                   <div id="graphD" style="min-height: 350px;"></div>
                               </div>
                           </section>
                       </div>
                  </div>            

          </div><br><br>
		
		<div class="row">
			<div class="col-md-12 portlets">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="pull-left">Tweet :</div>
                  <div class="widget-icons pull-right">
                    <a href="#" class="wminimize"><i class="fa fa-chevron-up"></i></a> 
                  </div>  
                  <div class="clearfix"></div>
                </div>
                <div class="panel-body">
                  <div class="padd">
                    
                      <div class="form quick-post">
                                <!-- Edit profile form (not working)-->
                                <form class="form-horizontal" name="tags" action="poster" method="POST">
                                    <!-- Title -->
                                    <div class="form-group">
                                      <label class="control-label col-lg-2" for="title">Title</label>
                                      <div class="col-lg-10"> 
                                        <input type="text"  name="title" class="form-control" id="title">
                                      </div>
                                    </div>   
                                    <!-- Content -->
                                    <div class="form-group">
                                      <label class="control-label col-lg-2" for="content">Content</label>
                                      <div class="col-lg-10">
                                        <textarea class="form-control" name="content" id="content"></textarea>
                                      </div>
                                    </div>                           
                                    <!-- Cateogry -->
                                    <div class="form-group">
                                      <label class="control-label col-lg-2">Leagues</label>
                                      <div class="col-lg-10">                               
                                          <select class="form-control" name="leagues">
                                            <option value="">- Choose League -</option>
                                            <option value="1">Premier League (England)</option>
                                            <option value="2">Primera Division (Spain)</option>
                                            <option value="3">Bundesliga (Germany)</option>
                                            <option value="4">Serie A (Italy)</option>
                                            <option value="4">Ligue 1 (France)</option>

                                          </select>  
                                      </div>
                                    </div>              
                                    <!-- Tags -->
                                    <div class="form-group">
                                      <label class="control-label col-lg-2" for="tags">Hashtags</label>
                                      <div class="col-lg-10">
                                        <input type="text" name="hashtags" class="form-control" id="hashtags">
                                      </div>
                                    </div>
                                    
                                    <!-- Buttons -->
                                    <div class="form-group">
                                       <!-- Buttons -->
					 <div class="col-lg-offset-2 col-lg-9">
						<button type="submit" class="btn btn-primary">Post</button>
						<button type="reset" class="btn btn-default">Reset</button>
					 </div>
                          </div>
                      </form>
                    </div>
                  

                  </div>
                  <div class="widget-foot">
                    <!-- Footer goes here -->
                  </div>
                </div>
              </div>
              
            </div>
                        
          </div> 
              <!-- project team & activity end -->

          </section>
      </section>
      <!--main content end-->
  </section>
  <!-- container section start -->

    <!-- javascripts -->
    <script src="resources/js/jquery.js"></script>
	<script src="resources/js/jquery-ui-1.10.4.min.js"></script>
    <script src="resources/js/jquery-1.8.3.min.js"></script>
    <script type="text/javascript" src="resources/js/jquery-ui-1.9.2.custom.min.js"></script>
    <!-- bootstrap -->
    <script src="resources/js/bootstrap.min.js"></script>
    <!-- nice scroll -->
    <script src="resources/js/jquery.scrollTo.min.js"></script>
    <script src="resources/js/jquery.nicescroll.js" type="text/javascript"></script>
    <!-- charts scripts -->
    <script src="resources/assets/jquery-knob/js/jquery.knob.js"></script>
    <script src="resources/js/jquery.sparkline.js" type="text/javascript"></script>
    <script src="resources/assets/jquery-easy-pie-chart/jquery.easy-pie-chart.js"></script>
    <script src="resources/js/owl.carousel.js" ></script>
    <!-- jQuery full calendar -->
    <<script src="resources/js/fullcalendar.min.js"></script> <!-- Full Google Calendar - Calendar -->
	<script src="resources/assets/fullcalendar/fullcalendar/fullcalendar.js"></script>
    <!--script for this page only-->
    <script src="resources/js/calendar-custom.js"></script>
	<script src="resources/js/jquery.rateit.min.js"></script>
    <!-- custom select -->
    <script src="resources/js/jquery.customSelect.min.js" ></script>
	<script src="resources/assets/chart-master/Chart.js"></script>
   
    <!--custome script for all page-->
    <script src="resources/js/scripts.js"></script>
    <!-- custom script for this page-->
    <script src="resources/js/sparkline-chart.js"></script>
    <script src="resources/js/easy-pie-chart.js"></script>
	<script src="resources/js/jquery-jvectormap-1.2.2.min.js"></script>
	<script src="resources/js/jquery-jvectormap-world-mill-en.js"></script>
	<script src="resources/js/xcharts.min.js"></script>
	<script src="resources/js/jquery.autosize.min.js"></script>
	<script src="resources/js/jquery.placeholder.min.js"></script>
	<script src="resources/js/gdp-data.js"></script>	
	<script src="resources/js/morris.min.js"></script>
	<script src="resources/js/sparklines.js"></script>	
	<script src="resources/js/charts.js"></script>
	<script src="resources/js/jquery.slimscroll.min.js"></script>
	
	<script src="resources/lib/jquery/jquery-3.0.0.js"></script>
	<script src="resources/lib/raphael-master/raphael.js"></script>
	<script src="resources/lib/morris.js-0.5.1/morris.min.js"></script>

	<script>
	
	  function graphBar(id,array){
		Morris.Bar({
			element:id,
			data:array,
			xkey:'country',
			ykeys:['positive'],
			labels:['positive']
		});
	  }

	  function graphDonut(id,array){
		  Morris.Donut({
				element:id,
				data:array,
			});
	  }

	  graphBar('graphA',JSON.parse('${graphA}'));
	  graphBar('graphB',JSON.parse('${graphB}'));
	  graphDonut('graphC',JSON.parse('${graphC}'));
	  graphDonut('graphD',JSON.parse('${graphD}'));

  </script>	
  
  <script>

      //knob
      $(function() {
        $(".knob").knob({
          'draw' : function () { 
            $(this.i).val(this.cv + '%')
          }
        })
      });

      //carousel
      $(document).ready(function() {
          $("#owl-slider").owlCarousel({
              navigation : true,
              slideSpeed : 300,
              paginationSpeed : 400,
              singleItem : true

          });
      });

      //custom select box

      $(function(){
          $('select.styled').customSelect();
      });
	  
	  /* ---------- Map ---------- */
	$(function(){
	  $('#map').vectorMap({
	    map: 'world_mill_en',
	    series: {
	      regions: [{
	        values: gdpData,
	        scale: ['#000', '#000'],
	        normalizeFunction: 'polynomial'
	      }]
	    },
		backgroundColor: '#eef3f7',
	    onLabelShow: function(e, el, code){
	      el.html(el.html()+' (GDP - '+gdpData[code]+')');
	    }
	  });
	});

  </script>

  </body>
</html>
