package taskTracking.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import taskTracking.model.WorksCategory.ProjectWork;
import taskTracking.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class UpdateProjectWorksController {
    private DataList projectDataList;
    private DataSource dataSource;
    private ProjectWork selectedProjectWork;
    private ObservableList<ProjectWork> projectWorkObservableList;

    @FXML
    TableView<ProjectWork> showTableP;
    @FXML
    ChoiceBox<Integer> EndYear, EndMonth, EndDay;
    @FXML
    ChoiceBox<String> updateStatusP;
    @FXML
    Label workNameLabel, leaderNameLabel, startDateLabel, endDateLabel, priorityPLabel;
    @FXML
    Button updateProject;

    @FXML
    public void initialize() {
        Platform.runLater((Runnable) new Runnable() {
            @Override
            public void run() {
                dataSource = new ProjectWorkFileDataSource("data", "projectWorks.csv");
                projectDataList = dataSource.getData();
                for (int i = 2021; i <= 2031; i++) {
                    EndYear.getItems().add(i);
                }
                for (int i = 1; i <= 12; i++) {
                    EndMonth.getItems().add(i);
                }
                for (int i = 1; i <= 31; i++) {
                    EndDay.getItems().add(i);
                }
                updateStatusP.getItems().addAll("Doing", "Finished");
                showStudentData();
            }
        });

        showTableP.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedProjectWork(newValue);
            }
        });
        updateProject.setDisable(true);

    }

    private void showStudentData() {
        projectWorkObservableList = FXCollections.observableArrayList(projectDataList.getProjectWorkArrayList());
        showTableP.setItems(projectWorkObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Work Name", "field:name"));
        configs.add(new StringConfiguration("title:Start Date", "field:madeDate"));
        configs.add(new StringConfiguration("title:Leader Name", "field:ProjectLeader"));
        configs.add(new StringConfiguration("title:Finished Time", "field:lastDate"));
        configs.add(new StringConfiguration("title:Priority", "field:priority"));
        configs.add(new StringConfiguration("title:Status", "field:status"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            showTableP.getColumns().add(col);
            if (conf.get("title").equals("Priority")) {
                showTableP.getSortOrder().addAll(col);
                col.setSortType(TableColumn.SortType.DESCENDING);
            }
        }
    }

    private void showSelectedProjectWork(ProjectWork projectWork) {
        selectedProjectWork = projectWork;
        workNameLabel.setText(projectWork.getName());
        priorityPLabel.setText(projectWork.getPriority());
        startDateLabel.setText(projectWork.getMadeDate());
        leaderNameLabel.setText(projectWork.getProjectLeader());
        endDateLabel.setText(projectWork.getLastDate());
        updateProject.setDisable(false);
    }

    private void clearSelectedStudent() {
        selectedProjectWork = null;
        workNameLabel.setText("....");
        priorityPLabel.setText("....");
        startDateLabel.setText("....");
        leaderNameLabel.setText("....");
        endDateLabel.setText("....");

        updateProject.setDisable(true);
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

    public void handleBackToProjectWorkButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("projectWork");
        }
        catch (IOException e) {
            System.err.println("ไปที่หน้า projectWork ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleUpdateProjectButton(ActionEvent actionEvent) {
        selectedProjectWork.setStatus(updateStatusP.getValue());
        selectedProjectWork.setLastDate(EndYear.getValue()+"/"+EndMonth.getValue()+"/"+EndDay.getValue());
        clearSelectedStudent();
        showTableP.refresh();
        showTableP.getSelectionModel().clearSelection();

        dataSource.setData(projectDataList);
    }
}
