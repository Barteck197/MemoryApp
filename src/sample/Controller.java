package sample;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {

    static boolean state = true;


    @FXML
    Label appTime;

    TimeFlow timeFlow = new TimeFlow(appTime);

    @FXML
    private void startTime() {
        state = true;

        Platform.runLater(timeFlow);

       /*Thread t = new Thread() {
            @Override
            public void run() {
                while (state) {
                    try {
                        Thread.sleep(1000);

                        if (seconds >= 60) {
                            seconds = 0;
                            minutes++;
                        }
                        if (minutes >= 60) {
                            seconds = 0;
                            minutes = 0;
                            hours++;
                        }

                        seconds++;

                        appTime.setText(minutes + " : " + seconds);
                        System.out.println(hours + " : " + minutes + " : " + seconds);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t.start();*/
    }
}