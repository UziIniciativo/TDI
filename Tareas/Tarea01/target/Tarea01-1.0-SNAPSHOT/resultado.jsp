<%-- 
    Document   : resultado
    Created on : 7 sep 2025, 4:39:15 p.m.
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.Map"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Resumen del crédito</title>
  <style>
    body{font-family:system-ui, Arial; margin:2rem;}
    table{border-collapse:collapse; min-width:520px}
    th, td{border:1px solid #ccc; padding:.5rem;}
    th{background:#f4f4f4; text-align:left;}
    a{display:inline-block; margin-top:1rem;}
  </style>
</head>
<body>
  <h2>Campos capturados</h2>
  <%
    Map<String,String> datos = (Map<String,String>) request.getAttribute("datos");
  %>
  <table>
    <tr><th>Campo</th><th>Valor</th></tr>
    <%
      if (datos != null) {
        for (Map.Entry<String,String> e : datos.entrySet()) {
    %>
      <tr>
        <td><%= e.getKey() %></td>
        <td><%= (e.getValue()==null || e.getValue().isBlank()) ? "-" : e.getValue() %></td>
      </tr>
    <%
        }
      }
    %>
  </table>

  <a href="<%= request.getContextPath() %>/index.jsp">← Capturar de nuevo</a>
</body>
</html>

