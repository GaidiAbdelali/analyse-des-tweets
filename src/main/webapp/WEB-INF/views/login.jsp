<!DOCTYPE html>
<html lang="en">
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

  <body class="login-img3-body">

    <div class="container">

      <form class="login-form" action="j_spring_security_check" method="post">        
        <div class="login-wrap">
        
        
            <p class="login-img"><i class="icon_lock_alt"></i></p>
            <span style="color: red;">${error }</span>
            <div class="input-group">
              <span class="input-group-addon"><i class="icon_profile"></i></span>
              <input type="text" name="username" class="form-control" placeholder="Username" autofocus>
            </div>
            <div class="input-group">
                <span class="input-group-addon"><i class="icon_key_alt"></i></span>
                <input type="password" name="password" class="form-control" placeholder="Password">
            </div>
            <label class="checkbox">
                <input type="checkbox" value="remember-me"> Remember me
                <span class="pull-right"> <a href="#"> Forgot Password?</a></span>
            </label>
            <button class="btn btn-primary btn-lg btn-block" type="submit">Login</button>
         </div>
           
      </form>
      
      
	      <form class="login-form"  action="signupPage" method="get" style="margin-top: 0px;" >
	      	<div class="login-wrap">
				<button class="btn btn-info btn-lg btn-block" type="submit">Sign up</button>
			</div>
		  </form>
    </div>
	

  </body>
</html>
