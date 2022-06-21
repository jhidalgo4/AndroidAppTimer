package edu.utep.cs.cs4381.timer;
// Joaquin Hidalgo - UTEP

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private TimerModel tm;

    private TextView timeDisplay;
    private Button startButton;
    private Button stopButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); //Hey activity, here's the UI. Look at this notation*

        tm = new TimerModel();
        timeDisplay = findViewById(R.id.timeDisplay); // How does this connect/do?
        startButton = findViewById(R.id.startButton);
        stopButton = findViewById(R.id.stopButton);
       // startButton.setOnClickListener(this::startClicked); //could do this instead of insterting in activity_main
    }

    public void startClicked(View view) {
        tm.start();
        new Thread(() -> {
            while (tm.isRunning()) {
                this.runOnUiThread(this::displayTime);
                //runOnUiThread(() -> displayTime());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {}
            }
        }).start();

        //timerDisplay.setText("0:00:00");
        Toast.makeText(this, "Start tapped!", Toast.LENGTH_SHORT).show();
        startButton.setEnabled(false);
        stopButton.setEnabled(true);
    }

    public void stopClicked(View view) {
        displayTime();
        tm.stop();
        Toast.makeText(this, "Stopped!", Toast.LENGTH_SHORT).show();
        startButton.setEnabled(true);
        stopButton.setEnabled(false);
    }

    private void displayTime() {
        long sec = tm.elapsedTime() / 1000;
        long min = sec / 60; sec %= 60;
        long hour = min / 60; min %= 60;
        timeDisplay.setText(String.format("%d:%02d:%02d", hour, min, sec));
    }
}