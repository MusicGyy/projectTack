package taskTracking.model.WorksCategory;

import com.sun.xml.internal.fastinfoset.util.StringArray;

public class GeneralWork {
    private String name; // ชื่องาน
    private String madeDate; //วันที่ทำ
    private String startTime; // เวลาเริ่มทำ
    private String lastDate; // เวลาสิ้นสุดการทำ
    private String priority; // ลำดับความสาคัญของงาน
    private String status; // สถานะของงาน (ยังไม่เริ่ม, กาลังทา, เสร็จสิ้นแล้ว)
    private String category;


    public GeneralWork() {
    }

    public GeneralWork(String name) {
        this.name = name;
    }

    public GeneralWork(String category, String name, String priority, String status) {  /// ForwardWork
        this.name = name;
        this.priority = priority;
        this.status = status;
        this.category = category;
    }

    public GeneralWork(String category, String name, String madeDate, String priority, String status) { /// ProjectWork
        this.name = name;
        this.madeDate = madeDate;
        this.priority = priority;
        this.status = status;
        this.category = category;
    }


    public GeneralWork(String category, String name, String madeDate, String startTime, String priority, String status) {  /// GeneralWork
        this.name = name;
        this.madeDate = madeDate;
        setStartTime(startTime);
        this.priority = priority;
        this.status = status;
        this.category = category;
    }


    public GeneralWork(String category, String name, String madeDate, String startTime, String lastDate, String priority, String status) {
        this.name = name;
        this.madeDate = madeDate;
        setStartTime(startTime);
        setLastDate(lastDate);
        this.priority = priority;
        this.status = status;
        this.category = category;
    }

    public void addWeeklyDate(String madeDate) {
        if (!this.madeDate.contains(madeDate))
            this.madeDate = getMadeDate() + " | " + madeDate;
    }

    public void addToTA(String name) {
        if (this.name.equals("null"))
            this.name = getName().replace("null", name);
        else
            this.name = getName() + "//" + name;
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
        if (startTime.contains(":")) {
            String[] array = startTime.split(":");
            int hour = Integer.parseInt(array[0]);
            int minute = Integer.parseInt(array[1]);
            this.startTime = String.format("%02d:%02d", hour, minute);
        } else {
            this.startTime = startTime;
        }
    }

    public String getLastDate() {
        return lastDate;
    }

    public void setLastDate(String lastDate) {
        if (lastDate.contains(":")) {
            String[] array = lastDate.split(":");
            int hour = Integer.parseInt(array[0]);
            int minute = Integer.parseInt(array[1]);
            this.lastDate = String.format("%02d:%02d", hour, minute);
        } else {
            this.lastDate = lastDate;
        }
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return category + "," + name + "," + madeDate + "," + startTime + "," + lastDate + "," + priority + "," + status;
    }
}
