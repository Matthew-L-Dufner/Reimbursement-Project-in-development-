package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.Requests;
import com.revature.services.RequestsServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class AllRequestsServlet extends HttpServlet {
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
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer userID = Integer.parseInt(req.getParameter("user-id"));
            List<Requests> requestsList = service.getAllRequests(userID);

            String json = mapper.writeValueAsString(requestsList);

            resp.getWriter().println(json);



        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }
}
