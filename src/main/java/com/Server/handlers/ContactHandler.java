package com.Server.handlers;


import com.Server.JWT.JwtExtractor;
import com.Server.controller.ContactController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
            if (request.equals("GET")) {
                if (pathParts.length == 3) {
                    if (pathParts[2].equals("all")) { // contact/all
                        response = contactController.getAll();
                        exchange.sendResponseHeaders(200, response.length());
                    } else {
                        response = "Wrong input";
                        exchange.sendResponseHeaders(400, response.length());
                    }
                } else if (pathParts.length == 2) {

                    if (id != null) {
                        response = contactController.getContact(id);
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
                    response = "Wrong input";
                    exchange.sendResponseHeaders(400, response.length());
                }


            }
            if (request.equals("POST")) {
                if (pathParts.length == 2) {

                    JSONObject jsonObject = getJsonObject(exchange);

                    if (!isValidJson(jsonObject)) {
                        response = "Wrong input";
                        exchange.sendResponseHeaders(400, response.length());
                    }

                    if (id != null) {
                        contactController.creatContact(
                                jsonObject.getString("id"),
                                jsonObject.getString("profile_url"),
                                jsonObject.getString("email"),
                                jsonObject.getString("phone_number"),
                                jsonObject.getString("phone_type"),
                                new Date(jsonObject.getLong("birth_date")),
                                jsonObject.getString("address"),
                                jsonObject.getString("birthday_policy"),
                                jsonObject.getString("instant_message")
                        );

                        response = "Done";
                        exchange.sendResponseHeaders(200, response.length());
                    }
                } else {
                    response = "Wrong input";
                    exchange.sendResponseHeaders(400, response.length());
                }
            }
            if (request.equals("DELETE")) {
                if (pathParts.length == 2) {

                    if (id == null) {
                        response = "Wrong input";
                        exchange.sendResponseHeaders(400, response.length());
                    } else {
                        contactController.deleteContactByID(id);
                        response = "Done";
                        exchange.sendResponseHeaders(200, response.length());
                    }
                } else {
                    response = "Wrong input";
                    exchange.sendResponseHeaders(400, response.length());
                }
            }
            if (request.equals("PUT")) {
                if (pathParts.length == 2) {
                    JSONObject jsonObject = getJsonObject(exchange);

                    if (!isValidJson(jsonObject)) {
                        response = "Wrong input";
                        exchange.sendResponseHeaders(400, response.length());
                    }
                    if (id == null) {
                        response = "Wrong input";
                        exchange.sendResponseHeaders(400, response.length());
                    } else {
                        if (id != null) {
                            contactController.updateContact(
                                    jsonObject.getString("id"),
                                    jsonObject.getString("profile_url"),
                                    jsonObject.getString("email"),
                                    jsonObject.getString("phone_number"),
                                    jsonObject.getString("phone_type"),
                                    new Date(jsonObject.getLong("birth_date")),
                                    jsonObject.getString("address"),
                                    jsonObject.getString("birthday_policy"),
                                    jsonObject.getString("instant_message")
                            );
                            response = "Done";
                            exchange.sendResponseHeaders(200, response.length());
                        } else {
                            response = "Wrong input";
                            exchange.sendResponseHeaders(400, response.length());
                        }
                    }
                } else {
                    response = "Wrong input";
                    exchange.sendResponseHeaders(400, response.length());
                }

            } else {
                response = "Wrong input";
                exchange.sendResponseHeaders(400, response.length());
            }
        } catch (Exception e) {
            response = "Error";
            exchange.sendResponseHeaders(400, response.length());
            e.printStackTrace();
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

    private boolean isValidJson(JSONObject jsonObject) {
        return jsonObject.has("id") && jsonObject.has("profile_url") && jsonObject.has("email") && jsonObject.has("phone_number") && jsonObject.has("phone_type") && jsonObject.has("birth_date")&& jsonObject.has("address")&& jsonObject.has("birthday_policy")&& jsonObject.has("instant_message");
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();
        exchange.sendResponseHeaders(200, response.length());
    }
}
