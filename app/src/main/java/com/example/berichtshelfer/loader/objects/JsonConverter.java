package com.example.berichtshelfer.loader.objects;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

import java.io.*;

public class JsonConverter {

    public static Object jsonStringToObject(String jsonString, Class object) {
        return new Gson().fromJson(jsonString, object); //object.getClass()
    }

    public static Object jsonFileToObject(String path, Class object) {
        Gson gson = new Gson();
        JsonReader reader = null;
        FileReader fileReader;
        try {
            fileReader = new FileReader(path);
            reader = new JsonReader(fileReader);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  gson.fromJson(reader, object);

    }


    public static String readJSONFromFile(String filePath)  {
        Gson gson = new Gson();
        JsonReader reader = null;
        FileReader fileReader;
        Object json = "";
        try {
            fileReader = new FileReader(filePath);
            reader = new JsonReader(fileReader);
            json = gson.fromJson(fileReader, Object.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  json.toString();
    }


    public static void objectToJsonFile(String path, Object object) {
        Gson gson = new Gson();
        String json = objectToJsonString(object);
        FileWriter writer;

        try {
            writer = new FileWriter(path);
            writer.write(json);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String objectToJsonString(Object object) {
        return new Gson().toJson(object);
    }

}
