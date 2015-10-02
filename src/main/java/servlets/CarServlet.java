package servlets;

import dao.UserDao;
import dao.UserDaoImpl;
import db.dbManager;
import models.CarModel;
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
import java.sql.SQLException;

/**
 * Created by maksim on 02.10.2015.
 */

@WebServlet("/rest/Cars")
public class CarServlet extends HttpServlet {
    private UserDao userDao;
    private dbManager db;

    @Override
    public void init() throws ServletException {
        this.userDao=new UserDaoImpl();
        this.db=new dbManager();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("cars",userDao.getUserCars(id));
        String cars = jsonObject.toJSONString();
        response.setCharacterEncoding(ServletUtil.UTF_8);
        response.setContentType(ServletUtil.APPLICATION_JSON);
        PrintWriter out = response.getWriter();
        out.print(cars);
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String json = ServletUtil.getMessageBody(request);
        CarModel car=userDao.jsonToCar(json);
        db.addCar(car);
    }

    @Override
    protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String json = ServletUtil.getMessageBody(request);
            userDao.editCar(json);
        } catch (ParseException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String vin = request.getParameter("vin");
            db.deleteCar(vin);
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_CONFLICT);
        }
    }
}
