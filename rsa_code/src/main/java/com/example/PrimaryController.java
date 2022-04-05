package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class PrimaryController {

    public Text textQ;
    public Text textP;
    public Text resultE;
    public TextField inputN;

    @FXML
    public void initialize() {
//        List<Long> result = App.primeFactorization(n);
//        System.out.println(result);

        inputN.setText("33731");
        textQ.setText("Q goes here!");
        textP.setText("P goes here!");
        resultE.setText("Result E");
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
        while(q != 0){
            t = p;
            p = q;
            q = t%q;
        }
        System.out.print(p);
        // resultE.setText(Integer.toString(p));
        return p;
    }

    private boolean relativelyPrime() {
        return gcd(Integer.parseInt(textP.getText()),Integer.parseInt(textQ.getText())) == 1;
    }
}
