package android.unipu.kvizalica;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
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
    private String sadrzajDatoteke;
    private String getSelectedTopicName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voce_leveli);


        // za povući vrstu kategorije iz aktivnosti ChooseCategoryActivity
        getSelectedTopicName = getIntent().getStringExtra("selectedTopic");


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
        funkcijaOtkljucajLevel(buttonLevel1);
        funkcijaOtkljucajLevel(buttonLevel2);
        funkcijaOtkljucajLevel(buttonLevel3);

        // dodavanje listenera za gumb Level 1
        buttonLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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

                // pozivanje aktivnosti VocePitanjaLevel1Activity
                Intent intent = new Intent(VoceLeveliActivity.this, PovezivanjeFirebase.class);
                intent.putExtra("selectedTopicName", getSelectedTopicName);
                intent.putExtra("selectedLevel", selectedLevel);
                startActivity(intent);
            }

        });


    }

    // funkcija koja služi za otkljucavanje buttona za levele
    private void funkcijaOtkljucajLevel(Button buttonLevel) {
        level = String.valueOf(buttonLevel.getTag());


        skupa = getSelectedTopicName + level;


        File file = new File(getApplicationContext().getFilesDir(),skupa);
        if(file.exists()){


            load(skupa);

            if(level.equals("-level1")){
                if(sadrzajDatoteke.equals("otkljucano")) {

                    buttonLevel2.setEnabled(true);
                    buttonLevel2.setBackgroundResource(R.drawable.play_rounded_button);

                }
            }
            if(level.equals("-level2")){

                if(sadrzajDatoteke.equals("otkljucano")) {

                    buttonLevel3.setEnabled(true);
                    buttonLevel3.setBackgroundResource(R.drawable.play_rounded_button);

                }
            }
        }


    }
    // funkcija koja čita iz datoteke
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