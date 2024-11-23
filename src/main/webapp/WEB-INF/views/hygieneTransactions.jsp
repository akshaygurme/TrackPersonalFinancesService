<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
<head>
    <title>Consolidated passbook </title>
</head>
<body>
    <h1>Consolidated Passbook </h1>
    <table border="1">
        <thead>
            <tr>
                <th>Transaction Number</th>
                <th>Date</th>
                <th>Transaction Title</th>
                <th>Bank Name</th>
                <th>SBI Balance</th>
                <th>Axis Balance</th>
                <th>HDFC Balance</th>
                <th>Total Cash</th>
                <th>Salary</th>
                <th>Received Amount</th>
                <th>Bank FDs</th>
                <th>Invoice Discounting</th>
                <th>US Stocks</th>
                <th>Corporate Bonds</th>
                <th>Corporate FDs</th>
                <th>NPS</th>
                <th>ELSS</th>
                <th>Stocks</th>
                <th>Mutual Funds</th>
                <th>Gold Mutual Fund</th>
                <th>Physical Gold</th>
                <th>SGB</th>
                <th>Crypto</th>
                <th>Real Estate</th>
                <th>Returns</th>
                <th>Study</th>
                <th>Daily Needs</th>
                <th>Insurance</th>
                <th>Wants</th>
                <th>Lend</th>
                <th>Construction</th>
                <th>Donation</th>
                <th>Other</th>
            </tr>
        </thead>
        <tbody>
            <c:if test="${not empty transaction}">
                <c:forEach var="row" items="${transaction}">
                    <tr>
                        <td>${row.transactionNumber}</td>
                        <td>${row.date}</td>
                        <td>${row.transactionTitle}</td>
                        <td>${row.bankName}</td>
                        <td>${row.bankBalance.sbiBalance}</td>
                        <td>${row.bankBalance.axisBalance}</td>
                        <td>${row.bankBalance.hdfcBalance}</td>
                        <td>${row.bankBalance.totalCash}</td>
                        <td>${row.salary}</td>
                        <td>${row.receivedAmount}</td>
                        <td>${row.investments.bankFds}</td>
                        <td>${row.investments.invoiceDiscounting}</td>
                        <td>${row.investments.usStocks}</td>
                        <td>${row.investments.corporateBonds}</td>
                        <td>${row.investments.corporateBankFD}</td>
                        <td>${row.investments.nps}</td>
                        <td>${row.investments.elss}</td>
                        <td>${row.investments.stocks}</td>
                        <td>${row.investments.mutualFunds}</td>
                        <td>${row.investments.goldMutualFund}</td>
                        <td>${row.investments.physicalGold}</td>
                        <td>${row.investments.sgb}</td>
                        <td>${row.investments.crypto}</td>
                        <td>${row.investments.realEstate}</td>
                        <td>${row.returns}</td>
                        <td>${row.expenses.study}</td>
                        <td>${row.expenses.dailyNeeds}</td>
                        <td>${row.expenses.insurance}</td>
                        <td>${row.expenses.wants}</td>
                        <td>${row.expenses.lend}</td>
                        <td>${row.expenses.construction}</td>
                        <td>${row.expenses.donation}</td>
                        <td>${row.expenses.other}</td>
                    </tr>
                </c:forEach>
            </c:if>
            <c:if test="${empty transaction}">
                <p>No transactions available.</p>
            </c:if>
        </tbody>

    </table>
</body>
</html>


