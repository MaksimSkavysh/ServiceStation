package servelets;

import dao.UserDao;
import dao.UserDaoImpl;
import models.CarModel;
import models.ClientCardModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.xml.sax.SAXException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by maksim on 30.09.2015.
 */

@WebServlet("/rest/home")
public class HomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    public static final String APPLICATION_JSON = "application/json";
    public static final String UTF_8 = "UTF-8";
    private UserDao userDao;

    @Override
    public void init() throws ServletException {
        this.userDao=new UserDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("users", userDao.getUsers(firstName,lastName));
            String users = jsonObject.toJSONString();
            response.setCharacterEncoding(UTF_8);
            response.setContentType(APPLICATION_JSON);
            PrintWriter out = response.getWriter();
            out.print(users);
            out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String data = request.toString();
        System.out.println(data);
    }

}