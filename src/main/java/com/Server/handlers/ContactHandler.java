package com.Server.handlers;


import com.Server.JWT.JwtBuilder;
import com.Server.JWT.JwtExtractor;
import com.Server.controller.ContactController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.*;
import java.sql.Date;
import java.sql.SQLException;

public class ContactHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String response = "";
        String id = "";

        try {

            ContactController contactController = new ContactController();
            String request = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            String[] pathParts = path.split("/");

            try {

                id = JwtExtractor.ExtractToken(exchange);
//                System.out.println(id);

                if (id == null) {
                    response = "Wrong input";
                    exchange.sendResponseHeaders(400, response.length());
                    sendResponse(exchange, response);

                }
            } catch (Exception e) {
                response = "Error";
                exchange.sendResponseHeaders(404, response.length());
                sendResponse(exchange, response);

            }
            if (pathParts[2].equals("view")) {

//                System.out.println(id);
                if (id != null) {
//                    System.out.println(id);

                    response = contactController.getContact(id);

                    System.out.println(response);
                    if (response == null) {
                        response = "Wrong input";
                        exchange.sendResponseHeaders(400, response.length());
                    } else {


//                        Headers responseHeaders = exchange.getResponseHeaders();
//                        responseHeaders.; // Add LKN to response headers
//                        System.out.println(id);
                        exchange.sendResponseHeaders(200, response.length());
//                        System.out.println(id);
                        sendResponse(exchange, response);


                    }
                } else {
                    response = "Wrong input";
                    exchange.sendResponseHeaders(400, response.length());
                }
            }
            if (pathParts[2].equals("edit")) {
                if (pathParts.length == 3) {

                    JSONObject json = getJsonObject(exchange);

//                    System.out.println(json.getString("birthday_policy"));
                    if (!isValidJson(json)) {
//                    System.out.println(json.getString("birthday_policy"));
                        response = "Wrong JSON";
                        exchange.sendResponseHeaders(401, response.length());

                    }
                     if (id != null && contactController.getContact(id) == null) {
                        contactController.creatContact(
                                json.getString("id"),
                                json.getString("profile_url"),
                                json.getString("email"),
                                json.getString("phone_number"),
                                json.getString("phone_type"),
                                json.getString("address"),
                                json.getString("birthday_policy"),
                                json.getString("instant_message"),
                                json.getString("birth_date")
                        );
                        response = "Done";
                        exchange.sendResponseHeaders(200, response.length());
                        sendResponse(exchange, response);

                        return;
                    } else if (id != null) {


                        contactController.updateContact(
                                json.getString("id"),
                                json.getString("profile_url"),
                                json.getString("email"),
                                json.getString("phone_number"),
                                json.getString("phone_type"),
                                json.getString("birth_date"),
                                json.getString("address"),
                                json.getString("birthday_policy"),
                                json.getString("instant_message")
                        );
                        response = "Done";
                        exchange.sendResponseHeaders(200, response.length());
                        sendResponse(exchange, response);

                        return;
                    }
                } else {
                    response = "Wrong input";
                    exchange.sendResponseHeaders(400, response.length());
                    sendResponse(exchange, response);
                }
            } else {
                response = "Wrong input";
                exchange.sendResponseHeaders(400, response.length());
            }
        } catch (Exception e) {
//            System.out.println("ErrorContactFuckedddddddddme");
            response = "Error";
            exchange.sendResponseHeaders(404, response.length());
            e.printStackTrace();
        }
//        sendResponse(exchange, response);

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

    private boolean isValidJson(JSONObject jsonObject) {
        return jsonObject.has("id") && jsonObject.has("profile_url") && jsonObject.has("email") && jsonObject.has("phone_number") && jsonObject.has("phone_type") && jsonObject.has("birth_date") && jsonObject.has("address") && jsonObject.has("birthday_policy") && jsonObject.has("instant_message");
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
        exchange.close();
    }
}
