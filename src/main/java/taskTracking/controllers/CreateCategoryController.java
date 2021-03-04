package taskTracking.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import taskTracking.model.WorksCategory.CategoryWork;
import taskTracking.services.CategoryFileDataSource;
import taskTracking.services.DataList;
import taskTracking.services.DataSource;

import java.io.IOException;

public class CreateCategoryController {

    @FXML
    TextField createCategoryText;
    @FXML
    Button createButton;
    @FXML
    Label statusCategory;

    private CategoryWork categoryWork;
    private DataList dataList;
    private DataSource workDataSource;

    @FXML
    public void initialize() {
        Platform.runLater((Runnable)new Runnable() {
            @Override
            public void run() {
                workDataSource = new CategoryFileDataSource("data","category.csv");
                dataList = workDataSource.getData();
            }
        });
    }

    @FXML public void handleCreateButtonAction(ActionEvent event) throws IOException {
        if (createCategoryText.getText().isEmpty())
        {
            statusCategory.setText("Please enter the name of the desired category.!!");

        }
        else {
            if (dataList.checkCategory(createCategoryText.getText())) {
                categoryWork = new CategoryWork(createCategoryText.getText(), 0, 0, 0, 0, 0);


//            System.out.println(categoryWork.toString());
                dataList.addCategory(categoryWork);
                workDataSource.setData(dataList);
                createCategoryText.clear();
                statusCategory.setText("....");
            }
            else {
                statusCategory.setText("Repeat category name, please enter it again.!!");
            }
        }
    }


    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        }
        catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
