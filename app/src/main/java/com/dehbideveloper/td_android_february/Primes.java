package com.dehbideveloper.td_android_february;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class Primes extends AppCompatActivity {

    Button btnBack;
    Button btnAction;
    ProgressBar progressBar;
    ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_primes);

        btnBack = (Button) findViewById(R.id.btnPrimesToMain);
        btnAction = (Button) findViewById(R.id.btnGeneratePrimes);
        progressBar = (ProgressBar) findViewById(R.id.progressBarPrimes);
        lv = (ListView) findViewById(R.id.listViewPrimes);
        EditText etPrime = (EditText)findViewById(R.id.etPrime);

        //Back Home
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentHome = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intentHome);


            }
        });

        btnAction.setOnClickListener(new View.OnClickListener() {

            //checking the number if it prime
            private boolean isPrime(int n){

                if (n <= 1) {
                    return false;
                }
                for (int i = 2; i <= Math.sqrt(n); i++) {
                    if (n % i == 0) {
                        return false;
                    }
                }
                return true;
            }

            //getting all primes possible
            public ArrayList<Integer> primes(double number){
                ArrayList<Integer> nbList = new ArrayList<>();

                int progress = 0;
                for(int i =1 ; i < number; i++){
                    if (isPrime(i)){
                        nbList.add(i);
                    }
                    int newProgress = i * 100 / (int)number;
                    if (newProgress > progress) {
                        progress = newProgress;
                        progressBar.setProgress(progress);
                    }
                }
                return nbList;
            }




            @Override
            public void onClick(View v) {

                //Getting inputs values
                int number = Integer.parseInt(String.valueOf(etPrime.getText()));

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        ArrayList<Integer> primesList= primes(number);

                        Handler handler = new Handler(Looper.getMainLooper());
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                ArrayAdapter<Integer> adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, primesList);
                                lv.setAdapter(adapter);
                            }
                        });

                    }
                });

                thread.start();

            }

            });





    }
}