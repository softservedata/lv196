<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>WRITE FEEDBACK</title>
</head>
<body>
<form name="test" method="post" action="order?action=writeFeedback">
   <textarea title="Write Feedback here" name="message" cols="90" rows="4" >Write feedback here</textarea>
    <textarea title="Write Rate here"  name="rate" cols="10" rows="1">Write rate here</textarea>
    <input type="submit" value="Send">
</form>
</body>
</html>