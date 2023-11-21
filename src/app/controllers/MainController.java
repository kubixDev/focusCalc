package app.controllers;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class MainController {
    @FXML
    private TextField display;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private Button buttonMode;

    private double firstNumber = 0;
    private double secondNumber = 0;
    private String action;

    private boolean isLightMode = true;

    public void changeMode() {
        isLightMode = !isLightMode;

        if (isLightMode) {
            setLightMode();
        }

        else setDarkMode();
    }

    private void setLightMode() {
        mainPane.getStylesheets().remove("/resources/stylesheets/darkMode.css");
        mainPane.getStylesheets().add("/resources/stylesheets/lightMode.css");
        buttonMode.setText("\uD83C\uDF19");
    }

    private void setDarkMode() {
        mainPane.getStylesheets().remove("/resources/stylesheets/lightMode.css");
        mainPane.getStylesheets().add("/resources/stylesheets/darkMode.css");
        buttonMode.setText("\uD83D\uDD06");
    }

    private void checkForLetters() {
        if (display.getText().matches(".*[A-Za-z].*") || display.getText().contains(" ")) {
            display.setText("0");
        }
    }

    @FXML
    private void numericButtonClicked(ActionEvent event) {
        checkForLetters();
        Button clickedButton = (Button)event.getSource();
        String buttonText = clickedButton.getText();
        String currentText = display.getText();

        if (currentText.equals("0")) {
            display.setText(buttonText);
        }

        else display.setText(currentText + buttonText);
    }

    @FXML
    private void actionButtonClicked(ActionEvent event) {
        checkForLetters();
        Button clickedButton = (Button)event.getSource();
        String buttonText = clickedButton.getText();

        switch (buttonText) {
            case "+":
            case "-":
            case "*":
            case "/":
                action = buttonText;
                firstNumber = Double.parseDouble(display.getText());
                display.setText("0");
                break;

            case "=":
                secondNumber = Double.parseDouble(display.getText());
                performOperation();
                break;

            case ".":
                handleDotClick();
                break;

            case "C":
                display.setText("0");
                break;

            case "binary":
            case "octal":
            case "hex":
                handleConversion(buttonText);
                break;
        }
    }

    private void performOperation() {
        switch (action) {
            case "+":
                display.setText(String.valueOf(firstNumber + secondNumber));
                break;

            case "-":
                display.setText(String.valueOf(firstNumber - secondNumber));
                break;

            case "*":
                display.setText(String.valueOf(firstNumber * secondNumber));
                break;

            case "/":
                if (secondNumber == 0) {
                    display.setText("Division by 0");
                    break;
                }

                display.setText(String.valueOf(firstNumber / secondNumber));
                break;
        }
    }

    private void handleDotClick() {
        if (display.getText().endsWith(".") || display.getText().contains(".")) {
            display.setText(display.getText());
        }

        else display.setText(display.getText() + ".");
    }

    private void handleConversion(String conversionType) {
        if (display.getText().contains(".")) {
            display.setText("Double not supported");
        }

        else {
            switch (conversionType) {
                case "binary":
                    display.setText(Integer.toBinaryString(Integer.parseInt(display.getText())));
                    break;

                case "octal":
                    display.setText(Integer.toOctalString(Integer.parseInt(display.getText())));
                    break;

                case "hex":
                    display.setText(Integer.toHexString(Integer.parseInt(display.getText())));
                    break;
            }
        }
    }
}