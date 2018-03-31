
package com.example.suhasini.countdowntimer;
import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView time;
    private Button start;
    private Button cancel;
    private CountDownTimer countDownTimer;
    public int counter = 10;
    Button button;
    TextView textView;
    @Override
    protected void onPause() {
        super.onPause();

        ActivityManager activityManager = (ActivityManager) getApplicationContext()
                .getSystemService(Context.ACTIVITY_SERVICE);

        activityManager.moveTaskToFront(getTaskId(), 0);
    }
    private View.OnClickListener btnClickListener =new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.start:
                    start();
                    break;
                case R.id.cancel:
                    cancel();
                    break;
            }
        }
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start= (Button) findViewById(R.id.start);
        start.setOnClickListener(btnClickListener);
        cancel=(Button) findViewById(R.id.cancel);
        cancel.setOnClickListener(btnClickListener);
        time= (TextView) findViewById(R.id.time);
    }
    private void start(){
        time.setText("15min");
        countDownTimer= new CountDownTimer(15*60*1000,1000){
            @Override
            public void onTick(long millisUntilFinished)
            {
                time.setText("Time remaining"+ " " + millisUntilFinished+ ":" + millisUntilFinished/1000);
            }
            @Override
            public void onFinish(){
                time.setText("Done!");
            }
        };
        countDownTimer.start();
    }
    private void cancel(){
        if(countDownTimer!=null){
            countDownTimer.cancel();
            countDownTimer=null;
        }
    }
}
