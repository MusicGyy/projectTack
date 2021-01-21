package taskTracking.controllers;

import com.github.saacsos.FXRouter;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class HomeController {

    @FXML
    public void handleImageFeeMemberCardButton(ActionEvent actionEvent) {
        try {
            // เปลี่ยนการแสดงผลไปที่ route ที่ชื่อ member_card_detail
            // พร้อมส่ง reference instance john ไปด้วย
            FXRouter.goTo("ImageFee");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า member_card_detail ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
