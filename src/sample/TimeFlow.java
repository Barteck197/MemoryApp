package sample;

import javafx.scene.control.Label;

public class TimeFlow implements Runnable {

    static int seconds = 0;
    static int minutes = 0;
    static int hours = 0;

    static boolean state = true;

    String time;

    Label label;

    public TimeFlow(Label label){
        this.label = label;
    }

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

                time = minutes + " : " + hours;

                label.setText(minutes + " : " + seconds);
                System.out.println(hours + " : " + minutes + " : " + seconds);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
