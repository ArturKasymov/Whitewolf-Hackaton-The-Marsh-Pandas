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
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import static com.marsh_pandas.model.data_provider.PostgreSQLQueries.GET_PRODUKTY_PRZEPISU;
import static com.marsh_pandas.model.data_provider.PostgreSQLQueries.GET_PRZEPIS;

@WebServlet(
        name = "API_RECIPES",
        urlPatterns = "/api/user/recipes"
)

public class UserRecipesServlet extends HttpServlet {
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

        List<Recipe> list_recipes=interactor.getRecipesByAuthor(Integer.parseInt(user_token));
        JSONObject responseJSON = new JSONObject();
        JSONArray list_recipesJSON = new JSONArray();
        if(list_recipes!=null) {
            for (Recipe recipe : list_recipes) {
                list_recipesJSON.put(recipe.getJSON());
            }
        }
        responseJSON.put("products_recipes", list_recipesJSON);
        resp.getWriter().println(responseJSON.toString());
    }
}
