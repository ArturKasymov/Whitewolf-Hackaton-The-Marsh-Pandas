package com.marsh_pandas.servlets.api;

import com.marsh_pandas.model.interactors.Interactor;
import com.marsh_pandas.model.interactors.RegistrationInteractor;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(
        name = "API_REGISTRATION",
        urlPatterns = "/api/registration"
)
public class RegistrationServlet extends BaseApplicationServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        System.out.println(email + " " + password);

        int userID = interactor.registerUser(email, password);

        JSONObject respJSON = new JSONObject();
        if(userID>0){
            respJSON.put("user_token", userID);
            resp.getWriter().println(respJSON.toString());
        }
        else {
            resp.sendError(404);
        }
    }

}
