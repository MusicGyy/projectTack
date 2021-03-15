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
import taskTracking.model.WorksCategory.ProjectWork;
import taskTracking.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class UpdateProjectWorksController {
    private DataList projectDataList,categoryDataList;
    private DataSource dataSource,categoryDataSource;
    private ProjectWork selectedProjectWork;
    private ObservableList<ProjectWork> projectWorkObservableList;

    @FXML
    TableView<ProjectWork> showTableP;
    @FXML
    ChoiceBox<Integer> EndYear, EndMonth, EndDay,sYear,sMonth,sDay;
    @FXML
    ChoiceBox<String> updateStatusP,categoryWorkCB;
    @FXML
    Label workNameLabel, leaderNameLabel, startDateLabel, endDateLabel, priorityPLabel,categoryLabel,statusLabel;
    @FXML
    Button updateProject;

    @FXML
    public void initialize() {
        Platform.runLater((Runnable) new Runnable() {
            @Override
            public void run() {
                dataSource = new ProjectWorkFileDataSource("data", "projectWorks.csv");
                categoryDataSource = new CategoryFileDataSource("data","category.csv");

                categoryDataList = categoryDataSource.getData();
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
                for (int i = 2021; i <= 2031; i++) {
                    sYear.getItems().add(i);
                }
                for (int i = 1; i <= 12; i++) {
                    sMonth.getItems().add(i);
                }
                for (int i = 1; i <= 31; i++) {
                    sDay.getItems().add(i);
                }
                updateStatusP.getItems().addAll("Doing", "Finished");
                for (CategoryWork categoryWork : categoryDataList.getCategoryArrayList()) {
                    categoryWorkCB.getItems().add(categoryWork.getNameC());
                }
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
        configs.add(new StringConfiguration("title:Category Name", "field:category"));
        configs.add(new StringConfiguration("title:Work Name", "field:name"));
        configs.add(new StringConfiguration("title:Leader Name", "field:ProjectLeader"));
        configs.add(new StringConfiguration("title:Start Date", "field:madeDate"));
        configs.add(new StringConfiguration("title:Finished Time", "field:startTime"));
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
        if (projectWork.getStatus().equals("Finished")) {
            workNameLabel.setText(projectWork.getName());
            priorityPLabel.setText(projectWork.getPriority());
            startDateLabel.setText(projectWork.getMadeDate());
            leaderNameLabel.setText(projectWork.getProjectLeader());
            endDateLabel.setText(projectWork.getStartTime());
            categoryLabel.setText(projectWork.getCategory());
            updateProject.setDisable(true);
        }
        else {
            if (projectWork.getCategory().equals("Not choose")) {
                workNameLabel.setText(projectWork.getName());
                priorityPLabel.setText(projectWork.getPriority());
                startDateLabel.setText(projectWork.getMadeDate());
                leaderNameLabel.setText(projectWork.getProjectLeader());
                endDateLabel.setText(projectWork.getStartTime());
                categoryLabel.setText(projectWork.getCategory());
                categoryWorkCB.setDisable(false);
                updateProject.setDisable(false);
            }
            else {
                workNameLabel.setText(projectWork.getName());
                priorityPLabel.setText(projectWork.getPriority());
                startDateLabel.setText(projectWork.getMadeDate());
                leaderNameLabel.setText(projectWork.getProjectLeader());
                endDateLabel.setText(projectWork.getStartTime());
                categoryLabel.setText(projectWork.getCategory());
                categoryWorkCB.setDisable(true);
                updateProject.setDisable(false);
            }
        }
    }

    private void clearSelectedStudent() {
        selectedProjectWork = null;
        workNameLabel.setText("....");
        priorityPLabel.setText("....");
        startDateLabel.setText("....");
        leaderNameLabel.setText("....");
        endDateLabel.setText("....");
        categoryLabel.setText("....");

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
        if (updateStatusP.getValue()!=null)
            selectedProjectWork.setStatus(updateStatusP.getValue());
        if (EndDay.getValue()!=null && EndMonth.getValue()!=null && EndYear.getValue()!=null && sDay.getValue()!=null && sMonth.getValue()!=null && sYear.getValue()!=null)
        {
            if(EndYear.getValue() > sYear.getValue()) {
                if ((sMonth.getValue()==2 && sDay.getValue() >= 29) || ((sMonth.getValue()==4 || sMonth.getValue()==6 || sMonth.getValue()==8 || sMonth.getValue()==11) && sDay.getValue() >30) ||
                        (EndMonth.getValue()==2 && EndDay.getValue() >= 29) || ((EndMonth.getValue()==4 || EndMonth.getValue()==6 || EndMonth.getValue()==8 || EndMonth.getValue()==11) && EndDay.getValue() >30)) {
                    statusLabel.setText(("Please select a new date.!!"));
                }
                else {
                selectedProjectWork.setStartTime(EndYear.getValue() + "/" + EndMonth.getValue() + "/" + EndDay.getValue());
                selectedProjectWork.setMadeDate(sYear.getValue() + "/" + sMonth.getValue() + "/" + sDay.getValue());
                }
            }
            else if (EndYear.getValue().equals(sYear.getValue()) && EndMonth.getValue() > sMonth.getValue()){
                if ((sMonth.getValue()==2 && sDay.getValue() >= 29) || ((sMonth.getValue()==4 || sMonth.getValue()==6 || sMonth.getValue()==8 || sMonth.getValue()==11) && sDay.getValue() >30) ||
                        (EndMonth.getValue()==2 && EndDay.getValue() >= 29) || ((EndMonth.getValue()==4 || EndMonth.getValue()==6 || EndMonth.getValue()==8 || EndMonth.getValue()==11) && EndDay.getValue() >30)) {
                    statusLabel.setText(("Please select a new date.!!"));
                }
                else {
                    selectedProjectWork.setStartTime(EndYear.getValue() + "/" + EndMonth.getValue() + "/" + EndDay.getValue());
                    selectedProjectWork.setMadeDate(sYear.getValue() + "/" + sMonth.getValue() + "/" + sDay.getValue());
                }
            }
            else if (EndYear.getValue().equals(sYear.getValue()) && EndMonth.getValue().equals(sMonth.getValue()) && EndDay.getValue() > sDay.getValue()){
                if ((sMonth.getValue()==2 && sDay.getValue() >= 29) || ((sMonth.getValue()==4 || sMonth.getValue()==6 || sMonth.getValue()==8 || sMonth.getValue()==11) && sDay.getValue() >30) ||
                        (EndMonth.getValue()==2 && EndDay.getValue() >= 29) || ((EndMonth.getValue()==4 || EndMonth.getValue()==6 || EndMonth.getValue()==8 || EndMonth.getValue()==11) && EndDay.getValue() >30)) {
                    statusLabel.setText(("Please select a new date.!!"));
                }
                else {
                    selectedProjectWork.setStartTime(EndYear.getValue() + "/" + EndMonth.getValue() + "/" + EndDay.getValue());
                    selectedProjectWork.setMadeDate(sYear.getValue() + "/" + sMonth.getValue() + "/" + sDay.getValue());
                }
            }
            else {
                statusLabel.setText("Please select a new date.!!");
            }
        }
        if (categoryWorkCB.getValue()!= null){
            selectedProjectWork.setCategory(categoryWorkCB.getValue());
            categoryDataList.addWorkToCategory(categoryWorkCB.getValue(), "ProjectWork",selectedProjectWork.getName());
        }
        clearSelectedStudent();
        categoryWorkCB.setValue(null);
        sYear.setValue(null);
        sMonth.setValue(null);
        sDay.setValue(null);
        EndYear.setValue(null);
        EndMonth.setValue(null);
        EndDay.setValue(null);
        updateStatusP.setValue(null);
        showTableP.refresh();
        showTableP.getSelectionModel().clearSelection();
        statusLabel.setText("");

        categoryDataSource.setData(categoryDataList);
        dataSource.setData(projectDataList);
    }

    public void handleShowWorkAllButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("worksAll");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า worksAll ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
