<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <jsp:include page="/WEB-INF/include/style.jsp"/>
  <title>Tasker!</title>
</head>
<body data-bs-theme="dark">
<jsp:include page="/WEB-INF/include/navbar.jsp"/>
<div class="container py-5">
  <main>
    <h1 class="text-center mb-4">Tasker</h1>
    <form id="MainForm" action="${pageContext.request.contextPath}/task/edit" method="post">
      <input type="hidden" name="id" value='<c:out value="${task.getId()}" />'/>
<%--      <input type="hidden" name="page" value='<c:out value="${requestDTO.page}" />'/>--%>
<%--      <input type="hidden" name="size" value='<c:out value="${requestDTO.size}" />'/>--%>
      <fieldset class="border p-4 mb-4 rounded">
        <legend class="w-auto">Edit</legend>
        <div class="form-group">
          <label for="TaskTitle">Title:</label>
          <input id="TaskTitle" type="text" name="title" value='<c:out value="${task.getTitle()}" />' class="form-control"
                 placeholder="Title here">
        </div>
        <div class="form-group">
          <label for="TaskContent">Content:</label>
          <textarea id="TaskContent" name="content" class="form-control" rows="6"
                    placeholder="Enter your content"><%--
            --%><c:out value="${task.getContent()}" /><%--
          --%></textarea>
        </div>
      </fieldset>
      <fieldset class="border p-4 mb-4 rounded">
        <div class="py-2">
          <label for="EndDateTime">Due to</label>
          <input id="EndDateTime" type="datetime-local" name="end" value='<c:out value="${task.getEnd()}" />' class="form-control"
                 placeholder="Date here">
        </div>
        <div class="py-2">
          <div>Status</div>
          <div class="btn-group" role="group" aria-label="Basic radio toggle button group">
            <input type="radio" class="btn-check" name="status" id="btnradio1" value='<c:out value="0" />' autocomplete="off" ${task.getStatus() == 0 ? "checked" : ""}>
            <label class="btn btn-outline-secondary" for="btnradio1">In Progress</label>
            <input type="radio" class="btn-check" name="status" id="btnradio2" value='<c:out value="1" />' autocomplete="off" ${task.getStatus() == 1 ? "checked" : ""}>
            <label class="btn btn-outline-success" for="btnradio2">Finished</label>
            <input type="radio" class="btn-check" name="status" id="btnradio3" value='<c:out value="-1" />' autocomplete="off" ${task.getStatus() == -1 ? "checked" : ""}>
            <label class="btn btn-outline-danger" for="btnradio3">Dropped</label>
          </div>
        </div>
      </fieldset>
      <fieldset class="border p-4 rounded">
        <button type="submit" class="btn btn-primary btn-block">Publish</button>
      </fieldset>
    </form>
  </main>
  <c:if test="${errors != null}">
    <footer>
      <c:forEach items="${errors}" var="error">
        <section>${error.getField()} ${error.defaultMessage}</section>
      </c:forEach>
    </footer>
  </c:if>
</div>
<jsp:include page="/WEB-INF/include/bs-script.jsp"/>
<script id="RequestPreserve" type="application/x-www-form-urlencoded"><c:out value="${requestDTO.usePage()}" /></script>
<script>

  function hide(name, value) {
    const input = document.createElement('input');
    input.type = 'hidden';
    input.name = name;
    input.value = value;
    return input;
  }

  const form = document.getElementById('MainForm');
  form.addEventListener('submit', (event) => {
    event.preventDefault();
    event.stopPropagation();

    const requestPreserved = document.getElementById('RequestPreserve').innerText.replace(/&amp;/g, '&');
    if (requestPreserved) {
      const usp = new URLSearchParams(requestPreserved);

      for (const [name, value] of usp.entries()) {
        form.append(hide(name, value));
      }
    }


    const formData = new FormData(form);
    console.log([...formData.entries()]);

    form.submit();
  })
</script>
</body>
</html>
