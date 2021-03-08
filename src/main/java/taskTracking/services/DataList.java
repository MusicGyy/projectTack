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

    public ArrayList<GeneralWork> searchGeneral(String input){
        ArrayList<GeneralWork> generalWorks = new ArrayList<>();
        for (GeneralWork work : getGeneralWorkArrayList()){
            if (work.getStatus().equals(input)  || work.getMadeDate().equals(input))
                generalWorks.add(work);
        }
        return generalWorks;
    }

    public ArrayList<ForwardWork> searchForward(String input){
        ArrayList<ForwardWork> forwardWorks = new ArrayList<>();
        for (ForwardWork work : getForwardWorksArrayList()){
            if (work.getStatus().equals(input)   ||  work.getAssignedDate().equals(input))
                forwardWorks.add(work);
        }
        return forwardWorks;
    }

    public ArrayList<WeeklyWork> searchWeekly(String input){
        ArrayList<WeeklyWork> weeklyWorks = new ArrayList<>();
        for (WeeklyWork work : getWeeklyWorkArrayList()){
            if (work.getStatus().equals(input) ||  work.getMadeDate().equals(input))
                weeklyWorks.add(work);
        }
        return weeklyWorks;
    }

    public ArrayList<ProjectWork> searchProject(String input){
        ArrayList<ProjectWork> projectWorks = new ArrayList<>();
        for (ProjectWork work : getProjectWorkArrayList()){
            if (work.getStatus().equals(input) || work.getMadeDate().equals(input) || work.getStartTime().equals(input))
                projectWorks.add(work);
        }
        return projectWorks;
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
            if(name.equals(categoryWork.getNameC())){
                return false;
            }
        }
        return true;
    }

    public void addWorkToCategory(String name,String typeName){
        for (CategoryWork categoryWork : categoryArrayList)
        {
            if(categoryWork.getNameC().equals(name)){
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