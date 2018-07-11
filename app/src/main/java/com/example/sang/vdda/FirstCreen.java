package com.example.sang.vdda;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FirstCreen extends AppCompatActivity {
    Button btplaygame;
    public static SharedPreferences sharedPreferences;
    public static SharedPreferences.Editor editor;
    public static  String highscore="";
    Button btback;
    Button bthelp;
    Button btdiem;
    Button btxoa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_creen);
        btplaygame = (Button) findViewById(R.id.idplaygame);
        sharedPreferences=getSharedPreferences("highscore", MODE_PRIVATE);
        //deleteCache(this);

        btback=(Button) findViewById(R.id.idback);
        bthelp=(Button) findViewById(R.id.idhelp);
        btdiem=(Button) findViewById(R.id.idscore);
        btxoa=(Button) findViewById(R.id.xoaud);
        final MediaPlayer ap = MediaPlayer.create(this, R.raw.flyaway);

        btxoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAppData();
            }
        });
        btplaygame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstCreen.this, MainActivity.class));
                //overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
            }
        });
        bthelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FirstCreen.this, Help.class));
                //overridePendingTransition(R.anim.anim_enter, R.anim.anim_exit);
            }
        });
        btdiem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                AlertDialog.Builder builder = new AlertDialog.Builder(FirstCreen.this);
                builder.setMessage("Điểm cao nhất hiện tại là "+sharedPreferences.getString(highscore.toString(), "00"));
                builder.setCancelable(true);

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();

                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
        btback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                AlertDialog.Builder builder = new AlertDialog.Builder(FirstCreen.this);
                builder.setMessage("Are you sure want to exit ?");
                builder.setCancelable(true);
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override

                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override

                    public void onClick(DialogInterface dialog, int which) {
                       /* finish();
                        moveTaskToBack(true);*/
                        Intent homeIntent = new Intent(Intent.ACTION_MAIN);
                        homeIntent.addCategory( Intent.CATEGORY_HOME );
                        homeIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(homeIntent);



                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();

            }
        });
    }




    public void deleteAppData() {
        try {
            // clearing app data
            String packageName = getApplicationContext().getPackageName();
            Runtime runtime = Runtime.getRuntime();
            runtime.exec("pm clear "+packageName);

        } catch (Exception e) {
            e.printStackTrace();
        } }
}

