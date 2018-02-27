<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Welcome to </title>
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" type="text/css" href="assets/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="assets/css/style.css">
        <script src="assets/js/modernizr-2.8.3-respond-1.4.2.min.js"></script>
        <style type="text/css">
            #freecssfooter{display:block;width:100%;padding:120px 0 20px;overflow:hidden;background-color:transparent;z-index:5000;text-align:center;}
            #freecssfooter div#fcssholder div{display:none;}
            #freecssfooter div#fcssholder div:first-child{display:block;}
            #freecssfooter div#fcssholder div:first-child a{float:none;margin:0 auto;}
        </style>
    </head>
    <body>
        <section id="login" class="login sections">
            <div class="container">
                <div class="row">
                    <div class="main_contact whitebackground">
                        <div class="head_title text-center">
                            <h2>LOG IN</h2>
                        </div>
                        <div class="contact_content">
                            <div class="col-md-6">
                                <div class="single_left_contact">
                                    <form action="LogInController" method="post" id="formid">
                                        <div class="form-group">
                                            <input type="text" class="form-control" name="username" placeholder="Username" required/>
                                        </div>
                                        <div class="form-group">
                                            <input type="password" class="form-control" name="password" placeholder="Password" required/>
                                        </div>
                                        <div class="center-content">
                                            <input type="submit" value="Login!" class="btn btn-default">
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="single_right_contact">
                                    <p>Lorem ipsum dolor sit amet, consectetuer adipiscing elit. Donec odio. Quisque volutpat mattis eros. Nullam malesuada erat ut turpis. Suspendisse urna nibh, viverra non, semper suscipit, posuere a, pede.</p>
                                    <div class="contact_address margin-top-40"><span>#16-01 One Raffles Quay</span><span>North Tower, Singapore - 048583.</span> <span class="margin-top-20">T: (65) 1234 5678</span> <span>M: (65) 8765 4321</span></div>
                                    <div class="contact_socail_bookmark"><a href="https://www.facebook.com/"><i class="fa fa-facebook"></i></a><a href="https://twitter.com/?lang=en"><i class="fa fa-twitter"></i></a><a href="https://plus.google.com/discover"><i class="fa fa-google"></i></a></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
</html>
