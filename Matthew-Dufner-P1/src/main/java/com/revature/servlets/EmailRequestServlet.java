package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.User;
import com.revature.services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EmailRequestServlet extends HttpServlet {
    UserServices service;
    ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        System.out.println("Email Lookup servlet initializing...");
        this.service = new UserServices();
        this.mapper = new ObjectMapper();
        System.out.println("Email Lookup servlet initialized!");
    }

    @Override
    public void destroy() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String email = req.getParameter("email");

        User authUser = service.getUserEm(email);

        String json = mapper.writeValueAsString(authUser);
        resp.getWriter().println(json);

        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }
}
