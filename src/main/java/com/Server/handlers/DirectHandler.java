package com.Server.handlers;


import com.Server.JWT.JwtExtractor;
import com.Server.controller.MessageController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class DirectHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        try {
            MessageController messageController = new MessageController();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String response = "";
        String id = "";

        try {
            String request = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            String[] pathParts = path.split("/");
            id = JwtExtractor.ExtractToken(exchange);
            if (id == null) {
                response = "Wrong input";
                exchange.sendResponseHeaders(400, response.length());
                sendResponse(exchange, response);
            }

            try {
                if (request.equals("POST"))
                    response = null;

                if (request.equals("DELETE"))
                    response = null;

                if (request.equals("PUT"))
                    response = null;
                else {
                    response = "Wrong input";
                    exchange.sendResponseHeaders(400, response.length());
                }
            } catch (Exception e) {
                response = "Error";
                exchange.sendResponseHeaders(400, response.length());
                e.printStackTrace();
            }
        } catch (Exception e) {
            response = "Error";
            exchange.sendResponseHeaders(400, response.length());
            sendResponse(exchange, response);
            return;
        }
        sendResponse(exchange, response);
    }


    private static JSONObject getJsonObject(HttpExchange exchange) throws IOException {
        try (InputStream requestBody = exchange.getRequestBody();
             BufferedReader reader = new BufferedReader(new InputStreamReader(requestBody))) {
            StringBuilder body = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                body.append(line);
            }
            return new JSONObject(body.toString());
        }
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();
        exchange.sendResponseHeaders(200, response.length());
    }
}
