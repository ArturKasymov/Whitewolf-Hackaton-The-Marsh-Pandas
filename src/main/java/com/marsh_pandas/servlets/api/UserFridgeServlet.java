package com.marsh_pandas.servlets.api;

import com.marsh_pandas.model.Entites.Product;
import com.marsh_pandas.model.interactors.Interactor;
import com.marsh_pandas.model.interactors.LoginInteractor;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "API_LOGIN",
        urlPatterns = "/api/user/fridge"
)

public class UserFridgeServlet extends HttpServlet {
    private Interactor interactor;

    @Override
    public void init() throws ServletException {
        try {
            interactor = Interactor.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_token = req.getParameter("user_token");

        List<Product> list_products=interactor.getUserFridgeProducts(user_token);
        JSONObject responseJSON = new JSONObject();
        JSONArray list_productsJSON = new JSONArray();
        if(list_products!=null) {
            for (Product product : list_products) {
                list_productsJSON.put(product.getJSON());
            }
        }
        responseJSON.put("products_list", list_productsJSON);
        resp.getWriter().println(responseJSON.toString());


        /*}else
        {*/
         //   resp.getWriter().println("Permission denied");
        //}
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_token = req.getParameter("user_token");

        super.doPost(req, resp);
    }
}
