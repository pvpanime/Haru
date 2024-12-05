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
  <c:forEach items="${list}" var="task">
    <div class="col pb-4">
      <div class="card" data-task-id="${task.getId()}">
        <div class="card-header">
          <div class="row justify-content-between">
            <div class="col my-0" style="align-content: center">
              <span class="h5">
                <c:out value="${task.getTitle()}"/>
              </span>
              <c:if test="${task.isExpired()}">
                <span class="badge text-bg-warning" style="vertical-align: text-top">Expired</span>
              </c:if>
              <c:choose>
                <c:when test="${task.getStatus() == 1}">
                  <span class="badge text-bg-success" style="vertical-align: text-top">Finished</span>
                </c:when>
                <c:when test="${task.getStatus() == -1}">
                  <span class="badge text-bg-danger" style="vertical-align: text-top">Dropped</span>
                </c:when>
                <c:otherwise />
              </c:choose>
            </div>
            <div class="col-md-auto">
              <a href="/task/view/${task.getId()}" class="btn btn-sm btn-outline-light p-1">View</a>
            </div>
          </div>
        </div>
        <div class="card-body">
          <div>
            <c:out value="${task.getContent()}"/>
          </div>
        </div>
        <div class="card-footer">
            Due to <span class="fw-bold"><c:out value="${task.getEnd()}"/></span>
        </div>
      </div>
    </div>
  </c:forEach>
</div>
</body>
<jsp:include page="/WEB-INF/include/bs-script.jsp"/>
</html>
