import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/Viewnames")
public class Viewnames extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // JDBC URL, username, and password of MySQL server
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/name";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "password";

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Name> userList = new ArrayList<>();

        // Retrieve user data from the database
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;
        try {
            // Load the JDBC driver class
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establishing a connection to the database
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // SQL query to select all users
            String sql = "SELECT * FROM user_name";
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            // Process the result set
            while (resultSet.next()) {
                Name user = new Name();
                user.setName(resultSet.getString("name"));
                userList.add(user);
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // You might want to handle this exception more gracefully, e.g., by forwarding to an error page.
        } finally {
            // Closing the connection, statement, and result set
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
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

        // Forward the user list to Viewnames.jsp
        request.setAttribute("userList", userList);
        request.getRequestDispatcher("Viewnames.jsp").forward(request, response);
    }
}
