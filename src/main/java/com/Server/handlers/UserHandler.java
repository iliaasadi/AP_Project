package com.Server.handlers;

import com.Server.controller.UserController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.*;
import java.sql.Date;

public class UserHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            UserController userController = new UserController();

            String request = exchange.getRequestMethod();

            if (request.equals("POST")) {
                try (InputStream requestBody = exchange.getRequestBody();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(requestBody))) {
                    StringBuilder body = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        body.append(line);
                    }
                    JSONObject jsonObject = new JSONObject(body.toString());
                    if ((userController.isUserExist(jsonObject.getString("id")))) {
                        exchange.sendResponseHeaders(400, "DUPLICATE".length());
                        OutputStream outputStream = exchange.getResponseBody();
                        outputStream.write("DUPLICATE".getBytes());
                        outputStream.close();
                        return;
                    }
                    userController.createUser(jsonObject.getString("id"),
                                    jsonObject.getString("email"),
                                    jsonObject.getString("firstname"),
                                    jsonObject.getString("lastname"),
                                    jsonObject.getString("password"),
                                    jsonObject.getString("additionalname"),
                                    new Date(jsonObject.getLong("joindate")),
                                    jsonObject.getString("worktype"));
                    exchange.sendResponseHeaders(200, "DONE".length());
                    OutputStream outputStream = exchange.getResponseBody();
                    outputStream.write("DONE".getBytes());
                    outputStream.close();
                }

            }
        } catch (Exception e) {
            exchange.sendResponseHeaders(400, "ERROR".length());
            OutputStream outputStream = exchange.getResponseBody();
            outputStream.write("ERROR".getBytes());
            outputStream.close();
            e.printStackTrace();
        }
    }
}
