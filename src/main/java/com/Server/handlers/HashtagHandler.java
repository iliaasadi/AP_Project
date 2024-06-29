package com.Server.handlers;


import com.Server.JWT.JwtExtractor;
import com.Server.controller.HashTagController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class HashtagHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        HashTagController hashTagController = null;
        try {
            hashTagController = new HashTagController();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String request = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] pathParts = path.split("/");
        String response = "";
        String id = JwtExtractor.ExtractToken(exchange);

        try {
            if (id == null) {
                response = "Wrong input";
                exchange.sendResponseHeaders(400, response.length());
                sendResponse(exchange, response);
                return;
            }
        } catch (Exception e) {
            response = "Error";
            exchange.sendResponseHeaders(400, response.length());
            sendResponse(exchange, response);
            return;
        }
        try {

            if (request.equals("GET")) {
                if (pathParts.length == 2) {
                    response = hashTagController.getByHashTag(pathParts[1]);
                    if (response == null) {
                        response = "Wrong input";
                        exchange.sendResponseHeaders(400, response.length());
                    } else {
                        exchange.sendResponseHeaders(200, response.length());
                    }
                } else {
                    response = "Wrong input";
                    exchange.sendResponseHeaders(400, response.length());
                }
            } else {
                response = "Invalid request";
                exchange.sendResponseHeaders(400, response.length());
            }
        } catch (SQLException | IOException e) {
            response = "Error";
            exchange.sendResponseHeaders(400, response.length());
        }
        sendResponse(exchange, response);
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
        exchange.close();
    }


}
