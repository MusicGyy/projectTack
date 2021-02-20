package taskTracking.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;

import java.io.IOException;

public class UpdateGeneralWorksController {

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


}
