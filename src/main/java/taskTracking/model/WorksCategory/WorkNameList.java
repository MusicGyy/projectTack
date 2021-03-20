package taskTracking.model.WorksCategory;

public class WorkNameList {
    private String name;

    public WorkNameList(){}

    public WorkNameList(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void getName(String name){
        this.name = name;
    }
}