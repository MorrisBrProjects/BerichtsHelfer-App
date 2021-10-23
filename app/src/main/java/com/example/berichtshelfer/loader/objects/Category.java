package com.example.berichtshelfer.loader.objects;

import android.media.Image;

public class Category {
    private String title;
    private Image image = null;

    public Category() {

    }

    public Category(String name) {
        this.title = name;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
