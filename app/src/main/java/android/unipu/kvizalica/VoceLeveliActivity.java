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

    public static ArrayList<ModelClass>  listapitanja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_voce_leveli);

        // za povući vrstu kategorije iz aktivnosti ChooseCategoryActivity
        final String getSelectedTopicName = getIntent().getStringExtra("selectedTopic");


        listapitanja = new ArrayList<>();
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
                selectedLevel = "level1";
                // pozivanje aktivnosti VocePitanjaLevel1Activity
                Intent intent = new Intent(VoceLeveliActivity.this, VocePitanjaLevel1Activity.class);
                intent.putExtra("selectedTopicName", getSelectedTopicName);
                intent.putExtra("selectedLevel", selectedLevel);
                startActivity(intent);
            }
        });

        // Dohvaćanje pitanja s Firebase-a
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://kvizalicajegervosten-default-rtdb.europe-west1.firebasedatabase.app/");

        // Prikaz dialoga dok se dohvaćaju pitanja iz baze
        Log.i("prikaz dijaloga", "dijalog");
        ProgressDialog progresDijalog = new ProgressDialog(VoceLeveliActivity.this);
        progresDijalog.setCancelable(false);
        progresDijalog.setMessage("Učitavanje...");
        progresDijalog.show();

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // dohvaćanje svih pitanja iz Firebase-a za određenu kategoriju i određeni level
                for(DataSnapshot dataSnapshot : snapshot.child(getSelectedTopicName).getChildren()) {
                    Log.i("prvi for", "ušao");
                    for(DataSnapshot dt : snapshot.child(selectedLevel).getChildren()) {
                        Log.i("drugi for", "ušao");
                        final String getQuestion = dt.child("Pitanje").getValue(String.class);
                        //Log.i("dohvat pitanja", getQuestion);
                        final String getSlika = dt.child("Slika").getValue(String.class);
                        final String getOdgA = dt.child("Odgovor1").getValue(String.class);
                        final String getOdgB = dt.child("Odgovor2").getValue(String.class);
                        final String getOdgC = dt.child("Odgovor3").getValue(String.class);
                        final String getOdgD = dt.child("Odgovor4").getValue(String.class);
                        final String getTocan = dt.child("TočanOdgovor").getValue(String.class);

                        // dodavanje podataka u listu listapitanja
                        ModelClass listaPodataka = new ModelClass(getQuestion, getSlika, getOdgA, getOdgB, getOdgC, getOdgD, getTocan);
                        listapitanja.add(listaPodataka);
                        Log.i("listapitanja", "napunio listu");
                        Log.i("prikaz liste", listapitanja.toString());
                    }
                }
                // sakrij dijalog
                progresDijalog.hide();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


}