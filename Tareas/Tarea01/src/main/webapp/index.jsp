<%-- 
    Document   : index
    Created on : 7 sep 2025, 4:32:29 p.m.
    Author     : ADMIN
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Financiera Huanca - Captura</title>
  <style>
    body{font-family:system-ui, Arial; margin:2rem;}
    fieldset{margin-bottom:1rem;}
    label{display:block; margin-top:.4rem;}
    input[type=text], input[type=number], input[type=date]{width:280px; padding:.35rem;}
    .row{display:flex; gap:2rem; flex-wrap:wrap;}
    .row > div{min-width:280px;}
    .actions{margin-top:1rem;}
  </style>
</head>
<body>
  <h2>Datos del crédito</h2>

  <form method="post" action="${pageContext.request.contextPath}/Capturar">
    <div class="row">
      <div>
        <label>Fecha</label>
        <input type="date" name="fecha" required>
      </div>

      <div>
        <label>Moneda</label>
        <label><input type="radio" name="moneda" value="Soles (S/.)" checked> Soles (S/.)</label>
        <label><input type="radio" name="moneda" value="Dólares (US$)"> Dólares (US$)</label>
      </div>

      <div>
        <label>Monto</label>
        <input type="number" name="monto" step="0.01" min="0" required>
      </div>

      <div>
        <label>Periodo (meses)</label>
        <input type="number" name="periodo" min="1" required>
      </div>

      <div>
        <label>Cuota</label>
        <input type="number" name="cuota" step="0.01" min="0">
      </div>

      <div>
        <label>TEA (%)</label>
        <input type="number" name="tea" step="0.01" min="0">
      </div>

      <div>
        <label>Fecha de vencimiento</label>
        <input type="date" name="fechaVenc">
      </div>
    </div>

    <div class="actions">
      <button type="submit">ACEPTAR</button>
    </div>
  </form>
</body>
</html>
