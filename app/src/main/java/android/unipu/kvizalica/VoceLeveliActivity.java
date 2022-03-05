package android.unipu.kvizalica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
//punimo listu pitanja
public class VoceLeveliActivity extends AppCompatActivity {
    Button buttonLevel1, buttonLevel2, buttonLevel3;
    TextView odabranaKategorija;

   public static ArrayList<ModelClass>  listapitanja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voce_leveli);

        // za povući vrstu kategorije iz aktivnosti ChooseCategoryActivity
        final String getSelectedTopicName = getIntent().getStringExtra("selectedTopic");


        listapitanja= new ArrayList<>();
        listapitanja.add(new ModelClass("koje je voce zeleno","kivi", "banana","jabuka","jagoda","kivi","slika"));
        listapitanja.add(new ModelClass("koje je voce žuto","kivi", "banana","jabuka","jagoda","banana","slika"));
        listapitanja.add(new ModelClass("koje je voce plavo","kivi", "banana","jabuka","borovnica","borovnica","slika"));

        // za TextView Kategorija
        odabranaKategorija = findViewById(R.id.odabranaKategorija);
        odabranaKategorija.setText(getSelectedTopicName);

        // za gumb Level 1
        buttonLevel1 = findViewById(R.id.voce_level1);

        // za gumb Level 2
        buttonLevel2 = findViewById(R.id.voce_level2);
        buttonLevel2.setEnabled(false);

        // za gumb Level 3
        buttonLevel3 = findViewById(R.id.voce_level3);

        // dodavanje listenera za gumb Level 1
        buttonLevel1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pozivanje aktivnosti VocePitanjaLevel1Activity
                Intent intent = new Intent(VoceLeveliActivity.this, VocePitanjaLevel1Activity.class);
                startActivity(intent);
            }
        });
    }


}