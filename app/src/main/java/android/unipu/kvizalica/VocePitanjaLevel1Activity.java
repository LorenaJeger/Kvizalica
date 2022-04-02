package android.unipu.kvizalica;


import static android.unipu.kvizalica.PovezivanjeFirebase.listapitanja;

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
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.Collections;
import java.util.List;

public class VocePitanjaLevel1Activity extends AppCompatActivity {
    CountDownTimer countDownTimer;
    int timerValue = 20;
    ProgressBar progressBar;
    List<ModelClass> PitanjaLista;
    int brpitanja;
    int index_pitanja = 1;
    ModelClass modelclass;
    int index = 0; //služi kao pozicija pitanja
    TextView pitanje,btn_odgovor1,btn_odgovor2, btn_odgovor3,btn_odgovor4, naslov_pitanje;
    CardView cardpitanje,cardodg1,cardodg2, cardodg3,cardodg4;

    String slika;
    ImageView slika_view;



    // varijable za brojanje točno i netočno odgovorenih pitanja
    int brojTocnihOdgovora = 0;
    int brojNetocnihOdgovora = 0;

    //Play sound prvo objekt, flag
    private PlayAudio playAudio;
    int FLAG = 0;
    Button btn_sljedecePitanje;
    private Object getSelected_TopicName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voce_pitanja_level1);

        getSelected_TopicName = getIntent().getStringExtra("getselectedLevel");

        playAudio = new PlayAudio(this);
        Hooks();


        PitanjaLista = listapitanja;
        Collections.shuffle(PitanjaLista);
        modelclass = listapitanja.get(index);

        brpitanja = listapitanja.size();

        cardodg1.setBackgroundColor(getResources().getColor(R.color.bijela));
        cardodg2.setBackgroundColor(getResources().getColor(R.color.bijela));
        cardodg3.setBackgroundColor(getResources().getColor(R.color.bijela));
        cardodg4.setBackgroundColor(getResources().getColor(R.color.bijela));

        btn_sljedecePitanje.setClickable(false);

        setAllData();

        setResetTimer();

    }
    // metoda za odbrojavanje vremena
    private void setResetTimer() {
        countDownTimer = new CountDownTimer(20000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerValue = timerValue - 1;
                progressBar.setProgress(timerValue);
                // ako je ostalo manje od 10s ProgressBar mijenja boju u crvenu
                if(millisUntilFinished < 10000) {
                    progressBar.getProgressDrawable().setColorFilter(
                            Color.RED, PorterDuff.Mode.SRC_IN);
                }
                else{
                    progressBar.getProgressDrawable().setColorFilter(
                            Color.YELLOW, PorterDuff.Mode.SRC_IN);
                }
            }
            // ako je vrijeme isteklo
            @Override
            public void onFinish() {
                Dialog dialog = new Dialog(VocePitanjaLevel1Activity.this);
                dialog.setContentView(R.layout.dialog_vrijeme_isteklo);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                Button ok = dialog.findViewById(R.id.button_vrijemeIsteklo);
                dialog.setCancelable(false); // da ako se klikne van njega da ne nestane
                dialog.show();
                FLAG = 3;
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
    // metoda koja puni textView-ove podacima iz baze
    private void setAllData() {
        pitanje.setText(modelclass.getQuestion());
        btn_odgovor1.setText(modelclass.getOdgA());
        btn_odgovor2.setText(modelclass.getOdgB());
        btn_odgovor3.setText(modelclass.getOdgC());
        btn_odgovor4.setText(modelclass.getOdgD());
        slika = modelclass.getSlika();
        //sluzi za prikaz slike iz baze pomoću url-a
        Glide.with(this).load(slika).into(slika_view);

        naslov_pitanje.setText("Broj pitanja: " + index_pitanja + "/"+ brpitanja);
    }

    // metoda za pronalazk svih view-ova po id-u
    private void Hooks() {
        naslov_pitanje = findViewById(R.id.naslov_pitanje);
        progressBar = findViewById(R.id.progress_bar);
        pitanje = findViewById(R.id.pitanje);
        btn_odgovor1 = findViewById(R.id.btn_odgovor1);
        btn_odgovor2 = findViewById(R.id.btn_odgovor2);
        btn_odgovor3 = findViewById(R.id.btn_odgovor3);
        btn_odgovor4 = findViewById(R.id.btn_odgovor4);
        slika_view = findViewById(R.id.imageView);

        cardpitanje = findViewById(R.id.cardpitanje);
        cardodg1 = findViewById(R.id.cardodg1);
        cardodg2 = findViewById(R.id.cardodg2);
        cardodg3 = findViewById(R.id.cardodg3);
        cardodg4 = findViewById(R.id.cardodg4);

        btn_sljedecePitanje = findViewById(R.id.button_sljedece_pitanje);
    }
    // funkcija koja reagira na klik na točan odgovor
    public void TocanOdgovor(CardView cardodg1) {
        cardodg1.setBackgroundColor(getResources().getColor(R.color.tocan_odgovor_zelena));

        FLAG = 1;
        playAudio.setAudioforAnswer(FLAG);
        countDownTimer.cancel(); // kad se kikne na odgovor timer se stopira
        brojTocnihOdgovora++;

        if(index == listapitanja.size() - 1) {
            LevelZavrsen();
        } else {
            btn_sljedecePitanje.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    index++;
                    index_pitanja++;
                    modelclass = listapitanja.get(index);
                    resetColor();
                    setAllData();
                    EnableButton();
                    timerValue = 20;
                    setResetTimer();
                }
            });
        }
    }
    // funkcija koja reagira na klik na netočan odgovor
    public void NetocanOdgovor(CardView cardodg1) {
        cardodg1.setBackgroundColor(getResources().getColor(R.color.netocan_odgovor_crvena));
        FLAG = 2;
        playAudio.setAudioforAnswer(FLAG);
        countDownTimer.cancel(); // kad se kikne na odgovor timer se stopira
        brojNetocnihOdgovora++;
        if(index == listapitanja.size() - 1) {
            LevelZavrsen();
        } else {
            btn_sljedecePitanje.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    index++;
                    index_pitanja++;
                    modelclass = listapitanja.get(index);
                    resetColor();
                    setAllData();
                    EnableButton();
                    timerValue = 20;
                    setResetTimer();
                }
            });
        }
    }
    // metoda koja poziva novu aktivnost
    private void LevelZavrsen() {
        Intent intent = new Intent(VocePitanjaLevel1Activity.this, LevelZavrsenActivity.class);
        //šalju se 4 varijable pomocu intent put extra u novu aktivnost LevelZavrsen
        intent.putExtra("brojTocnihOdgovora", brojTocnihOdgovora);
        intent.putExtra("brojNetocnihOdgovora", brojNetocnihOdgovora);
        intent.putExtra("brpitanja", brpitanja);
        intent.putExtra("getSelectedTopicName", String.valueOf(getSelected_TopicName));
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

    // funkcija za klik na prvi ponuđeni odgovor
    public void Odgovor1Click(View view) {
        DisableButton();
        btn_sljedecePitanje.setClickable(true);
        if(modelclass.getOdgA().equals(modelclass.getTocan())) {
            cardodg1.setCardBackgroundColor(getResources().getColor(R.color.tocan_odgovor_zelena));
            TocanOdgovor(cardodg1);
        } else {
            NetocanOdgovor(cardodg1);
        }

    }
    // funkcija za klik na drugi ponuđeni odgovor
    public void Odgovor2Click(View view) {
        DisableButton();
        btn_sljedecePitanje.setClickable(true);
        if(modelclass.getOdgB().equals(modelclass.getTocan())) {
            cardodg2.setCardBackgroundColor(getResources().getColor(R.color.tocan_odgovor_zelena));
            TocanOdgovor(cardodg2);
        } else {
            NetocanOdgovor(cardodg2);
        }
    }
    // funkcija za klik na treći ponuđeni odgovor
    public void Odgovor3Click(View view) {
        DisableButton();
        btn_sljedecePitanje.setClickable(true);
        if(modelclass.getOdgC().equals(modelclass.getTocan())) {
            cardodg3.setCardBackgroundColor(getResources().getColor(R.color.tocan_odgovor_zelena));
            TocanOdgovor(cardodg3);
        } else {
            NetocanOdgovor(cardodg3);
        }
    }
    // funkcija za klik na četvrti ponuđeni odgovor
    public void Odgovor4Click(View view) {
        DisableButton();
        btn_sljedecePitanje.setClickable(true);
        if(modelclass.getOdgD().equals(modelclass.getTocan())) {
            cardodg4.setCardBackgroundColor(getResources().getColor(R.color.tocan_odgovor_zelena));
            TocanOdgovor(cardodg4);
        } else {
            NetocanOdgovor(cardodg4);
        }
    }
}