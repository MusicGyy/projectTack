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
    ChoiceBox<Integer> StartYear,StartMonth,StartDay;
    @FXML
    ChoiceBox<String> PpriorityCB,categoryWorkCB;
    @FXML
    Button Psubmit;
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
                    PpriorityCB.getItems().add(String.valueOf(i));}
                for (int i = 2021; i <= 2031; i++){
                    StartYear.getItems().add(i);}
                for (int i = 1; i <= 12; i++){
                    StartMonth.getItems().add(i);}
                for (int i = 1; i <= 31; i++){
                    StartDay.getItems().add(i);}

                categoryWorkCB.getItems().add("ไม่เลือก");
                for (CategoryWork categoryWork : categoryDataList.getCategoryArrayList()) {
                    categoryWorkCB.getItems().add(categoryWork.getName());
                }
            }
        });
    }

    @FXML public void handleSubmitAction(ActionEvent event) throws IOException {
        if (categoryWorkCB.getItems().equals("ไม่เลือก"))
            projectWork = new ProjectWork(null,PName.getText(),LeaderName.getText() ,
                    StartYear.getValue() + "/" + StartMonth.getValue() + "/" + StartDay.getValue(), PpriorityCB.getValue(),"Not Started");
        else {
            projectWork = new ProjectWork(categoryWorkCB.getValue(), PName.getText(), LeaderName.getText(),
                    StartYear.getValue() + "/" + StartMonth.getValue() + "/" + StartDay.getValue(), PpriorityCB.getValue(), "Not Started");

            categoryDataList.addWorkToCategory(categoryWorkCB.getValue(), "ProjectWork");

        }

        System.out.println(projectWork.toString());
        dataList.addProjectWork(projectWork);
        workDataSource.setData(dataList);
        categoryDataSource.setData(categoryDataList);
        PStatusLabel.setText("");
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
