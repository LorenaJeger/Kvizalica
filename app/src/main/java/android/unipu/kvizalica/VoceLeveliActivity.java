package android.unipu.kvizalica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class VoceLeveliActivity extends AppCompatActivity {




    Button Level1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voce_leveli);


        // za gumb Voce
        Level1 = findViewById(R.id.voce_level1);

        // dodavanje listenera za gumb Igraj
        Level1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pozivanje aktivnosti ChooseCategory
                Intent intent = new Intent(VoceLeveliActivity.this, VocePitanjaLevel1Activity.class);
                startActivity(intent);
            }
        });
    }


}