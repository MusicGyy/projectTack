package taskTracking.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
    ChoiceBox<Integer> hourFin, minFin, secFin;
    @FXML
    ChoiceBox<String> updateStatus;
    @FXML
    Label workNameLabel,madeDateLabel,timeStartLabel,priorityLabel;
    @FXML
    Button updateGeneral;

    @FXML
    public void initialize() {
        Platform.runLater((Runnable)new Runnable() {
            @Override
            public void run() {
                dataSource = new GeneralWorkFileDataSource("data", "works.csv");
                generalList = dataSource.getData();
                for (int i = 0; i <= 23; i++){
                    hourFin.getItems().add(i);
                }
                for (int i = 0; i <= 59; i++){
                    minFin.getItems().add(i);
                }
                for (int i = 0; i <= 59; i++){
                    secFin.getItems().add(i);
                }
                updateStatus.getItems().addAll("Not started", "Doing", "Finished");
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
        configs.add(new StringConfiguration("title:Work Name", "field:name"));
        configs.add(new StringConfiguration("title:Made Date", "field:madeDate"));
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
        workNameLabel.setText(generalWork.getName());
        priorityLabel.setText(generalWork.getPriority());
        madeDateLabel.setText(generalWork.getMadeDate());
        timeStartLabel.setText(generalWork.getStartTime());
        updateGeneral.setDisable(false);
    }

    private void clearSelectedStudent() {
        selectedGeneralWork = null;
        workNameLabel.setText("....");
        priorityLabel.setText("....");
        madeDateLabel.setText("....");
        timeStartLabel.setText("....");

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
        selectedGeneralWork.setStatus(updateStatus.getValue());
        selectedGeneralWork.setLastDate(hourFin.getValue()+":"+minFin.getValue()+":"+secFin.getValue());
        clearSelectedStudent();
        showTable.refresh();
        showTable.getSelectionModel().clearSelection();

        dataSource.setData(generalList);
    }
}
