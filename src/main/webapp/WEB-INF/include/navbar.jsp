<!-- As a link -->
<nav class="navbar navbar-expand bg-body-tertiary sticky-top">
  <div class="container-fluid">
    <a class="navbar-brand mb-0 h1" href="${pageContext.request.contextPath}/">MySite</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/board">Board</a>
        </li>
        <li class="nav-item">
          <a class="nav-link" href="${pageContext.request.contextPath}/task">Tasker</a>
        </li>
      </ul>
    </div>
  </div>
</nav>