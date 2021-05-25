package com.android.guicelebrini.howtoplay.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.android.guicelebrini.howtoplay.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class TutorialActivity extends AppCompatActivity {

    private DatabaseReference referencia = FirebaseDatabase.getInstance().getReference();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
    }
}