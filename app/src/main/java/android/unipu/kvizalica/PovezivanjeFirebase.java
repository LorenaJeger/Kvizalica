package android.unipu.kvizalica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
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

    String slikaKategorije;
    ImageView slikaKategorije_view;

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

        // za sliku
        slikaKategorije_view = findViewById(R.id.slikaKategorije);

        //Služi za prikaz slike kategorije iz baze pomoću url-a

        switch (getselectedLevel){
            case "Brojevi-level1":
                    Glide.with(this).load("https://i.pinimg.com/564x/66/eb/ba/66ebbaecbd90426ade50cfd43fdfbe88.jpg").into(slikaKategorije_view);
                    break;

            case "Brojevi-level2":
                Glide.with(this).load("https://i.pinimg.com/474x/f2/f0/9c/f2f09cce9b47b591487e8b819161c270.jpg").into(slikaKategorije_view);
                break;

            case "Brojevi-level3":
                Glide.with(this).load("https://i.pinimg.com/474x/ab/0c/40/ab0c4065e7ce4508ea88f9216830c26a.jpg").into(slikaKategorije_view);
                break;
            case "Voće-level1":
                Glide.with(this).load("https://i.pinimg.com/736x/4e/93/83/4e9383a3531db0c67739f7bff5cc64bb.jpg").into(slikaKategorije_view);
                break;

            case "Voće-level2":
                Glide.with(this).load("https://i.pinimg.com/564x/be/e2/aa/bee2aa50f83b6cbdab29c7fa2b67e16b.jpg").into(slikaKategorije_view);
                break;

            case "Voće-level3":
                Glide.with(this).load("https://i.pinimg.com/564x/77/49/89/7749896f48857bd836323cdb26c73215.jpg").into(slikaKategorije_view);
                break;

            case "Povrće-level1":
                Glide.with(this).load("https://i.pinimg.com/564x/a6/75/f0/a675f04777b0d4eaf5c65a1e274c0972.jpg").into(slikaKategorije_view);
                break;

            case "Povrće-level2":
                Glide.with(this).load("https://i.pinimg.com/564x/11/2b/f4/112bf4571d137148712b581e0c12b520.jpg").into(slikaKategorije_view);
                break;

            case "Povrće-level3":
                Glide.with(this).load("https://i.pinimg.com/564x/2e/1c/ac/2e1cac0c1440a90724bd1d2f7154ee16.jpg").into(slikaKategorije_view);
                break;

            case "Životinje-level1":
                Glide.with(this).load("https://i.pinimg.com/564x/dc/26/98/dc2698f0883cb7cc30e16171f6ee7569.jpg").into(slikaKategorije_view);
                break;

            case "Životinje-level2":
                Glide.with(this).load("https://i.pinimg.com/564x/69/d7/4c/69d74c13276be2ca8adaf88fafc6c8f6.jpg").into(slikaKategorije_view);
                break;

            case "Životinje-level3":
                Glide.with(this).load("https://i.pinimg.com/564x/9d/5e/5c/9d5e5cb2e36e00524b7ca1b2fbc95e28.jpg").into(slikaKategorije_view);
                break;
        }

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