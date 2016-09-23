<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link href="style.css" rel="stylesheet" type="text/css">
    <title>addFeedback</title>
</head>
<body>

<div id="search">
    Search by Order ID
    <form  action="searchOrder" method="get">
        <input type="text" name="orderNumber"><br/>
        <button type="submit">Search</button>
        <br>
    </form>
</div>

<div id="order">
    <iframe src="ordersList.jsp" name="orderList" width="240" height="600" align="left">
        Ваш браузер не поддерживает встроенные фреймы!
    </iframe>
</div>

<div id="info">
    <iframe src="orderInfo.jsp" name="orderInfo"  width="600" height="600" >
        Ваш браузер не поддерживает встроенные фреймы!
    </iframe>
</div>

<div id="feedback">
    <iframe src="writeFeedback.jsp" name="writeFeedback" width="840" height="140" align="left">
        Ваш браузер не поддерживает встроенные фреймы!
    </iframe>
</div>

</body>
</html>