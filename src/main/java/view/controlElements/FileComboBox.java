package view.controlElements;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;
import model.FilePathBaseDao;

import java.io.File;

public class FileComboBox extends ComboBox {

    private File emptyFile;

    public FileComboBox() {
        String filePathBase = new FilePathBaseDao().getFilePathBase().toString();
        File folder = new File(filePathBase);
        File[] fileArray = folder.listFiles();

        ObservableList<File> filteredList = FXCollections.observableArrayList();

        for (int i = 0; i < fileArray.length; i++) {
            if (fileArray[i].isFile())
                filteredList.add(new File(fileArray[i].getName()));

        }
        emptyFile =  new File("Bez obrázku | videa");

        filteredList.add(0, emptyFile);
        setCellFactory(new Callback<ListView<File>, ListCell<File>>() {
            @Override
            public ListCell<File> call(ListView<File> arg0) {
                return new ListCell<File>() {
                    @Override
                    protected void updateItem(File item, boolean empty) {
                        super.updateItem(item, empty);

                        if (item == null || empty) {
                            setGraphic(null);
                        } else {
                            String fileText = item.getPath();
                            setText(fileText);
                        }
                    }
                };
            }
        });

        setItems(filteredList);
        setValue(emptyFile);
    }

    public File getEmptyFile() {
        return emptyFile;
    }
}
