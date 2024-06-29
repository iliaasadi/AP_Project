package com.Server.handlers;


import com.Server.JWT.JwtExtractor;
import com.Server.controller.FollowController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class FollowHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {


        FollowController followController = null;
        try {
            followController = new FollowController();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        String request = exchange.getRequestMethod();
        String path = exchange.getRequestURI().getPath();
        String[] pathParts = path.split("/");
        String response = "";
        String id = JwtExtractor.ExtractToken(exchange);
        String follower_id = pathParts[2];

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
                {
                    String user_id = pathParts[3];
                    if (pathParts.length == 3) {
                        if (pathParts[2].equals("follower")) { //follow/follower  -> get all followers of one user
                            try {
                                response = followController.getFollowers(id);
                                exchange.sendResponseHeaders(200, response.length());
                            } catch (Exception e) {
                                response = "Error";
                                exchange.sendResponseHeaders(400, response.length());
                            }
                        }
                        /**
                         *
                         *
                         *
                         *
                         *
                         *
                         *
                         *
                         *
                         *
                         *
                         */
                        else if (pathParts[2].equals("following")) { //follow/following  -> get all followings of one user

                            try {
                                response = followController.getFollows(id);
                                exchange.sendResponseHeaders(200, response.length());
                            } catch (Exception e) {
                                response = "Error";
                                exchange.sendResponseHeaders(400, response.length());
                            }
                        } else {
                            response = "Wrong input";
                            exchange.sendResponseHeaders(400, response.length());
                        }
/**
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *                                              followerssssssssssss & folowingsssssssssss
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */
                    } else if (pathParts.length == 4) {//follow/followings/id  --> get all **followings** of one user
                        if (pathParts[2].equals("following")) {

                            try {
                                response = followController.getFollows(user_id);
                                exchange.sendResponseHeaders(200, response.length());
                            } catch (Exception e) {
                                response = "Error";
                                exchange.sendResponseHeaders(400, response.length());
                            }
                        } else if (pathParts[2].equals("follower")) {  //follow/followers/id  --> get all **followers** of one user
                            try {
                                response = followController.getFollowers(user_id);
                                exchange.sendResponseHeaders(200, response.length());
                            } catch (Exception e) {
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

                }
            }
            if (request.equals("POST")) {

                if (pathParts.length == 3) {//follow/follower_email

                    try {
                        followController.saveFollow(id, follower_id);  //id wants to follow follower_id
                        response = "Done";
                        exchange.sendResponseHeaders(200, response.length());
                    } catch (Exception e) {
                        response = "Error";
                        exchange.sendResponseHeaders(400, response.length());
                    }
                } else {
                    response = "Wrong input";
                    exchange.sendResponseHeaders(400, response.length());
                }
            }
            if (request.equals("DELETE")) {
                if (pathParts.length == 3) {//follow/follower_email
                    try {

                        followController.deleteFollow(id, follower_id);  //id wants to unfollow follower_id
                        response = "Done";
                        exchange.sendResponseHeaders(200, response.length());
                    } catch (Exception e) {
                        response = "Error";
                        exchange.sendResponseHeaders(400, response.length());
                    }
                } else {
                    response = "Wrong input";
                    exchange.sendResponseHeaders(400, response.length());
                }
            } else {
                response = "Invalid request";
                exchange.sendResponseHeaders(400, response.length());
            }
        } catch (Exception e) {
            response = "Internal server error";
            exchange.sendResponseHeaders(500, response.length());
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