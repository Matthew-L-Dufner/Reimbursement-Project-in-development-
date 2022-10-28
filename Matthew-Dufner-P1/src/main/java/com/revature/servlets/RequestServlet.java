package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.Requests;
import com.revature.services.RequestsServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;

public class RequestServlet extends HttpServlet {
    private RequestsServices service;
    private ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        this.service = new RequestsServices();
        this.mapper = new ObjectMapper();
    }

    @Override
    public void destroy(){
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
            Integer requestID = Integer.parseInt(req.getParameter("request-id"));

            Requests requests = service.getRequests(requestID);
            String json = mapper.writeValueAsString(requests);
            resp.getWriter().println(json);

            resp.setContentType("Application/Json; Charset=UTF-8");
            resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        Integer userID = Integer.parseInt(req.getParameter("user-id"));


        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();

        while(buffer.ready()){
            builder.append(buffer.readLine());
        }

        String json = builder.toString();
        Requests requests = mapper.readValue(json, Requests.class);

        service.saveRequests(requests, userID);
        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        String paramRID = req.getParameter("request-id");

        Integer requestId = Integer.parseInt(req.getParameter("request-id"));
        Requests requests = service.getRequests(requestId);

            while (buffer.ready()) {
                builder.append(buffer.readLine());
            }
            String json = builder.toString();

            requests = mapper.readValue(json, Requests.class);
            service.updateRequests(requests);
            resp.getWriter().println("Request updated.");

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String param = req.getParameter("request-id");
        if(param == null){
            resp.getWriter().println("Request ID not found.");
        }else{
            Integer requestId = Integer.parseInt(req.getParameter("request-id"));
            service.deleteRequests(requestId);
            resp.getWriter().println("I'm dead and gone, dead and gone...");
        }
        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }
}
