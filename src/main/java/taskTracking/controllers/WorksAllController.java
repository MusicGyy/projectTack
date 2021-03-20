package taskTracking.controllers;

import com.github.saacsos.FXRouter;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import taskTracking.model.WorksCategory.*;
import taskTracking.services.*;

import java.io.IOException;
import java.util.ArrayList;

public class WorksAllController {
    private DataList dataList1,dataList2,dataList3,dataList4,dataList5;
    private DataSource dataSource1,dataSource2,dataSource3,dataSource4,dataSource5;
    private ObservableList<GeneralWork> generalWorkObservableList;
    private ObservableList<WeeklyWork> weeklyWorkObservableList;
    private ObservableList<ForwardWork> forwardWorkObservableList;
    private ObservableList<ProjectWork> projectWorkObservableList;
    private ObservableList<CategoryWork> categoryWorkObservableList;

    @FXML
    TableView<ForwardWork> showTableF;
    @FXML
    TableView<GeneralWork> showTable;
    @FXML
    TableView<WeeklyWork> showTableW;
    @FXML
    TableView<ProjectWork> showTableP;
    @FXML
    TableView<CategoryWork> showTableC;
    @FXML
    TextField searchText;

    @FXML
    public void initialize() {
        Platform.runLater((Runnable)new Runnable() {
            @Override
            public void run() {
                dataSource1 = new GeneralWorkFileDataSource("data", "generalWorks.csv");
                dataSource2 = new ForwardWorkFileDataSource("data", "forwardWorks.csv");
                dataSource3 = new WeeklyWorkFileDataSource("data", "weeklyWorks.csv");
                dataSource4 = new ProjectWorkFileDataSource("data", "projectWorks.csv");
                dataSource5 = new CategoryFileDataSource("data","category.csv");
                dataList1 = dataSource1.getData();
                dataList2 = dataSource2.getData();
                dataList3 = dataSource3.getData();
                dataList4 = dataSource4.getData();
                dataList5 = dataSource5.getData();
                showForwardWorks("");
                showGeneralWorks("");
                showWeeklyWorks("");
                showProjectWorks("");
                showCategoryAll();
            }
        });
    }

