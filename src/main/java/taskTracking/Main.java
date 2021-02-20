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
        //Parent root = FXMLLoader.load(getClass().getResource("../../resources/home.fxml"));
        //primaryStage.setTitle("Hello World");
        //primaryStage.setScene(new Scene(root, 300, 275));
        //primaryStage.show();
    }
    private static void configRoute() {
        FXRouter.when("home", "home.fxml");
        FXRouter.when("ImageFee", "ImageFee.fxml");
        FXRouter.when("workCategoryHome", "workCategoryHome.fxml");
        FXRouter.when("GeneralWork", "GeneralWork.fxml");
        FXRouter.when("updateGeneralWorks", "updateGeneralWorks.fxml");
    }


    public static void main(String[] args) {
        launch(args);
    }
}
