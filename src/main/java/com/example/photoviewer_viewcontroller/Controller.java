package com.example.photoviewer_viewcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class Controller {
    // HACK
    static Stage stage;

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
    public TextField selectedImageText;

    // 1 initialize method
    public void initialize() throws FileNotFoundException {
        images = new ArrayList<Image>();
        deletedImages = FXCollections.observableArrayList("was empty");
        selectedListItem = oldPhotos.getSelectionModel();
        currentImage = 0;
        //myImageView.setImage(images.get(currentImage));
        oldPhotos.setItems(deletedImages);
    }

    // Methods for all onActions
    public void prevButtonWasPressed() {
        if (images == null || images.size() == 0) {
            instructions.setText("Please load a photo.");
        } else {
            if (currentImage == 0) {
                currentImage = images.size()-1;
            } else {
                currentImage = currentImage-1;
            }
            myImageView.setImage(images.get(currentImage));
        }
    }

    public void nextButtonWasPressed() {
        if (images == null || images.size() == 0) {
            instructions.setText("Please load a photo.");
        } else {
            if (currentImage < images.size() - 1) {
                currentImage = currentImage + 1;
            } else {
                currentImage = 0;
            }
            myImageView.setImage(images.get(currentImage));
        }
    }

    public void undoDeleteFromOldList() {
        selectedImageText.setText(selectedListItem.getSelectedItem().toString());
        deletedImages.remove(selectedListItem.getSelectedIndex());
    }

    public void newButtonPressed() throws Exception {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage);
        FileInputStream inputNew = new FileInputStream(selectedFile);
        Image imageNew = new Image(inputNew);
        if (images.size() > 0) {
            currentImage = currentImage + 1;
        }
        images.add(imageNew);

        myImageView.setImage(images.get(currentImage));
    }

    public void deleteImage() {
        CheckBox newCheckBox = new CheckBox(String.valueOf(currentImage));
        deletedImages.add(newCheckBox);
        newCheckBox.setOnAction(actionEvent -> {
            deletedImages.remove(selectedListItem.getSelectedIndex());
        });
        images.remove(currentImage);
        if (currentImage == images.size()) {
            currentImage = images.size() - 1;
        }
        myImageView.setImage(images.get(currentImage));
    }

}