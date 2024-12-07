import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AddBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String title = request.getParameter("title");
        String url = "jdbc:mysql://localhost:3306/LibraryDB?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "newUser";  // replace with your database username
        String password = "yourPassword";  // replace with your database password

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully!");

            // Establish connection
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                String insertQuery = "INSERT INTO Book (title) VALUES (?)";
                try (PreparedStatement statement = connection.prepareStatement(insertQuery)) {
                    statement.setString(1, title);
                    statement.executeUpdate();
                }
                response.sendRedirect("librarian.html");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Add the driver to your project.");
            e.printStackTrace(response.getWriter());
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
