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
        <div class="row justify-content-between">
          <div class="col h5 my-0">
            <c:out value="${task.getTitle()}"/>
          </div>

        </div>
      </div>
      <div class="card-body">
        <c:out value="${task.getContent()}" />
      </div>
<%--      <div class="card-footer">--%>
        <div class="list-group list-group-flush">
          <div class="list-group-item">From <c:out value="${task.getStart()}" /></div>
          <div class="list-group-item">Due to <c:out value="${task.getEnd()}" /></div>
        </div>
<%--      </div>--%>
    </div>
  <div class="container-fluid py-4">
    <div class="row justify-content-end gap-2">
      <a href="/task/edit/${task.getId()}" class="col-auto btn btn-outline-light">Edit</a>
      <button id="deleteButton" class="col-auto btn btn-danger">Delete</button>
    </div>
  </div>
  </div>
<script>
  function actionDelete() {
    const form = document.createElement('form');
    document.body.appendChild(form);
    form.action = '/task/delete/${task.getId()}';
    form.method = 'POST';
    form.submit();
  }
  document.getElementById("deleteButton").addEventListener("click", () => {
    if (window.confirm("Are you really sure you want to delete this task?"))
      actionDelete();
  })
</script>
</body>
<jsp:include page="/WEB-INF/include/bs-script.jsp" />
</html>
