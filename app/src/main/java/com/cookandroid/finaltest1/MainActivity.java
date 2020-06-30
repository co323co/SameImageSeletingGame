package com.cookandroid.finaltest1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] ivID = {R.id.iv1, R.id.iv2, R.id.iv3, R.id.iv4,
                    R.id.iv5, R.id.iv6, R.id.iv7, R.id.iv8,
                    R.id.iv9, R.id.iv10, R.id.iv11, R.id.iv12,
                    R.id.iv13, R.id.iv14, R.id.iv15, R.id.iv16};

    int[] picID = {R.drawable.apple, R.drawable.chicken, R.drawable.apple,R.drawable.car,
            R.drawable.chicken, R.drawable.banana, R.drawable.flag, R.drawable.candy,
            R.drawable.car, R.drawable.tiger, R.drawable.flag, R.drawable.banana,
            R.drawable.candy, R.drawable.flower,R.drawable.tiger, R.drawable.flower};

    boolean[] checked = new boolean[16];

    ImageView[] iv = new ImageView[16];
    int select1, select2;
    int clickNum = 0, correctNum=0;

    Chronometer chrono;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("SameImageSeletingGame");

        chrono = (Chronometer)findViewById(R.id.chrono);
        chrono.setBase(SystemClock.elapsedRealtime());
        chrono.start();

        for(int i=0; i<16; i++) {

            iv[i]= (ImageView)findViewById(ivID[i]);

            final int index = i;

            iv[index].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(checked[index]==true) return;

                    iv[index].setImageResource(picID[index]);
                    checked[index] = true;
                    clickNum++;

                    if(clickNum%2 == 1) select1 = index;
                    //두번째 클릭
                    else
                    {
                        select2 = index;

                        //못맞출 경우
                        if(picID[select1]!=picID[select2])
                        {
                            iv[select1].setImageResource(R.drawable.android_background);
                            iv[select2].setImageResource(R.drawable.android_background);
                            checked[select1] = false;
                            checked[select2] = false;
                        }
                        else
                            correctNum++;

                    }

                    //Toast.makeText(getApplicationContext(),select1+","+select2,Toast.LENGTH_SHORT).show();

                    if(correctNum==8)
                    {
                        chrono.stop();
                        Toast.makeText(getApplicationContext(),"게임 종료 기록:"+
                                (SystemClock.elapsedRealtime()-chrono.getBase()),Toast.LENGTH_SHORT).show();
                    }


                }
            });

        }




    }
}
