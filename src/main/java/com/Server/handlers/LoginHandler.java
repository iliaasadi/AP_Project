package com.Server.handlers;

import com.Server.JWT.JwtBuilder;
import com.Server.controller.UserController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class LoginHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";
        UserController userController = null;
        try {
            userController = new UserController();
            String[] paths = exchange.getRequestURI().getPath().split("/");
            String request = exchange.getRequestMethod();
            if (request.equals("GET")) {
                if (paths.length == 4) {
                    response = userController.getUserByIdAndPass(paths[2], paths[3]);
                    if (response != null) {
                        response = JwtBuilder.MakeToken(paths[2]);
                    }
                    exchange.sendResponseHeaders(200, response.length());

                }
            } else {
                response = "invalid method";
                exchange.sendResponseHeaders(400, response.length());
            }

        } catch (SQLException e) {
            response = "ERROR";
            exchange.sendResponseHeaders(400, response.length());

        }
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }
}
