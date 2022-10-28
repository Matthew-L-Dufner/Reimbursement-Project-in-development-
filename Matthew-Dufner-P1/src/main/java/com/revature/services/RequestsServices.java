package com.revature.services;

import com.revature.daos.RequestOptions;
import com.revature.pojos.Requests;

import java.util.List;

public class RequestsServices {

    private RequestOptions ROpts;

    public RequestsServices(){
        this.ROpts = new RequestOptions();
    }

    public void saveRequests(Requests requests, int userID){
        ROpts.create(requests, userID);
    }

    public void saveRequests(Requests requests){
        ROpts.create(requests);
    }
    public void updateRequests(Requests requests){
        ROpts.update(requests);
    }

    public Requests getRequests(int requestID,int requestFK){
        return ROpts.read(requestID, requestFK);
    }
    public Requests getRequests(int requestID){
        return ROpts.read(requestID);
    }

    public List<Requests> getAllRequests(int id){
        return ROpts.readAll(id);
    }

    public void deleteRequests(int id){
        ROpts.delete(id);
    }
}
