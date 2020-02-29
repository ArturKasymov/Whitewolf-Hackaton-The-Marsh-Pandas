package com.marsh_pandas.servlets;


import com.marsh_pandas.model.data_provider.DatabaseProvider;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(
        name = "sample_servlet/",
        urlPatterns = "/sample"
)
public class SampleServlet extends HttpServlet {


    @Override
    public void init() throws ServletException {

        try {
            DatabaseProvider provider = new DatabaseProvider();


        } catch (Exception e) {
            e.printStackTrace();
        }

        super.init();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.getWriter().println("Lorum ipsum");
    }
}