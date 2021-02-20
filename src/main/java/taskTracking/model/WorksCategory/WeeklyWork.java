package taskTracking.model.WorksCategory;

public class WeeklyWork extends GeneralWork{
    private String weeklyDate;

    public WeeklyWork() {
    }

    public WeeklyWork(String name, String startDate, String lastDate, String priority, String weeklyDate) {
        super(name, startDate, lastDate, priority);
        this.weeklyDate = weeklyDate;
    }

    //setter and getter
}
