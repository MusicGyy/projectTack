package taskTracking.model.WorksCategory;

import java.util.ArrayList;

public class CategoryWork {
    private String name;
    private int all;
    private int countGeneral;
    private int countWeekly;
    private int countForward;
    private int countProject;

    public CategoryWork() {
    }

    public CategoryWork(String name, int all, int countGeneral, int countWeekly, int countForward, int countProject) {
        this.name = name;
        this.all = all;
        this.countGeneral = countGeneral;
        this.countWeekly = countWeekly;
        this.countForward = countForward;
        this.countProject = countProject;
    }

    public void addCountGeneral(){
        this.countGeneral += 1;
    }
    public void addCountWeekly(){
        this.countWeekly += 1;
    }
    public void addCountForward(){
        this.countForward += 1;
    }
    public void addCountProject(){
        this.countProject += 1;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAll() {
        return countGeneral+countForward+countProject+countWeekly;
    }

    public void setAll(int all) {
        this.all = all;
    }

    public int getCountGeneral() {
        return countGeneral;
    }

    public void setCountGeneral(int countGeneral) {
        this.countGeneral = countGeneral;
    }

    public int getCountWeekly() {
        return countWeekly;
    }

    public void setCountWeekly(int countWeekly) {
        this.countWeekly = countWeekly;
    }

    public int getCountForward() {
        return countForward;
    }

    public void setCountForward(int countForward) {
        this.countForward = countForward;
    }

    public int getCountProject() {
        return countProject;
    }

    public void setCountProject(int countProject) {
        this.countProject = countProject;
    }

    @Override
    public String toString() {
        return name +","+ all +","+ countGeneral +","+ countWeekly +","+ countForward +","+ countProject;
    }
}