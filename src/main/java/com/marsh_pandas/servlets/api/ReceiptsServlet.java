package com.marsh_pandas.servlets.api;


import com.marsh_pandas.model.entities.ProductBalance;
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


        JSONObject responceJSON = new JSONObject();


        List<Recipe> recipes = interactor.getRecipesForUser(Integer.parseInt(user_token));
        if(recipes!=null){
            JSONArray JSONDataArray = new JSONArray();
            for(Recipe rec : recipes){

                JSONObject recJSON = rec.getJSON();
                List<ProductBalance> bal = interactor.getRecipeProductsBalance(rec.getId(), Integer.parseInt(user_token));
                if(bal!=null){
                    JSONArray recProd = new JSONArray();
                    for (ProductBalance entity : bal) {
                        recProd.put(entity.getJSON());
                    }
                    recJSON.put("balance", recProd);
                }
                JSONDataArray.put(recJSON);
            }
            responceJSON.put("recipes_list",JSONDataArray);
        }



        resp.getWriter().println(responceJSON);

        //resp.getWriter().println(getResponseJSON(interactor.getRecipesForUser(Integer.parseInt(user_token)),"recipes_list"));
    }
}
