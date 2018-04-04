import database.HibernateUtil;
import helpClasses.PropertiesUtil;
import javafx.application.Application;
import javafx.stage.Stage;
import model.*;
import view.menu.LogInController;
import view.menu.LogInView;

public class Main extends Application {
    public static void main(String[] args) {
        Application.launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        setStage();
        openSession();
        Admin admin = new Admin("admin", "admin", "admin", "admin");
        new AdminDao().insertAdmin(admin);
        new FilePathBaseDao().insertFilePathBase(new FilePathBase("K:\\Projekty\\Stefja_Video_2017\\"));
    }

    private void setStage(){
        LogInView mv = new LogInView();
        new LogInController(mv);
    }
    private void openSession(){
        HibernateUtil.getSessionFactory().openSession();
    }
}
