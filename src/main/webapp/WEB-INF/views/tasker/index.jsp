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
  <div class="row justify-content-center">
    <a class="col-auto btn btn-primary" href="${pageContext.request.contextPath}/task/write">Create New</a>
    <button id="SearchButton" type="button" class="btn btn-light col-auto" data-bs-toggle="collapse" data-bs-target="#SearchGroup">Search</button>
  </div>
</div>
<div class="collapse" id="SearchGroup">
  <div class="container">
    <div class="card card-body">
      <h3>Search</h3>
      <form id="SearchForm" action="${pageContext.request.contextPath}/task" method="GET">
        <div class="py-2">
          <div>Status</div>
          <div class="btn-group" role="group">
            <input type="radio" class="btn-check" name="status" id="status-none" value="" checked autocomplete="off">
            <label class="btn btn-outline-secondary" for="status-none">None</label>
            <input type="radio" class="btn-check" name="status" id="status-in-progress" value='<c:out value="0" />' autocomplete="off">
            <label class="btn btn-outline-light" for="status-in-progress">In Progress</label>
            <input type="radio" class="btn-check" name="status" id="status-finished" value='<c:out value="1" />' autocomplete="off">
            <label class="btn btn-outline-success" for="status-finished">Finished</label>
            <input type="radio" class="btn-check" name="status" id="status-dropped" value='<c:out value="-1" />' autocomplete="off">
            <label class="btn btn-outline-danger" for="status-dropped">Dropped</label>
          </div>
        </div>
        <div class="py-2">
          <div>Text</div>
          <div class="input-group">
              <input type="checkbox" class="btn-check" name="searchFor" id="UseTitle" value="t" autocomplete="off">
              <label class="btn btn-outline-light" for="UseTitle">Title</label>
              <input type="checkbox" class="btn-check" name="searchFor" id="UseContent" value="c" autocomplete="off">
              <label class="btn btn-outline-light" for="UseContent">Content</label>
            <input type="text" class="form-control" name="search" placeholder="Search..." autocomplete="off">
          </div>
        </div>
        <div class="py-2">
          <div>Due Date range From:</div>
          <input type="datetime-local" class="form-control" name="rangeStart" id="rangeStart" autocomplete="off">
          <div>To:</div>
          <input type="datetime-local" class="form-control" name="rangeEnd" id="rangeEnd" autocomplete="off">
        </div>
        <input type="submit" class="btn btn-primary" value="Search!">
      </form>
    </div>
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
              <a href="/task/view/${task.id}${dto.usePage()}" class="btn btn-sm btn-outline-light p-1">View</a>
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
<script>
  const f = document.getElementById("SearchForm");
  f.addEventListener("submit", (event) => {
    event.preventDefault();
    event.stopPropagation();
    const fd = new FormData(f);

    const realForm = document.createElement("form");

    let useSearch = !!fd.get("search");

    for (const [k, v] of fd.entries()) {
      if (!v) continue;
      if (!useSearch && (k === 'searchFor')) continue;
      const input = document.createElement("input");
      input.type = "hidden";
      input.name = k;
      input.value = v;
      realForm.appendChild(input);
    }

    document.body.appendChild(realForm);
    realForm.method = "GET";
    realForm.action = "${pageContext.request.contextPath}/task";
    realForm.submit();
  })
</script>
</body>
<jsp:include page="/WEB-INF/include/bs-script.jsp"/>
</html>
