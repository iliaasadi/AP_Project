package com.Server.handlers;

import com.Server.JWT.JwtBuilder;
import com.Server.JWT.JwtExtractor;
import com.Server.controller.*;
import com.Server.model.Follow;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.*;
import java.sql.Date;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class UserHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";
        try {
            UserController userController = new UserController();
            JSONObject jsonObject = null;
//            String request = exchange.getRequestMethod();
            String[] paths = exchange.getRequestURI().getPath().split("/");


            if (paths[2].equals("checkingId")) {
                try (InputStream requestBody = exchange.getRequestBody();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(requestBody))) {
                    StringBuilder body = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        body.append(line);
                    }
                    jsonObject = new JSONObject(body.toString());

                    if ((userController.isUserExist(jsonObject.getString("id")))) {

                        response = "DUPLICATE ID";
                        exchange.sendResponseHeaders(401, "DUPLICATE ID".length());

                        sendResponse(exchange, response);

                    }  else {
                        response = "DONE";
                        exchange.sendResponseHeaders(200, response.length());
                        sendResponse(exchange, response);
                    }
                }
            }else if (paths[2].equals("checkingEmail")) {
                try (InputStream requestBody = exchange.getRequestBody();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(requestBody))) {
                    StringBuilder body = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        body.append(line);
                    }
                    jsonObject = new JSONObject(body.toString());

                     if ((userController.isEmailExist(jsonObject.getString("email")))) {
                        response = "DUPLICATE Email";
                        exchange.sendResponseHeaders(402, "DUPLICATE Email".length());

                        sendResponse(exchange, response);

                    } else {
                        response = "DONE";
                        exchange.sendResponseHeaders(200, response.length());
                        sendResponse(exchange, response);
                    }
                }
            } else if (paths[2].equals("creating2")) {
                try (InputStream requestBody = exchange.getRequestBody();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(requestBody))) {
                    StringBuilder body = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        body.append(line);
                    }
                    jsonObject = new JSONObject(body.toString());
                    userController.createUser(jsonObject.getString("id"),
                            jsonObject.getString("email"),
                            jsonObject.getString("first_name"),
                            jsonObject.getString("last_name"),
                            jsonObject.getString("password"),
                            jsonObject.getString("additional_name"),
                            jsonObject.getString("join_date"),
                            jsonObject.getString("work_type"));

                    response = JwtBuilder.MakeToken(jsonObject.getString("id"));
                    exchange.sendResponseHeaders(200, response.length());
                    sendResponse(exchange, response);
                }

            } else if (paths[2].equals("updating")) {
                try (InputStream requestBody = exchange.getRequestBody();
                     BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(requestBody))) {
                    StringBuilder body = new StringBuilder();
                    String line;
                    while ((line = bufferedReader.readLine()) != null) {
                        body.append(line);
                    }
                    try {
                        jsonObject = new JSONObject(body.toString());
                        System.out.println(jsonObject.toString());
                        userController.updateUser(jsonObject.getString("id"),
                                jsonObject.getString("email"),
                                jsonObject.getString("first_name"),
                                jsonObject.getString("last_name"),
                                jsonObject.getString("password"),
                                jsonObject.getString("additional_name"),
                                jsonObject.getString("city"),
                                jsonObject.getString("country"),
                                jsonObject.getString("profession"),
                                jsonObject.getString("work_type"));

                    } catch (Exception e) {
                        System.out.println("Json Error");
                        response = "Json Error";
                        exchange.sendResponseHeaders(403, response.length());
                    }
                    response = "Done";
                    exchange.sendResponseHeaders(200, response.length());
                    sendResponse(exchange, response);
                }

            } else if (paths[2].equals("view")) {
                String id = "";
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
//                System.out.println(id);
                if (id != null) {

                    response = userController.getUserById(id);

//                    System.out.println(response);
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
            } else if (paths[2].equals("deleteMyAccount")) {
                String idDelete = paths[3];
                String passwordDelete = paths[4];

                if (userController.getUserByIdAndPass(idDelete, passwordDelete) != null) {
                    ContactController contactController = new ContactController();
//                    EducationController educationController = new EducationController();
//                    PostController postController = new PostController();

                    if (contactController.getContact(idDelete) != null) {
                        contactController.deleteContactByID(idDelete);
                    }
//                    if (educationController.getEducations(idDelete) != null) {
//                        educationController.deleteEducationByID(idDelete);
//                    }
//                    if (postController.getAllPostsOfaUser(idDelete) != null) {
//                        postController.deleteAllPostsOfaUser(idDelete);
//                    }

                    userController.deleteUser(idDelete);
                    response = "User deleted successfully";
                    exchange.sendResponseHeaders(200, response.length());
                    sendResponse(exchange, response);

                } else {
                    response = "Wrong Input";
                    exchange.sendResponseHeaders(400, response.length());
                    sendResponse(exchange, response);
                }

            }
        } catch (Exception e) {
            response = "ERROR";
            exchange.sendResponseHeaders(400, "ERROR".length());
            sendResponse(exchange, response);
            e.printStackTrace();
        }
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        try (OutputStream os = exchange.getResponseBody()) {
            os.write(response.getBytes());
        }
        exchange.close();
    }
}
