package android.unipu.kvizalica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LevelZavrsenActivity extends AppCompatActivity {
    TextView ukupanBrojPitanja, tocniOdgovori, netocniOdgovori;
    Button sljedeciLevel, pocetniIzbornik, igrajPonovno;
    int brojTocnihOdgovora, brojNetocnihOdgovora, brPitanja;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_zavrsen);

        //dohvacas varijable brtocnih i br netocnih iz voce pitanja level1
        brojTocnihOdgovora=getIntent().getIntExtra("brojTocnihOdgovora",0);
        brojNetocnihOdgovora=getIntent().getIntExtra("brojNetocnihOdgovora",0);
        brPitanja=getIntent().getIntExtra("brpitanja",0);
        Log.i("ukupan br tocnih", String.valueOf(brojTocnihOdgovora));
        Log.i("ukupan br netocnih", String.valueOf(brojNetocnihOdgovora));

        tocniOdgovori=findViewById(R.id.text_tocniOdgovori);
        netocniOdgovori=findViewById(R.id.text_netocniOdgovori);
        ukupanBrojPitanja=findViewById(R.id.text_ukupanBrojPitanja);
        //postavljanje u text view dohvacenje varijable
    tocniOdgovori.setText("Tocni odgovori: " + brojTocnihOdgovora);
    netocniOdgovori.setText("Netocni odgovori: " + brojNetocnihOdgovora);
    ukupanBrojPitanja.setText("Ukupan broj pitanja: "+ brPitanja);

        // button sljedeci level
        sljedeciLevel = findViewById(R.id.button_sljedeciLevel);

        //button pocetni izbornik
        pocetniIzbornik = findViewById(R.id.button_pocetniIzbornik);

        //button igraj ponovno
        igrajPonovno = findViewById(R.id.button_igrajPonovno);

        //dodavanje listenera za gumb pocetni izbornik
        pocetniIzbornik.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pozivanje aktivnosti Choose Category
                Intent intent = new Intent(LevelZavrsenActivity.this, ChooseCategoryActivity.class);
                startActivity(intent);
            }
        });

        //dodavanje listenera na gumb igraj ponovno
        igrajPonovno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //pozivanje aktivnosti VocePitanjaLevel1Activity
                Intent intent = new Intent(LevelZavrsenActivity.this, VocePitanjaLevel1Activity.class);
                startActivity(intent);
            }
        });
    }
}