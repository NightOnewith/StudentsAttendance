package com.yzj.studentsattendance.fragments;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.yzj.studentsattendance.activitys.MainActivity;

import com.yzj.studentsattendance.R;

import com.yzj.studentsattendance.network.AttendanceService;
import com.yzj.studentsattendance.network.MyWifiManager;

public class AttendanceFragment extends Fragment implements MainActivity.OnMenuClick, View.OnClickListener, MyWifiManager.OnConnectSuccess {
    private View view;
    private Button btn_connect;
    private MyWifiManager myWifiManager;
    private Button btn_start;
    Handler handler;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (myWifiManager == null)
            myWifiManager = new MyWifiManager(getContext(), this);
        if (handler == null)
            handler = new Handler(Looper.getMainLooper()) {
                @Override
                public void handleMessage(Message msg) {
                    super.handleMessage(msg);
                    if (msg.what == 1)
                        Toast.makeText(getContext(), "签到成功", Toast.LENGTH_SHORT).show();
                    else {
                        Toast.makeText(getContext(), "签到失败 " + msg.getData().getString("EXTRA_INFO"), Toast.LENGTH_SHORT).show();
                    }
                }
            };

    }

    @Override
    public void onResume() {
        super.onResume();
        btn_start.setEnabled(myWifiManager.isConnectCorrect());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_attendance, container, false);
        btn_connect = (Button) view.findViewById(R.id.fragment_attendance_btn_connect);
        btn_connect.setOnClickListener(this);

        btn_start = (Button) view.findViewById(R.id.fragment_attendance_btn_start);
        btn_start.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            //签到按钮
            case R.id.fragment_attendance_btn_start:
                SharedPreferences pref = this.getContext().getSharedPreferences("userInfo", Context.MODE_WORLD_READABLE);
                String username = pref.getString("name", "");
                String usernumber = pref.getString("number", "");
                new AttendanceService(myWifiManager.getServeIp(), 12369, usernumber, username, handler).start();
                break;
            //连接按钮
            case R.id.fragment_attendance_btn_connect:
                if (!myWifiManager.isConnectCorrect())
                    Toast.makeText(getContext(), "尝试连接", Toast.LENGTH_SHORT).show();
                myWifiManager.startAttend();
                break;
        }
    }

    @Override
    public void onConnectSuccess(boolean state) {
        btn_start.setEnabled(state);
    }

    @Override
    public void isWifiChange() {
        if (myWifiManager.getManager().getWifiState() != WifiManager.WIFI_STATE_ENABLED) {
            btn_connect.setText("打开WIFI");
        } else {
            if (!myWifiManager.isConnectCorrect())
                btn_connect.setText("连接教师端");
            else
                btn_connect.setText("已连接");
        }
    }

    @Override
    public void onMenuClick() {
    }
}
