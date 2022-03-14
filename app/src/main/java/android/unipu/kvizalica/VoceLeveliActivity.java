package android.unipu.kvizalica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//punimo listu pitanja
public class VoceLeveliActivity extends AppCompatActivity {
    Button buttonLevel1, buttonLevel2, buttonLevel3;
    TextView odabranaKategorija;

    // string pomoću kojeg ćemo povlačiti level koji je odabran
    private String selectedLevel = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voce_leveli);

        // za povući vrstu kategorije iz aktivnosti ChooseCategoryActivity
        final String getSelectedTopicName = getIntent().getStringExtra("selectedTopic");
        Log.i("VoceLeveliActivity", getSelectedTopicName);


        /*listapitanja.add(new ModelClass("koje je voce zeleno","kivi", "banana","jabuka","jagoda","kivi","slika"));
        listapitanja.add(new ModelClass("koje je voce žuto","kivi", "banana","jabuka","jagoda","banana","slika"));
        listapitanja.add(new ModelClass("koje je voce plavo","kivi", "banana","jabuka","borovnica","borovnica","slika"));*/

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


    }


}