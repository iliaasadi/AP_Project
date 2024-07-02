package com.Server.handlers;

import com.Server.controller.UserController;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.*;
import java.sql.Date;
import java.sql.Date;
import java.time.LocalDate;

public class UserHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        try {
            UserController userController = new UserController();

//            String request = exchange.getRequestMethod();
            String[] paths = exchange.getRequestURI().getPath().split("/");
            if (paths[2].equals("creating1")) {
                try (InputStream requestBody = exchange.getRequestBody();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(requestBody))) {
                    StringBuilder body = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        body.append(line);
                    }
                    JSONObject jsonObject = new JSONObject(body.toString());

                    if ((userController.isUserExist(jsonObject.getString("id")))) {
                        exchange.sendResponseHeaders(401, "DUPLICATE ID".length());
                        OutputStream outputStream = exchange.getResponseBody();
                        outputStream.write("DUPLICATE ID".getBytes());
                        outputStream.close();

                        return;
                    } else if ((userController.isEmailExist(jsonObject.getString("email")))) {
                        exchange.sendResponseHeaders(402, "DUPLICATE Email".length());
                        OutputStream outputStream = exchange.getResponseBody();
                        outputStream.write("DUPLICATE Email".getBytes());
                        outputStream.close();

                        return;
                    }

                        exchange.sendResponseHeaders(200, "DONE".length());
                        OutputStream outputStream = exchange.getResponseBody();
                        outputStream.write("DONE".getBytes());
                        outputStream.close();

                        return;
                }
            } else if (paths[2].equals("creating2")) {
                try (InputStream requestBody = exchange.getRequestBody();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(requestBody))) {
                    StringBuilder body = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        body.append(line);
                    }
                    JSONObject jsonObject = new JSONObject(body.toString());
                    userController.createUser(jsonObject.getString("id"),
                            jsonObject.getString("email"),
                            jsonObject.getString("first_name"),
                            jsonObject.getString("last_name"),
                            jsonObject.getString("password"),
                            jsonObject.getString("additional_name"),
                            jsonObject.getString("join_date"),
                            jsonObject.getString("work_type"));
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
