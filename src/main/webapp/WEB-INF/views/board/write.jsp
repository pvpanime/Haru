<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <meta charset="utf-8">
  <jsp:include page="/WEB-INF/include/style.jsp" />
  <title>Write Board!</title>
</head>
<body data-bs-theme="dark">
<div class="container py-5">
  <main>
    <h1 class="text-center mb-4">Write Board!</h1>
    <form action="${pageContext.request.contextPath}/board/write" method="post">
      <fieldset class="border p-4 mb-4 rounded">
        <legend class="w-auto">Board</legend>
        <div class="form-group">
          <label for="BoardTitle">Board Title:</label>
          <input id="BoardTitle" type="text" name="title" class="form-control" placeholder="Enter board title">
        </div>
        <div class="form-group">
          <label for="BoardContent">Content:</label>
          <textarea id="BoardContent" name="content" class="form-control" rows="6" placeholder="Enter your content"></textarea>
        </div>
      </fieldset>
      <fieldset class="border p-4 rounded">
        <button type="submit" class="btn btn-primary btn-block">Publish</button>
      </fieldset>
    </form>
  </main>
</div>
</body>
</html>
