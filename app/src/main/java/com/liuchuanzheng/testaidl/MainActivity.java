package com.liuchuanzheng.testaidl;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.liuchuanzheng.myaidlserver.IMyAidlInterface;

public class MainActivity extends AppCompatActivity {
    private IMyAidlInterface iMyAidlInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //调用远程服务的方法。
                try {
                    iMyAidlInterface.showToast("你好你好!");
                } catch (RemoteException e) {
                    e.printStackTrace();
                }
            }
        });
        Intent intent = new Intent();
        intent.setAction("com.liuchuanzheng.myaidlserver.MyService");
        intent.setPackage("com.liuchuanzheng.myaidlserver");
       bindService(intent, new ServiceConnection() {
           @Override
           public void onServiceConnected(ComponentName name, IBinder service) {
                iMyAidlInterface = IMyAidlInterface.Stub.asInterface(service);
           }

           @Override
           public void onServiceDisconnected(ComponentName name) {

           }
       },BIND_AUTO_CREATE);

    }
}
