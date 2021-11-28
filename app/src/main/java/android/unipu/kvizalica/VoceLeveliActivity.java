package android.unipu.kvizalica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VoceLeveliActivity extends AppCompatActivity {
    Button buttonLevel1, buttonLevel2, buttonLevel3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voce_leveli);


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