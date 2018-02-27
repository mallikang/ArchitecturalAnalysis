<%-- 
    Document   : viewresults
    Created on : Feb 27, 2018, 4:00:49 PM
    Author     : Adeline Chin
--%>

<%@page import="java.util.Date"%>
<%@page import="dao.StockTradeDAO"%>
<%@page import="model.StockTrade"%>
<%@page import="dao.StockMarketDAO"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.StockMarket"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>My Results</title>
    </head>

    <body>
        <%
            String username = (String) session.getAttribute("username");
        %>

        <form action="ViewResultsController" method="post">
            <input type="hidden" name='username' value='<%=%>'/>
            <button type="submit" class="btn btn-success">Overall Results</button>
        </form>

        <table style="width:100%">
            <tr>
                <th>Market</th>
                <th>Date</th> 
                <th>Trade Type</th>
                <th>Price</th>
                <th>Quantity</th>
            </tr>
            <%
                ArrayList<StockTrade> smList = StockTradeDAO.getStockTradeByTrader(username);
                if (smList.size() != 0) {
                    for (int i = 0; i < smList.size(); i++) {
                        StockTrade st = smList.get(i);

                        int stockId = st.getStockId();
                        Date tradeTime = st.getTradeTime();
                        String tradeType = st.getTradeType();
                        double price = st.getPrice();
                        int quantity = st.getQuantity();
            %>
            <tr>
                <th><%=stockId%></th>
                <th><%=tradeTime%></th> 
                <th><%=tradeType%></th>
                <th><%=price%></th>
                <th><%=quantity%></th>
            </tr>
            <%
                    }
                }
            %>
        </table>
    </body>
</html>
