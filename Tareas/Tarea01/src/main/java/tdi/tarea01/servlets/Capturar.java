/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tdi.tarea01.servlets;

/**
 *
 * @author ADMIN
 */
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name="Capturar", urlPatterns={"/Capturar"})
public class Capturar extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Map<String, String> datos = new LinkedHashMap<>();
        datos.put("Fecha", request.getParameter("fecha"));
        datos.put("Moneda", request.getParameter("moneda"));
        datos.put("Monto", request.getParameter("monto"));
        datos.put("Periodo", request.getParameter("periodo"));
        datos.put("Cuota", request.getParameter("cuota"));
        datos.put("TEA (%)", request.getParameter("tea"));
        datos.put("Fecha vencimiento", request.getParameter("fechaVenc"));

        request.setAttribute("datos", datos);
        request.getRequestDispatcher("/resultado.jsp").forward(request, response);
    }
}

