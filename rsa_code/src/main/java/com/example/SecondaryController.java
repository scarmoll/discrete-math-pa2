package com.example;

import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class SecondaryController {

    public TextField inputN;
    public TextField inputE;
    public Button calculateBtn;
    public Text resultD;
    public TextField inputEncryptedMessage;
    public Text decryptedMessage;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    private long phiFromN(long n){
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

        long phi = (result.get(0)-1) * (result.get(1)-1);
        return phi;
    }

    @FXML
    private void calculateD() {
        long n = Long.valueOf(inputN.getText());
        long phi = phiFromN(n);

        long e = Integer.parseInt(inputE.getText());
        double d = 0;
        long result = 0;

        for (int i = 0; i < phi; i++) {
            d = (1+i*phi)/(double)e;

            if (d % 1 == 0) {
                result = (int)d;
                break;
            }
        }

        resultD.setText(Long.toString(result));
    }


    @FXML
    private void decryptAndDecodeMessage() {
        //  Decryption
        BigInteger bN = BigInteger.valueOf(Long.valueOf(inputN.getText()));
        int d = Integer.parseInt(resultD.getText());

        String input = inputEncryptedMessage.getText();
        List<String> encryptedMessageList = Arrays.asList(input.split("\\s*,\\s*"));
        List<BigInteger> bEncryptedMessageList = new ArrayList<>();
        List<BigInteger> decryptedMessageList = new ArrayList<>();

        for (String stringValue : encryptedMessageList){
            bEncryptedMessageList.add(BigInteger.valueOf(Long.parseLong(stringValue)));
        }

        for (BigInteger encryptedCharacter : bEncryptedMessageList) {
            decryptedMessageList.add(encryptedCharacter.pow(d).mod(bN));
        }

        //  Decoding
        StringBuilder decodedMessage = new StringBuilder();

        for (BigInteger asciiValue : decryptedMessageList){
            decodedMessage.append(Character.toString(asciiValue.intValue()));
        }
        decryptedMessage.setText(decodedMessage.toString());
    }
}
