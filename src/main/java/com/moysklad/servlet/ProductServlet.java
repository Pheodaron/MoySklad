package com.moysklad.servlet;

import com.google.gson.Gson;
import com.moysklad.service.DocumentsRepository;
import com.moysklad.service.DocumentsRepositoryImpl;
import com.moysklad.entity.Product;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ProductServlet", urlPatterns = "/products/*")
public class ProductServlet extends HttpServlet {

    private final DocumentsRepository documentsRepository = new DocumentsRepositoryImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String[] parts = req.getRequestURI().split("/");
        Long storeId = Long.parseLong(parts[parts.length-1]);
        String stringBody = documentsRepository.readBody(req);
        Product product = new Gson().fromJson(stringBody, Product.class);
        documentsRepository.addProductToDatabase(product, storeId);
    }
}
