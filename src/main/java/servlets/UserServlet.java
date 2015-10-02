package servlets;

import dao.UserDao;
import dao.UserDaoImpl;
import db.dbManager;
import models.ClientCardModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by maksim on 01.10.2015.
 */
@WebServlet("/rest/userPage")
public class UserServlet extends HttpServlet {
    private UserDao userDao;
    @Override
    public void init() throws ServletException {
        this.userDao=new UserDaoImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
        String id = request.getParameter("id");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("user", userDao.getUser(id));
        String users = jsonObject.toJSONString();
        response.setCharacterEncoding(ServletUtil.UTF_8);
        response.setContentType(ServletUtil.APPLICATION_JSON);
        PrintWriter out = response.getWriter();
        out.print(users);
        out.flush();
        } catch (IOException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }

    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String json = ServletUtil.getMessageBody(request);
            userDao.editUser(json);
        } catch (ParseException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}
