package com.Server.handlers;


import com.Server.JWT.JwtExtractor;
import com.Server.controller.EducationController;
import com.Server.model.Skill;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.SQLException;

public class EducationHandler implements HttpHandler {
    @Override
    public void handle(HttpExchange exchange) throws IOException {

        String response = "";
        String id = "";

        try {
            EducationController educationController = new EducationController();
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
                response = "Wrong input";
                exchange.sendResponseHeaders(400, response.length());
                sendResponse(exchange, response);
                return;
            }

            if (request.equals("GET")) {

                if (pathParts.length == 3) {
                    if (pathParts[2].equals("all")) { // education/all
                        response = educationController.getEducations();
                        exchange.sendResponseHeaders(200, response.length());
                    } else { // education/instituteName
                        String institute = pathParts[2];

                        if (id != null) {
                            response = educationController.getEducationByIDAndInstitute(id, institute);
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
                    }
                } else if (pathParts.length == 2) { // /education -> return all of that person's educations

                    if (id != null) {
                        response = educationController.getEducations(id);
                        if (response == null) {
                            response = "Wrong input!";
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

            if (request.equals("PUT")) {
                {

                    if (pathParts.length == 2) { // education -> update education

                        JSONObject jsonObject = getJsonObject(exchange);
                        String institute = jsonObject.getString("institute");
                        if (!isValidJson(jsonObject)) {
                            response = "Wrong input";
                            exchange.sendResponseHeaders(400, response.length());
                            sendResponse(exchange, response);
                        }
                        if (id == null) {
                            response = "Wrong input";
                            exchange.sendResponseHeaders(400, response.length());
                        } else {
                            if (educationController.getEducationByIDAndInstitute(id, institute) != null) {
                                educationController.updateEducation(
                                        jsonObject.getString("id"),
                                        jsonObject.getString("institute"),
                                        jsonObject.getString("field_study"),
                                        new Date(jsonObject.getLong("start_date")),
                                        new Date(jsonObject.getLong("finish_date")),
                                        jsonObject.getFloat("grade"),
                                        jsonObject.getString("activity_description"),
                                        jsonObject.getString("education_description"),
                                        (Skill) jsonObject.get("skill"),
                                        jsonObject.getString("edu_notification"));
                                response = "Done";
                                exchange.sendResponseHeaders(200, response.length());
                            } else {
                                response = "Wrong input";
                                exchange.sendResponseHeaders(400, response.length());
                            }
                        }
                    } else {
                        response = "Wrong inputt";
                        exchange.sendResponseHeaders(400, response.length());
                    }

                }
            }
            if (request.equals("DELETE")) {
                {

                    if (pathParts.length == 2) {

                        if (id == null) {
                            response = "Wrong input";
                            exchange.sendResponseHeaders(400, response.length());
                        } else if (educationController.getEducations(id) != null) {
                            educationController.deleteEducationByID(id);
                            response = "Done";
                            exchange.sendResponseHeaders(200, response.length());
                        } else {
                            response = "Wrong input";
                            exchange.sendResponseHeaders(400, response.length());
                        }
                    } else if (pathParts.length == 3) {

                        String institute = pathParts[2];
                        if (id == null) {
                            response = "Wrong input";
                            exchange.sendResponseHeaders(400, response.length());
                        } else if (educationController.getEducationByIDAndInstitute(id, institute) != null) {
                            educationController.deleteEducationByIDAndInstitute(id, institute);
                            response = "Done";
                            exchange.sendResponseHeaders(200, response.length());
                        } else {
                            response = "Education not found";
                            exchange.sendResponseHeaders(404, response.length());
                        }
                    } // education/instituteName
                    else {
                        response = "Wrong input";
                        exchange.sendResponseHeaders(400, response.length());
                    }

                }
            }
            if (request.equals("POST")) {
                {

                    if (pathParts.length == 2) {

                        JSONObject jsonObject = getJsonObject(exchange);
                        if (!isValidJson(jsonObject)) {
                            response = "Wrong input";
                            exchange.sendResponseHeaders(400, response.length());
                            sendResponse(exchange, response);
                        }
                        if (id != null) {
                            if (educationController.getEducationByIDAndInstitute(id, jsonObject.getString("institute")) == null) {
                                educationController.creatEducation(
                                        jsonObject.getString("id"),
                                        jsonObject.getString("institute"),
                                        jsonObject.getString("field_study"),
                                        new Date(jsonObject.getLong("start_date")),
                                        new Date(jsonObject.getLong("finish_date")),
                                        jsonObject.getFloat("grade"),
                                        jsonObject.getString("activity_description"),
                                        jsonObject.getString("education_description"),
                                        (Skill) jsonObject.get("skill"),
                                        jsonObject.getString("edu_notification"));
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
                    } else {
                        response = "Wrong input";
                        exchange.sendResponseHeaders(400, response.length());
                    }

                }
            } else {
                response = "Wrong input";
                exchange.sendResponseHeaders(400, response.length());
                sendResponse(exchange, response);
            }
            sendResponse(exchange, response);
        } catch (Exception e) {
            response = "Error";
            exchange.sendResponseHeaders(400, response.length());
            sendResponse(exchange, response);
            e.printStackTrace();
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

    private boolean isValidJson(JSONObject jsonObject) {
        return jsonObject.has("institute") && jsonObject.has("field_study") && jsonObject.has("start_date") && jsonObject.has("finish_date") && jsonObject.has("grade") && jsonObject.has("activity_description") && jsonObject.has("education_description") && jsonObject.has("edu_notification") && jsonObject.has("id") && jsonObject.has("skill");
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();
        exchange.sendResponseHeaders(200, response.length());
    }


}
