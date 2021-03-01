package taskTracking.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import taskTracking.model.WorksCategory.WeeklyWork;
import taskTracking.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class UpdateWeeklyWorksController {
    private DataList weeklyList;
    private DataSource dataSource;
    private WeeklyWork selectedWeeklyWork;
    private ObservableList<WeeklyWork> weeklyWorkObservableList;

    @FXML
    TableView<WeeklyWork> showTableW;
    @FXML
    ChoiceBox<Integer> hourSTW,minuteSTW,secondSTW,hourETW,minuteETW,secondETW;
    @FXML
    ChoiceBox<String> dateUpdate,updateStatusW;
    @FXML
    Label workNameWLabel,startDateLabel,dateUpdateLabel,timeStartLabel,timeEndLabel,priorityWLabel;
    @FXML
    Button updateWeekly;

    @FXML
    public void initialize() {
        Platform.runLater((Runnable)new Runnable() {
            @Override
            public void run() {
                dataSource = new WeeklyWorkFileDataSource("data", "weeklyWorks.csv");
                weeklyList = dataSource.getData();
                for (int i = 0; i <= 23; i++){
                    hourSTW.getItems().add(i);
                }
                for (int i = 0; i <= 59; i++){
                    minuteSTW.getItems().add(i);
                }
                for (int i = 0; i <= 59; i++){
                    secondSTW.getItems().add(i);
                }
                for (int i = 0; i <= 23; i++){
                    hourETW.getItems().add(i);
                }
                for (int i = 0; i <= 59; i++){
                    minuteETW.getItems().add(i);
                }
                for (int i = 0; i <= 59; i++){
                    secondETW.getItems().add(i);
                }
                dateUpdate.getItems().addAll("Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday");
                updateStatusW.getItems().addAll( "Doing", "Finished");
                showStudentData();
            }
        });

        showTableW.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedWeeklyWork(newValue);
            }
        });
        updateWeekly.setDisable(true);

    }

    private void showStudentData() {
        weeklyWorkObservableList = FXCollections.observableArrayList(weeklyList.getWeeklyWorkArrayList());
        showTableW.setItems(weeklyWorkObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Work Name", "field:name"));
        configs.add(new StringConfiguration("title:Start Date", "field:weeklyDate"));
        configs.add(new StringConfiguration("title:Date Update", "field:weeklyDateUpdate"));
        configs.add(new StringConfiguration("title:Start Time", "field:startTime"));
        configs.add(new StringConfiguration("title:End Time", "field:lastDate"));
        configs.add(new StringConfiguration("title:Priority", "field:priority"));
        configs.add(new StringConfiguration("title:Status", "field:status"));

        for (StringConfiguration conf : configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            showTableW.getColumns().add(col);
            if (conf.get("title").equals("Priority")) {
                showTableW.getSortOrder().addAll(col);
                col.setSortType(TableColumn.SortType.DESCENDING);
            }
        }
    }

    private void showSelectedWeeklyWork(WeeklyWork weeklyWork){
        selectedWeeklyWork = weeklyWork;
        workNameWLabel.setText(weeklyWork.getName());
        priorityWLabel.setText(weeklyWork.getPriority());
        startDateLabel.setText(weeklyWork.getWeeklyDate());
        dateUpdateLabel.setText(weeklyWork.getWeeklyDateUpdate());
        timeStartLabel.setText(weeklyWork.getStartTime());
        timeEndLabel.setText(weeklyWork.getLastDate());
        updateWeekly.setDisable(false);
    }
    private void clearSelectedStudent() {
        selectedWeeklyWork = null;
        workNameWLabel.setText("....");
        priorityWLabel.setText("....");
        startDateLabel.setText("....");
        timeStartLabel.setText("....");
        dateUpdateLabel.setText("....");
        timeEndLabel.setText("....");

        updateWeekly.setDisable(true);
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

    public void handleBackToWeeklyWorkButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("weeklyWork");
        }
        catch (IOException e) {
            System.err.println("ไปที่หน้า weeklyWork ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleUpdateWeeklyButton(ActionEvent actionEvent) {
        selectedWeeklyWork.setStatus(updateStatusW.getValue());
        selectedWeeklyWork.setWeeklyDateUpdate(dateUpdate.getValue());
        selectedWeeklyWork.setStartTime(hourSTW.getValue()+":"+minuteSTW.getValue()+":"+secondSTW.getValue());
        selectedWeeklyWork.setLastDate(hourETW.getValue()+":"+minuteETW.getValue()+":"+secondETW.getValue());
        clearSelectedStudent();
        showTableW.refresh();
        showTableW.getSelectionModel().clearSelection();

        dataSource.setData(weeklyList);
    }

}