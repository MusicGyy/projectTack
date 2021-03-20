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
    ChoiceBox<Integer> year,month,day;
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
                for (int i = 2021; i <= 2031; i++){
                    year.getItems().add(i);}
                for (int i = 1; i <= 12; i++){
                    month.getItems().add(i);}
                for (int i = 1; i <= 31; i++){
                    day.getItems().add(i);}
                weeklyDate.getItems().addAll("Mon","Tues","Wed","Thurs","Fri","Sat","Sun");

                categoryWorkCB.getItems().add("Not choose");
                for (CategoryWork categoryWork : categoryDataList.getCategoryArrayList()) {
                    categoryWorkCB.getItems().add(categoryWork.getNameC());
                }
            }
        });
    }

    @FXML public void handleSubmitAction(ActionEvent event) throws IOException {
        if (categoryWorkCB.getValue()==null || WName.getText().isEmpty() || weeklyDate.getValue()==null || priorityWCB.getValue()==null || year.getValue()==null || month.getValue()==null || day.getValue()==null)
            statusWLabel.setText("Please complete all information.!!");
        if ((month.getValue()==2 && day.getValue() >= 29) || ((month.getValue()==4 || month.getValue()==6 || month.getValue()==8 || month.getValue()==11) && day.getValue() >30))
            statusWLabel.setText(("Please select a new date.!!"));
        else {
            if (dataList.checkWorkName(WName.getText(), "WeeklyWork")) {
                if (categoryWorkCB.getValue().equals("Not choose"))
                    weeklyWork = new WeeklyWork("Not choose", WName.getText().trim(), weeklyDate.getValue(), "", "", priorityWCB.getValue(), "Not Started",year.getValue()+"/"+month.getValue()+"/"+day.getValue());
                else {
                    weeklyWork = new WeeklyWork(categoryWorkCB.getValue(), WName.getText().trim(), weeklyDate.getValue(), "", "", priorityWCB.getValue(), "Not Started",year.getValue()+"/"+month.getValue()+"/"+day.getValue());
                    categoryDataList.addWorkToCategory(categoryWorkCB.getValue(), "WeeklyWork",WName.getText());

                }

                dataList.addWeeklyWork(weeklyWork);     //<----- Add อยู่นี่
                workDataSource.setData(dataList);
                categoryDataSource.setData(categoryDataList);
                WName.clear();
                weeklyDate.setValue(null);
                priorityWCB.setValue(null);
                year.setValue(null);
                month.setValue(null);
                day.setValue(null);
                categoryWorkCB.getSelectionModel().clearSelection();
                statusWLabel.setText("");
            }
            else {
                statusWLabel.setText("This work name is already use.!!");
            }
        }
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
