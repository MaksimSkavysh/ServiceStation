package servlets;

import dao.UserDao;
import dao.UserDaoImpl;
import db.dbManager;
import models.OrderModel;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

/**
 * Created by maksim on 02.10.2015.
 */
@WebServlet("/rest/orders")
public class OrderServlet extends HttpServlet {
    private UserDao userDao;
    private dbManager db;

    @Override
    public void init() throws ServletException {
        this.userDao=new UserDaoImpl();
        this.db=new dbManager();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
        String vin = request.getParameter("vin");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("orders",userDao.getCarOrders(vin));
        String orders = jsonObject.toJSONString();
        response.setCharacterEncoding(ServletUtil.UTF_8);
        response.setContentType(ServletUtil.APPLICATION_JSON);
        PrintWriter out = response.getWriter();
        out.print(orders);
        out.flush();
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String json = ServletUtil.getMessageBody(request);
            userDao.addNewOrder(json);
        } catch (ParseException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        }
    }

}
