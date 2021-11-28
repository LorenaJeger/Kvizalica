package android.unipu.kvizalica;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

public class VocePitanjaLevel1Activity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    int timerValue = 20;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voce_pitanja_level1);

        progressBar = findViewById(R.id.progress_bar);
        countDownTimer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerValue = timerValue-1;
                progressBar.setProgress(timerValue);
            }

            @Override
            public void onFinish() {
                //Log.i("1","Zavrsio sam");
                Dialog dialog = new Dialog(VocePitanjaLevel1Activity.this);
                dialog.setContentView(R.layout.time_out);
                //Log.i("2","Trebam se pokazati");

                dialog.show();
                //Log.i("Pokazao sam se","Pokazao sam se");
            }
        }.start();
    }
}