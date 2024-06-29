package com.Server.handlers;


import com.Server.JWT.JwtExtractor;
import com.Server.controller.LikeController;
import com.Server.controller.PostController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.SQLException;

public class LikeHandler implements HttpHandler {


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        PostController postController = null;
        LikeController likeController = null;

        try {
            postController = new PostController();
            likeController = new LikeController();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String request = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] pathParts = path.split("/");
        String response = "";
        String id = "";
        String post_id = pathParts[2];

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
                    if (pathParts[2].equals("all")) {// get all user likes  /like/all
                        response = likeController.getLikes(id);
                        if (response == null) {
                            response = "Wrong input";
                            exchange.sendResponseHeaders(400, response.length());
                        } else {

                            exchange.sendResponseHeaders(200, response.length());
                        }
                    } else { //like/post_id    get all likes of one post
                        try {

                            response = likeController.getLikers(post_id);
                            if (response == null) {
                                response = "Wrong input";
                                exchange.sendResponseHeaders(400, response.length());
                            } else {
                                exchange.sendResponseHeaders(200, response.length());
                            }
                        } catch (NumberFormatException e) {
                            response = "Error";
                            exchange.sendResponseHeaders(400, response.length());
                        }
                    }
                } else if (pathParts.length == 4) { // like/all/all
                    if (pathParts[2].equals("all") && pathParts[3].equals("all")) {
                        response = likeController.getAll();
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


                if (pathParts.length == 3) { //like/post_id
                    try {

                        if (postController.postExists(post_id)) {
                            if (likeController.likeExists(id, post_id)) {
                                response = "Wrong input";
                                exchange.sendResponseHeaders(400, response.length());
                            } else {
                                likeController.saveLike(id, post_id);
                                response = "Done";
                                exchange.sendResponseHeaders(200, response.length());
                            }
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
            }
            if (request.equals("DELETE")) {

                if (pathParts.length == 3) { //like/post_id
                    try {
                        if (postController.postExists(post_id)) {
                            if (likeController.likeExists(id, post_id)) {
                                likeController.deleteLike(id, post_id);
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
                    } catch (NumberFormatException e) {
                        response = "Error";
                        exchange.sendResponseHeaders(400, response.length());
                    }
                } else {
                    response = "Wrong input";
                    exchange.sendResponseHeaders(400, response.length());
                }

            } else
                response = "Wrong input";
            exchange.sendResponseHeaders(400, response.length());

        } catch (Exception e) {
            response = "Error";
            exchange.sendResponseHeaders(400, response.length());
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
