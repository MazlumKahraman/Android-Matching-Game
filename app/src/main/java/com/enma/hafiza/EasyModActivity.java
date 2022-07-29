package com.enma.hafiza;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import static java.lang.Thread.sleep;

public class EasyModActivity extends AppCompatActivity {
    ImageButton button1,button2,button3,button4,button5,button6;

    private ArrayList<Drawable> images = new ArrayList<Drawable>();

    int [] numbs = new int[3];
    public int moves=0;
    boolean isClicked = false;
    int matchCounter =0;
    ImageButton currentButton;
    TextView txtMoveCounter;

    ImageButton buttons[]={ button1,button2,button3,button4,button5,button6};
    int ids[]={R.id.img_easy1,R.id.img_easy2,R.id.img_easy3,R.id.img_easy4,R.id.img_easy5,R.id.img_easy6};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_mod);

        String nick = getIntent().getExtras().getString("nick");
        txtMoveCounter = (TextView) findViewById(R.id.tv_moves);

        numbs[0] = new Random().nextInt(7) + 1;
        numbs[1] = new Random().nextInt(7) + 1;
        while (numbs[0] == numbs[1] ) {
            numbs[1] = new Random().nextInt(7) + 1;
        }
        numbs[2] = new Random().nextInt(7) + 1;
        while (numbs[2] == numbs[1] || numbs[2] == numbs[0] ) {
            numbs[2] = new Random().nextInt(7) + 1;
        }

        for(int i =0 ; i<3 ;i++)
        {
            for(int j=0; j<2;j++)
            {
                images.add(getResources().getDrawable(getResources().getIdentifier("a"+ numbs[i],"drawable",getPackageName())));
            }
        }

        Collections.shuffle(images);


        for(int i = 0; i< buttons.length;i++)
        {
            buttons[i]=(ImageButton) findViewById(ids[i]);
        }

        for (int i=0;i<buttons.length;i++)
        {
            int finalI = i;
            buttons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    buttons[finalI].setBackground(images.get(finalI));
                    if(!isClicked)
                    {
                        isClicked =true;
                        currentButton = buttons[finalI];
                    }
                    else if(isClicked)
                    {
                        if(currentButton.getBackground().getConstantState().equals(buttons[finalI].getBackground().getConstantState()) && currentButton.getId() != buttons[finalI].getId())
                        {
                            isClicked = false;
                            currentButton.setVisibility(View.INVISIBLE);
                            buttons[finalI].setVisibility(View.INVISIBLE);
                            moves++;
                            matchCounter++;
                            if(matchCounter == 3)
                            {
                                Intent intent = new Intent(getApplicationContext(), CongScreenActivity.class);
                                intent.putExtra("Game_Moves", moves);
                                intent.putExtra("Game_Diff", "easy");
                                intent.putExtra("nick",nick);
                                startActivity(intent);
                            }
                        }
                        else
                        {
                            isClicked = false;
                            moves++;
                            currentButton.setBackground(getResources().getDrawable(getResources().getIdentifier("card","drawable",getPackageName())));
                            buttons[finalI].setBackground(getResources().getDrawable(getResources().getIdentifier("card","drawable",getPackageName())));
                        }
                        txtMoveCounter.setText("Hamle Sayısı :"+moves);
                    }
                }
            });
        }
      }
}