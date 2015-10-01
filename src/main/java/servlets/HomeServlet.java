package servlets;

import dao.UserDao;
import dao.UserDaoImpl;
import db.dbManager;
import models.ClientCardModel;
import org.json.simple.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by maksim on 30.09.2015.
 */

@WebServlet("/rest/home")
public class HomeServlet extends HttpServlet {
    private UserDao userDao;
    private dbManager db;

    @Override
    public void init() throws ServletException {
        this.userDao=new UserDaoImpl();
        this.db=new dbManager();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("users",userDao.getUsers(firstName, lastName));
        String users = jsonObject.toJSONString();
            response.setCharacterEncoding(ServletUtil.UTF_8);
            response.setContentType(ServletUtil.APPLICATION_JSON);
            PrintWriter out = response.getWriter();
            out.print(users);
            out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = ServletUtil.getMessageBody(request);
        ClientCardModel user=userDao.jsonToUser(json);
        db.addUser(user);
        System.out.println(user.toString());
    }

}