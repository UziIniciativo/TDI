/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import model.Book;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ADMIN
 */
public class BookDAO {

    // Ajusta según tu configuración
    private final String jdbcURL;
    private final String jdbcUsername = "user";
    private final String jdbcPassword = "pass";

    private static final String SELECT_ALL  = "SELECT id, title, author, price FROM books ORDER BY id";
    private static final String INSERT_BOOK = "INSERT INTO books (title, author, price) VALUES (?, ?, ?)";
    private static final String SEARCH_BOOK = "SELECT id, title, author, price FROM books " +
                                              "WHERE LOWER(title) LIKE ? OR LOWER(author) LIKE ? ORDER BY id";
    
    public BookDAO(String jdbcURL) {
        this.jdbcURL = jdbcURL;
        initDatabase();
    }
    
    private void initDatabase() {
        final String CREATE_TABLE = 
            "CREATE TABLE IF NOT EXISTS books (" +
            " id INTEGER PRIMARY KEY AUTOINCREMENT," +
            " title  TEXT NOT NULL," +
            " author TEXT NOT NULL," +
            " price  REAL NOT NULL" +
            ")";
        try (Connection c = getConnection();
             Statement st = c.createStatement()) {
            st.executeUpdate(CREATE_TABLE);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Conexión
    protected Connection getConnection() throws SQLException {
        try {
            Class.forName("org.sqlite.JDBC"); // SQLite3
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver de MySQL", e);
        }
        return DriverManager.getConnection(jdbcURL);
    }

    // Listar todos
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SELECT_ALL);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                books.add(map(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Agregar libro
    public Book addBook(Book book) {
        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(INSERT_BOOK, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, book.getTitle());
            ps.setString(2, book.getAuthor());
            ps.setDouble(3, book.getPrice());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    book.setId(keys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return book;
    }

    // Buscar por título o autor
    public List<Book> searchBooks(String query) {
        if (query == null || query.trim().isEmpty()) {
            return getAllBooks();
        }
        List<Book> books = new ArrayList<>();
        String like = "%" + query.trim().toLowerCase() + "%";

        try (Connection conn = getConnection();
             PreparedStatement ps = conn.prepareStatement(SEARCH_BOOK)) {

            ps.setString(1, like);
            ps.setString(2, like);

            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    books.add(map(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Mapear fila -> objeto Book
    private static Book map(ResultSet rs) throws SQLException {
        return new Book(
            rs.getLong("id"),
            rs.getString("title"),
            rs.getString("author"),
            rs.getDouble("price")
        );
    }
}
