package com.example.samuelhimself.agent;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class SPLASH1 extends AppCompatActivity {


    private SharedPreferences prefl;
    private String preflogin="preflogin";
    private static final String LOGIN_STATUS_KEY ="Login Status";
    String dayo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash1);

//        STATUS BAR
        if(Build.VERSION.SDK_INT >=21){
            Window window=this.getWindow();
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setStatusBarColor(this.getResources().getColor(R.color.darkdarkTurq));
        }


        prefl=getSharedPreferences(preflogin, MODE_PRIVATE);

//*************time*************8
        Calendar date= Calendar.getInstance();
        SimpleDateFormat sdf= new SimpleDateFormat("dd-MM-yyyy");
        dayo=sdf.format(date.getTime());

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                if (prefl.getBoolean(LOGIN_STATUS_KEY,false)){
                    Intent intent= new Intent(SPLASH1.this,bicyclesIn.class);//mainactibity
                    startActivity(intent);
                finish();}
                else{
                    Intent intent= new Intent(SPLASH1.this,LOGIN.class);
                    startActivity(intent);
                    finish();
                }
            }
        }, 100);

        Toast.makeText(getApplicationContext(), dayo, Toast.LENGTH_LONG).show();

    }
}

