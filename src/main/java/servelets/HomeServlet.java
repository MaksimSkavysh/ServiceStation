package servelets;

import models.CarModel;
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
    private static final long serialVersionUID = 1L;
    public static final String APPLICATION_JSON = "application/json";
    public static final String UTF_8 = "UTF-8";
    private String temp;

    @Override
    public void init() throws ServletException {
        temp="test is fone";
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            String firstName = request.getParameter("firstName");
            String lastName = request.getParameter("lastName");
            System.out.println("-----------------------------");
            System.out.println(firstName);
            System.out.println(lastName);
            System.out.println("------------------------------");
            JSONObject jsonObject = new JSONObject();
            CarModel car1=new CarModel("volvo","s300",1997,"g5ht42d3");
            jsonObject.put("car", car1);
            String cars = jsonObject.toJSONString();
            response.setCharacterEncoding(UTF_8);
            response.setContentType(APPLICATION_JSON);
            PrintWriter out = response.getWriter();
            out.print(cars);
            out.flush();
    }

}