package com.example.root.jobify.Deserializers;

import com.example.root.jobify.Globals;
import com.example.root.jobify.Models.Person;
import com.example.root.jobify.Services.Persons.PersonsArrayResponse;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by dani on 24/09/16.
 */
public class PersonsArrayDeserializer implements JsonDeserializer<PersonsArrayResponse> {


    @Override
    public PersonsArrayResponse deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ArrayList<Person> persons = new ArrayList<>();
        PersonDeserializer deserializer = new PersonDeserializer();
        for(JsonElement jsonElement:json.getAsJsonArray()){
            Person person = deserializer.deserialize(jsonElement, Person.class, context);
            Date instanceStartDate = Globals.parseDate(jsonElement.getAsJsonObject().get("closest_session_start_date").getAsString(), Globals.DATE_SHORT_STYLE);
            persons.add(person);
        }
        return new PersonsArrayResponse(persons);
    }
}
