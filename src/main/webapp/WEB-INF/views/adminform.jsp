<html >
<head>
    <meta charset="utf-8">
   <title>Login</title>

    <link href="resources/css/bootstrap.min.css" rel="stylesheet">
    <link href="resources/css/bootstrap-theme.css" rel="stylesheet">
     <link href="resources/css/elegant-icons-style.css" rel="stylesheet" />
    <link href="resources/css/font-awesome.css" rel="stylesheet" />

    <link href="resources/css/style.css" rel="stylesheet">
    <link href="resources/css/style-responsive.css" rel="stylesheet" /> 
</head>

	<body style="background-image: url('resources/img/dd.jpeg');">
						<div class="container">				

										<form  class="login-form" role="form" name="match" action="addMatch" method="post" >

											 <div class="login-wrap">
                                           		 <label>first Team</label>
                                          		 <input name="firstTeam" class="form-control" placeholder="Enter team">
                                       	    </div>

                                       	   <div class="login-wrap">
                                           		 <label>Second Team </label>
                                           		 <input name="secondTeam" class="form-control" placeholder="Enter tem">
                                            </div>

                                             <div class="login-wrap">
                                            	<label>leagues</label>
                                            	<select name="league" class="form-control">
                                            		<option value="">- Choose League -</option>
	                                                <option value="1">Premier League (England)</option>
                                                    <option value="2">Primera Division (Spain)</option>
                                                    <option value="3">Bundesliga (Germany)</option>
                                                   <option value="4">Serie A (Italy)</option>
                                                   <option value="4">Ligue 1 (France)</option>
                                                </select>
                                       	 	</div>

                                       	 	 <button type="submit" class="btn-primary btn-lg btn-block">Submit Button</button>
                                       		

                                        </form>	
                                        
                                        </div>	 
                                        
                                        </body>
                                        
                                        
</html>                                      