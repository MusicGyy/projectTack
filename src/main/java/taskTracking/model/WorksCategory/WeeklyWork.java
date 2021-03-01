package taskTracking.model.WorksCategory;

public class WeeklyWork extends GeneralWork{
    private String weeklyDate;
    private String weeklyDateUpdate;

    public WeeklyWork() {
    }

    public WeeklyWork(String category,String name,String weeklyDate ,String weeklyDateUpdate, String startTime, String lastDate, String priority, String status ) {
        super(category,name,startTime,lastDate,priority, status);
        this.weeklyDate = weeklyDate;
        this.weeklyDateUpdate = weeklyDateUpdate;
    }


    public String getWeeklyDateUpdate() {
        return weeklyDateUpdate;
    }

    public void setWeeklyDateUpdate(String weeklyDateUpdate) {
        this.weeklyDateUpdate = weeklyDateUpdate;
    }

    public String getWeeklyDate() {
        return weeklyDate;
    }

    public void setWeeklyDate(String weeklyDate) {
        this.weeklyDate = weeklyDate;
    }

    @Override
    public String toString() {
        return getCategory() +","+ getName() + "," + weeklyDate + ","+ weeklyDateUpdate + "," + getStartTime() + "," + getLastDate()+ "," + getPriority() + "," + getStatus();
    }
}
