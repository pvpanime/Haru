<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <jsp:include page="/WEB-INF/include/style.jsp" />
  <title>Tasker!</title>
</head>
<body data-bs-theme="dark">
<div class="container py-5">
  <main>
    <h1 class="text-center mb-4">Tasker</h1>
    <form action="${pageContext.request.contextPath}/task/write" method="post">
      <fieldset class="border p-4 mb-4 rounded">
        <legend class="w-auto">Write</legend>
        <div class="form-group">
          <label for="TaskTitle">Title:</label>
          <input id="TaskTitle" type="text" name="title" value="" class="form-control" placeholder="Title here">
        </div>
        <div class="form-group">
          <label for="TaskContent">Content:</label>
          <textarea id="TaskContent" name="content" class="form-control" rows="6" placeholder="Enter your content"></textarea>
        </div>
      </fieldset>
      <fieldset class="border p-4 mb-4 rounded">
        <label for="EndDateTime">Due to</label>
        <input id="EndDateTime" type="datetime-local" name="end" class="form-control" placeholder="Date here">
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
<jsp:include page="/WEB-INF/include/bs-script.jsp" />
</body>
</html>
