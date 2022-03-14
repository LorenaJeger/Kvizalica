package android.unipu.kvizalica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


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

public class PovezivanjeFirebase extends AppCompatActivity {
    Button zapocni;
    //TextView kategorija;
    TextView level;

    public static ArrayList<ModelClass> listapitanja;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_povezivanje_firebase);

        // za povući vrstu kategorije iz aktivnosti ChooseCategoryActivity i leveli
        final String getTopicNameFirebase = getIntent().getStringExtra("selectedTopicName");
        final String getselectedLevel = getIntent().getStringExtra("selectedLevel");
        Log.i("topic: ", getTopicNameFirebase);
        Log.i("level: ", getselectedLevel);

        listapitanja = new ArrayList<>();

        // Dohvaćanje pitanja s Firebase-a
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://kvizalicajegervosten-default-rtdb.europe-west1.firebasedatabase.app/");

        // Prikaz dialoga dok se dohvaćaju pitanja iz baze
      /*  Log.i("prikaz dijaloga", "dijalog");
        ProgressDialog progresDijalog = new ProgressDialog(VoceLeveliActivity.this);
        progresDijalog.setCancelable(false);
        progresDijalog.setMessage("Učitavanje...");
        progresDijalog.show();
*/
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                // dohvaćanje svih pitanja iz Firebase-a za određenu kategoriju i određeni level
                for(DataSnapshot dataSnapshot : snapshot.child(getselectedLevel).getChildren()) {
                    Log.i("prvi for", "ušao");
                    //for(DataSnapshot dt : snapshot.child(selectedLevel).getChildren()) {
                    //Log.i("drugi for", "ušao");
                    final String getQuestion = dataSnapshot.child("Pitanje").getValue(String.class);
                    final String getOdgA = dataSnapshot.child("Odgovor1").getValue(String.class);
                    final String getOdgB = dataSnapshot.child("Odgovor2").getValue(String.class);
                    final String getOdgC = dataSnapshot.child("Odgovor3").getValue(String.class);
                    final String getOdgD = dataSnapshot.child("Odgovor4").getValue(String.class);
                    final String getTocan = dataSnapshot.child("TočanOdgovor").getValue(String.class);
                    final String getSlika = dataSnapshot.child("Slika").getValue(String.class);

                    // dodavanje podataka u listu listapitanja
                    ModelClass listaPodataka = new ModelClass(getQuestion, getOdgA, getOdgB, getOdgC, getOdgD, getTocan,getSlika);
                    listapitanja.add(listaPodataka);
                    Log.i("listapitanja", "napunio listu");
                    //Log.i("prikaz liste", listapitanja.toString());
                }
            }
            // sakrij dijalog

            //progresDijalog.hide();
            //}

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // TextView
        //kategorija = findViewById(R.id.text_Odabranakategorija);
        //kategorija.setText(getTopicNameFirebase);
        level = findViewById(R.id.text_Odabranilevel);
        level.setText(getselectedLevel);

        // za gumb Igraj
        zapocni = findViewById(R.id.kreni);

        // dodavanje listenera za gumb Igraj
        zapocni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // pozivanje aktivnosti Vocepitanjeleveli1
                Intent intent = new Intent(PovezivanjeFirebase.this, VocePitanjaLevel1Activity.class);
                intent.putExtra("getselectedLevel", getselectedLevel);
                startActivity(intent);
            }
        });


    }
}