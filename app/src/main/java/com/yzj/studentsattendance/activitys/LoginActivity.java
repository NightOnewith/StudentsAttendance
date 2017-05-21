package com.yzj.studentsattendance.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.yzj.studentsattendance.R;

public class LoginActivity extends AppCompatActivity {

    private EditText edt_name;
    private EditText edt_number;
    private Button btn_save;

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_login_toolbar);
        setSupportActionBar(toolbar);

        edt_name = (EditText) findViewById(R.id.activity_login_edt_name);
        edt_number = (EditText) findViewById(R.id.activity_login_edt_number);
        LinearLayout layout = (LinearLayout) findViewById(R.id.activity_login_back);
        btn_save = (Button) findViewById(R.id.activity_login_btn_save);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edt_name.getText().toString();
                String number = edt_number.getText().toString();
                pref = getSharedPreferences("userInfo", Context.MODE_PRIVATE);
                editor = pref.edit();
                editor.putString("name", name);
                editor.putString("number", number);
                editor.commit();
                Toast.makeText(getApplicationContext(),"保存成功", Toast.LENGTH_SHORT).show();
            }
        });
        pref = getSharedPreferences("userInfo", Activity.MODE_PRIVATE);
        String username = pref.getString("name", "");
        String usernumber = pref.getString("number", "");
        edt_name.setText(username);
        edt_number.setText(usernumber);
    }

}


