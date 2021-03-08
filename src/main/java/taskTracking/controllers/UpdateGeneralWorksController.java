package taskTracking.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import taskTracking.model.WorksCategory.GeneralWork;
import taskTracking.services.DataList;
import taskTracking.services.DataSource;
import taskTracking.services.GeneralWorkFileDataSource;
import taskTracking.services.StringConfiguration;

import java.io.IOException;
import java.util.ArrayList;

public class UpdateGeneralWorksController {
    private DataList generalList;
    private DataSource dataSource;
    private GeneralWork selectedGeneralWork;
    private ObservableList<GeneralWork> generalObservableList;

    @FXML
    private TableView<GeneralWork> showTable;
    @FXML
    ChoiceBox<Integer> hourFin, minFin,hourS,minS;
    @FXML
    ChoiceBox<String> updateStatus;
    @FXML
    Label workNameLabel,madeDateLabel,timeStartLabel,priorityLabel,categoryLabel,statusLabel,endTimeLabel;
    @FXML
    Button updateGeneral;

    @FXML
    public void initialize() {
        Platform.runLater((Runnable)new Runnable() {
            @Override
            public void run() {
                dataSource = new GeneralWorkFileDataSource("data", "generalWorks.csv");
                generalList = dataSource.getData();
                for (int i = 0; i <= 23; i++){
                    hourFin.getItems().add(i);
                }
                for (int i = 0; i <= 59; i++){
                    minFin.getItems().add(i);
                }
                for (int i = 0; i <= 23; i++ ){
                    hourS.getItems().add(i);
                }
                for (int i = 0; i <= 59; i++){
                    minS.getItems().add(i);
                }
                updateStatus.getItems().addAll( "Doing", "Finished");
                showStudentData();
            }
        });

        showTable.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                showSelectedGeneralWork(newValue);
            }
        });
        updateGeneral.setDisable(true);

    }

    private void showStudentData() {
        generalObservableList = FXCollections.observableArrayList(generalList.getGeneralWorkArrayList());
        showTable.setItems(generalObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Category Name", "field:category"));
        configs.add(new StringConfiguration("title:Work Name", "field:name"));
        configs.add(new StringConfiguration("title:MadeDate", "field:madeDate"));
        configs.add(new StringConfiguration("title:Start Time", "field:startTime"));
        configs.add(new StringConfiguration("title:Finished Time", "field:lastDate"));
        configs.add(new StringConfiguration("title:Priority", "field:priority"));
        configs.add(new StringConfiguration("title:Status", "field:status"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            showTable.getColumns().add(col);
            if (conf.get("title").equals("Priority")) {
                showTable.getSortOrder().addAll(col);
                col.setSortType(TableColumn.SortType.DESCENDING);
            }
        }
    }

    private void showSelectedGeneralWork(GeneralWork generalWork) {
        selectedGeneralWork = generalWork;
        if (generalWork.getStatus().equals("Finished")) {
            workNameLabel.setText(generalWork.getName());
            priorityLabel.setText(generalWork.getPriority());
            madeDateLabel.setText(generalWork.getMadeDate());
            timeStartLabel.setText(generalWork.getStartTime());
            categoryLabel.setText(generalWork.getCategory());
            endTimeLabel.setText(generalWork.getLastDate());
            updateGeneral.setDisable(true);

        }
        else {
            workNameLabel.setText(generalWork.getName());
            priorityLabel.setText(generalWork.getPriority());
            madeDateLabel.setText(generalWork.getMadeDate());
            timeStartLabel.setText(generalWork.getStartTime());
            categoryLabel.setText(generalWork.getCategory());
            endTimeLabel.setText(generalWork.getLastDate());
            updateGeneral.setDisable(false);
        }
    }

    private void clearSelectedStudent() {
        selectedGeneralWork = null;
        workNameLabel.setText("....");
        priorityLabel.setText("....");
        madeDateLabel.setText("....");
        timeStartLabel.setText("....");
        categoryLabel.setText("....");
        endTimeLabel.setText("....");

        updateGeneral.setDisable(true);
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

    public void handleBackToGeneralWorkButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("GeneralWork");
        }
        catch (IOException e) {
            System.err.println("ไปที่หน้า GeneralWork ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleUpdateGeneralButton(ActionEvent actionEvent) {
        if (updateStatus.getValue()!=null)
            selectedGeneralWork.setStatus(updateStatus.getValue());
        else {
            statusLabel.setText("");
        }
        if (hourFin.getValue()!=null && minFin.getValue()!=null && hourS.getValue()!=null && minS.getValue()!=null) {
            if (hourFin.getValue() == 0) {
                selectedGeneralWork.setLastDate(hourFin.getValue() + ":" + minFin.getValue());
                selectedGeneralWork.setStartTime(hourS.getValue() + ":" + minS.getValue());
            }
            else if (hourFin.getValue() > hourS.getValue()){
                selectedGeneralWork.setLastDate(hourFin.getValue() + ":" + minFin.getValue());
                selectedGeneralWork.setStartTime(hourS.getValue() + ":" + minS.getValue());
            }
            else if (hourFin.getValue().equals(hourS.getValue()) && minFin.getValue() > minS.getValue()){
                selectedGeneralWork.setLastDate(hourFin.getValue() + ":" + minFin.getValue());
                selectedGeneralWork.setStartTime(hourS.getValue() + ":" + minS.getValue());
            }
            else {
                statusLabel.setText("Please enter a new time.!!");
            }
        }
        else {
            statusLabel.setText("Please enter a new time.!!");
        }

        clearSelectedStudent();
        hourFin.setValue(null);
        minFin.setValue(null);
        hourS.setValue(null);
        minS.setValue(null);
        updateStatus.setValue(null);
        showTable.refresh();
        showTable.getSelectionModel().clearSelection();
        statusLabel.setText("");

        //CB.setVa(null)<-----เซต

        dataSource.setData(generalList);
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
