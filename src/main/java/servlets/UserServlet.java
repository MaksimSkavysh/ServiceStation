package servlets;

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
 * Created by maksim on 01.10.2015.
 */
@WebServlet("/rest/userPaige")
public class UserServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        JSONObject jsonObject = new JSONObject();
        //TODO: need normal user
        jsonObject.put("user", new ClientCardModel("vasya","sidorov","30.06.1995","minsk","vas@gmail.com","+375291186106","1"));
        String users = jsonObject.toJSONString();
        response.setCharacterEncoding(ServletUtil.UTF_8);
        response.setContentType(ServletUtil.APPLICATION_JSON);
        PrintWriter out = response.getWriter();
        out.print(users);
        out.flush();
    }
}
