package sample;

import javafx.application.Platform;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.media.MediaPlayer;

public class VideoBar extends HBox {

    Slider timeSlider = new Slider();
    Slider volSlider = new Slider();

    Button playButton = new Button("||");
    Label volume = new Label("Volume");

    MediaPlayer player;

    public VideoBar(MediaPlayer play) {
        player = play;

        //css styling thing

        setAlignment(Pos.CENTER);
        setPadding(new Insets(5, 10, 5, 10));

        volSlider.setPrefWidth(70.0);
        volSlider.setMinWidth(30.0);
        volSlider.setValue(100);

        HBox.setHgrow(timeSlider, Priority.ALWAYS);

        playButton.prefWidth(40.0);

        getChildren().add(playButton);
        getChildren().add(timeSlider);
        getChildren().add(volume);
        getChildren().add(volSlider);


        //Make a play pause button work

        playButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                MediaPlayer.Status status = player.getStatus();

                if(status == MediaPlayer.Status.PLAYING){
                    if(player.getCurrentTime().greaterThanOrEqualTo(player.getTotalDuration())){
                        player.seek(player.getStartTime());
                        player.play();
                    }
                    else {
                        player.pause();
                        playButton.setText(">");
                    }
                }
                if(status == MediaPlayer.Status.PAUSED || status == status.STOPPED){
                    player.play();
                    playButton.setText("||");
                }
            }
        });

        player.currentTimeProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                moveSlider();
            }
        });

        volSlider.valueProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                if(volSlider.isPressed()){
                    player.setVolume(volSlider.getValue()/100);
                }
            }
        });
    }

    private void moveSlider(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                timeSlider.setValue(player.getCurrentTime().toMinutes()/player.getTotalDuration().toMinutes());
            }
        });
    }




}





