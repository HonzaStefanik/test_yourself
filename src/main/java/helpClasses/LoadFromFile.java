package helpClasses;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.FileChooser;
import model.*;
import org.imgscalr.Scalr;
import view.adminAccess.root.AdminAccessRootView;
import view.adminAccess.studentOverview.StudentOverviewView;
import view.adminAccess.teacherOverview.TeacherOverviewView;
import view.teacherAccess.quizOverview.taskOverview.AddTaskView;
import view.teacherAccess.root.TeacherAccessRootView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class LoadFromFile {

    public static void loadStudents(AdminAccessRootView rootView, StudentOverviewView view){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Nahrát studenty ze souboru");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV +  Files", "*.csv", "*.txt"));
        File file = fileChooser.showOpenDialog(rootView.getStage());

        if (file != null) {

            String line = "";
            String cvsSplitBy = ";";
            String message = "";
            boolean error = false;
            Student studentObject;
            Classroom classroomObject;

            try {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file), "UTF8"));

                while ((line = br.readLine()) != null) {

                    String[] student = line.split(cvsSplitBy);

                    if ((CheckString.isNotBlank(student[0]) || CheckString.isNotBlank(student[1]) || CheckString.isNotBlank(student[2]) || CheckString.isNotBlank(student[3]))) {
                        String nickname = createNickname(student[0], student[1]);
                        if (!nickname.equals(null)) {
                            if(checkNick(nickname)){
                                classroomObject = checkClassroom(student[2], student[3], view);
                                studentObject = new Student(student[1], student[0], nickname, "1234", classroomObject);
                                System.out.println(studentObject.toString() + classroomObject);
                                new StudentDao().insertStudent(studentObject);
                                List<Student> studentList = view.getStudentTableView().getStudentList();
                                studentList.add(studentObject);
                                view.getStudentTableView().setItems(FXCollections.observableArrayList(studentList));
                            }
                            else{
                                message += student[0] + " '" + student[1] + "\n";
                                error = true;
                            }
                        } else {
                            message += student[0] + " '" + student[1] + "' " + student[3] + "\n";
                            error = true;
                        }

                    } else {
                        message += student[0] + " '" + student[1] + "' " + student[3] + "\n";
                        error = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (error) {
                createAlertBox(message);
            }
        }
    }

    public static void loadTeachers(AdminAccessRootView rootView, TeacherOverviewView view){
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Nahrát učitele ze souboru");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("CSV +  Files", "*.csv", "*.txt"));
        File file = fileChooser.showOpenDialog(rootView.getStage());

        if (file != null) {
            String line = "";
            String cvsSplitBy = ";";
            String message = "";
            boolean error = false;
            Teacher teacherObject;

            try {
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(
                                new FileInputStream(file), "UTF8"));

                while ((line = br.readLine()) != null) {

                    String[] teacher = line.split(cvsSplitBy);

                    System.out.println(teacher[0]);

                    if (!(CheckString.isBlank(teacher[0]) || CheckString.isBlank(teacher[1]))) {
                        String username = removeDiacritics(teacher[0].toLowerCase());
                        if (checkNick(username)) {
                            teacherObject = new Teacher(teacher[1], teacher[0], username, "1234");
                            new TeacherDao().insertTeacher(teacherObject);
                            view.getTeacherTableView().getItems().add(teacherObject);
                        } else {
                            message += teacher[0] + " '" + teacher[1] + "' " + teacher[3] + "\n";
                            error = true;
                        }

                    } else {
                        message += teacher[0] + " '" + teacher[1] + "' " + teacher[3] + "\n";
                        error = true;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (error) {
                createAlertBox(message);
            }
        }
    }

    public static void uploadFile(TeacherAccessRootView rootView, AddTaskView view){
        String filePathBase = new FilePathBaseDao().getFilePathBase().toString();

        File sourceFile;
        File destinationFile = new File(filePathBase);

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Nahrát obrázek / video");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Video / picture files", "*.mp4", "*.wmv", "*.jpg", "*.jpeg", "*.png", "*.gif"));


        sourceFile = fileChooser.showOpenDialog(rootView.getStage());

        if (sourceFile != null) {
            File checkFile = new File(destinationFile + "\\" + sourceFile.getName());
            if (!(checkFile.exists())) {
                try {
                    String mimeType = Files.probeContentType(sourceFile.toPath());
                    String[] type = mimeType.split("/");

                    if(type[0].equals("image")){
                        BufferedImage img = ImageIO.read(sourceFile);
                        BufferedImage scaledImage = Scalr.resize(img, 300);
                        ImageIO.write(scaledImage, type[1], new File(destinationFile + "\\" + sourceFile.getName()));
                    }
                    else
                        Files.copy(Paths.get(sourceFile.getPath()), Paths.get(destinationFile + "\\" + sourceFile.getName()));

                    view.getAllFilesComboBox().getItems().add(checkFile);
                    view.getAllFilesComboBox().setValue(checkFile);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else{
                view.getLbWarning().setText("Tento soubor již existuje");
                view.getLbWarning().setVisible(true);
            }
        }
    }

    private static Classroom checkClassroom(String classroomName, String classroomYear, StudentOverviewView view){
        List<Classroom> classroomList = new ClassroomDao().getAllClassrooms();

        for(Classroom c : classroomList){
            if(c.getClassroomName().equals(classroomName) && c.getClassroomYear().equals(classroomYear))
                return c;
        }

        Classroom classroom = new Classroom(classroomName, classroomYear);
        new ClassroomDao().insertClassroom(classroom);
        updateFilterBox(classroom, view);
        view.getStudentTableView().getClassroomChoiceBoxCell().addClassroom(classroom);
        return classroom;
    }

    private static boolean checkNick(String nick){
        Student student = new StudentDao().getStudentByNick(nick);
        Teacher teacher = new TeacherDao().getTeacherByNick(nick);
        Admin admin = new AdminDao().getAdminByNick(nick);
        if (student == null && teacher == null && admin == null)
            return true;
        return false;
    }

    private static void createAlertBox(String message){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText("Nepodařilo se nahrát následující uživatele");
        alert.setContentText(message);
        alert.showAndWait().ifPresent(rs -> {
            if (rs == ButtonType.OK) {
                System.out.println("Pressed OK.");
            }
        });
    }

    private static void updateFilterBox(Classroom newClassroom,  StudentOverviewView view){
        ObservableList<Classroom> listChoices = view.getClassroomComboBox().getItems();
        listChoices.add(newClassroom);
        view.getClassroomComboBox().setItems(listChoices);
        view.getClassroomComboBox().setValue(view.getAllStudentsChoice());
    }

    private static String removeDiacritics(String line){
        String replacement = line.toLowerCase();
        replacement = replacement.replaceAll("ě", "e");
        replacement = replacement.replaceAll("é", "e");
        replacement = replacement.replaceAll("š", "s");
        replacement = replacement.replaceAll("č", "c");
        replacement = replacement.replaceAll("ř", "r");
        replacement = replacement.replaceAll("ž", "z");
        replacement = replacement.replaceAll("ý", "y");
        replacement = replacement.replaceAll("á", "a");
        replacement = replacement.replaceAll("í", "i");
        replacement = replacement.replaceAll("ů", "u");
        replacement = replacement.replaceAll("ú", "u");
        replacement = replacement.replaceAll("ď", "d");
        replacement = replacement.replaceAll("ť", "t");
        replacement = replacement.replaceAll(" ", "");
        return replacement;
    }

    private static String createNickname(String surname, String name){
        String replacedName = removeDiacritics(name);
        String replacedSurname = removeDiacritics(surname);
        if (replacedName.length() < 2 || replacedSurname.length() < 4)
            return null;
        else
            return replacedSurname.substring(0,3) + replacedName.substring(0, 2);

    }
}
