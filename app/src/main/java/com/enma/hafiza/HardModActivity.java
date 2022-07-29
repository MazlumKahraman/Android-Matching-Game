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

public class HardModActivity extends AppCompatActivity {

    ImageButton button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12,button13,button14,button15,button16;

    private ArrayList<Drawable> images = new ArrayList<Drawable>();

    public int moves=0;
    boolean isClicked = false;
    int matchCounter =0;
    ImageButton currentButton;
    TextView txtMoveCounter;

    ImageButton buttons[]={ button1,button2,button3,button4,button5,button6,button7,button8,button9,button10,button11,button12,button13,button14,button15,button16};
    int ids[]={R.id.img_hard1,R.id.img_hard2,R.id.img_hard3,R.id.img_hard4,R.id.img_hard5,R.id.img_hard6,R.id.img_hard7,R.id.img_hard8,
            R.id.img_hard9,R.id.img_hard10,R.id.img_hard11,R.id.img_hard12,R.id.img_hard13,R.id.img_hard14,R.id.img_hard15,R.id.img_hard16};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hard_mod);
        String nick = getIntent().getExtras().getString("nick");

        txtMoveCounter = (TextView) findViewById(R.id.tv_moves);

        for(int i =0 ; i<8 ;i++)
        {
            for(int j=0; j<2;j++)
            {
                images.add(getResources().getDrawable(getResources().getIdentifier("a"+(i+1),"drawable",getPackageName())));
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
                            if(matchCounter == 8)
                            {
                                Intent intent = new Intent(getApplicationContext(), CongScreenActivity.class);
                                intent.putExtra("Game_Moves", moves);
                                intent.putExtra("Game_Diff", "hard");
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