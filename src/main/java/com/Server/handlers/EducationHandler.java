package com.Server.handlers;


import com.Server.DAO.EducationDAO;
import com.Server.JWT.JwtExtractor;
import com.Server.controller.EducationController;
import com.Server.model.Education;
import com.Server.model.Skill;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import org.json.JSONObject;
import org.json.JSONString;

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
                    System.out.println("w1");
                    response = "Wrong input";
                    exchange.sendResponseHeaders(400, response.length());
                    sendResponse(exchange, response);
                }
            } catch (Exception e) {
                System.out.println("w2");
                response = "Wrong input";
                exchange.sendResponseHeaders(400, response.length());
                sendResponse(exchange, response);

            }

//            if (request.equals("GET")) {
//
//                if (pathParts.length == 3) {
//                    if (pathParts[2].equals("all")) { // education/all
//                        response = educationController.getEducations();
//                        exchange.sendResponseHeaders(200, response.length());
//                    } else { // education/instituteName
//                        String institute = pathParts[2];
//
//                        if (id != null) {
//                            response = educationController.getEducationByIDAndInstitute(id, institute);
//                            if (response == null) {
//                                response = "Wrong input";
//                                exchange.sendResponseHeaders(400, response.length());
//                            } else {
//                                exchange.sendResponseHeaders(200, response.length());
//                            }
//                        } else {
//                            response = "Wrong input";
//                            exchange.sendResponseHeaders(400, response.length());
//                        }
//                    }
//                } else if (pathParts.length == 2) { // /education -> return all of that person's educations
//
//                    if (id != null) {
//                        response = educationController.getEducations(id);
//                        if (response == null) {
//                            response = "Wrong input!";
//                            exchange.sendResponseHeaders(400, response.length());
//                        } else {
//                            exchange.sendResponseHeaders(200, response.length());
//                        }
//                    } else {
//                        response = "Wrong input";
//                        exchange.sendResponseHeaders(400, response.length());
//                    }
//                } else {
//                    response = "Wrong input";
//                    exchange.sendResponseHeaders(400, response.length());
//                }
//            }

            if (pathParts[2].equals("edit")) {
                if (pathParts.length == 3) {

                    JSONObject json = getJsonObject(exchange);
//                    System.out.println(json);
//
                    if (!isValidJson(json)) {
                        response = "Wrong JSON";
                        exchange.sendResponseHeaders(401, response.length());

                    }
                    if (id != null && educationController.getEducations(id) == null) {
                         System.out.println(json.toString());
                         String a1 = json.getString("id");
                         String a2 = json.getString("institute");
                         String a3 = json.getString("field_study");
                         String a4 = json.getString("start_date");
                         String a5 = json.getString("finish_date");
                         float a6 = json.getFloat("grade");
                         String a7 = json.getString("activity_descreption");
                         String a8 = json.getString("education_description");

//                        EducationDAO educationDAO = new EducationDAO();
//                        System.out.println(a1+a2+a3+a4+a5+a6+a7+a8);
//                        Education education = new Education(a1,a2,a3,a4,a5,a6,a7,a8);
//                        System.out.println(1);
//                         educationDAO.createEducationTable();
//                        System.out.println(2);

                        educationController.creatEducation(
                                json.getString("id"),
                                json.getString("institute"),
                                json.getString("field_study"),
                                json.getString("start_date"),
                                json.getString("finish_date"),
                                json.getFloat("grade"),
                                json.getString("activity_descreption"),
                                json.getString("education_description")
                        );
                        System.out.println("done");
                        response = "Done";
                        exchange.sendResponseHeaders(200, response.length());
                        sendResponse(exchange, response);


                    } else if (id != null && educationController.getEducations(id) != null) {


                        educationController.updateEducation(
                                json.getString("id"),
                                json.getString("institute"),
                                json.getString("field_study"),
                                json.getString("start_date"),
                                json.getString("finish_date"),
                                json.getFloat("grade"),
                                json.getString("activity_descreption"),
                                json.getString("education_description")
                        );
                        response = "Done";
                        exchange.sendResponseHeaders(200, response.length());
                        sendResponse(exchange, response);


                    }else {
                        System.out.println("w3");
                        response = "Wrong input";
                        exchange.sendResponseHeaders(400, response.length());
                        sendResponse(exchange, response);
                    }
                } else {
                    System.out.println("w4");
                    response = "Wrong input";
                    exchange.sendResponseHeaders(400, response.length());
                    sendResponse(exchange, response);
                }
            }
