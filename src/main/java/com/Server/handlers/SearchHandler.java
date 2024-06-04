package com.Server.handlers;

import com.Server.controller.SearchController;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.sql.SQLException;

public class SearchHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String response = "";
        try {
            SearchController searchController = new SearchController();
            String request = exchange.getRequestMethod();
            String path = exchange.getRequestURI().getPath();
            String[] paths = path.split("/");
            if (request.equals("GET")) {
                if (paths.length == 3) { //search/searchBODY
                    String search = paths[2];
                    response = searchController.searchUser(search);
                    exchange.sendResponseHeaders(200, response.length());
                } else {
                    response = "Wrong Command";
                    exchange.sendResponseHeaders(404, response.length());
                }
            } else {
                response = "Wrong command";
                exchange.sendResponseHeaders(400, response.length());
            }
        } catch (SQLException e) {
            response = "Wrong Command";
            exchange.sendResponseHeaders(404, response.length());
            throw new RuntimeException(e);
        }
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();
    }
}
