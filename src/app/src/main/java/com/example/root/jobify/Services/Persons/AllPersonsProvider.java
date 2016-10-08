package com.example.root.jobify.Services.Persons;

import com.example.root.jobify.Models.Person;
import com.example.root.jobify.Models.PersonsProvider;
import com.example.root.jobify.Services.*;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 17/09/16.
 */
public class AllPersonsProvider implements PersonsProvider {
    @Override
    public void getPersons(final Callback callback) {
        new PersonService().allPersons(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                PaginatedResponse<Person> body = (PaginatedResponse<Person>) response.body();
                callback.onResponse(call, Response.success(body.table.payload));
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                callback.onFailure(call, t);
            }
        });
    }


}
