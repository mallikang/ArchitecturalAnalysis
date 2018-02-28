<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login - Welcome to StockWatch</title>
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <link rel="stylesheet" href="resources/assets/css/main.css" />
    </head>
    <body>
        <!-- Header -->
        <header id="header">
            <div class="inner">
                <a><h4><strong>Stock Watch.</strong></h4></a>
            </div>
        </header>
        <!-- Footer -->
        <footer id="footer">
            <div class="inner">

                <h3>Log In!</h3>

                <form action="LogInController" method="post">

                    <div class="field">
                        <label for="username">Username</label>
                        <input name="username" id="username" type="text" placeholder="Username">
                    </div>
                    <div class="field">
                        <label for="password">Password</label>
                        <input name="password" id="password" type="password" placeholder="Password">
                    </div>
                    <ul class="actions">
                        <li><input value="Log In" class="button alt" type="submit"></li>
                    </ul>
                </form>
            </div>
        </footer>
    </body>
</html>