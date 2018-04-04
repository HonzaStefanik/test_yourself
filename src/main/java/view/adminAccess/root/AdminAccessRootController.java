package view.adminAccess.root;

import database.HibernateUtil;
import helpClasses.LoggedInUtil;
import javafx.stage.Stage;

public class AdminAccessRootController {

    public AdminAccessRootController(Stage stage) {
        stage.setOnCloseRequest(e -> windowClosedAction());
    }

    private void windowClosedAction(){
        LoggedInUtil.deleteLoggedInStatus();
        HibernateUtil.shutDown();
        System.exit(0);
    }


}
