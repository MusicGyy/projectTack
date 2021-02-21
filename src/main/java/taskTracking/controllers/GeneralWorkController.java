package taskTracking.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import taskTracking.model.WorksCategory.GeneralWork;
import taskTracking.services.DataList;
import taskTracking.services.DataSource;
import taskTracking.services.GeneralWorkFileDataSource;

import java.io.IOException;

public class GeneralWorkController {

    @FXML
    TextField Name;
    @FXML
    ChoiceBox<Integer> year, month, day, hourStart, minStart, secStart;
    @FXML
    ChoiceBox<String> priorityCB;
    @FXML
    Button submit;
    @FXML
    Label statusLabel;

    private GeneralWork generalWork;
    private DataList dataList;
    private DataSource workDataSource;

    @FXML
    public void initialize() {
        Platform.runLater((Runnable)new Runnable() {
            @Override
            public void run() {
                workDataSource = new GeneralWorkFileDataSource("data", "works.csv");
                dataList = workDataSource.getData();
                //priorityCB.getItems().addAll("1","2","3"); // ระดับความสำคัญ
                for (int i = 1; i <= 3; i++){
                    priorityCB.getItems().add(String.valueOf(i));}
                for (int i = 2021; i <= 2031; i++){
                    year.getItems().add(i);}
                for (int i = 1; i <= 12; i++){
                    month.getItems().add(i);}
                for (int i = 1; i <= 31; i++){
                    day.getItems().add(i);}
                for (int i = 0; i <= 23; i++){
                    hourStart.getItems().add(i);
//                    hourFin.getItems().add(i);
                    }
                for (int i = 0; i <= 59; i++){
                    minStart.getItems().add(i);
//                    minFin.getItems().add(i);
                    }
                for (int i = 0; i <= 59; i++){
                    secStart.getItems().add(i);
//                    secFin.getItems().add(i);
                    }
            }
        });
    }

    @FXML public void handleSubmitAction(ActionEvent event) throws IOException {
//        if (Name.getText().isEmpty())
//            statusLabel.setText("ยังไม่ได้เขียนชื่อ!!");
//        else if (year.getValue()==null)
//            statusLabel.setText("ยังไม่เลือกปี!!");
//        else if (month.getValue()==null)
//            statusLabel.setText("ยังไม่เลือกเดือน!!");
//        else if (day.getValue()==null)
//            statusLabel.setText("ยังไม่เลือกวัน!!");
//        else if (minFin.getValue()==null)
//            statusLabel.setText("ยังไม่เลือกนาทีที่ทำงานเสร็จ!!");
//        else if (secFin.getValue()==null)
//            statusLabel.setText("ยังไม่เลือกวินาทีที่ทำงานเสร็จ!!");
//        else if (priorityCB.getValue()==null)
//            statusLabel.setText("ยังไม่เลือกลำดับความสำคัญ!!");
//        else if (month.getValue()==2 && day.getValue() > 28)
//            statusLabel.setText("กรุณาเลือกวันใหม่");
//        else if ((month.getValue()==4 || month.getValue()==6 || month.getValue()==11 || month.getValue()==9) && day.getValue() > 30)
//            statusLabel.setText("กรุณาเลือกวันใหม่");
//        else if (hourStart.getValue()==null)
//            statusLabel.setText("ยังไม่เลือกชั่วโมงเริ่มทำงาน!!");
//        else if (minStart.getValue()==null)
//            statusLabel.setText("ยังไม่เลือกนาทีเริ่มทำงาน!!");
//        else if (secStart.getValue()==null)
//            statusLabel.setText("ยังไม่เลือกวินาทีเริ่มทำงาน!!");
//        else if (hourFin.getValue()==null)
//            statusLabel.setText("ยังไม่เลือกชั่วโมงที่ทำงานเสร็จ!!");
//        else if (hourFin.getValue().equals(hourStart.getValue()))
//            statusLabel.setText("ยังไม่เลือกนาทีที่ทำงานเสร็จ!!");
//            if (minFin.getValue() < minStart.getValue())
//                statusLabel.setText("กรุณาเลือกนาทีใหม่!!");
//            else if (minFin.getValue().equals(minStart.getValue()))
//                if (secFin.getValue() < secStart.getValue())
//                    statusLabel.setText("กรุณาเลือกวินาทีใหม่!!");
//        else if (hourFin.getValue() < hourStart.getValue())
//            statusLabel.setText("กรุณาเลือกชั่วโมงใหม่!!");
//        else if (minFin.getValue() < minStart.getValue())
//            statusLabel.setText("กรุณาเลือกนาทีใหม่!!");
//        else if (minFin.getValue().equals(minStart.getValue()))
//            if (secFin.getValue() < secStart.getValue() || secFin.getValue().equals(secStart.getValue()))
//                statusLabel.setText("กรุณาเลือกวินาทีใหม่!!");
//        else if (hourFin.getValue()==hourStart.getValue()){
//            statusLabel.setText("ยังไม่เลือกนาทีที่ทำงานเสร็จ!!");
//            if (minFin.getValue() < minStart.getValue()){
//                statusLabel.setText("กรุณาเลือกนาทีใหม่่!!");
//            }
//            else if (minFin.getValue().equals(minStart.getValue())){
//                statusLabel.setText("ยังไม่เลือกวินาทีที่ทำงานเสร็จ!!");
//                if (secFin.getValue() < secStart.getValue() || secFin.getValue().equals(secStart.getValue())){
//                    statusLabel.setText("กรุณาเลือกวินาทีใหม่!!");
//                }
//            }
//        }
//        else if (minFin.getValue()==null)
//            statusLabel.setText("ยังไม่เลือกนาทีที่ทำงานเสร็จ!!");
//        else if (secFin.getValue()==null)
//            statusLabel.setText("ยังไม่เลือกวินาทีที่ทำงานเสร็จ!!");
//        else if (priorityCB.getValue()==null)
//            statusLabel.setText("ยังไม่เลือกลำดับความสำคัญ!!");
//        else if (month.getValue()==2 && day.getValue() > 28)
//            statusLabel.setText("กรุณาเลือกวันใหม่");
//        else if ((month.getValue()==4 || month.getValue()==6 || month.getValue()==11 || month.getValue()==9) && day.getValue() > 30)
//            statusLabel.setText("กรุณาเลือกวันใหม่");
//        else {
            generalWork = new GeneralWork(Name.getText(), year.getValue() + "/" + month.getValue() + "/" + day.getValue(),
                    hourStart.getValue() + ":" + minStart.getValue() + ":" + secStart.getValue(), priorityCB.getValue());


            System.out.println(generalWork.toString());
            dataList.addWork(generalWork);
            workDataSource.setData(dataList);
            statusLabel.setText("");
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

    public void handleUpdateGeneralWorksControllerButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("updateGeneralWorks");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า updateGeneralWorks ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
