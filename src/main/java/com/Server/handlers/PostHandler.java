package com.Server.handlers;


import com.Server.JWT.JwtExtractor;
import com.Server.controller.PostController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLException;



public class PostHandler implements HttpHandler {


    @Override
    public void handle(HttpExchange exchange) throws IOException {

        PostController postController = null;
        try {
            postController = new PostController();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String request = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] pathParts = path.split("/");
        String response = "";
        String id = "";

        try {
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
                    if (pathParts[2].equals("all")) { // post/all
                        response = postController.getAllPostsOfaUser(id);
                        if (response == null) {
                            response = "Wrong input";
                            exchange.sendResponseHeaders(400, response.length());
                        } else {
                            exchange.sendResponseHeaders(200, response.length());
                        }
                    } else { //post/post_id
                        try {
                            String postId = pathParts[2];
                            response = postController.getPost(postId);
                            if (response != null) {
                                exchange.sendResponseHeaders(200, response.length());
                            } else {
                                response = "Wrong input";
                                exchange.sendResponseHeaders(400, response.length());
                            }
                        } catch (NumberFormatException e) {
                            response = "Error";
                            exchange.sendResponseHeaders(400, response.length());
                        }
                    }
                } else if (pathParts.length == 4) {
                    if (pathParts[2].equals("all") && pathParts[3].equals("all")) { //post/all/all
                        response = postController.getAll();
                        if (response != null) {
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
            }
            if (request.equals("POST")) {
                if (pathParts.length == 2) {
                    JSONObject jsonObject = getJsonObject(exchange);
                    String message = jsonObject.getString("message");
                    if (id != null) {
                        postController.creatPost(id, message, new Date());
                        response = "Done";
                        exchange.sendResponseHeaders(200, response.length());
                    } else {
                        response = "Wrong input";
                        exchange.sendResponseHeaders(400, response.length());
                    }
                } else {
                    response = "Wrong input";
                    exchange.sendResponseHeaders(400, response.length());
                }
            }
            if (request.equals("DELETE")) {

                if (pathParts.length == 3) { //post/all or post/post_id
                    if (pathParts[2].equals("all")) {
                        postController.deleteAllPostsOfaUser(id);
                        response = "Done";
                        exchange.sendResponseHeaders(200, response.length());
                    } else {
                        try {
                            String postId = (pathParts[2]);
                            if (postController.getPost(postId) == null) {
                                response = "Wrong input";
                                exchange.sendResponseHeaders(400, response.length());
                            } else {
                                postController.deletePost(postId);
                                response = "Done";
                                exchange.sendResponseHeaders(200, response.length());
                            }
                        } catch (NumberFormatException e) {
                            response = "Error";
                            exchange.sendResponseHeaders(400, response.length());
                        }
                    }
                } else if (pathParts.length == 4) { //post/all/all
                    if (pathParts[2].equals("all") && pathParts[3].equals("all")) {
                        postController.deleteAll();
                        response = "Done";
                        exchange.sendResponseHeaders(200, response.length());
                    } else {
                        response = "Wrong input";
                        exchange.sendResponseHeaders(400, response.length());
                    }
                } else {
                    response = "Wrong input";
                    exchange.sendResponseHeaders(400, response.length());
                }
            }
            if (request.equals("PUT")) {
                if (pathParts.length == 3) { //post/post_id
                    JSONObject jsonObject = getJsonObject(exchange);
                    String message = jsonObject.getString("message");
                    try {
                        String postId = (pathParts[2]);
                        if (postController.getPost(postId) != null) {
                            postController.updatePost(id, postId, message);
                            response = "Done";
                            exchange.sendResponseHeaders(200, response.length());
                        } else {
                            response = "Wrong input";
                            exchange.sendResponseHeaders(400, response.length());
                        }
                    } catch (NumberFormatException e) {
                        response = "Error";
                        exchange.sendResponseHeaders(400, response.length());
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

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();
        exchange.sendResponseHeaders(200, response.length());
    }
}
