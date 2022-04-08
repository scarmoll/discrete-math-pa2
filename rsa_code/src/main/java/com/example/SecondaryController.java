package com.example;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SecondaryController {

    public TextField inputN;
    public TextField inputE;
    public Button calculateBtn;
    public Text resultD;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void calculateD() {
        int n = Integer.parseInt(inputN.getText());
        int e = Integer.parseInt(inputE.getText());
        double d = 0;
        int result = 0;

        for (int i = 0; i < n; i++) {
            d = (1+i*n)/(double)e;

            if (d % 1 == 0) {
                result = (int)d;
                break;
            }
        }

        resultD.setText(Integer.toString(result));
    }
}