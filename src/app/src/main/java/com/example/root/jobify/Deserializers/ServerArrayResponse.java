package com.example.root.jobify.Deserializers;

import java.util.ArrayList;

/**
 * Created by root on 17/09/16.
 *
 * This clas wraps the paginated response of the server. It keeps information about pagination and the actual data array of objects as payload.
 */
public class ServerArrayResponse<T> {

    public ArrayList<T> data;
    public String status;

    public ServerArrayResponse(ArrayList<T> data, String status) {
        this.data = data;
        this.status = status;
    }
}
