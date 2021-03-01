package taskTracking.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import taskTracking.model.WorksCategory.CategoryWork;
import taskTracking.model.WorksCategory.WeeklyWork;
import taskTracking.services.CategoryFileDataSource;
import taskTracking.services.DataList;
import taskTracking.services.DataSource;
import taskTracking.services.WeeklyWorkFileDataSource;

import java.io.IOException;

public class WeeklyWorkController {

    @FXML
    TextField WName;
    @FXML
    ChoiceBox<String> weeklyDate, priorityWCB,categoryWorkCB;
    @FXML
    Button submitW;
    @FXML
    Label statusWLabel;

    private WeeklyWork weeklyWork;
    private DataList dataList,categoryDataList;
    private DataSource workDataSource,categoryDataSource;

    @FXML
    public void initialize() {
        Platform.runLater((Runnable)new Runnable() {
            @Override
            public void run() {
                workDataSource = new WeeklyWorkFileDataSource("data", "weeklyWorks.csv");
                categoryDataSource = new CategoryFileDataSource("data","category.csv");

                categoryDataList = categoryDataSource.getData();
                dataList = workDataSource.getData();
                for (int i = 1; i <= 3; i++){
                    priorityWCB.getItems().add(String.valueOf(i));}
                weeklyDate.getItems().addAll("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday");

                categoryWorkCB.getItems().add("ไม่เลือก");
                for (CategoryWork categoryWork : categoryDataList.getCategoryArrayList()) {
                    categoryWorkCB.getItems().add(categoryWork.getName());
                }
            }
        });
    }

    @FXML public void handleSubmitAction(ActionEvent event) throws IOException {
        if (categoryWorkCB.getItems().equals("ไม่เลือก"))
            weeklyWork = new WeeklyWork(null,WName.getText(), weeklyDate.getValue(),"","","", priorityWCB.getValue(),"Not Started");
        else {
            weeklyWork = new WeeklyWork(categoryWorkCB.getValue(), WName.getText(), weeklyDate.getValue(), "", "", "", priorityWCB.getValue(), "Not Started");
            categoryDataList.addWorkToCategory(categoryWorkCB.getValue(), "WeeklyWork");

        }

        System.out.println(weeklyWork.toString());
        dataList.addWeeklyWork(weeklyWork);     //<----- Add อยู่นี่
        workDataSource.setData(dataList);
        categoryDataSource.setData(categoryDataList);
        statusWLabel.setText("");
    }

    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("workCategoryHome");
        }
        catch (IOException e) {
            System.err.println("ไปที่หน้า workCategoryHome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleUpdateWeeklyWorksControllerButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("updateWeeklyWorks");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า updateWeeklyWorks ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
