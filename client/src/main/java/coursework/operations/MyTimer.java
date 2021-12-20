package coursework.operations;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Label;
import javafx.util.Duration;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class MyTimer {

    private void initClock(Label label) {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy\nHH:mm:ss");
            label.setText(LocalDateTime.now().format(formatter));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    };

    private long count = 0;

    private void initTimer(Label label) {
        Timeline clock = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            label.setText(String.valueOf(LocalTime.MIDNIGHT.plusSeconds(count++)));
        }), new KeyFrame(Duration.seconds(1)));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    public MyTimer (long count, Label dateTime, Label timer) {

        this.count = count;
        initClock(dateTime);
        initTimer(timer);
    }
}
