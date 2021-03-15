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
import taskTracking.model.WorksCategory.ProjectWork;
import taskTracking.services.CategoryFileDataSource;
import taskTracking.services.DataList;
import taskTracking.services.DataSource;
import taskTracking.services.ProjectWorkFileDataSource;

import java.io.IOException;

public class ProjectWorkController {

    @FXML
    TextField PName,LeaderName;
    @FXML
    ChoiceBox<String> pPriorityCB,categoryWorkCB;
    @FXML
    Button pSubmit;
    @FXML
    Label PStatusLabel;

    private ProjectWork projectWork;
    private DataList dataList,categoryDataList;
    private DataSource workDataSource,categoryDataSource;

    @FXML
    public void initialize() {
        Platform.runLater((Runnable)new Runnable() {
            @Override
            public void run() {
                workDataSource = new ProjectWorkFileDataSource("data", "projectWorks.csv");
                categoryDataSource = new CategoryFileDataSource("data","category.csv");

                categoryDataList = categoryDataSource.getData();
                dataList = workDataSource.getData();
                for (int i = 1; i <= 3; i++){
                    pPriorityCB.getItems().add(String.valueOf(i));}

                categoryWorkCB.getItems().add("Not choose");
                for (CategoryWork categoryWork : categoryDataList.getCategoryArrayList()) {
                    categoryWorkCB.getItems().add(categoryWork.getNameC());
                }
            }
        });
    }

    @FXML public void handleSubmitAction(ActionEvent event) throws IOException {
        if (PName.getText().isEmpty() || LeaderName.getText().isEmpty() || pPriorityCB.getValue()==null || categoryWorkCB.getValue()==null)
            PStatusLabel.setText("Please complete all information.!!");

//        else if ((StartMonth.getValue()==2 && StartDay.getValue() >= 29) || ((StartMonth.getValue()==4 || StartMonth.getValue()==6 || StartMonth.getValue()==8 || StartMonth.getValue()==11) && StartDay.getValue() >30)) {
//            PStatusLabel.setText(("Please select a new date.!!"));
//        }

        else {
            if (dataList.checkWorkName(PName.getText(), "ProjectWork")) {
                if (categoryWorkCB.getValue().equals("Not choose"))
                    projectWork = new ProjectWork("Not choose", PName.getText(), LeaderName.getText(),
                            null, null, pPriorityCB.getValue(), "Not Started");
                else {
                    projectWork = new ProjectWork(categoryWorkCB.getValue(), PName.getText(), LeaderName.getText(),
                            null, null, pPriorityCB.getValue(), "Not Started");

                    categoryDataList.addWorkToCategory(categoryWorkCB.getValue(), "ProjectWork",PName.getText());

                }

                dataList.addProjectWork(projectWork);
                workDataSource.setData(dataList);
                categoryDataSource.setData(categoryDataList);
                PName.clear();
                LeaderName.clear();
                pPriorityCB.setValue(null);
                categoryWorkCB.getSelectionModel().clearSelection();
                PStatusLabel.setText("");
            }
            else {
                PStatusLabel.setText("This work name is already use.!!");
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

    public void handleUpdateProjectWorksControllerButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("updateProjectWorks");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า updateProjectWorks ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
