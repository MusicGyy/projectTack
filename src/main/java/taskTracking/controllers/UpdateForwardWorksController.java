package taskTracking.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import taskTracking.model.WorksCategory.ForwardWork;
import taskTracking.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class UpdateForwardWorksController {
    private DataList forwardList;
    private DataSource dataSource;
    private ForwardWork selectedForwardWork;
    private ObservableList<ForwardWork> forwardWorkObservableList;

    @FXML
    TableView<ForwardWork> showTableF;
    @FXML
    ChoiceBox<String> updateStatusF;
    @FXML
    Label workNameLabel,responsiblePersonLabel,assignedDateLabel,assignedTimeLabel,priorityFLabel,categoryLabel;
    @FXML
    Button updateForward;
    @FXML
    TextField addResponsiblePerson;

    @FXML
    public void initialize() {
        Platform.runLater((Runnable)new Runnable() {
            @Override
            public void run() {
                dataSource = new ForwardWorkFileDataSource("data", "forwardWorks.csv");
                forwardList = dataSource.getData();
                updateStatusF.getItems().addAll( "Doing", "Finished");
                showStudentData();
            }
        });

        showTableF.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedForwardWork(newValue);
            }
        });
        updateForward.setDisable(true);

    }

    private void showStudentData() {
        forwardWorkObservableList = FXCollections.observableArrayList(forwardList.getForwardWorksArrayList());
        showTableF.setItems(forwardWorkObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Category Name", "field:category"));
        configs.add(new StringConfiguration("title:Work Name", "field:name"));
        configs.add(new StringConfiguration("title:Name of person in charge", "field:responsiblePerson"));
        configs.add(new StringConfiguration("title:Assigned Date", "field:assignedDate"));
        configs.add(new StringConfiguration("title:Assigned Time", "field:assignedTime"));
        configs.add(new StringConfiguration("title:Priority", "field:priority"));
        configs.add(new StringConfiguration("title:Status", "field:status"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            showTableF.getColumns().add(col);
            if (conf.get("title").equals("Priority")) {
                showTableF.getSortOrder().addAll(col);
                col.setSortType(TableColumn.SortType.DESCENDING);
            }
        }
    }

    private void showSelectedForwardWork(ForwardWork forwardWork) {
        selectedForwardWork = forwardWork;
        if (forwardWork.getStatus().equals("Finished")) {
            workNameLabel.setText(forwardWork.getName());
            responsiblePersonLabel.setText(forwardWork.getResponsiblePerson());
            priorityFLabel.setText(forwardWork.getPriority());
            assignedDateLabel.setText(forwardWork.getAssignedDate());
            assignedTimeLabel.setText(forwardWork.getAssignedTime());
            categoryLabel.setText(forwardWork.getCategory());
            updateForward.setDisable(true);
        }

        else {
            workNameLabel.setText(forwardWork.getName());
            responsiblePersonLabel.setText(forwardWork.getResponsiblePerson());
            priorityFLabel.setText(forwardWork.getPriority());
            assignedDateLabel.setText(forwardWork.getAssignedDate());
            assignedTimeLabel.setText(forwardWork.getAssignedTime());
            categoryLabel.setText(forwardWork.getCategory());
            updateForward.setDisable(false);
        }
    }

    private void clearSelectedStudent() {
        selectedForwardWork = null;
        workNameLabel.setText("....");
        priorityFLabel.setText("....");
        responsiblePersonLabel.setText("....");
        assignedDateLabel.setText("....");
        assignedTimeLabel.setText("....");
        categoryLabel.setText("....");

        updateForward.setDisable(true);
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

    public void handleBackToForwardWorkButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("forwardWork");
        }
        catch (IOException e) {
            System.err.println("ไปที่หน้า forwardWork ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleUpdateForwardButton(ActionEvent actionEvent) {
        if (updateStatusF.getValue()!= null)
            selectedForwardWork.setStatus(updateStatusF.getValue());
        if(!addResponsiblePerson.getText().isEmpty())
            selectedForwardWork.addResponsiblePerson(addResponsiblePerson.getText());
        addResponsiblePerson.clear();
        updateStatusF.setValue(null);
        clearSelectedStudent();
        showTableF.refresh();
        showTableF.getSelectionModel().clearSelection();

        dataSource.setData(forwardList);
    }
}
