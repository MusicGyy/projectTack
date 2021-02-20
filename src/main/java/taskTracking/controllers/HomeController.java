package taskTracking.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeController {

    @FXML
    public void handleImageFeeMemberCardButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("ImageFee");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า member_card_detail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleWorkCategoryHomeButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("workCategoryHome");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า member_card_detail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

}
