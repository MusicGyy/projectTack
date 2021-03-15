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
            if (work.getStatus().equals(input)  || work.getMadeDate().equals(input) || work.getCategory().equals(input))
                generalWorks.add(work);
        }
        return generalWorks;
    }

    public ArrayList<ForwardWork> searchForward(String input){
        ArrayList<ForwardWork> forwardWorks = new ArrayList<>();
        for (ForwardWork work : getForwardWorksArrayList()){
            if (work.getStatus().equals(input)   ||  work.getAssignedDate().equals(input)|| work.getCategory().equals(input))
                forwardWorks.add(work);
        }
        return forwardWorks;
    }

    public ArrayList<WeeklyWork> searchWeekly(String input){
        ArrayList<WeeklyWork> weeklyWorks = new ArrayList<>();
        for (WeeklyWork work : getWeeklyWorkArrayList()){
            if (work.getStatus().equals(input)|| work.getCategory().equals(input))
                weeklyWorks.add(work);
        }
        return weeklyWorks;
    }

    public ArrayList<ProjectWork> searchProject(String input){
        ArrayList<ProjectWork> projectWorks = new ArrayList<>();
        for (ProjectWork work : getProjectWorkArrayList()){
            if (work.getStatus().equals(input) || work.getMadeDate().equals(input) || work.getStartTime().equals(input) || work.getCategory().equals(input))
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

    public void addWorkToCategory(String name,String typeName,String workName){
        for (CategoryWork categoryWork : categoryArrayList)
        {
            if(categoryWork.getNameC().equals(name)){
                switch (typeName) {
                    case "GeneralWork":
                        categoryWork.addCountGeneral();
                        categoryWork.addToTA(workName);
                        break;
                    case "WeeklyWork":
                        categoryWork.addCountWeekly();
                        categoryWork.addToTA(workName);
                        break;
                    case "ForwardWork":
                        categoryWork.addCountForward();
                        categoryWork.addToTA(workName);
                        break;
                    case "ProjectWork":
                        categoryWork.addCountProject();
                        categoryWork.addToTA(workName);
                        break;
                }
            }
        }
    }

//    public void addNameWorkToCategory(String name){
//        for (CategoryWork categoryWork : categoryArrayList){
//                categoryWork.addNameWork(name);
//            }
//        }

//    public boolean checkWorkName(String name,String typeName) {
//        switch (typeName) {
//            case "GeneralWork":
//                for (GeneralWork generalWork : generalWorkArrayList) {
//                    if (name.equals(generalWork.getName())) {
//                        return false;
//                    }
//                }
//                break;
//
//            case "ForwardWork":
//                for (ForwardWork forwardWork : forwardWorksArrayList) {
//                    if (name.equals(forwardWork.getName())) {
//                        return false;
//                    }
//                }
//                break;
//
//            case "WeeklyWork":
//                for (WeeklyWork weeklyWork : weeklyWorkArrayList) {
//                    if (name.equals(weeklyWork.getName())) {
//                        return false;
//                    }
//                }
//                break;
//
//            case "ProjectWork":
//                for (ProjectWork projectWork : projectWorkArrayList) {
//                    if (name.equals(projectWork.getName())) {
//                        return false;
//                    }
//                }
//                break;
//
//        }return true;
//    }

    public boolean checkWorkName(String name,String typeName) {
        switch (typeName) {
            case "GeneralWork":
                for (GeneralWork generalWork : generalWorkArrayList) {
                    if (name.equals(generalWork.getName())) {
                        return false;
                    }
                }
                break;

            case "ForwardWork":
                for (ForwardWork forwardWork : forwardWorksArrayList) {
                    if (name.equals(forwardWork.getName())) {
                        return false;
                    }
                }
                break;

            case "WeeklyWork":
                for (WeeklyWork weeklyWork : weeklyWorkArrayList) {
                    if (name.equals(weeklyWork.getName())) {
                        return false;
                    }
                }
                break;

            case "ProjectWork":
                for (ProjectWork projectWork : projectWorkArrayList) {
                    if (name.equals(projectWork.getName())) {
                        return false;
                    }
                }
                break;

        }
        return true;
    }



    public ArrayList<GeneralWork> getGeneralWorkArrayList() {
        return generalWorkArrayList;
    }
    public ArrayList<ForwardWork> getForwardWorksArrayList() { return forwardWorksArrayList;}
    public ArrayList<ProjectWork> getProjectWorkArrayList() { return projectWorkArrayList;}
    public ArrayList<WeeklyWork>  getWeeklyWorkArrayList() { return weeklyWorkArrayList;}
    public ArrayList<CategoryWork> getCategoryArrayList() {return categoryArrayList;}

 }
