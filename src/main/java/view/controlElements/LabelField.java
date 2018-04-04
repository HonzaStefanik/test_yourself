package view.controlElements;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.HBox;

public class LabelField extends HBox{

    private Label label;

    private TextInputControl textField, passwordField;

    private int type;

    public LabelField(String labelText, int width, int type){
        this.type = type;

        setSpacing(10);
        setAlignment(Pos.CENTER);

        label = new Label(labelText);
        label.setPrefSize(width,20);


        getChildren().add(label);

        if(type == 1)
            createTextField();
        else
            createPasswordField();
    }


    private void createTextField(){
        textField = new TextField();
        textField.setPrefSize(200, 25);
        textField.setMaxSize(200, 25);
        getChildren().add(textField);
    }

    private void createPasswordField(){
        passwordField = new PasswordField();
        passwordField.setPrefSize(200, 25);
        passwordField.setMaxSize(200, 25);
        getChildren().add(passwordField);
    }

    public Label getLabel() {
        return label;
    }

    public TextInputControl getField(){
        if(type == 1)
            return textField;
        else
            return passwordField;
    }
}
