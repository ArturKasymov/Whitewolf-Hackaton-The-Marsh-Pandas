package com.marsh_pandas.servlets.api;


import com.marsh_pandas.model.entities.Product;
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
        name = "API_PRODUCTS_LIST",
        urlPatterns = "/api/products_list"
)

public class ProductsListServlet extends BaseApplicationServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println(getResponseJSON(interactor.getAllProducts(),"products_list"));
    }

}
