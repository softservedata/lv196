<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Delivery.ua/Feedback</title>
    <script src="https://code.jquery.com/jquery-1.12.3.js"></script>
    <script src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
    <link rel="stylesheet" type="text/css"
          href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css"
          href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css">
</head>
<body>
<h1 align="center">Feedback page</h1>

<div>
    <table id="feedback" class="table table-striped table-bordered table-hover" cellspacing="0" width="100%">
    </table>
</div>

<script>
    $(document).ready(function () {
        $('#feedback').DataTable({
            ajax: {
                url: "/web/feedback",
                dataSrc: "feedbacks",
                type: "POST"
            },
            columns: [
                {title: "Feedback id", data: "feedbackId"},
                {title: "Order id", data: "orderId"},
                {title: "User name", data: "userName"},
                {title: "Transporter name", data: "transporterName"},
                {title: "Rate", data: "rate"},
                {title: "Text", data: "text"},
                {title: "Approved", data: "approved"}
            ],
            columnDefs: [
                {"width": "7%", "targets": 0},
                {"width": "7%", "targets": 1},
                {"width": "10%", "targets": 2},
                {"width": "10%", "targets": 3},
                {"width": "5%", "targets": 4},
                {"width": "10%", "targets": 5},
                {"width": "5%", "targets": 6}
            ]
        });
    });
</script>
</body>
</html>
