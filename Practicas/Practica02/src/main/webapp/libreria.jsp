<%-- 
    Document   : libreria
    Created on : 21 sep 2025, 7:08:22 p.m.
    Author     : ADMIN
--%>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    // Datos pasados por el servlet
    java.util.List<model.Book> bookList =
        (java.util.List<model.Book>) request.getAttribute("bookList");
    if (bookList == null) bookList = java.util.Collections.emptyList();

    String q = (String) request.getAttribute("q");

    // Mensajes flash desde sesión (PRG)
    String flashOk = (String) session.getAttribute("flash_ok");
    String flashErr = (String) session.getAttribute("flash_error");
    if (flashOk != null) session.removeAttribute("flash_ok");
    if (flashErr != null) session.removeAttribute("flash_error");
%>
<!doctype html>
<html lang="es">
<head>
  <meta charset="UTF-8">
  <title>Librería — Práctica 2</title>
  <meta name="viewport" content="width=device-width, initial-scale=1">

  <!-- Bootstrap 5 (CDN) -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-dark mb-4">
  <div class="container">
    <span class="navbar-brand">Librería (MVC · Servlets/JSP · SQLite)</span>
    <a class="btn btn-outline-light" href="<%=request.getContextPath()%>/books">Inicio</a>
  </div>
</nav>

<div class="container">

  <% if (flashOk != null) { %>
    <div class="alert alert-success"><%= flashOk %></div>
  <% } %>
  <% if (flashErr != null) { %>
    <div class="alert alert-danger"><%= flashErr %></div>
  <% } %>

  <div class="row g-4">
    <!-- Agregar libro -->
    <div class="col-lg-5">
      <div class="card shadow-sm">
        <div class="card-header">Agregar libro</div>
        <div class="card-body">
          <form method="post" action="<%=request.getContextPath()%>/books">
            <div class="mb-3">
              <label class="form-label">Título</label>
              <input class="form-control" name="title" required>
            </div>
            <div class="mb-3">
              <label class="form-label">Autor</label>
              <input class="form-control" name="author" required>
            </div>
            <div class="mb-3">
              <label class="form-label">Precio</label>
              <input class="form-control" name="price" placeholder="199.99" required>
            </div>
            <button class="btn btn-primary">Agregar</button>
          </form>
        </div>
      </div>
    </div>

    <!-- Buscar + Tabla -->
    <div class="col-lg-7">
      <div class="card shadow-sm mb-3">
        <div class="card-header">Buscar</div>
        <div class="card-body">
          <form class="row g-2" method="get" action="<%=request.getContextPath()%>/books">
            <div class="col-md-9">
              <input class="form-control" name="q" value="<%= q != null ? q : "" %>"
                     placeholder="Buscar por título o autor">
            </div>
            <div class="col-md-3 d-grid">
              <button class="btn btn-outline-primary">Buscar</button>
            </div>
          </form>
        </div>
      </div>

      <div class="card shadow-sm">
        <div class="card-header">Libros</div>
        <div class="card-body p-0">
          <div class="table-responsive">
            <table class="table table-striped table-hover m-0">
              <thead class="table-light">
                <tr>
                  <th style="width:80px;">ID</th>
                  <th>Título</th>
                  <th>Autor</th>
                  <th style="width:140px;">Precio</th>
                </tr>
              </thead>
              <tbody>
              <% if (bookList.isEmpty()) { %>
                <tr><td colspan="4" class="text-center text-muted py-4">Sin resultados</td></tr>
              <% } else {
                   for (model.Book b : bookList) { %>
                <tr>
                  <td><%= b.getId() %></td>
                  <td><%= b.getTitle() %></td>
                  <td><%= b.getAuthor() %></td>
                  <td><%= b.getPrice() %></td>
                </tr>
              <% } } %>
              </tbody>
            </table>
          </div>
        </div>
      </div>

    </div>
  </div>

</div>

</body>
</html>

