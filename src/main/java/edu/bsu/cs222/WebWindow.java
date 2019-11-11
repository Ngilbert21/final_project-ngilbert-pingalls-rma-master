package edu.bsu.cs222;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

class WebWindow {
    static void  display(String site){
        Stage window = new Stage();
        window.setTitle("Web Info");

        final WebView browser = new WebView();
        final WebEngine webEngine = browser.getEngine();

        VBox layout2 = new VBox();
        layout2.setPadding(new Insets(10));
        layout2.getChildren().addAll(browser);

        webEngine.load(site);

        Scene scene = new Scene(layout2, 900, 600);
        window.setScene(scene);
        window.show();
    }
}
