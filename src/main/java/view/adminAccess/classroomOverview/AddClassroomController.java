package view.adminAccess.classroomOverview;

import helpClasses.CheckString;
import model.Classroom;
import model.ClassroomDao;
import view.adminAccess.root.AdminAccessRootView;

import java.util.List;

public class AddClassroomController {

    private AddClassroomView view;

    private AdminAccessRootView rootView;

    public AddClassroomController(AddClassroomView view, AdminAccessRootView rootView) {
        this.view = view;
        this.rootView = rootView;
        setActions();
    }

    private void setActions(){
        view.getBtnAddClassroom().setOnAction(e -> addClassroomAction());
        view.getBtnBack().setOnAction(e -> backAction());
        rootView.setOnMouseClicked(e -> view.getLbWarning().setVisible(false));
    }

    private void addClassroomAction(){
        String name = view.getNameField().getField().getText();
        String year = view.getYearField().getField().getText();
        String message;

        if(CheckString.isNotBlank(name) && CheckString.isNotBlank(year)){
            Classroom classroom = new Classroom(name, year);
            if(checkClassroom(classroom.toString())) {
                new ClassroomDao().insertClassroom(classroom);
                message = "Třída " + classroom.toString() + " byla vytvořena";

                view.getNameField().getField().clear();
                view.getYearField().getField().clear();

            }
            else
                message = "Třída " + classroom.toString() + " již existuje";
        }
        else
            message = "Vyplňte všechny údaje";

        view.getLbWarning().setText(message);
        view.getLbWarning().setVisible(true);

    }
    private void backAction(){
        rootView.setClassroomOverviewMiddle();
    }


    private boolean checkClassroom(String classroom){
        List<Classroom> classroomList = new ClassroomDao().getAllClassrooms();

        for(Classroom c : classroomList){
            String cString = c.toString();
            if(cString.equals(classroom))
                return false;
        }
        return true;
    }
}
