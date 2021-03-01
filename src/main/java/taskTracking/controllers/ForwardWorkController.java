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
    ChoiceBox<Integer> Fyear, Fmonth, Fday, FhourStart, FminStart, FsecStart;
    @FXML
    ChoiceBox<String> FpriorityCB,categoryWorkCB;
    @FXML
    Button Fsubmit;
    @FXML
    Label FstatusLabel;

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
                    FpriorityCB.getItems().add(String.valueOf(i));}
                for (int i = 2021; i <= 2031; i++){
                    Fyear.getItems().add(i);}
                for (int i = 1; i <= 12; i++){
                    Fmonth.getItems().add(i);}
                for (int i = 1; i <= 31; i++){
                    Fday.getItems().add(i);}
                for (int i = 0; i <= 23; i++){
                    FhourStart.getItems().add(i);
                }
                for (int i = 0; i <= 59; i++){
                    FminStart.getItems().add(i);
                }
                for (int i = 0; i <= 59; i++){
                    FsecStart.getItems().add(i);
                }


                categoryWorkCB.getItems().add("ไม่เลือก");
                for (CategoryWork categoryWork : categoryDataList.getCategoryArrayList()) {
                    categoryWorkCB.getItems().add(categoryWork.getName());
                }
            }
        });
    }

    @FXML public void handleSubmitAction(ActionEvent event) throws IOException {
        if (categoryWorkCB.getItems().equals("ไม่เลือก"))
            forwardWork = new ForwardWork(null,FName.getText(),namePerson.getText() ,Fyear.getValue() + "/" + Fmonth.getValue() + "/" + Fday.getValue(),
                    FhourStart.getValue() + ":" + FminStart.getValue() + ":" + FsecStart.getValue(),
                    FpriorityCB.getValue(), "Not Started");
        else {
            forwardWork = new ForwardWork(categoryWorkCB.getValue(), FName.getText(), namePerson.getText(), Fyear.getValue() + "/" + Fmonth.getValue() + "/" + Fday.getValue(),
                    FhourStart.getValue() + ":" + FminStart.getValue() + ":" + FsecStart.getValue(),
                    FpriorityCB.getValue(), "Not Started");

            categoryDataList.addWorkToCategory(categoryWorkCB.getValue(), "ForwardWork");

        }



        System.out.println(forwardWork.toString());
        dataList.addForwardWork(forwardWork);
        workDataSource.setData(dataList);
        categoryDataSource.setData(categoryDataList);
        FstatusLabel.setText("");
    }
//    }

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
