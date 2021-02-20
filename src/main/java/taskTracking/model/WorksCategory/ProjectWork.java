package taskTracking.model.WorksCategory;

public class ProjectWork extends GeneralWork{
    private String ProjectLeader;

    public ProjectWork(String name, String startDate, String lastDate, String priority, String projectLeader) {
        super(name, startDate, lastDate, priority);
        ProjectLeader = projectLeader;
    }
}
