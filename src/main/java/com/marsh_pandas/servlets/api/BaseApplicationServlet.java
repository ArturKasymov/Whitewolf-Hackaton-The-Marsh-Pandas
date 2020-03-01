package com.marsh_pandas.servlets.api;


import com.marsh_pandas.model.entities.BaseApplicationEntity;
import com.marsh_pandas.model.interactors.Interactor;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import java.util.List;

abstract public class BaseApplicationServlet extends HttpServlet {


    protected Interactor interactor;

    @Override
    public void init() throws ServletException {
        try {
            interactor = Interactor.getInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected JSONObject getResponseJSON(List<? extends BaseApplicationEntity> source, String fieldName){

        JSONObject responseJSON = new JSONObject();
        JSONArray JSONDataArray = new JSONArray();
        if(source!=null) {
            for (BaseApplicationEntity entity : source) {
                JSONDataArray.put(entity.getJSON());
            }
        }
        responseJSON.put(fieldName, JSONDataArray);
        return responseJSON;
    }
}
