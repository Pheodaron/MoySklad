package com.moysklad.servlet;

import com.google.gson.Gson;
import com.moysklad.dto.StoreDto;
import com.moysklad.entity.Store;
import com.moysklad.service.DocumentsRepository;
import com.moysklad.service.DocumentsRepositoryImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "StoreServlet", urlPatterns = "/stores")
public class StoreServlet extends HttpServlet {

    private final DocumentsRepository documentsRepository = new DocumentsRepositoryImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String stringBody = documentsRepository.readBody(req);
        Store store = new Gson().fromJson(stringBody, Store.class);

        documentsRepository.addStoreToDatabase(store);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        List<StoreDto> stores = documentsRepository.getAllStoresFromDatabase();
        String response = new Gson().toJson(stores);

        documentsRepository.writeBody(resp, response);
    }
}
