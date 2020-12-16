package com.example.eva2_7_animaciones_actividades;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btntrans;
    Intent inte;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btntrans = findViewById(R.id.btnTrans);
        inte = new Intent(this, Main2Activity.class);
    }

    public void onClick(View v){
        startActivity(inte);
    }
}
