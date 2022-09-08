package com.moysklad.servlet;

import com.google.gson.Gson;
import com.moysklad.dto.ReceiptDto;
import com.moysklad.service.DocumentsRepository;
import com.moysklad.service.DocumentsRepositoryImpl;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "ReceiptsServlet", urlPatterns = "/receipts")
public class ReceiptServlet extends HttpServlet {
    private final DocumentsRepository documentsRepository = new DocumentsRepositoryImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String stringBody = documentsRepository.readBody(req);
        ReceiptDto receiptDto = new Gson().fromJson(stringBody, ReceiptDto.class);

//        documentsRepository.addStoreToDatabase(store);
    }

//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
//        List<Store> stores = documentsRepository.getAllStoresFromDatabase();
//        String response = new Gson().toJson(stores);
//
//        documentsRepository.writeBody(resp, response);
//    }
}
