package com.pratham.flipclocker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {
    TextToSpeech textToSpeech;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);

        WindowManager.LayoutParams layout = getWindow().getAttributes();
        layout.screenBrightness = 1F;
        getWindow().setAttributes(layout);


        getWindow().getDecorView().setSystemUiVisibility(
                android.view.View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | android.view.View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | android.view.View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
                        | android.view.View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        );


        TextView hourNumber = findViewById(R.id.hourNumber);
        TextView minuteNumber = findViewById(R.id.minutsNumber);
        TextView secondNumber = findViewById(R.id.secondsNumber);
        TextView ampm = findViewById(R.id.ampm);


        CardView card1 = findViewById(R.id.card1);
        CardView card2 = findViewById(R.id.card2);
        CardView card3 = findViewById(R.id.card3);

        int width = getResources().getDisplayMetrics().widthPixels;

        LinearLayout container = findViewById(R.id.container);

        int orientation = getResources().getConfiguration().orientation;
        if (orientation == 1) {
//            Toast.makeText(this, "Portrait", Toast.LENGTH_SHORT).show();
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        }
        if (orientation == 2) {
//            Toast.makeText(this, "Landscape", Toast.LENGTH_SHORT).show();
            container.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width / 3, ViewGroup.LayoutParams.MATCH_PARENT);
            card1.setLayoutParams(params);
            card2.setLayoutParams(params);
            card3.setLayoutParams(params);

        }

        String time = java.text.DateFormat.getTimeInstance().format(new java.util.Date());
        String[] timeArray = time.split(":");
        String hour = timeArray[0];
        String minute = timeArray[1];
        String second = timeArray[2].substring(0, 2);
        String ampmString = timeArray[2].substring(3, 5);
        new Thread(() -> {
            while (true) {
                try {
                    Thread.sleep(1000);
                    runOnUiThread(() -> {
                        String time1 = DateFormat.getTimeInstance().format(new Date());
                        String[] timeArray1 = time1.split(":");
                        String hour1 = timeArray1[0];
                        String minute1 = timeArray1[1];
                        String second1 = timeArray1[2].substring(0, 2);

                        hourNumber.setText(hour1);
                        minuteNumber.setText(minute1);
                        secondNumber.setText(second1);
                        ampm.setText(ampmString.toUpperCase());
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
