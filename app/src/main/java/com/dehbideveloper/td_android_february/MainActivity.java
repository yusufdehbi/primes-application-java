package com.dehbideveloper.td_android_february;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnPrimesActivity;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPrimesActivity = (Button)findViewById(R.id.btnPrimes) ;

        btnPrimesActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent primesIntent = new Intent(MainActivity.this,Primes.class);
                startActivity(primesIntent);
            }
        });



    }
}