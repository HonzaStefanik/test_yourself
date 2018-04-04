package view.adminAccess.changeFilePath;

import javafx.stage.DirectoryChooser;
import model.FilePathBaseDao;
import view.adminAccess.root.AdminAccessRootView;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class ChangeDirController {

    private ChangeDirView view;

    private AdminAccessRootView rootView;

    public ChangeDirController(ChangeDirView view, AdminAccessRootView rootView) {
        this.view = view;
        this.rootView = rootView;
        setActions();
    }

    private void setActions(){
        view.getBtnSelectDir().setOnAction(e -> selectDirAction());
        view.getBtnChangeDir().setOnAction(e -> changeDirAction());
        view.getBtnBack().setOnAction(e -> backAction());
    }

    private void selectDirAction(){
        DirectoryChooser directoryChooser = new DirectoryChooser();
        File file = directoryChooser.showDialog(rootView.getStage());
        view.getNewDirTextField().setText(file.toString());
    }

    private void changeDirAction(){
        File newFile = new File(view.getNewDirTextField().getText());
        File oldFile = new File(new FilePathBaseDao().getFilePathBase().toString());

        if(!newFile.equals(oldFile)) {
            if (view.getCbMoveFiles().isSelected() && oldFile.exists()) {
                copyFiles(newFile.getPath());
            }
            new FilePathBaseDao().updateFilePathBase(newFile.getPath());
            view.getLbCurrentDir().setText(newFile.getPath());
        }

    }

    private void backAction(){
        rootView.setMenuMiddle();
    }

    private void copyFiles(String newPath){
        String filePathBase = new FilePathBaseDao().getFilePathBase().toString();
        if(filePathBase != null){
            File folder = new File(filePathBase);
            File[] fileArray = folder.listFiles();

            for (File f: fileArray) {
                try {
                    File checkFile = new File(newPath + "\\" + f.getName());
                    if(!checkFile.exists())
                        Files.copy(Paths.get(filePathBase +  "\\" + f.getName()), Paths.get(newPath + "\\" + f.getName()));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
