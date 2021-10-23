package com.example.berichtshelfer.loader.objects;

import java.util.HashMap;

public class Bericht {


    private String title;
    private HashMap<String, DayBericht> days = new HashMap<>();


    public Bericht(String title) {
        this.title = title;
    }

    public Bericht() {

    }


    public DayBericht getDayByID(String name) {
        return days.get(name);
    }


    public void addDay(String dayId, DayBericht dayBericht) {
        getDays().put(dayId, dayBericht);
    }

    public void removeDay(CategoryWithContent category) {
        getDays().remove(category);
    }


    public HashMap<String, DayBericht> getDays() {
        return days;
    }

    public void setDays(HashMap<String, DayBericht> days) {
        this.days = days;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public void save() {
        //JsonConverter.objectToJsonFile("resources/OnlineBanking/berichte/" + title + ".json", this);
    }

}
