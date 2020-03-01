package com.marsh_pandas.servlets.api;

import com.marsh_pandas.model.entities.UtilEntityProduct;
import com.marsh_pandas.model.interactors.Interactor;
import org.json.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet(
        name = "API_USER_FRIDGE",
        urlPatterns = "/api/user/fridge"
)

public class UserFridgeServlet extends BaseApplicationServlet{


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_token = req.getParameter("user_token");

        List<UtilEntityProduct> list_products=interactor.getUserFridgeProducts(user_token);
        JSONObject responseJSON = new JSONObject();
        JSONArray list_productsJSON = new JSONArray();
        if(list_products!=null) {
            for (UtilEntityProduct product : list_products) {
                list_productsJSON.put(product.getJSON());
            }
        }
        responseJSON.put("products_list", list_productsJSON);
        resp.getWriter().println(responseJSON.toString());
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_token = req.getParameter("user_token");

        StringBuffer jb = new StringBuffer();
        String line = null;
        try {
            BufferedReader reader = req.getReader();
            while ((line = reader.readLine()) != null)
                jb.append(line);
        } catch (Exception e) {
            e.printStackTrace();
        }


        try {

            JSONObject body =  HTTP.toJSONObject(jb.toString());

            System.out.println(body.toString());

            String methodString = body.getString("Method");
            JSONObject method = new JSONObject(methodString);
            interactor.addProductToUserFridge(method.getInt("product_id"),Integer.parseInt(user_token),method.getBigDecimal("quantity"));
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
