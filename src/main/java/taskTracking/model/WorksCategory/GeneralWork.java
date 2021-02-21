package taskTracking.model.WorksCategory;

public class GeneralWork {
    private String name; // ชื่องาน
    private String madeDate; //วันที่ทำ
    private String startTime; // เวลาเริ่มทำ
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

    public GeneralWork(String name, String madeDate, String startTime, String priority) {
        this.name = name;
        this.madeDate = madeDate;
        this.startTime = startTime;
        this.priority = priority;
        this.status = "Not started" ;
    }

    public GeneralWork(String name, String madeDate, String startTime, String lastDate, String priority) {
        this.name = name;
        this.madeDate = madeDate;
        this.startTime = startTime;
        this.lastDate = lastDate;
        this.priority = priority;
        this.status = "Not started" ; // Not started, Doing, Finished
    }

    public GeneralWork(String name, String madeDate, String startTime, String lastDate, String priority, String status) {
        this.name = name;
        this.madeDate = madeDate;
        this.startTime = startTime;
        this.lastDate = lastDate;
        this.priority = priority;
        this.status = status;
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

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
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
        return name + "," + madeDate + "," + startTime + "," + lastDate + "," + priority + "," + status;
    }
}

