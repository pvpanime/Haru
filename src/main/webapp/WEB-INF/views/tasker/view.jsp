<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset="utf-8">
  <jsp:include page="/WEB-INF/include/style.jsp" />
  <title>Tasker</title>
</head>
<body data-bs-theme="dark">
<jsp:include page="/WEB-INF/include/navbar.jsp" />
<div class="container-fluid py-4">
    <div class="card" data-task-id="${task.getId()}">
      <div class="card-header">
        <div class="h5 my-0">
          <c:out value="${task.getTitle()}" />
        </div>
      </div>
      <div class="card-body">
        <c:out value="${task.getContent()}" />
      </div>
      <div class="card-footer list-group">
        <div class="list-group-item">From <c:out value="${task.getStart()}" /></div>
        <div class="list-group-item">Due to <c:out value="${task.getEnd()}" /></div>
      </div>
    </div>
  </div>
</body>
<jsp:include page="/WEB-INF/include/bs-script.jsp" />
</html>
