<%-- 
    Document   : buyconfirmation
    Created on : Feb 27, 2018, 8:22:49 PM
    Author     : Adeline Chin
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.StockMarket"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Buy Confirmation</title>
    </head>
    <body>
        <%
            String username = (String) session.getAttribute("username");
            String stockId = (String)session.getAttribute("stockId");
        %>
        <form action="BuyConfirmationController" method="post">
            <input type="submit" name='Submit' value='Confirm Purchase'/>
            <input type="hidden" name='username' value='<%=username%>'/>
            <input type="hidden" name='stockid' value='<%=stockId%>'/>
            <table style="width:100%">
                    <tr>
                        <th>Market</th>
                        <th>Buy</th>
                        <th>Quantity</th>
                    </tr>
                    <%
                        ArrayList<StockMarket> smList = (ArrayList<StockMarket>) session.getAttribute("smList");
                        if (smList.size() != 0) {
                            for (int i = 0; i < smList.size(); i++) {
                                StockMarket sm = smList.get(i);
                                String stockName = sm.getStockName();
                                double buyPrice = sm.getBuyPrice();
                    %>
                    <tr>
                        <th><%=stockName%></th>
                        <th><%=buyPrice + " per unit"%></th>
                        <th><input type="text" name='quantity'/></th>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
        </form>
    </body>
</html>
