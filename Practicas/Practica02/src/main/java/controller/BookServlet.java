/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import dao.BookDAO;
import model.Book;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class BookServlet extends HttpServlet {
    private BookDAO bookDAO;

    @Override
    public void init() throws ServletException {
        String absPath = getServletContext().getRealPath("/WEB-INF/books.db");
        String jdbcUrl = "jdbc:sqlite:" + absPath;
        bookDAO = new dao.BookDAO(jdbcUrl);
        System.out.println("[BookServlet] DB = " + jdbcUrl);

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String q = trim(request.getParameter("q"));
        List<Book> books = (q == null || q.isBlank())
                ? bookDAO.getAllBooks()
                : bookDAO.searchBooks(q);

        request.setAttribute("bookList", books);
        request.setAttribute("q", q);
        request.getRequestDispatcher("/libreria.jsp").forward(request, response); // o booklist.jsp si así la llamas
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String title  = trim(request.getParameter("title"));
        String author = trim(request.getParameter("author"));
        String sPrice = trim(request.getParameter("price"));

        // Validación simple
        Double price = null;
        String error = null;
        if (isBlank(title) || isBlank(author) || isBlank(sPrice)) {
            error = "Todos los campos son obligatorios.";
        } else {
            try {
                price = Double.valueOf(sPrice);
                if (price < 0) error = "El precio debe ser positivo.";
            } catch (NumberFormatException e) {
                error = "Precio inválido (usa números, ej. 199.99).";
            }
        }

        if (error != null) {
            // Guardamos error en sesión para mostrarlo tras el redirect
            request.getSession().setAttribute("flash_error", error);
            response.sendRedirect(request.getContextPath() + "/books");
            return;
        }

        bookDAO.addBook(new Book(title, author, price));
        request.getSession().setAttribute("flash_ok", "Libro agregado correctamente.");
        response.sendRedirect(request.getContextPath() + "/books");
    }

    // helpers
    private static String trim(String s) { return s == null ? null : s.trim(); }
    private static boolean isBlank(String s) { return s == null || s.trim().isEmpty(); }
}