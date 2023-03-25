package com.example.sdiicw;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class HelloController {
    @FXML
    TextField weight;
    @FXML
    TextField height;
    @FXML
    TextField nadem;
    @FXML
    Button ok;
    @FXML
    Label output;
    public void setOutput(){
        if (!(weight.getText() == "" || height.getText() == "" ) ){

            output.setText("hello nadeem");

        }
    }
}