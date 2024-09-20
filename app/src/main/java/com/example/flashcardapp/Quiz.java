package com.example.flashcardapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class Quiz extends AppCompatActivity {

    TextView question,opt1,opt2,opt3,opt4,rans,wans;
    Appdatabase db;

    private String RightAnswer;
    int rightAnswer = 0,WrongAnswer=0;

    boolean allQuestionLoaded = false;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quiz);

        question = findViewById(R.id.question);
        opt1 = findViewById(R.id.opt1);
        opt2 = findViewById(R.id.opt2);
        opt3 = findViewById(R.id.opt3);
        opt4 = findViewById(R.id.opt4);
        rans = findViewById(R.id.rans);
        wans = findViewById(R.id.wans);

        db = Room.databaseBuilder(this,Appdatabase.class,"DataEntry").build();

        loadRandomQuestion();

        opt1.setOnClickListener(v -> {
            String opt1ans = opt1.getText().toString();
            if(opt1ans.equals(RightAnswer)){
                opt1.setBackgroundColor(getResources().getColor(R.color.Green,null));
                rightAnswer++;
                rans.setText(String.valueOf(rightAnswer));
            }else {
                WrongAnswer++;
                wans.setText(String.valueOf(WrongAnswer));
                opt1.setBackgroundColor(getResources().getColor(R.color.Red,null));
            }
            loadRandomQuestion();
        });


        opt2.setOnClickListener(v -> {
            String opt2ans = opt2.getText().toString();
            if(opt2ans.equals(RightAnswer)){
                opt2.setBackgroundColor(getResources().getColor(R.color.Green,null));
                rightAnswer++;
                rans.setText(String.valueOf(rightAnswer));
            }else {
                WrongAnswer++;
                wans.setText(String.valueOf(WrongAnswer));
                opt2.setBackgroundColor(getResources().getColor(R.color.Red,null));
            }
            loadRandomQuestion();
        });


        opt3.setOnClickListener(v -> {
            String opt3ans = opt3.getText().toString();
            if(opt3ans.equals(String.valueOf(RightAnswer))){
                opt3.setBackgroundColor(getResources().getColor(R.color.Green,null));
                rightAnswer++;

                rans.setText(String.valueOf(rightAnswer));
            }else {
                WrongAnswer++;
                wans.setText(String.valueOf(WrongAnswer));
                opt3.setBackgroundColor(getResources().getColor(R.color.Red,null));
            }
            loadRandomQuestion();
        });

        opt4.setOnClickListener(v -> {
            String opt4ans = opt4.getText().toString();
            if(opt4ans.equals(String.valueOf(RightAnswer))){
                opt4.setBackgroundColor(getResources().getColor(R.color.Green,null));
                rightAnswer++;

                rans.setText(String.valueOf(rightAnswer));
            }else {
                WrongAnswer++;
                wans.setText(String.valueOf(WrongAnswer));
                opt4.setBackgroundColor(getResources().getColor(R.color.Red,null));
            }
            loadRandomQuestion();
        });

    }
    private final CompositeDisposable compositeDisposable = new CompositeDisposable();
    public void loadRandomQuestion(){

        Handler handler = new Handler(Looper.getMainLooper());
        Disposable disposable = Single.fromCallable(()->db.questiondao().getRandomQuestion())
                .subscribeOn(Schedulers.io())
                .subscribe(dataEntry -> {
                    if(!allQuestionLoaded) {
                        handler.post(() -> {
                            opt1.setBackgroundColor(getResources().getColor(R.color.gray, null));
                            opt2.setBackgroundColor(getResources().getColor(R.color.gray, null));
                            opt3.setBackgroundColor(getResources().getColor(R.color.gray, null));
                            opt4.setBackgroundColor(getResources().getColor(R.color.gray, null));
                            question.setText(dataEntry.getQuestion());
                            opt1.setText(dataEntry.getOption1());
                            opt2.setText(dataEntry.getOption2());
                            opt3.setText(dataEntry.getOption3());
                            opt4.setText(dataEntry.getOption4());
                            RightAnswer = dataEntry.getRightAnswer();
                            allQuestionLoaded = true;
                        });
                    }
                },throwable -> Log.e("QuizActivity", "Error loading question:", throwable));



        compositeDisposable.add(disposable);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}