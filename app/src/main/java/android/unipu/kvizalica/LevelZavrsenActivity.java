package android.unipu.kvizalica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

//level je zavrsen
public class LevelZavrsenActivity extends AppCompatActivity {

    private static  String FILE_NAME;

    TextView ukupanBrojPitanja, tocniOdgovori, netocniOdgovori, levelZavrsen;
    Button pocetniIzbornik, igrajPonovno;
    int brojTocnihOdgovora, brojNetocnihOdgovora, brPitanja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_zavrsen);

        final String getTopic = getIntent().getStringExtra("getSelectedTopicName");
        //Log.i("LevelZavrsenActivitykat", getTopic);
        FILE_NAME = getTopic;


        // dohvacanje varijable brtocnih i brnetocnih iz aktivnosti VocePitanjaLevel1Activity
        brojTocnihOdgovora = getIntent().getIntExtra("brojTocnihOdgovora",0);
        brojNetocnihOdgovora = getIntent().getIntExtra("brojNetocnihOdgovora",0);
        brPitanja = getIntent().getIntExtra("brpitanja",0);
        //Log.i("ukupan br tocnih", String.valueOf(brojTocnihOdgovora));
        //Log.i("ukupan br netocnih", String.valueOf(brojNetocnihOdgovora));

        tocniOdgovori = findViewById(R.id.text_tocniOdgovori);
        netocniOdgovori = findViewById(R.id.text_netocniOdgovori);
        ukupanBrojPitanja = findViewById(R.id.text_ukupanBrojPitanja);
        levelZavrsen = findViewById(R.id.textLevel1Zavrsen);

        //postavljanje u text view dohvacenih varijabli
        tocniOdgovori.setText("Točni odgovori: " + brojTocnihOdgovora);
        netocniOdgovori.setText("Netočni odgovori: " + brojNetocnihOdgovora);
        ukupanBrojPitanja.setText("Ukupan broj pitanja: "+ brPitanja);
        levelZavrsen.setText(getTopic);

        // provjera ako igrač ima pola točnih odgovora da mu se omogući
        // igranje sljedećeg levela
        if(brojTocnihOdgovora >= brPitanja/2){
            save();  // poziva se funkcija za kreiranje datoteke
            if(getTopic.equals("Voće-level3") || getTopic.equals("Povrće-level3") || getTopic.equals("Brojevi-level3") || getTopic.equals("Životinje-level3")) {
                Toast.makeText(this, "Uspješno su otključani svi leveli u ovoj kategoriji!", Toast.LENGTH_LONG).show();
            }
            else {
                Toast.makeText(this, "Novi level je otključan!", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText(this, "Odigraj ponovno za otključavanje levela!", Toast.LENGTH_LONG).show();
        }

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
    // metoda za spremanje stringa u datoteku
    private void save() {
        String text = "otkljucano";
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos != null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


}