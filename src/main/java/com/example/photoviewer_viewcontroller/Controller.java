package com.example.photoviewer_viewcontroller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Controller {
    // HACK
    Stage stage;

    // Fields for your Controls
    public ImageView myImageView;
    public Button nextButton;
    public Button prevButton;
    public Button newButton;
    public Button deleteButton;
    public ListView oldPhotos;
    public Label statusText;

    // Fields for your data
    ArrayList<Model> images;
    ObservableList removedImages;
    SelectionModel selectedListItem;
    int currentImageIndex;

    // 1 initialize method
    public void initialize() throws Exception {
        images = new ArrayList<Model>();
        removedImages = FXCollections.observableArrayList();
        this.restoreData();
        selectedListItem = oldPhotos.getSelectionModel();
        currentImageIndex = -1;
        //myImageView.setImage(images.get(currentImage));
        oldPhotos.setItems(removedImages);
    }

    // Methods for all onActions
    public void prevButtonPressed() {
        if (images == null || images.size() == 0) {
            statusText.setText("Please load a photo.");
        } else if (currentImageIndex > -1) {
            if (currentImageIndex == 0) {
                currentImageIndex = images.size()-1;
            } else {
                currentImageIndex = currentImageIndex - 1;
            }
            myImageView.setImage(images.get(currentImageIndex).getImage());
            statusText.setText("Previous Image is #" + (currentImageIndex +1) + " of " + images.size());
        }
    }

    public void nextButtonPressed() {
        if (images == null || images.size() == 0) {
            statusText.setText("Please load a photo.");
        } else if (currentImageIndex > -1) {
            if (currentImageIndex < images.size() - 1) {
                currentImageIndex = currentImageIndex + 1;
            } else {
                currentImageIndex = 0;
            }
            myImageView.setImage(images.get(currentImageIndex).getImage());
            statusText.setText("Next Image is #" + (currentImageIndex +1) + " of " + images.size());
        }
    }

    public void newButtonPressed() throws Exception {
        FileChooser fileChooser = new FileChooser();
        File selectedFile = fileChooser.showOpenDialog(stage);
        FileInputStream inputNew = new FileInputStream(selectedFile);
        Image imageNew = new Image(inputNew);
        Model newImage = new Model(imageNew, selectedFile.getName());
        images.add(newImage);
        currentImageIndex = images.size() - 1;
        myImageView.setImage(newImage.getImage());
        statusText.setText("New Image is #" + (currentImageIndex +1) + " of " + images.size());
    }

    public void removeImage() throws Exception {
        if (currentImageIndex == -1) {
            statusText.setText("Please load a photo.");
        } else {
            statusText.setText("Removed Image #" + (currentImageIndex +1) + " of " + images.size());
            Model imageToDelete = images.get(currentImageIndex);
            CheckBox newCheckBox = new CheckBox(imageToDelete.getFileName());
            removedImages.add(newCheckBox);
            newCheckBox.setOnAction(actionEvent -> {
                removedImages.remove(actionEvent.getSource());
                images.add(imageToDelete);
                currentImageIndex = images.size() - 1;
                myImageView.setImage(imageToDelete.getImage());
                statusText.setText("Recovered Image is #" + (currentImageIndex +1) + " of " + images.size());
            });
            images.remove(currentImageIndex);
            if (images.size() > 0) {
                if (currentImageIndex == images.size()) {
                    currentImageIndex = images.size() - 1;
                }
                myImageView.setImage(images.get(currentImageIndex).getImage());
            } else {
                currentImageIndex = -1;
                myImageView.setImage(null);
            }
        }
    }
    public void deleteImage() throws Exception {
        statusText.setText(selectedListItem.getSelectedItem().toString());
        removedImages.remove(selectedListItem.getSelectedIndex());
        this.saveData();
    }

    void saveData() throws Exception {
        File fileForData = new File("MyData");
        FileOutputStream outputStream = new FileOutputStream(fileForData);
        ObjectOutputStream objOutputStream = new ObjectOutputStream(outputStream);
        int numOfSavedObjects = removedImages.size();
        objOutputStream.writeObject(numOfSavedObjects);
        for (Object imageName : removedImages) {
            CheckBox imageNameCB = (CheckBox)imageName;
            objOutputStream.writeObject(imageNameCB.getText());
        }
        objOutputStream.flush();
    }

    void restoreData() throws Exception {
        File fileForData = new File("MyData");
        FileInputStream inputStream = new FileInputStream(fileForData);
        ObjectInputStream objInputStream = new ObjectInputStream(inputStream);
        int numOfSavedObjects = objInputStream.readInt();
        for (int i = 0; i < numOfSavedObjects; i = i + 1) {
            String listText = (String) objInputStream.readObject();
            removedImages.add(listText);
        }
        inputStream.close();
    }
}