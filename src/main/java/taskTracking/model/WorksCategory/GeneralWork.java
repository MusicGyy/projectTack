package taskTracking.model.WorksCategory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeneralWork {
    private String name; // ชื่องาน
    private String madeDate; //วันที่ทำ
    private String startDate; // เวลาเริ่มทำ
    private String lastDate; // เวลาสิ้นสุดการทำ
    private String priority; // ลำดับความสาคัญของงาน
    private String status; // สถานะของงาน (ยังไม่เริ่ม, กาลังทา, เสร็จสิ้นแล้ว)

    public GeneralWork() {
    }

    public GeneralWork(String name, String priority) {
        this.name = name;
        this.priority = priority;
        this.status = "Not started" ;
    }

    public GeneralWork(String name, String startDate, String lastDate, String priority) {
        this.name = name;
        this.startDate = startDate;
        this.lastDate = lastDate;
        this.priority = priority;
        this.status = "Not started" ; // Not started, Doing, Finished
    }

    public GeneralWork(String name, String madeDate, String startDate, String lastDate, String priority) {
        this.name = name;
        this.madeDate = madeDate;
        this.startDate = startDate;
        this.lastDate = lastDate;
        this.priority = priority;
        this.status = "Not started" ; // Not started, Doing, Finished
    }

    public GeneralWork(String name, String madeDate, String startDate, String lastDate, String priority, String status) {
        this.name = name;
        this.madeDate = madeDate;
        this.startDate = startDate;
        this.lastDate = lastDate;
        this.priority = priority;
        this.status = status;
    }

    public String dayTime(){
        DateTimeFormatter timeMillis = DateTimeFormatter.ofPattern("yyyy/MM/dd-HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        return timeMillis.format(date) ;
    }

    public String day(){
        DateTimeFormatter timeMillis = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        LocalDateTime date = LocalDateTime.now();
        return timeMillis.format(date) ;
    }

    public String Time(){
        DateTimeFormatter timeMillis = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime date = LocalDateTime.now();
        return timeMillis.format(date) ;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMadeDate() {
        return madeDate;
    }

    public void setMadeDate(String madeDate) {
        this.madeDate = madeDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        this.lastDate = lastDate;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return name + "," + madeDate + "," + startDate + "," + lastDate + "," + priority + "," + status;
    }
}

