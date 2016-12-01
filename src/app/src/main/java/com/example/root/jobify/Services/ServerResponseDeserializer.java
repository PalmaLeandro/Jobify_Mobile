package com.example.root.jobify.Services;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;

/**
 * Created by root on 18/09/16.
 *
 * This class does the parse of the paginated response.
 * It explicity need the class of the object that are arranged as payload to properly parse them.
 * Unfortantly gson cannot automatly parse them from the generic type because of Java's type erasure.
 * More at http://stackoverflow.com/questions/4364392/deserializing-generics-with-gson.
 */
public class ServerResponseDeserializer<T> implements JsonDeserializer<ServerResponse<T>> {
    Class<T> dataType;
    JsonDeserializer<T> classDeserializer;

    public ServerResponseDeserializer(Class<T> tClass) {
        dataType = tClass;
    }

    public ServerResponseDeserializer(Class<T> tClass,JsonDeserializer<T> classDeserializer) {
        dataType=tClass;
        this.classDeserializer=classDeserializer;
    }

    @Override
    public ServerResponse<T> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        ServerResponse<T> paginatedResponse = new GsonBuilder().create().fromJson(json,typeOfT);
        /*
        ArrayList<T> arrayList = new ArrayList<>();
        for(JsonElement jsonElement:json.getAsJsonObject().get("data").getAsJsonArray()){
            T responseElement=null;
            if(classDeserializer!=null){
                try {
                    responseElement= classDeserializer.deserialize(jsonElement,dataType,context);
                }catch (Throwable e){
                    responseElement =new Gson().fromJson(jsonElement,dataType);
                }
            } else{
                responseElement =new Gson().fromJson(jsonElement,dataType);
            }
            if (responseElement!=null){
                arrayList.add( responseElement);
            }
        }
        */
        paginatedResponse.data = new Gson().fromJson(json.getAsJsonObject().get("data"),dataType);
        return paginatedResponse;
    }
}
