package com.marsh_pandas.servlets.api;

import com.marsh_pandas.model.entities.ProductBalance;
import com.marsh_pandas.model.entities.UtilEntityProduct;
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
        name = "API_USER_RECIPES_PRODUCTS_BALANCE",
        urlPatterns = "/api/user/recipes/products/balance"
)
public class UserReceiptProductBalanceServlet extends BaseApplicationServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_token = req.getParameter("user_token");
        String receipt_id = req.getParameter("receipt_id");

        resp.getWriter().println(getResponseJSON(interactor.getRecipeProductsBalance(
                Integer.parseInt(receipt_id),
                Integer.parseInt(user_token)), "products_balance"));
    }

}
