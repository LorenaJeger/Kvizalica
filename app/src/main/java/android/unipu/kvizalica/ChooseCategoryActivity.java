package android.unipu.kvizalica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

//Izbor kategorije
public class ChooseCategoryActivity extends AppCompatActivity {

    ImageButton Brojevi, Zivotinje, Voce, Povrce;

    // string pomoću kojeg ćemo povlačiti vrstu kategorije koja je odabrana
    private String selectedTopicName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);

        // za ImageButton Brojevi
        Brojevi = findViewById(R.id.slika_kategorije_brojevi);

        //dodavanje listenera za ImageButton Brojevi
        Brojevi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopicName = "Brojevi";
                // pozivanje aktivnosti VoceLeveliActivity
                Intent intent = new Intent(ChooseCategoryActivity.this, VoceLeveliActivity.class);
                intent.putExtra("selectedTopic", selectedTopicName);
                startActivity(intent);
            }
        });

        // za ImageButton Zivotinje
        Zivotinje = findViewById(R.id.slika_kategorije_zivotinje);

        Zivotinje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopicName = "Životinje";
                // pozivanje aktivnosti VoceLeveliActivity
                Intent intent = new Intent(ChooseCategoryActivity.this, VoceLeveliActivity.class);
                intent.putExtra("selectedTopic", selectedTopicName);
                startActivity(intent);
            }
        });

        // za ImageButton Voce
        Voce = findViewById(R.id.slika_kategorije_voce);

        // dodavanje listenera za ImageButton Voce
        Voce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopicName = "Voće";
                // pozivanje aktivnosti VoceLeveliActivity
                Intent intent = new Intent(ChooseCategoryActivity.this, VoceLeveliActivity.class);
                intent.putExtra("selectedTopic", selectedTopicName);
                startActivity(intent);
            }
        });

        // za ImageButton Povrce
        Povrce = findViewById(R.id.slika_kategorije_povrce);

        // dodavanje listenera za ImageButton Povrce
        Povrce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTopicName = "Povrće";
                // pozivanje aktivnosti VoceLeveliActivity
                Intent intent = new Intent(ChooseCategoryActivity.this, VoceLeveliActivity.class);
                intent.putExtra("selectedTopic", selectedTopicName);
                startActivity(intent);
            }
        });


    }
}