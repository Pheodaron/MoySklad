package com.moysklad.servlet;

import com.google.gson.Gson;
import com.moysklad.entity.Store;
import com.moysklad.exceptions.EntityNotFoundException;
import com.moysklad.service.DocumentsRepository;
import com.moysklad.service.DocumentsRepositoryImpl;
import com.moysklad.utils.ServletHelper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "StoreServlet", urlPatterns = "/stores/*")
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
        var endPart = ServletHelper.getEndPart(req);
        switch (endPart) {
            case "stores" -> {
                var stores = documentsRepository.findAllStores();
                ServletHelper.writeJsonToBody(resp, stores);
            }
            default -> {
                try {
                    Long storeId = Long.parseLong(endPart);
                    var store = documentsRepository.getStoreById(storeId);
                    ServletHelper.writeJsonToBody(resp, store);
                } catch (EntityNotFoundException e) {
                    ServletHelper.writeJsonToBody(resp, e.getMessage());
                }
            }
        }
    }
}
