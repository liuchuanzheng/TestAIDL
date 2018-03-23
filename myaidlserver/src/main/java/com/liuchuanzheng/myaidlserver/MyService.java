package com.liuchuanzheng.myaidlserver;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case 1:
                    Toast.makeText(MyService.this,msg.obj+"",Toast.LENGTH_SHORT).show();
                    break;
            }

            super.handleMessage(msg);
        }
    };
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.

        return new MyBinder();
    }

    class MyBinder extends IMyAidlInterface.Stub{

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public void showToast(String str) throws RemoteException {
            Log.i("liuchuanzheng","被调用了"+str);
            Message message = new Message();
            message.what = 1;
            message.obj = str;
            handler.sendMessage(message);

        }
    }
}
