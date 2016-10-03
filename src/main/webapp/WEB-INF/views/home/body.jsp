<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<h1><font color="red"><b>home</b></font></h1>
<br/>
<p>Menu home 1</p> 
<p>Menu home 2</p> 
<br/>
CONTEXT:<c:out value="${pageContext.request.contextPath}" />
<br/>
<a href="${pageContext.request.contextPath}/index">goto index</a>