//            if (request.equals("DELETE")) {
//                {
//
//                    if (pathParts.length == 2) {
//
//                        if (id == null) {
//                            response = "Wrong input";
//                            exchange.sendResponseHeaders(400, response.length());
//                        } else if (educationController.getEducations(id) != null) {
//                            educationController.deleteEducationByID(id);
//                            response = "Done";
//                            exchange.sendResponseHeaders(200, response.length());
//                        } else {
//                            response = "Wrong input";
//                            exchange.sendResponseHeaders(400, response.length());
//                        }
//                    } else if (pathParts.length == 3) {
//
//                        String institute = pathParts[2];
//                        if (id == null) {
//                            response = "Wrong input";
//                            exchange.sendResponseHeaders(400, response.length());
//                        } else if (educationController.getEducationByIDAndInstitute(id, institute) != null) {
//                            educationController.deleteEducationByIDAndInstitute(id, institute);
//                            response = "Done";
//                            exchange.sendResponseHeaders(200, response.length());
//                        } else {
//                            response = "Education not found";
//                            exchange.sendResponseHeaders(404, response.length());
//                        }
//                    } // education/instituteName
//                    else {
//                        response = "Wrong input";
//                        exchange.sendResponseHeaders(400, response.length());
//                    }
//
//                }
//            }
            if (pathParts[2].equals("view")) {

//                System.out.println(id);
                if (id != null) {
//                    System.out.println(id);

                    response = educationController.getEducations(id);
//                    System.out.println(response);

                    if (response == null) {
                        System.out.println("w5");
                        response = "Wrong input";
                        exchange.sendResponseHeaders(400, response.length());
                    } else {


//                        Headers responseHeaders = exchange.getResponseHeaders();
//                        responseHeaders.; // Add LKN to response headers
//                        System.out.println(id);
                        exchange.sendResponseHeaders(200, response.length());
//                        System.out.println(id);
                        sendResponse(exchange, response);


                    }
                }
            }
//            if (pathParts[2].equals("POST")) {
//                {
//
//                    if (pathParts.length == 3) {
//
//                        JSONObject jsonObject = getJsonObject(exchange);
//                        if (!isValidJson(jsonObject)) {
//                            response = "Wrong input";
//                            exchange.sendResponseHeaders(400, response.length());
//                            sendResponse(exchange, response);
//                        }
//                        if (id != null) {
//                            if (educationController.getEducationByIDAndInstitute(id, jsonObject.getString("institute")) == null) {
//                                educationController.creatEducation(
//                                        jsonObject.getString("id"),
//                                        jsonObject.getString("institute"),
//                                        jsonObject.getString("field_study"),
//                                        jsonObject.getString("start_date"),
//                                        jsonObject.getString("finish_date"),
//                                        jsonObject.getFloat("grade"),
//                                        jsonObject.getString("activity_description"),
//                                        jsonObject.getString("education_description"),
//                                        jsonObject.getString("edu_notification"));
//                                response = "Done";
//                                exchange.sendResponseHeaders(200, response.length());
//                            } else {
//                                response = "Wrong input";
//                                exchange.sendResponseHeaders(400, response.length());
//                            }
//                        } else {
//                            response = "Wrong input";
//                            exchange.sendResponseHeaders(400, response.length());
//                        }
//                    } else {
//                        response = "Wrong input";
//                        exchange.sendResponseHeaders(400, response.length());
//                    }
//
//                }
//            }
            else {
                System.out.println("w6");
                response = "Wrong input";
                exchange.sendResponseHeaders(400, response.length());
                sendResponse(exchange, response);
            }
            sendResponse(exchange, response);
        } catch (Exception e) {
            System.out.println("w7");
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
        return jsonObject.has("institute") && jsonObject.has("field_study") && jsonObject.has("start_date") && jsonObject.has("finish_date") && jsonObject.has("grade") && jsonObject.has("activity_descreption") && jsonObject.has("education_description")  && jsonObject.has("id") ;
    }

    private void sendResponse(HttpExchange exchange, String response) throws IOException {
        exchange.getResponseBody().write(response.getBytes());
        exchange.getResponseBody().close();
        exchange.sendResponseHeaders(200, response.length());
    }


}
