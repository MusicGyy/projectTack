package taskTracking.model.WorksCategory;

public class WeeklyWork extends GeneralWork{
//    private String weeklyDate;

    public WeeklyWork() {
    }

    public WeeklyWork(String category,String name,String madeDate , String startTime, String lastDate, String priority, String status ) {
        super(category,name,madeDate,startTime,lastDate,priority, status);
//        this.weeklyDate = weeklyDate;
    }


//    public void addWeeklyDate (String madeDate){
//        if (!madeDate.contains(getMadeDate()))
//
//    }



//    public String getWeeklyDate() {
//        return weeklyDate;
//    }
//
//    public void setWeeklyDate(String weeklyDate) {
//        this.weeklyDate = weeklyDate;
//    }

    @Override
    public String toString() {
        return getCategory() +","+ getName() + "," + getMadeDate() + "," + getStartTime() + "," + getLastDate()+ "," + getPriority() + "," + getStatus();
    }
}
