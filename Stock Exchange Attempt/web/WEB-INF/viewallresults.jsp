<%-- 
    Document   : viewallresults
    Created on : Feb 27, 2018, 7:55:45 PM
    Author     : Adeline Chin
--%>

<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.StockMarket"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View All Results</title>
    </head>
    <body>
        <form action="ViewAllResultsController" method="post">
                <table style="width:100%">
                    <tr>
                        <th>Market</th>
                        <th>Buy</th> 
                        <th>Sell</th>
                        <th>Update</th>
                        <th>Change</th>
                    </tr>
                    <%
                        ArrayList<StockMarket> smList = (ArrayList<StockMarket>) session.getAttribute("smList");
                        if (smList.size() != 0) {
                            for (int i = 0; i < smList.size(); i++) {
                                StockMarket sm = smList.get(i);
                                String stockName = sm.getStockName();
                                double buyPrice = sm.getBuyPrice();
                                double sellPrice = sm.getSellPrice();
                                double totalChange = sm.getTotalChange();
                                Date tradeTime = sm.getTradeTime();
                    %>
                    <tr>
                        <th><%=stockName%></th>
                        <th><input type="submit" name='<%=buyPrice%>' value='<%=sm%>'/></th> 
                        <th><%=sellPrice%></th>
                        <th><%=tradeTime%></th>
                        <th><%=totalChange%></th>
                    </tr>
                    <%
                            }
                        }
                    %>
                </table>
            </form>
    </body>
</html>
