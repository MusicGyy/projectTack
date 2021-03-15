package taskTracking.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import taskTracking.model.WorksCategory.CategoryWork;
import taskTracking.services.CategoryFileDataSource;
import taskTracking.services.DataList;
import taskTracking.services.DataSource;
import taskTracking.services.StringConfiguration;

import java.io.IOException;
import java.util.ArrayList;

public class CreateCategoryController {

    @FXML
    TextField createCategoryText;
    @FXML
    Button createButton;
    @FXML
    Label statusCategory;
    @FXML
    TableView<CategoryWork> showTableC;
    @FXML
    TextArea categoryTA;

    private CategoryWork selectedCategoryWork;
    private DataList dataList;
    private DataSource workDataSource;
    private ObservableList<CategoryWork> categoryWorkObservableList;


    @FXML
    public void initialize() {
        Platform.runLater((Runnable)new Runnable() {
            @Override
            public void run() {
                workDataSource = new CategoryFileDataSource("data","category.csv");
                dataList = workDataSource.getData();
                showCategoryAll();

                showTableC.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        showSelectedCategory(newValue);
                    }
                });
            }
        });
    }

    private void showCategoryAll() {
        showTableC.getColumns().clear();
        categoryWorkObservableList = FXCollections.observableArrayList(dataList.getCategoryArrayList());
        showTableC.setItems(categoryWorkObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Category Name", "field:nameC"));
        configs.add(new StringConfiguration("title:Total number of tasks", "field:all"));
        configs.add(new StringConfiguration("title:Total number of General tasks", "field:countGeneral"));
        configs.add(new StringConfiguration("title:Total number of Weekly tasks", "field:countWeekly"));
        configs.add(new StringConfiguration("title:Total number of Forward tasks", "field:countForward"));
        configs.add(new StringConfiguration("title:Total number of Project tasks", "field:countProject"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            showTableC.getColumns().add(col);
            if (conf.get("title").equals("Priority")) {
                showTableC.getSortOrder().addAll(col);
                col.setSortType(TableColumn.SortType.DESCENDING);
            }
        }
    }

    private void showSelectedCategory(CategoryWork categoryWork){
        categoryTA.clear();
        selectedCategoryWork = categoryWork;
        String[] data = selectedCategoryWork.getName().split("//");
        for (int i = 0 ; i< data.length ; i++)
            categoryTA.appendText(data[i]+"\n");
    }



    @FXML public void handleCreateButtonAction(ActionEvent event) throws IOException {
        if (createCategoryText.getText().isEmpty())
        {
            statusCategory.setText("Please enter the name of the desired category.!!");

        }
        else {
            if (dataList.checkCategory(createCategoryText.getText())) {
                selectedCategoryWork = new CategoryWork(createCategoryText.getText(), 0, 0, 0, 0, 0,null);


//            System.out.println(categoryWork.toString());
                dataList.addCategory(selectedCategoryWork);
                workDataSource.setData(dataList);
                createCategoryText.clear();
                statusCategory.setText("....");
            }
            else {
                statusCategory.setText("Repeat category name, please enter it again.!!");
            }
        }
    }


    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        }
        catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
