import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    Connection con = null;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        String url = "jdbc:sqlserver://localhost\\ITDEPT:1433;databaseName=sarbagya;integratedSecurity=true";

        try {
            String n = request.getParameter("txtid");
            String p = request.getParameter("txtpass");
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            con = DriverManager.getConnection(url);
            Statement statement = con.createStatement();
            String sql = "select * from login where name='" + n + "' and password='" + p + "';";
            ResultSet resultSet = statement.executeQuery(sql);

            if (!resultSet.next()) {
                out.print("<html><head><title>Login Error</title></head><body>");
                out.print("<br><br><br><br><b>Unknown User</b><br><br>");
                out.print("<h3>Access Denied</h3></body></html>");
            } else {
                out.print("<html><head><title>Login Success</title></head><body><center>");
                out.print("<br><br><b>Welcome ");
                out.print(n + "</b><br>");
                out.print("<h3>You have been authenticated</h3></center></body></html>");
            }
            con.close();
        }catch (SQLException se){
            se.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
