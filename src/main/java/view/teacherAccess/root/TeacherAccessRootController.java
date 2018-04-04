package view.teacherAccess.root;

import database.HibernateUtil;
import helpClasses.LoggedInUtil;
import javafx.stage.Stage;

public class TeacherAccessRootController {

    public TeacherAccessRootController(Stage stage) {
        stage.setOnCloseRequest(e -> windowClosed());
    }

    private void windowClosed(){
        LoggedInUtil.deleteLoggedInStatus();
        HibernateUtil.shutDown();
        System.exit(0);
    }
}
