import java.io.IOException;
import java.sql.*;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DisplayBooksServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String url = "jdbc:mysql://localhost:3306/LibraryDB?useSSL=false&allowPublicKeyRetrieval=true";
        String user = "newUser";  // replace with your database username
        String password = "yourPassword";  // replace with your database password

        response.setContentType("text/html");

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded successfully!");

            // Establish connection
            try (Connection connection = DriverManager.getConnection(url, user, password)) {
                String selectQuery = "SELECT * FROM Book";
                try (Statement statement = connection.createStatement();
                     ResultSet resultSet = statement.executeQuery(selectQuery)) {
                    response.getWriter().println("<table border='1'><tr><th>Book ID</th><th>Title</th></tr>");
                    while (resultSet.next()) {
                        int bookID = resultSet.getInt("bookID");
                        String title = resultSet.getString("title");
                        response.getWriter().println("<tr><td>" + bookID + "</td><td>" + title + "</td></tr>");
                    }
                    response.getWriter().println("</table>");
                }
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC Driver not found. Add the driver to your project.");
            e.printStackTrace(response.getWriter());
        } catch (SQLException e) {
            e.printStackTrace(response.getWriter());
        }
    }
}
