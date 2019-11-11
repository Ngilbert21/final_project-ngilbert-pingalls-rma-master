package edu.bsu.cs222;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class UIcontroller extends Application {

    public void start(Stage primaryStage) {
        APICaller caller = new APICaller();
        DropDownPopulator populator = new DropDownPopulator();

        primaryStage.setTitle("College Finder");

        VBox layout = new VBox(20.0D);
        Scene scene1 = new Scene(layout, 800.0D, 700.0D);


        // declare buttons
        Button findButton = new Button("Find Schools");
        findButton.setDefaultButton(true);

        Button closeButton = new Button("Close");
        closeButton.setOnAction(event -> primaryStage.close());

        Button websiteButton = new Button("Visit their website");


        // create drop-down menus
        final ComboBox RegionDropDown = new ComboBox();
        RegionDropDown.setPromptText("What region do you want?");

        final ComboBox minCostDropDown = new ComboBox();
        minCostDropDown.setPromptText("Minimum tuition amount");

        final ComboBox maxCostDropDown = new ComboBox();
        maxCostDropDown.setPromptText("Maximum tuition amount");

        ListView<String> listAllColleges = new ListView<>();

        // create output area
        TextArea collegeDataArea = new TextArea();
        collegeDataArea.setPromptText("College Info");
        collegeDataArea.setEditable(false);

        layout.getChildren().addAll(
                RegionDropDown,
                minCostDropDown,
                maxCostDropDown,
                findButton,
                listAllColleges,
                collegeDataArea,
                websiteButton,
                closeButton
        );


        // populate drop-down menus

        //noinspection unchecked
        RegionDropDown.getItems().addAll(populator.regionsList());

        //noinspection unchecked
        minCostDropDown.getItems().addAll(populator.costRange());
        //noinspection unchecked
        maxCostDropDown.getItems().addAll(populator.costRange());


        primaryStage.setScene(scene1);
        primaryStage.show();

        //noinspection Convert2Diamond
        listAllColleges.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (newValue != null) {
                    try {
                        String collegeData = caller.pullOneCollegeData(newValue);
                        collegeDataArea.setText(collegeData);
                        String[] collegeSite = collegeData.split("Website: ");
                        String collegeSitePlusHttp = "http://"+ collegeSite[1];

                        //noinspection Convert2Diamond
                        websiteButton.setOnAction(new EventHandler<ActionEvent>() {
                            @Override
                            public void handle(ActionEvent event) {
                                WebWindow.display(collegeSitePlusHttp);
                            }
                        });
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    //noinspection UnusedAssignment
                    newValue = "";
                }
            }
        });

        //noinspection Convert2Diamond
        findButton.setOnAction(new EventHandler<ActionEvent>() {
            int regionID;
            int minCost;
            int maxCost;

            @Override
            public void handle(ActionEvent event) {
                collegeDataArea.clear();

                for (int i = 0; i < populator.regionsList().size(); i++) {
                    if (RegionDropDown.getValue() == populator.regionsList().get(i)) {
                        regionID = i + 1;
                    }
                }

                try { minCost = Integer.valueOf(minCostDropDown.getValue().toString()); }
                catch (NullPointerException e) { minCost = 0; }

                try { maxCost = Integer.valueOf(maxCostDropDown.getValue().toString()); }
                catch (NullPointerException e) { maxCost = 0; }

                if (RegionDropDown.getValue() != null && minCostDropDown.getValue() != null && maxCostDropDown.getValue() != null) {
                    if (minCost >= maxCost) {
                        collegeDataArea.setText("Minimum cost is higher than maximum cost.\nPlease adjust your cost range.");
                    } else {
                        String collegeNames = caller.pullAllCollegeData(regionID, minCost, maxCost);
                        String[] CollegeNamesArrayList = collegeNames.split("\n");

                        if (CollegeNamesArrayList.length > 1) {
                            ObservableList<String> items = FXCollections.observableArrayList(CollegeNamesArrayList);
                            listAllColleges.setItems(items);
                        } else {
                            collegeDataArea.setText("No colleges match search.\nTry widening your parameters.");
                        }
                    }
                } else if (RegionDropDown.getValue() == null && minCostDropDown.getValue() == null && maxCostDropDown.getValue() == null) {
                    collegeDataArea.setText("No search criteria selected.");
                } else if (RegionDropDown.getValue() == null) {
                    collegeDataArea.setText("Please select a region.");
                } else if (minCostDropDown.getValue() == null) {
                    collegeDataArea.setText("Please select a minimum cost.");
                } else if (maxCostDropDown.getValue() == null) {
                    collegeDataArea.setText("Please select a maximum cost.");
                }
            }
        });
    }
}
