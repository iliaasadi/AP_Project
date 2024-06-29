package com.Server;

import com.Server.handlers.*;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

public class Server {
    public static void main(String[] args) {
        try {
            HttpServer httpServer = HttpServer.create(new InetSocketAddress(8080), 0);

            httpServer.createContext("/user", new UserHandler());
            httpServer.createContext("/login", new LoginHandler());
            httpServer.createContext("/search", new SearchHandler());
            httpServer.createContext("/post", new PostHandler());
            httpServer.createContext("/like", new LikeHandler());
//            httpServer.createContext("/comment", new CommentHandler());
//            httpServer.createContext("/connection", new ConnectionHandler());
//            httpServer.createContext("/follow", new FollowHandler());
//            httpServer.createContext("/hashtag", new HashtagHandler());
            httpServer.createContext("/direct", new DirectHandler());
//            httpServer.createContext("/job", new JobHandler());
//            httpServer.createContext("/contact", new ContactHandler());
//            httpServer.createContext("/skill", new SkillHandler());
//            httpServer.createContext("/education", new EducationHandler());
//            httpServer.createContext("/block", new BlockHandler());
            httpServer.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
