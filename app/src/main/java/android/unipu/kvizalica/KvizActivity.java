package android.unipu.kvizalica;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kviz);

        setUpUI();
    }

    private void setUpUI() {

        textViewCorrect=findViewById(R.id.txtCorrect);
        textViewWrong=findViewById(R.id.txtWrong);
        textTimer=findViewById(R.id.txtTimer);
        textViewQuestionTotal=findViewById(R.id.textTotalQuestion);
        textViewScore=findViewById(R.id.Score);

        rb1=findViewById(R.id.button1);
        rb2 = findViewById(R.id.button2);
        rb3 = findViewById(R.id.button3);
        rb4 = findViewById(R.id.button4);






    }
}