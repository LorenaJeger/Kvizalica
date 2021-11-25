package android.unipu.kvizalica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ChooseCategoryActivity extends AppCompatActivity {

    ImageButton Voce;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_category);


        // za gumb Voce
        Voce = findViewById(R.id.slika_kategorije_voce);

        // dodavanje listenera za gumb Igraj
        Voce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pozivanje aktivnosti ChooseCategory
                Intent intent = new Intent(ChooseCategoryActivity.this, KvizActivity.class);
                startActivity(intent);
            }
        });
    }
}