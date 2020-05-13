package Snowman;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        ChooseMenu chooseSceneMenu = new ChooseMenu();
        chooseSceneMenu.StartScene(primaryStage);

    }


    public static void main(String[] args) {
        launch(args);
    }
}
