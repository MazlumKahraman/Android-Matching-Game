package com.enma.hafiza;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ModActivity extends AppCompatActivity {
    Button btnEasy;
    Button btnNormal;
    Button btnHard;
    Button btnScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod);

        String nick = getIntent().getExtras().getString("nick");
        btnEasy = (Button)findViewById(R.id.btn_easy);
        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),EasyModActivity.class).putExtra("nick",nick));
            }
        });

        btnNormal = (Button)findViewById(R.id.btn_normal);
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),NormalModActivity.class).putExtra("nick",nick));
            }
        });

        btnHard = (Button)findViewById(R.id.btn_hard);
        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HardModActivity.class).putExtra("nick",nick));
            }
        });

       btnScore = (Button)findViewById(R.id.btn_showScore);
        btnScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DBHelper db = new DBHelper(getApplicationContext());

                //Cursor data = db.getEasyData();
                //btnScore.setText(data.toString());

                Cursor res = db.getEasyData();
                StringBuffer buffer = new StringBuffer();
                buffer.append("KOLAY\n\n");
                do
                {
                    buffer.append("Kullanici Adi : " +res.getString(0)+"\n");
                    buffer.append("Hamle Sayisi : " +res.getString(1)+"\n");
                    buffer.append("Tarih : " +res.getString(2)+"\n");
                    buffer.append("-----------------------------------------------\n");
                }while(res.moveToNext());
                res =db.getNormalData();
                buffer.append("ORTA\n\n");
                do
                {
                    buffer.append("Kullanici Adi : " +res.getString(0)+"\n");
                    buffer.append("Hamle Sayisi : " +res.getString(1)+"\n");
                    buffer.append("Tarih : " +res.getString(2)+"\n");
                    buffer.append("-----------------------------------------------\n");
                }while(res.moveToNext());
                res =db.getHardData();
                buffer.append("ZOR\n\n");
                do
                {
                    buffer.append("Kullanici Adi : " +res.getString(0)+"\n");
                    buffer.append("Hamle Sayisi : " +res.getString(1)+"\n");
                    buffer.append("Tarih : " +res.getString(2)+"\n");
                    buffer.append("-----------------------------------------------\n");
                }while(res.moveToNext());
                AlertDialog.Builder builder = new AlertDialog.Builder(ModActivity.this);
                builder.setCancelable(true);
                builder.setTitle("Skorlar");
                builder.setMessage(buffer.toString());
                builder.show();
                //startActivity(new Intent(getApplicationContext(),ShowScoresActivity.class));
            }
        });

    }
}