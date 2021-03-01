package taskTracking.services;

import taskTracking.model.WorksCategory.*;

import java.util.ArrayList;

public class DataList {
    private ArrayList<GeneralWork> generalWorkArrayList;
    private ArrayList<ForwardWork> forwardWorksArrayList;
    private ArrayList<ProjectWork> projectWorkArrayList;
    private ArrayList<WeeklyWork> weeklyWorkArrayList;
    private ArrayList<CategoryWork> categoryArrayList;


    public DataList(){
        this.generalWorkArrayList = new ArrayList<>();
        this.forwardWorksArrayList = new ArrayList<>();
        this.projectWorkArrayList = new ArrayList<>();
        this.weeklyWorkArrayList = new ArrayList<>();
        this.categoryArrayList = new ArrayList<>();
    }

    public void addGeneralWork(GeneralWork generalWork) { this.generalWorkArrayList.add(generalWork); }

    public void addForwardWork(ForwardWork forwardWork){
        this.forwardWorksArrayList.add(forwardWork);
    }

    public void addProjectWork(ProjectWork projectWork){
        this.projectWorkArrayList.add(projectWork);
    }

    public void addWeeklyWork(WeeklyWork weeklyWork){
        this.weeklyWorkArrayList.add(weeklyWork);
    }

    public void addCategory(CategoryWork categoryWork){this.categoryArrayList.add(categoryWork);}


    public boolean checkCategory(String name){
        for (CategoryWork categoryWork : categoryArrayList)
        {
            if(name.equals(categoryWork.getName())){
                return false;
            }
        }
        return true;
    }

    public void addWorkToCategory(String name,String typeName){
        for (CategoryWork categoryWork : categoryArrayList)
        {
            if(categoryWork.getName().equals(name)){
                switch (typeName) {
                    case "GeneralWork":
                        categoryWork.addCountGeneral();
                        break;
                    case "WeeklyWork":
                        categoryWork.addCountWeekly();
                        break;
                    case "ForwardWork":
                        categoryWork.addCountForward();
                        break;
                    case "ProjectWork":
                        categoryWork.addCountProject();
                        break;
                }
            }
        }
    }



    public ArrayList<GeneralWork> getGeneralWorkArrayList() {
        return generalWorkArrayList;
    }
    public ArrayList<ForwardWork> getForwardWorksArrayList() { return forwardWorksArrayList;}
    public ArrayList<ProjectWork> getProjectWorkArrayList() { return projectWorkArrayList;}
    public ArrayList<WeeklyWork>  getWeeklyWorkArrayList() { return weeklyWorkArrayList;}
    public ArrayList<CategoryWork> getCategoryArrayList() {return categoryArrayList;}

 }