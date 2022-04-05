package com.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class PrimaryController {

    public Text textQ;
    public Text textP;

    @FXML
    public void initialize() {
        textQ.setText("Q goes here!");
        textP.setText("P goes here!");
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

}
