package com.example.photoviewer_viewcontroller;

import javafx.scene.image.Image;

import java.io.Serializable;

public class Model implements Serializable {
    // contain Image data
    transient Image image;
    String fileName;

    public Model(Image image, String fileName) {
        this.image = image;
        this.fileName = fileName;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
