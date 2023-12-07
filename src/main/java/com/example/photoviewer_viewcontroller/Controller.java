package com.example.photoviewer_viewcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionModel;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Controller {
    // Fields for your data
    ArrayList<Image> images;
    ObservableList deletedImages;
    SelectionModel selectedListItem;
    int currentImage;

    // Fields for your Controls
    public ImageView myImageView;
    public Button nextButton;
    public Button prevButton;
    public Button newButton;
    public Button deleteButton;
    public Label instructions;
    public ListView oldPhotos;

    // 1 initialize method
    public void initialize() throws FileNotFoundException {
        images = new ArrayList<Image>();
        deletedImages = FXCollections.observableArrayList("was empty");
        selectedListItem = oldPhotos.getSelectionModel();
        currentImage = 0;

        FileInputStream input1 = new FileInputStream("src/Condorito.jpg");
        Image image1 = new Image(input1);
        images.add(image1);
        FileInputStream input2 = new FileInputStream("src/Cone.jpg");
        Image image2 = new Image(input2);
        images.add(image2);
        FileInputStream input3 = new FileInputStream("src/Huevoduro.jpg");
        Image image3 = new Image(input3);
        images.add(image3);

        myImageView.setImage(images.get(currentImage));

        deletedImages.add("NOT empty");
        deletedImages.add("NOT empty 2");
        deletedImages.add("NOT empty 3");
        deletedImages.add("NOT empty 4");
        deletedImages.add("NOT empty 5");
        deletedImages.add("NOT empty 6");

        oldPhotos.setItems(deletedImages);

        deletedImages.add("THE EXTRA ONE");

    }
    // Methods for all onActions
    public void prevButtonWasPressed() {
        instructions.setText("Please load a photo.");
    }

    public void nextButtonWasPressed() {
        if (currentImage < images.size() - 1) {
            currentImage = currentImage + 1;
        } else {
            currentImage = 0;
        }
        myImageView.setImage(images.get(currentImage));

        instructions.setText(String.valueOf(selectedListItem.getSelectedIndex()));
    }

}