    private void showForwardWorks(String searchText) {
        showTableF.getColumns().clear();
        if (searchText.equals(""))
            forwardWorkObservableList = FXCollections.observableArrayList(dataList2.getForwardWorksArrayList());
        else
            forwardWorkObservableList = FXCollections.observableArrayList(dataList2.searchForward(searchText));
        showTableF.setItems(forwardWorkObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Category Name", "field:category"));
        configs.add(new StringConfiguration("title:Work Name", "field:name"));
        configs.add(new StringConfiguration("title:Name of person in charge", "field:responsiblePerson"));
        configs.add(new StringConfiguration("title:Assigned Date", "field:assignedDate"));
        configs.add(new StringConfiguration("title:Assigned Time", "field:assignedTime"));
        configs.add(new StringConfiguration("title:Priority", "field:priority"));
        configs.add(new StringConfiguration("title:Status", "field:status"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            showTableF.getColumns().add(col);
            if (conf.get("title").equals("Priority")) {
                showTableF.getSortOrder().addAll(col);
                col.setSortType(TableColumn.SortType.DESCENDING);
            }
        }
    }

    private void showGeneralWorks(String searchText) {
        showTable.getColumns().clear();
        if (searchText.equals(""))
            generalWorkObservableList = FXCollections.observableArrayList(dataList1.getGeneralWorkArrayList());
		else
            generalWorkObservableList = FXCollections.observableArrayList(dataList1.searchGeneral(searchText));

		showTable.setItems(generalWorkObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Category Name", "field:category"));
        configs.add(new StringConfiguration("title:Work Name", "field:name"));
        configs.add(new StringConfiguration("title:MadeDate", "field:madeDate"));
        configs.add(new StringConfiguration("title:Start Time", "field:startTime"));
        configs.add(new StringConfiguration("title:Finished Time", "field:lastDate"));
        configs.add(new StringConfiguration("title:Priority", "field:priority"));
        configs.add(new StringConfiguration("title:Status", "field:status"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            showTable.getColumns().add(col);
            if (conf.get("title").equals("Priority")) {
                showTable.getSortOrder().addAll(col);
                col.setSortType(TableColumn.SortType.DESCENDING);
            }
        }
    }

    private void showWeeklyWorks(String searchText) {
        showTableW.getColumns().clear();
        if (searchText.equals(""))
            weeklyWorkObservableList = FXCollections.observableArrayList(dataList3.getWeeklyWorkArrayList());
        else
            weeklyWorkObservableList = FXCollections.observableArrayList(dataList3.searchWeekly(searchText));
        showTableW.setItems(weeklyWorkObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Category Name", "field:category"));
        configs.add(new StringConfiguration("title:Work Name", "field:name"));
        configs.add(new StringConfiguration("title:Date", "field:madeDate"));
        configs.add(new StringConfiguration("title:Start Time", "field:startTime"));
        configs.add(new StringConfiguration("title:End Time", "field:lastDate"));
        configs.add(new StringConfiguration("title:Priority", "field:priority"));
        configs.add(new StringConfiguration("title:Status", "field:status"));
        configs.add(new StringConfiguration("title:Week Start", "field:weeklyDate"));

        for (StringConfiguration conf : configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            showTableW.getColumns().add(col);
            if (conf.get("title").equals("Priority")) {
                showTableW.getSortOrder().addAll(col);
                col.setSortType(TableColumn.SortType.DESCENDING);
            }
        }
    }

    private void showProjectWorks(String searchText) {
        showTableP.getColumns().clear();
        if (searchText.equals(""))
            projectWorkObservableList = FXCollections.observableArrayList(dataList4.getProjectWorkArrayList());
        else
            projectWorkObservableList = FXCollections.observableArrayList(dataList4.searchProject(searchText));
        showTableP.setItems(projectWorkObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Category Name", "field:category"));
        configs.add(new StringConfiguration("title:Work Name", "field:name"));
        configs.add(new StringConfiguration("title:Leader Name", "field:ProjectLeader"));
        configs.add(new StringConfiguration("title:Start Date", "field:madeDate"));
        configs.add(new StringConfiguration("title:Finished Time", "field:startTime"));
        configs.add(new StringConfiguration("title:Priority", "field:priority"));
        configs.add(new StringConfiguration("title:Status", "field:status"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            showTableP.getColumns().add(col);
            if (conf.get("title").equals("Priority")) {
                showTableP.getSortOrder().addAll(col);
                col.setSortType(TableColumn.SortType.DESCENDING);
            }
        }
    }

    private void showCategoryAll() {
        showTableC.getColumns().clear();
        categoryWorkObservableList = FXCollections.observableArrayList(dataList5.getCategoryArrayList());
        showTableC.setItems(categoryWorkObservableList);

        ArrayList<StringConfiguration> configs = new ArrayList<>();
        configs.add(new StringConfiguration("title:Category Name", "field:nameC"));
        configs.add(new StringConfiguration("title:Total number of tasks", "field:all"));
        configs.add(new StringConfiguration("title:Total number of General tasks", "field:countGeneral"));
        configs.add(new StringConfiguration("title:Total number of Weekly tasks", "field:countWeekly"));
        configs.add(new StringConfiguration("title:Total number of Forward tasks", "field:countForward"));
        configs.add(new StringConfiguration("title:Total number of Project tasks", "field:countProject"));

        for (StringConfiguration conf: configs) {
            TableColumn col = new TableColumn(conf.get("title"));
            col.setCellValueFactory(new PropertyValueFactory<>(conf.get("field")));
            showTableC.getColumns().add(col);
            if (conf.get("title").equals("Priority")) {
                showTableC.getSortOrder().addAll(col);
                col.setSortType(TableColumn.SortType.DESCENDING);
            }
        }
    }

    public void handleSearchButton(ActionEvent actionEvent){
        showForwardWorks(searchText.getText());
        showGeneralWorks(searchText.getText());
        showWeeklyWorks(searchText.getText());
        showProjectWorks(searchText.getText());
    }

    private void clearSearchInput(){
        searchText.clear();
    }

    public void handleClearButton(ActionEvent actionEvent){
        clearSearchInput();
        showForwardWorks("");
        showGeneralWorks("");
        showWeeklyWorks("");
        showProjectWorks("");
        showCategoryAll();

    }



    public void handleBackButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("home");
        }
        catch (IOException e) {
            System.err.println("ไปที่หน้า home ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleUpdateGeneralWorksControllerButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("updateGeneralWorks");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า updateGeneralWorks ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleUpdateForwardWorksControllerButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("updateForwardWorks");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า updateForwardWorks ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleUpdateProjectWorksControllerButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("updateProjectWorks");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า updateProjectWorks ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleUpdateWeeklyWorksControllerButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("updateWeeklyWorks");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า updateWeeklyWorks ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }

    public void handleCreateCategoryButton(ActionEvent actionEvent) {
        try {
            FXRouter.goTo("createCategory");
        } catch (IOException e) {
            System.err.println("ไปที่หน้า createCategory ไม่ได้");
            System.err.println("ให้ตรวจสอบการกำหนด route");
        }
    }
}
