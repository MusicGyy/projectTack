package taskTracking.services;

import taskTracking.model.WorksCategory.GeneralWork;

import java.util.ArrayList;

public class DataList {
    private GeneralWork currentWork;
    private ArrayList<GeneralWork> generalWorkArrayList;
    private ArrayList<GeneralWork> allList;

    public DataList(){
        this.currentWork = new GeneralWork();
        this.generalWorkArrayList = new ArrayList<>();
    }

    public void addWork(GeneralWork generalWork) {
        this.generalWorkArrayList.add(generalWork);
    }

    public ArrayList<GeneralWork> getGeneralWorkArrayList() {
        return generalWorkArrayList;
    }
}