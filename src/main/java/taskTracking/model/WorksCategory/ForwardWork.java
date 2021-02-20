package taskTracking.model.WorksCategory;

public class ForwardWork extends GeneralWork{
    private String responsiblePerson;

    public ForwardWork(String name, String priority, String responsiblePerson) {
        super(name, priority);
        this.responsiblePerson = responsiblePerson;
    }
}
