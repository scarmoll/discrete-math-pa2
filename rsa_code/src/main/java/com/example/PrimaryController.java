package com.example;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public Text textTime;
    public Button encodeButton;
    public TextField encryptedMessage;
    public Text encodedMessage;

    List<Long> asciiMessageList = new ArrayList<>();
    List<BigInteger> encryptedMessageList = new ArrayList<>();

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
        long start_time = System.currentTimeMillis();

        Long n = Long.parseLong(inputN.getText());

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
        long end_time = System.currentTimeMillis();
        String difference = (end_time - start_time) + " ms";

        textTime.setText(difference);

        textP.setText(String.valueOf(result.get(0)));
        textQ.setText(String.valueOf(result.get(1)));
        return result;
    }

    @FXML
    private void encryptMessage() {
        Long e = Long.valueOf(textE.getText());
        Long n = Long.parseLong(inputN.getText());
        encryptedMessageList.clear();

        for (Long character : asciiMessageList) {
            BigInteger bC = BigInteger.valueOf(character);
            BigInteger bN = BigInteger.valueOf(n);

            encryptedMessageList.add((bC.pow(Math.toIntExact(e))).mod(bN));
        }
        encryptedMessage.setText(encryptedMessageList.stream().map(Object::toString)
                .collect(Collectors.joining(", ")));
    }

    @FXML
    private void encodeMessage() {
        String message = String.valueOf(inputMessage.getText());
        asciiMessageList.clear();
        for (int i = 0; i < message.length(); i++) {
            asciiMessageList.add(Long.valueOf(message.charAt(i)));
        }

        encodedMessage.setText(asciiMessageList.stream().map(Object::toString)
                .collect(Collectors.joining(", ")));
    }

    private long phi() {
        long p = Integer.valueOf(textP.getText());
        long q = Integer.valueOf(textQ.getText());

        long n = (p-1)*(q-1);

        return n;
    }

    private long gcd(long e, long n)
    {
        if (e == 0)
            return n;
        else
            return gcd(n % e, e);
    }

    @FXML
    private void relativelyPrime() {
        int startValue = 0;
        long e = 0;
        long n = phi();
        int max = 20;
        for (startValue = 2; startValue < n; startValue++) {
            // e is for public key exponent
            e = getRandomNumber(startValue, max);
            if (gcd(e, n) == 1) {
                break;
            }
        }

        textE.setText(Long.toString(e));
    }

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random()* (max-min)) + min);
    }
}
