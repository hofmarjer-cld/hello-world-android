package com.example.helloworld;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        TextView textView = new TextView(this);
        textView.setText("Hello World from GitHub Actions!");
        textView.setTextSize(24);
        textView.setPadding(50, 100, 50, 50);
        
        setContentView(textView);
    }
}