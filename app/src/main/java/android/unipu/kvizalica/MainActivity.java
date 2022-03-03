package android.unipu.kvizalica;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

//main
public class MainActivity extends AppCompatActivity {

    // varijabla za gumb Igraj
    Button igraj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // za gumb Igraj
        igraj = findViewById(R.id.button_igraj);

        // dodavanje listenera za gumb Igraj
        igraj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pozivanje aktivnosti ChooseCategory
                Intent intent = new Intent(MainActivity.this, ChooseCategoryActivity.class);
                startActivity(intent);
            }
        });


    }


}