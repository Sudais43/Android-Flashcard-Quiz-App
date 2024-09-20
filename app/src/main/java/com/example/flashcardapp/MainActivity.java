package com.example.flashcardapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn1;
    private Button btn2;

    FloatingActionButton fmenu;

    private boolean isfabopen = false;

    List<DataEntry> questions = new ArrayList<>();

    Appdatabase db;
    RecyclerView flashrv;
    FlashAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        fmenu = findViewById(R.id.fmenu);
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        flashrv = findViewById(R.id.flashRv);
        db = Appdatabase.getInstance(MainActivity.this);
        questions = db.taskdao().loadalltask();

        adapter = new FlashAdapter(MainActivity.this,questions);
        flashrv.setAdapter(adapter);
        flashrv.setLayoutManager(new LinearLayoutManager(this));

        fmenu.bringToFront();
        btn1.bringToFront();
        btn2.bringToFront();


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i  = new Intent(MainActivity.this, AddQuestion.class);
                startActivity(i);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Quiz.class);
                startActivity(i);
            }
        });

        fmenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isfabopen){
                    CloseFabMenu();
                }else {
                    OpenFabMenu();
                }
            }
        });

    }

    public void OpenFabMenu(){
        isfabopen = true;
        btn1.setVisibility(View.VISIBLE);
        btn2.setVisibility(View.VISIBLE);
    }

    public void CloseFabMenu(){
        isfabopen = false;
        btn1.setVisibility(View.INVISIBLE);
        btn2.setVisibility(View.INVISIBLE);
    }
}