package view.studentAccess.root;

import database.HibernateUtil;
import helpClasses.LoggedInUtil;
import javafx.stage.Stage;

public class StudentAccessRootController {

    public StudentAccessRootController(Stage stage) {
        stage.setOnCloseRequest(e -> windowClosed());
    }

    private void windowClosed(){
        LoggedInUtil.deleteLoggedInStatus();
        HibernateUtil.shutDown();
        System.exit(0);
    }
}
