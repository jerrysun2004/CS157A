import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Hardcoded user credentials
        String correctUsernameUser = "user";
        String correctPasswordUser = "userpassword";
        String correctUsernameLibrarian = "librarian";
        String correctPasswordLibrarian = "librarianpassword";

        if (username.equals(correctUsernameUser) && password.equals(correctPasswordUser)) {
            response.sendRedirect("user.html");
        } else if (username.equals(correctUsernameLibrarian) && password.equals(correctPasswordLibrarian)) {
            response.sendRedirect("librarian.html");
        } else {
            response.sendRedirect("index.html");
        }
    }
}
