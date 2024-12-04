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
<div class="text-center py-5">
  <h1 class="display-3">Task</h1>
  <div>
    <a class="btn btn-primary" href="${pageContext.request.contextPath}/task/write">Create New</a>
  </div>
</div>
<div class="container-fluid py-4 row row-cols-3">
  <c:forEach items="${list}" var="item">
    <div class="col">
      <div class="card" data-task-id="${item.getId()}">
        <div class="card-header">
          <div class="row justify-content-between">
            <div class="col h5 my-0">
              <c:out value="${item.getTitle()}"/>
            </div>
            <div class="col-md-auto">
              <a href="/task/view/${item.getId()}" class="btn btn-sm btn-outline-light p-1">View</a>
            </div>
          </div>
        </div>
        <div class="card-body">
          <div>
            <c:out value="${item.getContent()}"/>
          </div>
        </div>
        <div class="card-footer">
          Due to <c:out value="${item.getEnd()}"/>
        </div>
      </div>
    </div>
  </c:forEach>
</div>
</body>
<jsp:include page="/WEB-INF/include/bs-script.jsp"/>
</html>
