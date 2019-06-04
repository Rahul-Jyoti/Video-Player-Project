package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        VideoPlayer videoPlayer = new VideoPlayer("file:///home/rj/Documents/Movies/World.War.Z.2013.720p.BluRay.x264.YIF_SITEMOVIEY.mp4");
        Scene scene = new Scene(videoPlayer,1280,600, Color.BLACK);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
