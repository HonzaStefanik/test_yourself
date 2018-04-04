package view.controlElements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

public class NewSolutionField extends HBox {

    private Label label;

    private TextField textField;

    private RadioButton radioButtonCorrect, radioButtonWrong;

    public NewSolutionField(String labelText, int width){
        setSpacing(10);
        setAlignment(Pos.CENTER);

        label = new Label(labelText);
        label.setPrefSize(width,20);

        textField = new TextField();
        textField.setPrefSize(200, 25);
        textField.setMaxSize(200, 25);

        ToggleGroup group = new ToggleGroup();

        radioButtonCorrect = new RadioButton();
        radioButtonCorrect.setToggleGroup(group);
        radioButtonCorrect.setSelected(true);

        radioButtonWrong = new RadioButton();
        radioButtonWrong.setToggleGroup(group);

        ImageView imgViewCorrect = new ImageView(new Image("correct.png"));
        ImageView imgViewWrong = new ImageView(new Image("wrong.png"));

        HBox hbCorrect = new HBox(radioButtonCorrect, imgViewCorrect);
        HBox hbWrong = new HBox(radioButtonWrong, imgViewWrong);

        getChildren().addAll(label,textField, hbCorrect, hbWrong);
    }


    public Label getLabel() {
        return label;
    }

    public TextField getTextField() {
        return textField;
    }

    public RadioButton getRadioButtonCorrect() {
        return radioButtonCorrect;
    }

    public RadioButton getRadioButtonWrong() {
        return radioButtonWrong;
    }
}

