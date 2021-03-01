package taskTracking.model.WorksCategory;

public class ForwardWork extends GeneralWork{
    private String responsiblePerson;
    private String assignedDate;
    private String assignedTime;


    public ForwardWork(String category,String name, String responsiblePerson, String assignedDate, String assignedTime, String priority, String status) {
        super(category,name, priority,status);
        this.responsiblePerson = responsiblePerson;
        this.assignedDate = assignedDate;
        this.assignedTime = assignedTime;
    }

    public void addResponsiblePerson (String responsiblePerson){
        this.responsiblePerson = getResponsiblePerson() + " | " + responsiblePerson;
    }

    public String getResponsiblePerson() {
        return responsiblePerson;
    }

    public void setResponsiblePerson(String responsiblePerson) {
        this.responsiblePerson = responsiblePerson;
    }

    public String getAssignedDate() {
        return assignedDate;
    }

    public void setAssignedDate(String assignedDate) {
        this.assignedDate = assignedDate;
    }

    public String getAssignedTime() {
        return assignedTime;
    }

    public void setAssignedTime(String assignedTime) {
        this.assignedTime = assignedTime;
    }


    @Override
    public String toString() {
        return getCategory() +","+getName() + "," + responsiblePerson + "," + assignedDate + "," + assignedTime + "," + getPriority() + "," + getStatus();
    }
}
