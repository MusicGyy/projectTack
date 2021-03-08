package taskTracking.model.WorksCategory;

public class ProjectWork extends GeneralWork{
    private String ProjectLeader;


    public ProjectWork(String category,String name, String projectLeader, String madeDate,String startTime, String priority, String status) {
        super(category,name, madeDate,startTime, priority, status);
        ProjectLeader = projectLeader;
    }

    public String getProjectLeader() {
        return ProjectLeader;
    }

    public void setProjectLeader(String projectLeader) {
        ProjectLeader = projectLeader;
    }

    @Override
    public String toString() {
        return getCategory() +","+ getName() + "," + ProjectLeader + "," + getMadeDate() + "," +  getStartTime() + "," + getPriority() + "," + getStatus();
    }
}
