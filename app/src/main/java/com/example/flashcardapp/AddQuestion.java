package com.example.flashcardapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class AddQuestion extends AppCompatActivity {

    EditText editqs,opt1,opt2,opt3,opt4,rightAns;
    Button addbtn;
    List<DataEntry> questions = new ArrayList<>();

    Appdatabase appdatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_question);

        editqs = findViewById(R.id.editqs);
        opt1 = findViewById(R.id.option1);
        opt2 = findViewById(R.id.option2);
        opt3 = findViewById(R.id.option3);
        opt4 = findViewById(R.id.option4);
        addbtn = findViewById(R.id.addbtn);
        rightAns = findViewById(R.id.rightAns);

        appdatabase = Appdatabase.getInstance(this);

        addbtn.setOnClickListener(v -> {
            String question = editqs.getText().toString();
            String o1 = opt1.getText().toString();
            String o2 = opt2.getText().toString();
            String o3 = opt3.getText().toString();
            String o4 = opt4.getText().toString();
            String answer;
            boolean AnswerIsValid = false;
            do {
                answer = rightAns.getText().toString();
                if (answer.equals(o1) || answer.equals(o2) || answer.equals(o3) || answer.equals(o4)) {
                    AnswerIsValid = true;
                    break;
                } else {
                    System.out.println("Your answer is not matching to any of your options");
                }
            } while (!AnswerIsValid);

            DataEntry DE = new DataEntry(question, o1, o2, o3, o4, answer);
            questions.add(DE);
            appdatabase.taskdao().insertTask(DE);
            Toast.makeText(AddQuestion.this, "Question Added Successfully", Toast.LENGTH_SHORT).show();
        });

    }
}