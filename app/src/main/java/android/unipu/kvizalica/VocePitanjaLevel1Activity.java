package android.unipu.kvizalica;

import static android.unipu.kvizalica.VoceLeveliActivity.listapitanja;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Collections;
import java.util.List;

public class VocePitanjaLevel1Activity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    int timerValue = 25;
    ProgressBar progressBar;
    List<ModelClass> PitanjaLista;
    ModelClass modelclass;
    int index = 0; //služi kao pozicija pitanja
    TextView pitanje,btn_odgovor1,btn_odgovor2, btn_odgovor3,btn_odgovor4;
    CardView cardpitanje,cardodg1,cardodg2, cardodg3,cardodg4;

    // varijable za brojanje točno i netočno odgovorenih pitanja
    int brojTocnihOdgovora = 0;
    int brojNetocnihOdgovora = 0;

    //Play sound prvo objekt, flag
    private PlayAudio playAudio;
    int FLAG=0;

    Button btn_sljedecePitanje;

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voce_pitanja_level1);
        playAudio= new PlayAudio(this);
        Hooks();
        

        PitanjaLista = listapitanja;
        Collections.shuffle(PitanjaLista);
        modelclass = listapitanja.get(index);

        cardodg1.setBackgroundColor(getResources().getColor(R.color.bijela));
        cardodg2.setBackgroundColor(getResources().getColor(R.color.bijela));
        cardodg3.setBackgroundColor(getResources().getColor(R.color.bijela));
        cardodg4.setBackgroundColor(getResources().getColor(R.color.bijela));

        btn_sljedecePitanje.setClickable(false);

        setAllData();

        setResetTimer();

    }

    private void setResetTimer() {
        countDownTimer = new CountDownTimer(25000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerValue = timerValue - 1;
                progressBar.setProgress(timerValue);

                // ako je preostalo zadnjih 10 sekundi promijeni boju progress bara u crvenu
                if(millisUntilFinished < 10000) {
                    progressBar.getProgressDrawable().setColorFilter(
                            Color.RED, PorterDuff.Mode.SRC_IN);
                }

            }
            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(VocePitanjaLevel1Activity.this);
                dialog.setContentView(R.layout.dialog_vrijeme_isteklo);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button ok = dialog.findViewById(R.id.button_vrijemeIsteklo);
                dialog.setCancelable(false); // da ako se klikne van njega da ne nestane
                dialog.show();
                FLAG=3;
                playAudio.setAudioforAnswer(FLAG);
                // klikom na gumb OK vraćamo se na ChooseCategory Activity
                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(VocePitanjaLevel1Activity.this, ChooseCategoryActivity.class);
                        startActivity(intent);
                    }
                });
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
        pitanje = findViewById(R.id.pitanje);
        btn_odgovor1 = findViewById(R.id.btn_odgovor1);
        btn_odgovor2 = findViewById(R.id.btn_odgovor2);
        btn_odgovor3 = findViewById(R.id.btn_odgovor3);
        btn_odgovor4 = findViewById(R.id.btn_odgovor4);


        cardpitanje = findViewById(R.id.cardpitanje);
        cardodg1 = findViewById(R.id.cardodg1);
        cardodg2 = findViewById(R.id.cardodg2);
        cardodg3 = findViewById(R.id.cardodg3);
        cardodg4 = findViewById(R.id.cardodg4);

        btn_sljedecePitanje = findViewById(R.id.button_sljedece_pitanje);
    }

    public void TocanOdgovor(CardView cardview) {
        cardview.setBackgroundColor(getResources().getColor(R.color.tocan_odgovor_zelena));
        FLAG=1;
        playAudio.setAudioforAnswer(FLAG);
        countDownTimer.cancel(); // kad se kikne na odgovor timer se stopira
        btn_sljedecePitanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brojTocnihOdgovora++;
                if(index < listapitanja.size() -1) {
                index++;
                modelclass = listapitanja.get(index);
                resetColor();
                setAllData();
                EnableButton();
                setResetTimer();
            } else {                // ako dode do kraja polja s pitanjima pozvat ce aktivnost LevelZavrsenActivity
                LevelZavrsen();
            }
            }
        });
    }

    public void NetocanOdgovor(CardView cardodg1) {
        cardodg1.setBackgroundColor(getResources().getColor(R.color.netocan_odgovor_crvena));
        FLAG=2;
        playAudio.setAudioforAnswer(FLAG);
        countDownTimer.cancel(); // kad se kikne na odgovor timer se stopira
        btn_sljedecePitanje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                brojNetocnihOdgovora++;
                if(index < listapitanja.size() -1) {
                    index++;
                    modelclass = listapitanja.get(index);
                    resetColor();
                    setAllData();
                    EnableButton();
                    setResetTimer();
                } else {                // ako dode do kraja polja s pitanjima pozvat ce aktivnost LevelZavrsenActivity
                    LevelZavrsen();
                }
            }
        });
    }
    // metoda koja poziva novu aktivnost
    private void LevelZavrsen() {
        Intent intent = new Intent(VocePitanjaLevel1Activity.this, LevelZavrsenActivity.class);
        startActivity(intent);
    }
    // metoda da se omogući klikanje na ponuđene odgovore
    public void EnableButton() {
        cardodg1.setClickable(true);
        cardodg2.setClickable(true);
        cardodg3.setClickable(true);
        cardodg4.setClickable(true);
    }
    // metoda da se onemogući klikanje na ponuđene odgovore
    public void DisableButton() {
        cardodg1.setClickable(false);
        cardodg2.setClickable(false);
        cardodg3.setClickable(false);
        cardodg4.setClickable(false);
    }
    // metoda za resetiranje boje na ponuđenim odgovorima
    public void resetColor() {
        cardodg1.setBackgroundColor(getResources().getColor(R.color.bijela));
        cardodg2.setBackgroundColor(getResources().getColor(R.color.bijela));
        cardodg3.setBackgroundColor(getResources().getColor(R.color.bijela));
        cardodg4.setBackgroundColor(getResources().getColor(R.color.bijela));
    }
    public void Odgovor1Click(View view) {
        DisableButton();
        btn_sljedecePitanje.setClickable(true);
        if(modelclass.getOdgA().equals(modelclass.getTocan())) {
            cardodg1.setCardBackgroundColor(getResources().getColor(R.color.tocan_odgovor_zelena));
            if(index < listapitanja.size() -1) {
                TocanOdgovor(cardodg1);
            } else {
                LevelZavrsen();
            }
        } else {
            NetocanOdgovor(cardodg1);
        }
    }

    public void Odgovor2Click(View view) {
        DisableButton();
        btn_sljedecePitanje.setClickable(true);
        if(modelclass.getOdgB().equals(modelclass.getTocan())) {
            cardodg2.setCardBackgroundColor(getResources().getColor(R.color.tocan_odgovor_zelena));
            if(index < listapitanja.size() -1) {
                TocanOdgovor(cardodg2);
            } else {
                LevelZavrsen();
            }
        } else {
            NetocanOdgovor(cardodg2);
        }
    }

    public void Odgovor3Click(View view) {
        DisableButton();
        btn_sljedecePitanje.setClickable(true);
        if(modelclass.getOdgC().equals(modelclass.getTocan())) {
            cardodg3.setCardBackgroundColor(getResources().getColor(R.color.tocan_odgovor_zelena));
            if(index < listapitanja.size() -1) {
                TocanOdgovor(cardodg3);
            } else {
                LevelZavrsen();
            }
        } else {
            NetocanOdgovor(cardodg3);
        }
    }

    public void Odgovor4Click(View view) {
        DisableButton();
        btn_sljedecePitanje.setClickable(true);
        if(modelclass.getOdgD().equals(modelclass.getTocan())) {
            cardodg4.setCardBackgroundColor(getResources().getColor(R.color.tocan_odgovor_zelena));
            if(index < listapitanja.size() -1) {
                TocanOdgovor(cardodg4);
            } else {
                LevelZavrsen();
            }
        } else {
            NetocanOdgovor(cardodg4);
        }
    }
}