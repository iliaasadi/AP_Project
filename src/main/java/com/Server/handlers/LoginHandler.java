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
                if ((paths.length == 4) && (!(paths[2].contains("@")))) {
                    response = userController.getUserByIdAndPass(paths[2], paths[3]);
                       if (!userController.isUserExist(paths[2])) {
                        response = "Invalid username";
                        exchange.sendResponseHeaders(402, response.length());
                        return;
                    }else if (userController.isUserExist(paths[2]) && (response == null)) {
                        response = "Wrong password";
                        exchange.sendResponseHeaders(401, response.length());
                        return;
                    }
                    else if (response != null) {
                        response = JwtBuilder.MakeToken(paths[2]);
                        exchange.sendResponseHeaders(200, response.length());
                        return;
                    }else {
                        response = "Error";
                        exchange.sendResponseHeaders(400, response.length());
                        return;
                    }

                }else if ((paths.length == 4) && (paths[2].contains("@"))) {
                    response = userController.getUserByEmailAndPass(paths[2], paths[3]);
                       if (!userController.isEmailExist(paths[2])) {
                        response = "Invalid email";
                        exchange.sendResponseHeaders(402, response.length());
                        return;
                    }else if (userController.isEmailExist(paths[2]) && (response == null)) {
                        response = "Wrong password";
                        exchange.sendResponseHeaders(401, response.length());
                        return;
                    }
                    else if (response != null) {
                        response = JwtBuilder.MakeToken(paths[2]);
                        exchange.sendResponseHeaders(200, response.length());
                        return;
                    }else {
                        response = "Error";
                        exchange.sendResponseHeaders(400, response.length());
                        return;
                    }

                }
            } else {
                response = "Wrong input";
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
