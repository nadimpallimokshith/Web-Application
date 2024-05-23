import java.io.*;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.*;
import javax.servlet.http.*;

@WebServlet("/Savename")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
                 maxFileSize = 1024 * 1024 * 10,      // 10MB
                 maxRequestSize = 1024 * 1024 * 50)   // 50MB
public class Savename extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/name";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");

        // Store data in MySQL database
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            // Establishing a connection to the database
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // SQL query to insert form data into the database
            String sql = "INSERT INTO user_name (name) VALUES (?)";
            statement = connection.prepareStatement(sql);
            statement.setString(1, name);
            statement.executeUpdate();

            // Redirect to Savednames.jsp on successful save
            response.sendRedirect("Savednames.jsp");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle any errors here
            // Redirect to an error page or show an error message
            response.sendRedirect("error.jsp");
        } finally {
            // Closing the connection and statement
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
