package com.example.simba.DTO;

import java.io.File;

public class CategoryCreate {
    private String name;
    private File image = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setImage(File image) {
        this.image = image;
    }
}
