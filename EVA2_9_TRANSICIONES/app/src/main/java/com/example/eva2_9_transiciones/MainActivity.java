package com.example.eva2_9_transiciones;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView img;
    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = findViewById(R.id.img);
        intent = new Intent(this,Main2Activity.class);
    }

    public void onClick(View v){
        ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(this, img, "mi_transicion");
        startActivity(intent,options.toBundle());
    }
}
