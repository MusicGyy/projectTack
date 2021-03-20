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
import taskTracking.model.WorksCategory.ForwardWork;
import taskTracking.services.CategoryFileDataSource;
import taskTracking.services.DataList;
import taskTracking.services.DataSource;
import taskTracking.services.ForwardWorkFileDataSource;

import java.io.IOException;

public class ForwardWorkController {

    @FXML
    TextField FName, namePerson;
    @FXML
    ChoiceBox<Integer> fYear, fMonth, fDay, fHourStart, fMinStart;
    @FXML
    ChoiceBox<String> fPriorityCB,categoryWorkCB;
    @FXML
    Button fSubmit;
    @FXML
    Label fStatusLabel,textForUse;

    private ForwardWork forwardWork;
    private DataList dataList,categoryDataList;
    private DataSource workDataSource,categoryDataSource;


    @FXML
    public void initialize() {
        Platform.runLater((Runnable)new Runnable() {
            @Override
            public void run() {
                workDataSource = new ForwardWorkFileDataSource("data", "forwardWorks.csv");
                categoryDataSource = new CategoryFileDataSource("data","category.csv");

                categoryDataList = categoryDataSource.getData();
                dataList = workDataSource.getData();

                for (int i = 1; i <= 3; i++){
                    fPriorityCB.getItems().add(String.valueOf(i));}
                for (int i = 2021; i <= 2031; i++){
                    fYear.getItems().add(i);}
                for (int i = 1; i <= 12; i++){
                    fMonth.getItems().add(i);}
                for (int i = 1; i <= 31; i++) {
                    fDay.getItems().add(i);
                }
                for (int i = 0; i <= 23; i++){
                    fHourStart.getItems().add(i);
                }
                for (int i = 0; i <= 59; i++){
                    fMinStart.getItems().add(i);
                }

                categoryWorkCB.getItems().add("Not choose");
                for (CategoryWork categoryWork : categoryDataList.getCategoryArrayList()) {
                    categoryWorkCB.getItems().add(categoryWork.getNameC());
                }
            }
        });
    }

    @FXML public void handleSubmitAction(ActionEvent event) throws IOException {
        if (FName.getText().isEmpty() || namePerson.getText().isEmpty() || fYear.getValue()==null || fMonth.getValue()==null || fDay.getValue()==null ||
                fHourStart.getValue()==null || fMinStart.getValue()==null || fPriorityCB.getValue()==null || categoryWorkCB.getValue()==null)
            fStatusLabel.setText("Please complete all information.!!");

        else if ((fMonth.getValue()==2 && fDay.getValue() >= 29) || ((fMonth.getValue()==4 || fMonth.getValue()==6 || fMonth.getValue()==8 || fMonth.getValue()==11) && fDay.getValue() >30)){
            fStatusLabel.setText(("Please select a new date.!!"));
        }

        else {
            if (dataList.checkWorkName(FName.getText(),"ForwardWork")) {
                if (categoryWorkCB.getValue().equals("Not choose"))
                    forwardWork = new ForwardWork("Not choose", FName.getText().trim(), namePerson.getText().trim(), fYear.getValue() + "/" + fMonth.getValue() + "/" + fDay.getValue(),
                            fHourStart.getValue() + ":" + fMinStart.getValue(),
                            fPriorityCB.getValue(), "Not Started");
                else {
                    forwardWork = new ForwardWork(categoryWorkCB.getValue(), FName.getText().trim(), namePerson.getText().trim(), fYear.getValue() + "/" + fMonth.getValue() + "/" + fDay.getValue(),
                            fHourStart.getValue() + ":" + fMinStart.getValue(),
                            fPriorityCB.getValue(), "Not Started");

                    categoryDataList.addWorkToCategory(categoryWorkCB.getValue(), "ForwardWork",FName.getText().trim());

                }
                dataList.addForwardWork(forwardWork);
                workDataSource.setData(dataList);
                categoryDataSource.setData(categoryDataList);
                FName.clear();
                namePerson.clear();
                fYear.setValue(null);
                fMonth.setValue(null);
                fDay.setValue(null);
                fHourStart.setValue(null);
                fMinStart.setValue(null);
                fPriorityCB.setValue(null);
                categoryWorkCB.getSelectionModel().clearSelection();
                fStatusLabel.setText("");
            }
            else {
                fStatusLabel.setText(("This work name is already use.!!"));
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

    public void handleUpdateForwardWorksControllerButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("updateForwardWorks");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า updateForwardWorks ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
