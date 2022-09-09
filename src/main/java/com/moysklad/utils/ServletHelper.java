package com.moysklad.utils;

import com.google.gson.Gson;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

public class ServletHelper {
    private static final Gson gson = new Gson();
    public static void writeJsonToBody(HttpServletResponse resp, Object data) throws IOException {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        PrintWriter out = resp.getWriter();
        out.print(toJson(data));
        out.flush();
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }

    public static String getEndPart(HttpServletRequest req) {
        String[] parts = req.getRequestURI().split("/");
        return parts[parts.length - 1];
    }
}
