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
public class UserReceiptProductBalanceServlet extends HttpServlet {

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
        String receipt_id = req.getParameter("receipt_id");

        List<ProductBalance> list_products = interactor.getRecipeProductsBalance(
                Integer.parseInt(receipt_id),
                Integer.parseInt(user_token));

        JSONObject responseJSON = new JSONObject();
        JSONArray list_productsJSON = new JSONArray();
        if(list_products!=null) {
            for (ProductBalance product : list_products) {
                list_productsJSON.put(product.getJSON());
            }
        }
        responseJSON.put("products_balance", list_productsJSON);
        resp.getWriter().println(responseJSON.toString());
    }

}
