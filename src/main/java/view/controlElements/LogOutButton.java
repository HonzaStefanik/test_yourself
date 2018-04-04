package view.controlElements;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import helpClasses.LoggedInUtil;
import view.menu.LogInController;
import view.menu.LogInView;

public class LogOutButton extends VBox {

    private Label lbLogOut, lbUserInfo;


    public LogOutButton(Stage window) {
        setAlignment(Pos.TOP_RIGHT);
        setPadding(new Insets(10));
        lbLogOut = new Label();
        lbLogOut.textProperty().set("Odhlásit se");
        lbLogOut.setTextFill(Color.web("00a3cc"));
        lbLogOut.setOnMouseClicked(e-> logOut(window));
        lbLogOut.setOnMouseEntered(e -> window.getScene().setCursor(Cursor.HAND));
        lbLogOut.setOnMouseExited(e -> window.getScene().setCursor(Cursor.DEFAULT));

        String user = LoggedInUtil.getUserToString();

        lbUserInfo = new Label();
        lbUserInfo.textProperty().set(user);

        getChildren().addAll(lbUserInfo, lbLogOut);
    }

    private void logOut(Stage window){
        LoggedInUtil.deleteLoggedInStatus();
        window.close();
        LogInView view = new LogInView();
        new LogInController(view);
    }
}
