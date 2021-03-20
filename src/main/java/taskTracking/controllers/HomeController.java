package taskTracking.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import taskTracking.services.OpenPDFFile;

import java.io.IOException;

public class HomeController {
    private OpenPDFFile openPDFFileEz;

    @FXML
    public void initialize() {
        Platform.runLater((Runnable)new Runnable() {
            @Override
            public void run() {
                openPDFFileEz = new OpenPDFFile("data", "6210450563.pdf");
            }
        });
    }


    @FXML
    public void handleImageFeeMemberCardButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("ImageFee");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า ImageFee ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleWorkCategoryHomeButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("workCategoryHome");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า workCategoryHome ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleHowToUseButton(ActionEvent actionEvent) {
        try {
            openPDFFileEz.openPDFFileKu();
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void handleShowWorkAllButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("worksAll");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า worksAll ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleCreateCategoryButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("createCategory");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า createCategory ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


}
