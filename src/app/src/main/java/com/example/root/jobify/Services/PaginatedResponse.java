package com.example.root.jobify.Services;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 17/09/16.
 *
 * This clas wraps the paginated response of the server. It keeps information about pagination and the actual data array of objects as payload.
 */
public class PaginatedResponse<T> {

    public final Table<T> table;
    public final Boolean modifiable;

    public PaginatedResponse(Table<T> table, Boolean modifiable) {
        this.table = table;
        this.modifiable = modifiable;
    }

    public class Table<T> {

        public ArrayList<T> payload;
        public final int total;

        private Table(ArrayList<T> payload, int total) {
            this.payload = payload;
            this.total = total;
        }
    }
}
