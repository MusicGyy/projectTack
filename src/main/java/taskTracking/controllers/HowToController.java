package taskTracking.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;

import java.io.IOException;

public class HowToController {
    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
