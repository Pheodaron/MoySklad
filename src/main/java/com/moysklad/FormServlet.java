package com.moysklad;

import com.google.gson.Gson;
import com.moysklad.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "FormServlet", urlPatterns = "/calculateServlet")
public class FormServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Product testProduct = new Product("123123", "product-name", "100$", "300$");

            addJsonResponse(resp, testProduct);
        } catch (Exception e) {
            req.getRequestDispatcher("/index.jsp").forward(req, resp);
        }

    }

    private void addJsonResponse(HttpServletResponse response, Object object) throws IOException {
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String employeeJsonString = new Gson().toJson(object);
        out.print(employeeJsonString);
        out.flush();
    }
}
