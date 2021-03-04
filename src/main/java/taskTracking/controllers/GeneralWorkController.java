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
import taskTracking.model.WorksCategory.GeneralWork;
import taskTracking.services.CategoryFileDataSource;
import taskTracking.services.DataList;
import taskTracking.services.DataSource;
import taskTracking.services.GeneralWorkFileDataSource;

import java.io.IOException;

public class GeneralWorkController {

    @FXML
    TextField Name;
    @FXML
    ChoiceBox<Integer> year, month, day;
    @FXML
    ChoiceBox<String> priorityCB,categoryWorkCB;
    @FXML
    Button submit;
    @FXML
    Label statusLabel;

    private GeneralWork generalWork;
    private DataList dataList,categoryDataList;
    private DataSource workDataSource,categoryDataSource;

    @FXML
    public void initialize() {
        Platform.runLater((Runnable)new Runnable() {
            @Override
            public void run() {
                workDataSource = new GeneralWorkFileDataSource("data", "generalWorks.csv");
                categoryDataSource = new CategoryFileDataSource("data","category.csv");

                categoryDataList = categoryDataSource.getData();
                dataList = workDataSource.getData();

                for (int i = 1; i <= 3; i++){
                    priorityCB.getItems().add(String.valueOf(i));}
                for (int i = 2021; i <= 2031; i++){
                    year.getItems().add(i);}
                for (int i = 1; i <= 12; i++){
                    month.getItems().add(i);}
                for (int i = 1; i <= 31; i++){
                    day.getItems().add(i);}


                categoryWorkCB.getItems().add("Not choose");
                for (CategoryWork categoryWork : categoryDataList.getCategoryArrayList()) {
                    categoryWorkCB.getItems().add(categoryWork.getNameC());
                }
            }
        });
    }

    @FXML public void handleSubmitAction(ActionEvent event) throws IOException {
        if (Name.getText().isEmpty() || year.getValue()==null || month.getValue()==null || day.getValue()==null ||
                priorityCB.getValue()==null || categoryWorkCB.getValue()==null)
            statusLabel.setText("Please complete all information.!!");

        else if ((month.getValue()==2 && day.getValue() >= 29) || ((month.getValue()==4 || month.getValue()==6 || month.getValue()==8 || month.getValue()==11) && day.getValue() >30)) {
            statusLabel.setText(("Please select a new date.!!"));
        }

        else {
            if (categoryWorkCB.getItems().equals("Not choose"))
                generalWork = new GeneralWork(null, Name.getText(), year.getValue() + "/" + month.getValue() + "/" + day.getValue(),
                        "","", priorityCB.getValue(), "Not Started");
            else {
                generalWork = new GeneralWork(categoryWorkCB.getValue(), Name.getText(), year.getValue() + "/" + month.getValue() + "/" + day.getValue(),
                        "","", priorityCB.getValue(), "Not Started");


                categoryDataList.addWorkToCategory(categoryWorkCB.getValue(), "GeneralWork");
            }

            dataList.addGeneralWork(generalWork);     //<----- Add อยู่นี่
            workDataSource.setData(dataList);
            categoryDataSource.setData(categoryDataList);
            Name.clear();
            year.setValue(null);
            month.setValue(null);
            day.setValue(null);
            priorityCB.setValue(null);
            categoryWorkCB.setValue(null);
            statusLabel.setText("");
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

    public void handleUpdateGeneralWorksControllerButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("updateGeneralWorks");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า updateGeneralWorks ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
