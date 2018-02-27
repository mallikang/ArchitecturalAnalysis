<%-- 
    Document   : viewresults
    Created on : Feb 27, 2018, 4:00:49 PM
    Author     : Adeline Chin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Results</title>
    </head>
    <body>
        <form action="ViewResultsController" method="post">
            <input type="hidden" name='username' value='<%=%>'/>
            <button type="submit" class="btn btn-success">Overall Results</button>
        </form>

        <table style="width:100%">
            <tr>
                <th>Market</th>
                <th>Buy</th> 
                <th>Sell</th>
                <th>Updates</th>
                <th>Change</th>
            </tr>
            
            StockMarket 
        </table>
    </body>
</html>
