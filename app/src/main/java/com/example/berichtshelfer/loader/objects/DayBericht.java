package com.example.berichtshelfer.loader.objects;

import java.util.HashMap;

public class DayBericht {

    private String title;
    private HashMap<String, String> tasks = new HashMap<>();

    public DayBericht(String title) {
        this.title = title;
    }

    public DayBericht() {}


    public void addTask(String taskName, String content) {
        getTasks().put(taskName, content);
    }

    public void addTask(Task task, String content) {
        getTasks().put(task.getTitle(), content);
    }

    public String getTaskContent(String name) {
        return tasks.get(name);
    }

    public void removeTask(String taskName) {
        getTasks().remove(taskName);
    }





    public void setTasks(HashMap<String, String> tasks) {
        this.tasks = tasks;
    }

    public HashMap<String, String> getTasks() {
        return tasks;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
