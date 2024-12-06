<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset="utf-8">
  <jsp:include page="/WEB-INF/include/style.jsp"/>
  <title>Tasker</title>
</head>
<body data-bs-theme="dark">
<jsp:include page="/WEB-INF/include/navbar.jsp"/>
<div class="container-fluid py-4">
  <div class="card" data-task-id="${task.id}">
    <div class="card-header">
      <div class="row justify-content-between">
        <div class="col my-0">
          <span class="h3">
            <c:out value="${task.getTitle()}"/>
          </span>
          <c:choose>
            <c:when test="${task.getStatus() == 1}">
              <span class="badge text-bg-success" style="vertical-align: text-top">Finished</span>
            </c:when>
            <c:when test="${task.getStatus() == -1}">
              <span class="badge text-bg-danger" style="vertical-align: text-top">Dropped</span>
            </c:when>
            <c:otherwise/>
          </c:choose>
        </div>
      </div>
    </div>
    <div class="card-body">
      <c:out value="${task.getContent()}"/>
    </div>
    <div class="list-group list-group-flush">
      <div class="list-group-item">From <c:out value="${task.getStart()}"/></div>
      <div class="list-group-item">
        Due to <span class="fw-bold"><c:out value="${task.getEnd()}"/></span>
        <c:if test="${task.isExpired()}">
          <span class="badge text-bg-warning" style="vertical-align: text-top">Expired</span>
        </c:if>
      </div>
    </div>
  </div>
  <div class="container-fluid py-4">
    <div class="row justify-content-between gap-2">
      <div class="col-auto">
        <a href="/task${requestDTO.question}" class="col-auto btn btn-secondary">Back to List</a>
        <a href="/task/edit/${task.id}${requestDTO.question}" class="col-auto btn btn-outline-light">Edit</a>
      </div>
      <button id="deleteButton" class="col-auto btn btn-danger">Delete</button>
      <form id="HiddenForm">
        <input type="hidden" name="page" value="${requestDTO.page}" />
        <input type="hidden" name="size" value="${requestDTO.size}" />
      </form>
    </div>
  </div>
</div>
<script>
  function actionDelete() {
    // const form = document.createElement('form');
    // document.body.appendChild(form);

    const form = document.querySelector('#HiddenForm');
    form.action = '/task/delete/${task.id}';
    form.method = 'POST';
    form.submit();
  }

  document.getElementById("deleteButton").addEventListener("click", () => {
    if (window.confirm("Are you really sure you want to delete this task?"))
      actionDelete();
  })
</script>
</body>
<jsp:include page="/WEB-INF/include/bs-script.jsp"/>
</html>
