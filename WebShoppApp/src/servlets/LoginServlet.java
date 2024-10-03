package servlets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/login-servlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve form data
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Database connection
        String jdbcUrl = "jdbc:mysql://localhost:3306/user_db";
        String dbUser = "root"; // Change as per your database setup
        String dbPassword = ""; // Change as per your database setup

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        try {
            // Load JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the connection
            Connection conn = DriverManager.getConnection(jdbcUrl, dbUser, dbPassword);

            // Create SQL query to validate user
            String sql = "SELECT * FROM users WHERE username=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, username);
            stmt.setString(2, password);

            // Execute query
            ResultSet rs = stmt.executeQuery();

            // Check if user exists
            if (rs.next()) {
                // Valid user, create a session
                HttpSession session = request.getSession();
                session.setAttribute("user", username);

                // Redirect to welcome page
                response.sendRedirect("welcome.jsp");
            } else {
                // Invalid login
                out.println("<h3>Invalid username or password!</h3>");
                out.println("<a href='login.jsp'>Try Again</a>");
            }

            // Close resources
            rs.close();
            stmt.close();
            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
        }
    }
}
