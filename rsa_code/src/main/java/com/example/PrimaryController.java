package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PrimaryController {

    public Text textQ;
    public Text textP;
    public TextField inputN;
    public Button calculatePQ;
    public Button calculateE;
    public Text textE;
    public Button encryptButton;
    public TextField inputMessage;
    public Button primaryButton;
    public Text textEncryptedMessage;
    public Text textTime;

    @FXML
    public void initialize() {
        inputN.setText("33731");
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    public List<Long> primeFactorization() {
        long n = Long.parseLong(inputN.getText());

        List<Long> result = new ArrayList<Long>();
        for (long i = 2; i <= n / i; i++) {
            while (n % i == 0) {
                result.add(i);
                n /= i;
            }
        }

        if (n > 1) {
            result.add(n);
        }
        textP.setText(String.valueOf(result.get(0)));
        textQ.setText(String.valueOf(result.get(1)));
        return result;
    }

    private int gcd(int p, int q) {
        int t;
        while (q != 0 && q > 11) {
            t = p;
            p = q;
            q = t % q;
        }
        textE.setText(Integer.toString(p));
        return p;
    }

    @FXML
    private boolean relativelyPrime() {
        return gcd(Integer.parseInt(textP.getText()), Integer.parseInt(textQ.getText())) == 1;
    }
}
