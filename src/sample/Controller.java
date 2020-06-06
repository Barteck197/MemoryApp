package sample;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class Controller {
    static int seconds = 0;
    static int minutes = 0;
    static int hours = 0;

    static boolean state = true;

    @FXML
    Label appTime;

    @FXML
    private void startTime() {
        state = true;
        Thread t = new Thread(() -> {
            for (; ; ) {
                if (state) {
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
                } else {
                    break;
                }
            }
        });
        t.start();
    }


}
