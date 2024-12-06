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
  <c:forEach items="${dto.list}" var="task">
    <div class="col pb-4">
      <div class="card" data-task-id="${task.id}">
        <div class="card-header">
          <div class="row justify-content-between">
            <div class="col my-0" style="align-content: center">
              <span class="h5">
                <c:out value="${task.title}"/>
              </span>
              <c:if test="${task.isExpired()}">
                <span class="badge text-bg-warning" style="vertical-align: text-top">Expired</span>
              </c:if>
              <c:choose>
                <c:when test="${task.status == 1}">
                  <span class="badge text-bg-success" style="vertical-align: text-top">Finished</span>
                </c:when>
                <c:when test="${task.status == -1}">
                  <span class="badge text-bg-danger" style="vertical-align: text-top">Dropped</span>
                </c:when>
                <c:otherwise/>
              </c:choose>
            </div>
            <div class="col-md-auto">
              <a href="/task/view/${task.id}" class="btn btn-sm btn-outline-light p-1">View</a>
            </div>
          </div>
        </div>
        <div class="card-body">
          <div>
            <c:out value="${task.content}"/>
          </div>
        </div>
        <div class="card-footer">
          Due to <span class="fw-bold"><c:out value="${task.end}"/></span>
        </div>
      </div>
    </div>
  </c:forEach>
</div>
<div>
  <nav aria-label="Page navigation example">
    <ul class="pagination justify-content-center">
      <c:if test="${dto.start > 1}">
        <li class="page-item">
          <a class="page-link" href="${pageContext.request.contextPath}/task${dto.usePage(1)}">
            1
          </a>
        </li>
        <li class="page-item disabled">
          <a class="page-link">
            &ctdot;
          </a>
        </li>
      </c:if>
      <c:forEach begin="${dto.start}" end="${dto.end}" var="pgIndex">
        <li class="page-item ${dto.page == pgIndex? "active" : ""}">
          <a class="page-link" href="/task${dto.usePage(pgIndex)}">
            <c:out value="${pgIndex}"/>
          </a>
        </li>
      </c:forEach>
      <c:if test="${dto.end < dto.last}">
        <li class="page-item disabled">
          <a class="page-link">
            &ctdot;
          </a>
        </li>
        <li class="page-item">
          <a class="page-link" href="/task${dto.usePage(dto.last)}">
            <c:out value="${dto.last}"/>
          </a>
        </li>
      </c:if>
    </ul>
  </nav>
</div>
</body>
<jsp:include page="/WEB-INF/include/bs-script.jsp"/>
</html>
