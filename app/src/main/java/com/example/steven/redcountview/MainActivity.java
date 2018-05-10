package com.example.steven.redcountview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RedCountView countView1 = findViewById(R.id.red_count_view1);
        Log.e("zxf", "8 start");
        countView1.setCount(8);
        Log.e("zxf", "8 end");

        RedCountView countView2 = findViewById(R.id.red_count_view2);
        Log.e("zxf", "99 start");
        countView2.setCount(99);
        Log.e("zxf", "99 end");


        RedCountView countView3 = findViewById(R.id.red_count_view3);
        Log.e("zxf", "onMeasure");
        countView3.setCount(998);
        Log.e("zxf", "onMeasure");

        RedCountView countView4 = findViewById(R.id.red_count_view4);
        countView4.setCount(1000);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e("zxf", "onResume");

    }
}
