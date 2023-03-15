package com.example.myapplication;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView text;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = (TextView) findViewById(R.id.text1);
        handler = new Handler(Looper.myLooper()) {   // создание хэндлера
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
//                msg.
                text.setText(text.getText().toString() + msg.what);
//                text.invalidate();
            }
        };
        AnotherThread t = new AnotherThread(2);//создание потока
        t.start();//запуск потока
    }

    class AnotherThread
            //implements Runnable{
            extends Thread {
        AnotherThread(){}
        AnotherThread(int x){y=x;}
        int y;
        @Override
        public void run() {
            for (int i = 0; i < y; i++) {
                try {
                    Thread.sleep(1000); //Приостанавливает поток на 1 секунду
                } catch (InterruptedException e) {
                }
                handler.sendEmptyMessage(2);  // отправка сообщения хендлеру
            }
        }
    }
}