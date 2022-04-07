package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    public Button encodeButton;
    public Text encryptedMessage;
    public Text encodedMessage;

    List<Long> asciiMessageList = new ArrayList<>();
    List<Long> encryptedMessageList = new ArrayList<>();
    Long n = null;

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
        n = Long.parseLong(inputN.getText());

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

    @FXML
    private void encryptMessage(){
        System.out.println(asciiMessageList);

        Long e = Long.valueOf(textE.getText());

        for (Long character: asciiMessageList){
            System.out.println((Math.pow(character, e)) % n);
        }
    }

    @FXML
    private void encodeMessage() {
        String message = String.valueOf(inputMessage.getText());
        for (int i = 0; i < message.length(); i++) {
            asciiMessageList.add(Long.valueOf(message.charAt(i)));
        }

        encodedMessage.setText(asciiMessageList.stream().map(Object::toString)
                .collect(Collectors.joining(", ")));
    }

    private int phi() {
        int p = Integer.valueOf(textP.getText());
        int q = Integer.valueOf(textQ.getText());

        int n = (p-1)*(q-1);

        return n;
    }

    private int gcd(int e, int n)
    {
        if (e == 0)
            return n;
        else
            return gcd(n % e, e);
    }

    @FXML
    private void relativelyPrime() {
        int startValue = 0;
        int e = 0;
        int n = phi();
        for (startValue = 2; startValue < n; startValue++) {
            // e is for public key exponent
            e = getRandomNumber(startValue, n);
            if (gcd(e, n) == 1) {
                break;
            }
        }

        textE.setText(String.valueOf(e));
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random()* (max-min)) + min);
    }
}
