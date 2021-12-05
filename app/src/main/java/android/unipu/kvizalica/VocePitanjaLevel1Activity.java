package android.unipu.kvizalica;

import static android.unipu.kvizalica.VoceLeveliActivity.listapitanja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class VocePitanjaLevel1Activity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    int timerValue = 20;
    ProgressBar progressBar;
    List<ModelClass> PitanjaLista;
    ModelClass modelclass;
    int index=0;
    TextView pitanje,btn_odgovor1,btn_odgovor2, btn_odgovor3,btn_odgovor4;
    CardView cardpitanje,cardodg1,cardodg2, cardodg3,cardodg4;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voce_pitanja_level1);

        Hooks();
        

        PitanjaLista=listapitanja;
        Collections.shuffle(PitanjaLista);
        modelclass=listapitanja.get(index);

        setAllData();

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
                dialog.setCancelable(false); // da ako se klikne van njega da ne nestane
                dialog.show();

                //Log.i("Pokazao sam se","Pokazao sam se");
            }
        }.start();
    }

    private void setAllData() {
        pitanje.setText(modelclass.getQuestion());
        btn_odgovor1.setText(modelclass.getOdgA());
        btn_odgovor2.setText(modelclass.getOdgB());
        btn_odgovor3.setText(modelclass.getOdgC());
        btn_odgovor4.setText(modelclass.getOdgD());



    }

    private void Hooks() {
        progressBar = findViewById(R.id.progress_bar);
        pitanje=findViewById(R.id.pitanje);
        btn_odgovor1=findViewById(R.id.btn_odgovor1);
        btn_odgovor2=findViewById(R.id.btn_odgovor2);
        btn_odgovor3=findViewById(R.id.btn_odgovor3);
        btn_odgovor4=findViewById(R.id.btn_odgovor4);


        cardpitanje=findViewById(R.id.cardpitanje);
        cardodg1=findViewById(R.id.cardodg1);
        cardodg2=findViewById(R.id.cardodg2);
        cardodg3=findViewById(R.id.cardodg3);
        cardodg4=findViewById(R.id.cardodg4);
    }
}