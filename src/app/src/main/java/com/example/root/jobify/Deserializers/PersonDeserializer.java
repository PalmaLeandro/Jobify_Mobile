package com.example.root.jobify.Deserializers;

import com.example.root.jobify.Models.Person;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

/**
 * Created by root on 18/09/16.
 */
public class PersonDeserializer implements JsonDeserializer<Person> {

    private static final String DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";
    private static final String PERSON_DATE_FIELD = "created_at";
    private static final String PERSON_IMAGE_FIELD = "image";
    private static final String PERSON_IMAGE_URL_FIELD = "url";

    @Override
    public Person deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        /*
        JsonObject jsonObject = json.getAsJsonObject();
        ArrayList<PersonInstance> personInstances = new ArrayList<>();
        JsonArray sessionsArray = (jsonObject.has("sessions")) ? json.getAsJsonObject().get("sessions").getAsJsonArray() : new JsonArray() ;
        for (JsonElement jsonElement : sessionsArray){
            PersonInstance personInstance = new PersonInstanceDeserializer().deserialize(jsonElement, PersonInstance.class,context);
            if (personInstance != null)
                personInstances.add(personInstance);
        }

        final Date personCreationDate = Globals.parseDate(jsonObject.get(PERSON_DATE_FIELD).getAsString());

        final String personImageUrl = Globals.getImageURLFrom(jsonObject);

        final String id = jsonObject.get("id").getAsString();

        final String name = jsonObject.get("name").getAsString();

        final String description = jsonObject.get("description").getAsString();

        final Integer duration = jsonObject.get("duration").getAsInt();

        return new Person(id, name, personImageUrl, personCreationDate,
                description, personInstances, new Random().nextDouble()*5, duration);
                */
        //return new Person(null,null,null,null,null,null,null,null,null,null);
        Person person = new Gson().fromJson(json,Person.class);
        return person;
    }
}
