package com.Ap_project;

import com.Ap_project.handlers.UserHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args) {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(8585), 0);

            httpServer.createContext("/user", new UserHandler());
            httpServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
