package com.marsh_pandas.servlets.api;

import com.marsh_pandas.model.interactors.Interactor;
import com.marsh_pandas.model.interactors.LoginInteractor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(
        name = "API_LOGIN",
        urlPatterns = "/api/login"
)
public class LoginServlet extends BaseApplicationServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        int userID = interactor.checkUserLoginData(email, password);

        if (userID > 0) {
            resp.getWriter().println(userID);
        }else
        {
            resp.sendError(404);
        }
    }
}
