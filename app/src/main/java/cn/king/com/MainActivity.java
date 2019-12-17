package cn.king.com;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

import cn.king.NativeTools.GenerateDateTools;
import cn.king.NativeTools.inputLibrary;
import cn.king.cn.king.obj.androidInfo;

public class MainActivity extends AppCompatActivity {

    androidInfo info = new androidInfo();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOnClike();

        try {
            inputLibrary.InputLibrary(MainActivity.this);
        } catch (InterruptedException e) {
            Log.i(inputLibrary.TAG, "Write so File Failure");
        }
    }


    /**
     * 设置按钮点击事件
     */
    public void setOnClike() {
        Button btn_SelectProcess = findViewById(R.id.SelectProcess);

        //选择需要hook的指定进程
        btn_SelectProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, selectProcessActivity.class);
                startActivity(intent);
            }
        });

        Button btn_newSystem = findViewById(R.id.NewSystem);

        //一键置入新的设备信息
        btn_newSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //生成随机androidID
                info.setAndroid_id(GenerateDateTools.CreateRandomHexStr(16));
                //生成随机IMEI
                info.setIMEI(GenerateDateTools.CreateRandomDigital(15));
                //置入需要hook的包名
                ArrayList<String> packages = new ArrayList<String>();
                packages.add("com.instagram.android");
                info.setPackages(packages);

                //置入新的设备信息
                boolean State = info.Builder().InputFile(MainActivity.this);
                if (State)
                {
                    Toast.makeText(MainActivity.this,"一键新机成功",Toast.LENGTH_SHORT).show();
                }else
                {
                    Toast.makeText(MainActivity.this,"新机失败:置入数据失败",Toast.LENGTH_SHORT).show();
                }



            }
        });


    }


}
