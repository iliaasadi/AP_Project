package com.Server.handlers;

import com.Server.JWT.JwtExtractor;
import com.Server.controller.CommentController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLException;

public class CommentHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {

        CommentController commentController = null;
        try {
            commentController = new CommentController();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        String response = "";
        String id = "";

        try {
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

                if (pathParts.length == 3) { // comment/comment_id
                    try {
                        String comment_id = pathParts[2];
                        response = commentController.getComment(comment_id);
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
                } else if (pathParts.length == 4 && pathParts[2].equals("all")) { //comment/all/post_id
                    try {
                        String post_id = pathParts[3];
                        response = commentController.getAllCommentsOfaPost(post_id);
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
                } else {
                    response = "Wrong input";
                    exchange.sendResponseHeaders(400, response.length());
                }

            }
            if (request.equals("POST")) {
                if (pathParts.length == 3) { //comment/post_id
                    JSONObject jsonObject = getJsonObject(exchange);

                    String comment = jsonObject.getString("comment");
                    try {
                        String post_id = pathParts[2];
                        commentController.creatComment(post_id, id, comment, new Date(12312321));
                        response = "Done";
                        exchange.sendResponseHeaders(200, response.length());
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

                if (pathParts.length == 3) { //comment/comment_id
                    try {
                        String comment_id = pathParts[2];
                        if (commentController.isCommentExist(comment_id)) {

                            commentController.deleteComment(comment_id, id);
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

            }
            if (request.equals("PUT")) {

                if (pathParts.length == 3) { //comment/comment_id
                    JSONObject jsonObject = getJsonObject(exchange);
                    String comment = jsonObject.getString("comment");
                    try {
                        String comment_id = pathParts[2];
                        if (commentController.isCommentExist(comment_id, id)) {
                            commentController.updateComment(comment_id , id, comment);
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
            e.printStackTrace();
            exchange.sendResponseHeaders(400, response.length());
        } finally {
            sendResponse(exchange, response);
        }

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
