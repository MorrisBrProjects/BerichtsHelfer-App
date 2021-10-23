package com.example.berichtshelfer.loader.objects;

import android.media.Image;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskService {

    public static List<String> getAllTasksAsJson() {
        File folder = new File("resources/OnlineBanking/tasks");
        File[] listOfFiles = folder.listFiles();
        List<String> tasks = new ArrayList<>();

        for (int i = 0; i < listOfFiles.length; i++) {
            if (listOfFiles[i].isFile()) {
                tasks.add(JsonConverter.readJSONFromFile(listOfFiles[i].getPath()));
                System.out.println("File " + listOfFiles[i].getName());
            } else if (listOfFiles[i].isDirectory()) {
                System.out.println("Directory " + listOfFiles[i].getName());
            }
        }

        return tasks;
    }

    public static String getTaskAsJson(String name) {
        for (String json : getAllTasksAsJson()) {
            Task task = (Task) JsonConverter.jsonStringToObject(json, Task.class);
            if(task.getTitle().equalsIgnoreCase(name)) {
                return json;
            }
        }
        return "Not found!";
    }

    public static Task getTaskAsObject(String name) {
        return (Task) JsonConverter.jsonStringToObject(getTaskAsJson(name), Task.class);
    }

   // public static Image getTaskImage(String name) {
    //   return getTaskAsObject(name).getImage().convertToImage();
    //}

    //public static InputStream getTaskInputStream(String name) throws IOException {
   //     ImageInputStream imageInputStream = ImageIO.createImageInputStream(getTaskImage(name));
    //    BufferedImage bufferedImage = ImageIO.read(imageInputStream);
    //    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
   //     ImageIO.write(bufferedImage, "png", byteArrayOutputStream);
   //     InputStream is = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
   //     return is;
    //}
}

