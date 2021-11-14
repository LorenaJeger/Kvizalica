package android.unipu.kvizalica;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class KvizActivity extends AppCompatActivity {

    private RadioGroup rbGroup;
    private RadioButton rb1;
    private RadioButton rb2;
    private RadioButton rb3;
    private RadioButton rb4;

    private TextView textViewQuestions;
    private TextView textViewScore;
    private TextView textViewQuestionTotal;
    private TextView textViewCorrect, textViewWrong;
    private TextView textTimer;

    private Timer quizTimer;
    private int totalTimeMins = 1;
    private int seconds = 0;

    private List<QuestionList> questionLists= new ArrayList<>();


    private int currentQuestionPosition=0;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kviz);

        setUpUI();
    }

    private void setUpUI() {

        textViewCorrect = findViewById(R.id.txtCorrect);
        textViewWrong = findViewById(R.id.txtWrong);
        textTimer = findViewById(R.id.txtTimer);
        textViewQuestionTotal = findViewById(R.id.textTotalQuestion);
        textViewScore = findViewById(R.id.Score);
        //final TextView selectedTopicName= findViewById(R.id.topicName);

        textViewQuestions = findViewById(R.id.textTotalQuestion);

        final String getTopicName = getIntent().getStringExtra("SelectedTopic");


        startTimer(textTimer);

        rb1 = findViewById(R.id.button1);
        rb2 = findViewById(R.id.button2);
        rb3 = findViewById(R.id.button3);
        rb4 = findViewById(R.id.button4);

        final String SelectedTopicName= getIntent().getStringExtra("selectedTopic");

        // selectedTopicName.setText(getSelectedTopicName);
      //  questionLists=QuestionsBlank.getQuestions(getSelectedTopicName);



        ProgressDialog progressDialog= new ProgressDialog(KvizActivity.this);
        progressDialog.setCancelable(false);
        progressDialog.setMessage("Očitavanje...");
        progressDialog.show();



        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl("https://kvizalicajegervosten-default-rtdb.europe-west1.firebasedatabase.app/");
        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {



            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                //uzimmamo sva pitanja za specificnu kategoriju iz firebasa

                for(DataSnapshot dataSnapshot: snapshot.child(getTopicName).getChildren()){

                    final String getQuestion= dataSnapshot.child("Pitanje").getValue(String.class);
                    final String getOdogovor1= dataSnapshot.child("Odgovor1").getValue(String.class);
                    final String getOdogovor2= dataSnapshot.child("Odgovor2").getValue(String.class);
                    final String getOdogovor3= dataSnapshot.child("Odgovor3").getValue(String.class);
                    final String getOdogovor4= dataSnapshot.child("Odgovor4").getValue(String.class);
                    final String getTocanOdg= dataSnapshot.child("TočanOdgovor").getValue(String.class);
                    final String getslika= dataSnapshot.child("Slika").getValue(String.class);


                    //dodaajemo podatke u pitanja
                    QuestionList questionsList= new QuestionList(getQuestion,getOdogovor1,getOdogovor2,getOdogovor3,getOdogovor4,getTocanOdg,getslika);
                    questionLists.add(questionsList);
                }
                progressDialog.hide();  //skrivamo progressDialog
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        rb4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }



    private void startTimer(TextView textTimer) {

        quizTimer = new Timer();
        quizTimer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {

                if (seconds == 0) {
                    totalTimeMins--;
                    seconds = 59;
                } else if (seconds == 0 && totalTimeMins == 0) {
                    quizTimer.purge();
                    quizTimer.cancel();
                    Toast.makeText(KvizActivity.this, "Time Over", Toast.LENGTH_SHORT).show();

                    Intent intent = new Intent(KvizActivity.this, KvizResults.class);
                    intent.putExtra("correct", getCorrectAnswer());
                    intent.putExtra("incorrect", getInCorrectAnswer());
                    startActivity(intent);

                    finish();
                }
                else{
                    seconds--;
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                         String finalMinutes= String.valueOf(totalTimeMins);
                         String finalSeconds= String.valueOf(seconds);

                        if(finalMinutes.length()==1){
                            finalMinutes="0"+finalMinutes;
                        }
                        if(finalSeconds.length()==1){
                            finalSeconds="0"+finalSeconds;
                        }
                        textTimer.setText(finalMinutes+finalSeconds);
                    }
                });

            }

        }, 1000, 1000);
    }

    private int getCorrectAnswer() {

        int CorrectAnswer = 0;
        for(int i=0; i<questionLists.size();i++){
            final String getUsersSelectedAnswer= questionLists.get(i).getUserSelectedAnswer();
            final String getAnswer=questionLists.get(i).getAnswer();

            if(getUsersSelectedAnswer.equals(getAnswer)){
                CorrectAnswer++;
            }
        }
        return getCorrectAnswer();
    }


    private int getInCorrectAnswer() {

        int CorrectAnswer = 0;
        for(int i=0; i<questionLists.size();i++){
            final String getUsersSelectedAnswer= questionLists.get(i).getUserSelectedAnswer();
            final String getAnswer=questionLists.get(i).getAnswer();

            if(!getUsersSelectedAnswer.equals(getAnswer)){
                CorrectAnswer++;
            }
        }
        return getCorrectAnswer();
    }



}


