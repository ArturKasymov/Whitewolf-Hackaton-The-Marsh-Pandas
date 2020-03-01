package com.marsh_pandas.servlets.api;


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
        name = "API_RECIPES",
        urlPatterns = "/api/recipes"
)

public class ReceiptsServlet extends BaseApplicationServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String user_token = req.getParameter("user_token");
        List<Recipe> list_recipes=interactor.getRecipesForUser(Integer.parseInt(user_token));
        JSONObject responseJSON = new JSONObject();
        JSONArray list_recipesJSON = new JSONArray();
        if(list_recipes!=null) {
            for (Recipe recipe : list_recipes) {
                list_recipesJSON.put(recipe.getJSON());
            }
        }
        responseJSON.put("recipes_list", list_recipesJSON);
        resp.getWriter().println(responseJSON.toString());
    }
}
