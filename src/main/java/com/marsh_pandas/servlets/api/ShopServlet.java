package com.marsh_pandas.servlets.api;

import com.marsh_pandas.model.entities.Product;
import com.marsh_pandas.model.entities.Recipe;
import com.marsh_pandas.model.interactors.Interactor;
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
        name = "API_SHOPS",
        urlPatterns = "/api/shop"
)

public class ShopServlet extends BaseApplicationServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_token = req.getParameter("user_token");
        resp.getWriter().println(getResponseJSON(interactor.getProductsShop(Integer.parseInt(user_token)), "products_shop"));
    }
}
