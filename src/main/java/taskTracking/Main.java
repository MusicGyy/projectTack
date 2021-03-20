package taskTracking;

import com.github.saacsos.FXRouter;
import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXRouter.bind(this, primaryStage, "TaskTrackingApp", 1280, 1024);

        configRoute();

        FXRouter.goTo("home");
    }
    private static void configRoute() {
        FXRouter.when("home", "home.fxml");
        FXRouter.when("ImageFee", "imageFee.fxml");
        FXRouter.when("workCategoryHome", "workCategoryHome.fxml");
        FXRouter.when("GeneralWork", "generalWork.fxml");
        FXRouter.when("updateGeneralWorks", "updateGeneralWorks.fxml");
        FXRouter.when("howTo", "howTo.fxml");
        FXRouter.when("forwardWork", "forwardWork.fxml");
        FXRouter.when("updateForwardWorks", "updateForwardWorks.fxml");
        FXRouter.when("weeklyWork", "weeklyWork.fxml");
        FXRouter.when("updateWeeklyWorks", "updateWeeklyWorks.fxml");
        FXRouter.when("projectWork", "projectWork.fxml");
        FXRouter.when("updateProjectWorks", "updateProjectWorks.fxml");
        FXRouter.when("worksAll", "worksAll.fxml");
        FXRouter.when("createCategory", "createCategory.fxml");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
