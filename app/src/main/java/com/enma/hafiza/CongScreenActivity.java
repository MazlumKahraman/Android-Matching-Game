package com.enma.hafiza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.enma.hafiza.databinding.ActivityMainBinding;

public class CongScreenActivity extends AppCompatActivity {

    TextView newGame, back, exit, message;
    DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cong_screen);

        int moves = getIntent().getExtras().getInt("Game_Moves");
        String diff = getIntent().getExtras().getString("Game_Diff");
        String nick = getIntent().getExtras().getString("nick");

        newGame = (TextView) findViewById(R.id.tv_new);
        back = (TextView) findViewById(R.id.tv_back);
        exit = (TextView) findViewById(R.id.tv_exit);
        message = (TextView) findViewById(R.id.tv_message);

        dbHelper = new DBHelper(this);

        boolean checkInsertData = dbHelper.insertData(nick,moves,diff);
        if (checkInsertData)
        {
            Toast.makeText(getApplicationContext(),"Skorun Kaydedildi.",Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Skor Kaydedilirken Bir Hata Olustu.",Toast.LENGTH_SHORT).show();
        }

        message.setText(moves + " hamlede oyunu bitirdiniz...");

        newGame.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (diff.equals("easy"))
                {
                    startActivity( new Intent(getApplicationContext(), EasyModActivity.class).putExtra("nick",nick));
                }
                else if(diff.equals("normal"))
                {
                    startActivity( new Intent(getApplicationContext(), NormalModActivity.class).putExtra("nick",nick));
                }
                else
                {
                    startActivity( new Intent(getApplicationContext(), HardModActivity.class).putExtra("nick",nick));
                }
            }
        });

        back.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity( new Intent(getApplicationContext(), ModActivity.class).putExtra("nick",nick));
            }
        });

        exit.setOnClickListener( new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                intent.addCategory(Intent.CATEGORY_HOME);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });
    }
}