package com.yzj.studentsattendance.activitys;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yzj.studentsattendance.R;


public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_about_back);
        TextView tv_content= (TextView) findViewById(R.id.activity_about_tv_content);
        tv_content.setText("制作团队：我们要坚强\n团队成员：殷志俊");
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
