package com.marsh_pandas.servlets.api;

import com.marsh_pandas.model.interactors.Interactor;
import com.marsh_pandas.model.interactors.LoginInteractor;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Random;


@WebServlet(
        name = "API_LOGIN",
        urlPatterns = "/api/login"
)
public class UserLoginServlet extends HttpServlet {


    private LoginInteractor interactor;

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

        String email = req.getParameter("email");
        String password = req.getParameter("password");

        if (interactor.checkUserLoginData(email, password)) {
            resp.getWriter().println("OK");
        }else
        {
            resp.getWriter().println("Permission denied");
        }
    }
}