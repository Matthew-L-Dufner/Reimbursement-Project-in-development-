package com.revature.servlets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.pojos.User;
import com.revature.services.UserServices;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
public class UserServlet extends HttpServlet {
    UserServices service;
    ObjectMapper mapper;

    @Override
    public void init() throws ServletException {
        System.out.println("User servlet initializing...");
        this.service = new UserServices();
        this.mapper = new ObjectMapper();
        System.out.println("User servlet initialized!");
    }

    @Override
    public void destroy() {
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String param = req.getParameter("user-id");
        Integer userId = Integer.parseInt(req.getParameter("user-id"));

        if (param == null) {
            List<User> userList = service.getAllUsers();
            String json = mapper.writeValueAsString(userList);
            resp.getWriter().println(json);
        }else {
            User user = service.getUser(userId);
            String json = mapper.writeValueAsString(user);
            resp.getWriter().println(json);
        }


        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        while(buffer.ready()){
            builder.append(buffer.readLine());
        }
        String json = builder.toString();

        User newUser = mapper.readValue(json, User.class);
        service.saveUser(newUser);
        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        StringBuilder builder = new StringBuilder();
        BufferedReader buffer = req.getReader();
        String param = req.getParameter("user-id");

        if(param == null){
            resp.getWriter().println("User ID not found.");
        }else {
            Integer userId = Integer.parseInt(req.getParameter("user-id"));
            User user = service.getUser(userId);

            while (buffer.ready()) {
                builder.append(buffer.readLine());
            }
            String json = builder.toString();

            user = mapper.readValue(json, User.class);
            service.updateUser(user);
        }
        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        String param = req.getParameter("user-id");
        if(param == null){
            resp.getWriter().println("User ID not found.");
        }else{
            Integer userId = Integer.parseInt(req.getParameter("user-id"));
            service.deleteUser(userId);
            resp.getWriter().println("I'm dead and gone, dead and gone...");
        }

        resp.getWriter().println("User updated.");
        resp.setContentType("Application/Json; Charset=UTF-8");
        resp.setStatus(200);
    }
}
