package com.revature.daos;

import com.revature.pojos.Requests;
import com.revature.pojos.User;
import com.revature.services.ConnectionManager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class RequestOptions implements DatabaseCRUD<Requests> {
    Connection connection;
    int id;

    public RequestOptions(Connection connection){
        this.connection = connection;
    }

    public RequestOptions(){
        connection = ConnectionManager.getConnection();
    }

    @Override
    public void create(Requests requests){

        try{
            String sql = "INSERT INTO requests (title, request_date, amount_requested, reason_for_reimbursement, reimbursement_comments, " +
                    "approve_deny) VALUES (?, ?, ?, ?, ?, false)" ;
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setString(1, requests.getTitle());
            pstmt.setString(2, requests.getRequestDate());
            pstmt.setDouble(3, requests.getAmtRequested());
            pstmt.setString(4, requests.getRsnforReimburse());
            pstmt.setString(5, requests.getCmtReimburse());
            pstmt.setString(6, requests.getStatus());

            pstmt.executeUpdate();


        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public void create(Requests request, int userID){

        try{
            String sql = "INSERT INTO requests (request_fk, title, request_date, amount_requested, reason_for_reimbursement, reimbursement_comments, " +
                    "status) VALUES (?, ?, ?, ?, ?, ?, ?)" ;
            PreparedStatement pstmt = connection.prepareStatement(sql);

            pstmt.setInt(1, userID);
            pstmt.setString(2, request.getTitle());
            pstmt.setString(3, request.getRequestDate());
            pstmt.setDouble(4, request.getAmtRequested());
            pstmt.setString(5, request.getRsnforReimburse());
            pstmt.setString(6, request.getCmtReimburse());
            pstmt.setString(7, "Pending");

            pstmt.executeUpdate();


        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public Requests read(int requestID) {
        Requests requests = new Requests();
        User user = new User();

        try{
            String sql = "SELECT * FROM requests WHERE request_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, requestID);;
            ResultSet results = pstmt.executeQuery();



            if(results.next()){
                requests.setRequestID(results.getInt("request_id"));
                requests.setRequestFK(results.getInt("request_fk"));
                requests.setTitle(results.getString("title"));
                requests.setRequestDate(results.getString("request_date"));
                requests.setAmtRequested(results.getDouble("amount_requested"));
                requests.setRsnforReimburse(results.getString("reason_for_reimbursement"));
                requests.setCmtReimburse(results.getString("reimbursement_comments"));
                requests.setStatus(results.getString("status"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public Requests read(int requestID, int userID) {
        Requests requests = new Requests();
        User user = new User();

        try{
            String sql = "SELECT * FROM requests WHERE request_id = ? AND request_fk = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, requestID);
            pstmt.setInt(2, userID);
            ResultSet results = pstmt.executeQuery();



            if(results.next()){
                requests.setRequestID(results.getInt("request_id"));
                requests.setRequestFK(results.getInt("request_fk"));
                requests.setTitle(results.getString("title"));
                requests.setRequestDate(results.getString("request_date"));
                requests.setAmtRequested(results.getDouble("amount_requested"));
                requests.setRsnforReimburse(results.getString("reason_for_reimbursement"));
                requests.setCmtReimburse(results.getString("reimbursement_comments"));
                requests.setStatus(results.getString("status"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return requests;
    }

    @Override
    public List<Requests> readAll() {
        List<Requests> requestList = new LinkedList<>();
        Requests requests = new Requests();
        User user = new User();

        try{
            String sql = "SELECT * FROM requests WHERE user_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            ResultSet results = pstmt.executeQuery();

            while(results.next()){
                requests.setRequestID(results.getInt("request_id"));
                requests.setRequestFK(results.getInt("request_fk"));
                requests.setTitle(results.getString("title"));
                requests.setRequestDate(results.getString("request_date"));
                requests.setAmtRequested(results.getDouble("amount_requested"));
                requests.setRsnforReimburse(results.getString("reason_for_reimbursement"));
                requests.setCmtReimburse(results.getString("reimbursement_comments"));
                requests.setStatus(results.getString("status"));;
                requestList.add(requests);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }

        return requestList;
    }

    @Override
    public void update(Requests requests) {

        try{
            String sql = "UPDATE requests SET title = ?, request_date = ?, amount_requested = ?, reason_for_reimbursement = ?, " +
                    "reimbursement_comments = ?, status = ? WHERE request_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, requests.getTitle());
            pstmt.setString(2, requests.getRequestDate());
            pstmt.setDouble(3, requests.getAmtRequested());
            pstmt.setString(4, requests.getRsnforReimburse());
            pstmt.setString(5, requests.getCmtReimburse());
            pstmt.setString(6, requests.getStatus());
            pstmt.setInt(7, requests.getRequestID());

        }catch (SQLException e){
            e.printStackTrace();
        }

    }

    @Override
    public void delete(int id) {
        try{
            String sql = "DELETE FROM requests WHERE request_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Requests> readAll(int userID) {
        List<Requests> requestList = new LinkedList<>();
        try {
            String sql = "SELECT * FROM requests WHERE request_fk = ?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setInt(1, userID);

            ResultSet results = pstmt.executeQuery();

           while(results.next()){
                Requests requests = new Requests();
                requests.setTitle(results.getString("title"));
                requests.setRequestDate(results.getString("request_date"));
                requests.setAmtRequested(results.getDouble("amount_requested"));
                requests.setRsnforReimburse(results.getString("reason_for_reimbursement"));
                requests.setCmtReimburse(results.getString("reimbursement_comments"));
                requests.setStatus(results.getString("status"));
                requestList.add(requests);
            }


        }catch (SQLException e){
            e.printStackTrace();
        }


        return requestList;
    }

}
