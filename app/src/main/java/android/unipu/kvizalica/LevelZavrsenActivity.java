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
    EditText mEditText;

    TextView ukupanBrojPitanja, tocniOdgovori, netocniOdgovori;
    Button sljedeciLevel, pocetniIzbornik, igrajPonovno;
    int brojTocnihOdgovora, brojNetocnihOdgovora, brPitanja;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_level_zavrsen);

    final String getTopic = getIntent().getStringExtra("getSelectedTopicName");
    Log.i("LevelZavrsenActivitykat", getTopic);
  FILE_NAME=getTopic;
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
        if(brojTocnihOdgovora >= brPitanja/2){
            save();
            load();
        }
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

    private void save() {
        String text="otkljucano";
        FileOutputStream fos=null;
        try {
            fos= openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());

//            mEditText.getText().clear();
            Toast.makeText(this, "Saved to"+ getFilesDir() + "/"+ FILE_NAME, Toast.LENGTH_LONG).show();
        }catch (FileNotFoundException e){
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(fos!=null){
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void load(){
        FileInputStream fis=null;
        try{
            fis=openFileInput(FILE_NAME);
            InputStreamReader isr= new InputStreamReader(fis);
            BufferedReader br= new BufferedReader(isr);
            StringBuilder sb= new StringBuilder();
            String text;
            while((text=br.readLine()) !=null ){
                sb.append(text);
            }
            Log.i("citam", String.valueOf(sb));
        } catch(FileNotFoundException e){
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis !=null){
                try {
                    fis.close();
                }
                catch(IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
}