package android.unipu.kvizalica;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

//punimo listu pitanja
public class VoceLeveliActivity extends AppCompatActivity {
    Button buttonLevel1, buttonLevel2, buttonLevel3;
    TextView odabranaKategorija;

    private String selectedLevel = "";  // string pomoću kojeg ćemo povlačiti level koji je odabran
    private String level = "";
    private String skupa = "";
    private static  String FILE_NAME;
    private String sadrzajDatoteke;
    private String getSelectedTopicName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voce_leveli);


        // za povući vrstu kategorije iz aktivnosti ChooseCategoryActivity
        getSelectedTopicName = getIntent().getStringExtra("selectedTopic");
        Log.i("VoceLeveliActivity", getSelectedTopicName);


        /*listapitanja.add(new ModelClass("koje je voce zeleno","kivi", "banana","jabuka","jagoda","kivi","slika"));
        listapitanja.add(new ModelClass("koje je voce žuto","kivi", "banana","jabuka","jagoda","banana","slika"));
        listapitanja.add(new ModelClass("koje je voce plavo","kivi", "banana","jabuka","borovnica","borovnica","slika"));*/

        // za TextView Kategorija
        odabranaKategorija = findViewById(R.id.odabranaKategorija);
        odabranaKategorija.setText(getSelectedTopicName);

        // za gumb Level 1
        buttonLevel1 = findViewById(R.id.level1);

        // za gumb Level 2
        buttonLevel2 = findViewById(R.id.voce_level2);
        buttonLevel2.setEnabled(false);




        // za gumb Level 3
        buttonLevel3 = findViewById(R.id.voce_level3);
        buttonLevel3.setEnabled(false);

        // postavljanje tagova na buttone
        buttonLevel1.setTag("-level1");
        buttonLevel2.setTag("-level2");
        buttonLevel3.setTag("-level3");

        //pozivi funkcije
        funkcija(buttonLevel1);
        funkcija(buttonLevel2);
        funkcija(buttonLevel3);

        // dodavanje listenera za gumb Level 1
        buttonLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("button Level1", "kliknuo");
                if(getSelectedTopicName.equals("Brojevi")) {
                    selectedLevel = "Brojevi-level1";
                }
                if(getSelectedTopicName.equals("Životinje")) {
                    selectedLevel = "Životinje-level1";
                }
                if(getSelectedTopicName.equals("Voće")) {
                    selectedLevel = "Voće-level1";
                }
                if(getSelectedTopicName.equals("Povrće")) {
                    selectedLevel = "Povrće-level1";
                }
                Log.i("selectedLevel button je", selectedLevel);
                // pozivanje aktivnosti VocePitanjaLevel1Activity
                Intent intent = new Intent(VoceLeveliActivity.this, PovezivanjeFirebase.class);
                intent.putExtra("selectedTopicName", getSelectedTopicName);
                intent.putExtra("selectedLevel", selectedLevel);
                startActivity(intent);
            }

        });

        buttonLevel2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("button Level2", "kliknuo");
                if(getSelectedTopicName.equals("Brojevi")) {
                    selectedLevel = "Brojevi-level2";
                }
                if(getSelectedTopicName.equals("Životinje")) {
                    selectedLevel = "Životinje-level2";
                }
                if(getSelectedTopicName.equals("Voće")) {
                    selectedLevel = "Voće-level2";
                }
                if(getSelectedTopicName.equals("Povrće")) {
                    selectedLevel = "Povrće-level2";
                }
                Log.i("selectedLevel button je", selectedLevel);
                // pozivanje aktivnosti VocePitanjaLevel1Activity
                Intent intent = new Intent(VoceLeveliActivity.this, PovezivanjeFirebase.class);
                intent.putExtra("selectedTopicName", getSelectedTopicName);
                intent.putExtra("selectedLevel", selectedLevel);
                startActivity(intent);
            }

        });

        buttonLevel3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("button Level3", "kliknuo");
                if(getSelectedTopicName.equals("Brojevi")) {
                    selectedLevel = "Brojevi-level3";
                }
                if(getSelectedTopicName.equals("Životinje")) {
                    selectedLevel = "Životinje-level3";
                }
                if(getSelectedTopicName.equals("Voće")) {
                    selectedLevel = "Voće-level3";
                }
                if(getSelectedTopicName.equals("Povrće")) {
                    selectedLevel = "Povrće-level3";
                }
                Log.i("selectedLevel button je", selectedLevel);
                // pozivanje aktivnosti VocePitanjaLevel1Activity
                Intent intent = new Intent(VoceLeveliActivity.this, PovezivanjeFirebase.class);
                intent.putExtra("selectedTopicName", getSelectedTopicName);
                intent.putExtra("selectedLevel", selectedLevel);
                startActivity(intent);
            }

        });


    }

    // funkcija koja služi za otkljucavanje buttona za levele
    private void funkcija(Button buttonLevel) {
        level = String.valueOf(buttonLevel.getTag());

        Log.i("tag", level);
        skupa = getSelectedTopicName + level;
        Log.i("skupaIme", skupa);

        File file = new File(getApplicationContext().getFilesDir(),skupa);
        if(file.exists()){
            Log.i("postojim","postojim");
//            Log.i("skupa",skupa);

            load(skupa);
            Log.i("sadrzajdat",sadrzajDatoteke);
            if(level.equals("-level1")){
                if(sadrzajDatoteke.equals("otkljucano")) {
                    Log.i("otkljucavam", "otkljucavam");
                    buttonLevel2.setEnabled(true);
                    buttonLevel2.setBackgroundResource(R.drawable.play_rounded_button);

                }  else {
                    Log.i("nije tocno","nije tocno");
                }
            }
            if(level.equals("-level2")){
                Log.i("usaosamu2","usaosam u 2");
                if(sadrzajDatoteke.equals("otkljucano")) {
                    Log.i("otkljucavam", "otkljucavam");
                    buttonLevel3.setEnabled(true);
                    buttonLevel3.setBackgroundResource(R.drawable.play_rounded_button);

                }  else {
                    Log.i("nije tocno","nije tocno");
                }
            }
        }
        else{
            Log.i("nepostojim","nepostojim");
            Log.i("skupa",skupa);
        }

    }

    private void load( String FILE_NAME){
        FileInputStream fis = null;
        try{
            fis = openFileInput(FILE_NAME);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while((text = br.readLine()) != null ){
                sb.append(text);
            }
            Log.i("citamizchoose", String.valueOf(sb));
            sadrzajDatoteke = String.valueOf(sb);
        } catch(FileNotFoundException e){
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(fis != null){
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