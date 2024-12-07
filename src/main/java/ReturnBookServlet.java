import java.io.IOException;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ReturnBookServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        int bookID = Integer.parseInt(request.getParameter("bookID"));
        String url = "jdbc:mysql://localhost:3306/LibraryDB?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "newUser";  // replace with your database username
        String password = "yourPassword";  // replace with your database password

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully!");

            // Establish connection
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                String deleteQuery = "DELETE FROM Book WHERE bookID = ?";
                try (PreparedStatement statement = connection.prepareStatement(deleteQuery)) {
                    statement.setInt(1, bookID);
                    statement.executeUpdate();
                }
                response.sendRedirect("user.html");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Add the driver to your project.");
            e.printStackTrace(response.getWriter());
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
