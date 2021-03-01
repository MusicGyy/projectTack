package taskTracking.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;

import java.io.IOException;

public class WorksCategoryHomeController {

    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleGeneralWorkControllerButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("GeneralWork");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า GeneralWork ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleForwardWorksControllerButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("forwardWork");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า forwardWork ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleProjectControllerButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("projectWork");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า projectWork ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleWeeklyWorkControllerButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("weeklyWork");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า weeklyWork ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }


}
