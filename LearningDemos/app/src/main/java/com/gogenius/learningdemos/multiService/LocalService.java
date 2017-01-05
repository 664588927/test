package com.gogenius.learningdemos.multiService;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.BitmapFactory;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.Toast;

import com.gogenius.learningdemos.MainActivity;
import com.gogenius.learningdemos.R;

/**
 * Created by shijiwei on 2016/9/19.
 */
public class LocalService extends Service {

    private MyBinder mBinder;

    private MyServiceConnection conn;

    private PendingIntent pendingIntent;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        if (mBinder == null) {
            mBinder = new MyBinder();
        }


    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        conn = new MyServiceConnection();

        //开启远程服务
        bindService(new Intent(LocalService.this, RemoteService.class), conn, Context.BIND_IMPORTANT);

        Intent actionIntent = new Intent(this, MainActivity.class);
        actionIntent.putExtra("name", getClass().getSimpleName());
        actionIntent.setAction("1");

        PendingIntent contentIntent = PendingIntent.getActivity
                (this, 0, actionIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        Notification noti =
                new NotificationCompat.Builder(this)
                        .setSmallIcon(R.mipmap.cat)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.bg))
                        .setTicker("服务正在启动")
                        .setContentText("今天有点re啊")
                        .setContentTitle("2016.9.20")
                        .setContentIntent(contentIntent)
                        .setAutoCancel(true)
                        .build();

        startForeground(startId, noti);
        return START_STICKY;
    }


    class MyBinder extends MultiService.Stub {

        @Override
        public void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat, double aDouble, String aString) throws RemoteException {

        }

        @Override
        public boolean alive() throws RemoteException {
            return false;
        }
    }


    class MyServiceConnection implements ServiceConnection {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {

            //连接成功

        }

        @Override
        public void onServiceDisconnected(ComponentName name) {

            //连接异常断开
            Toast.makeText(LocalService.this, "远程服務被杀死", Toast.LENGTH_SHORT).show();

            Log.e("local", System.currentTimeMillis() + "");
            //开启远程服务
            LocalService.this.startService(new Intent(LocalService.this, RemoteService.class));

            //主动连接远程服务
            LocalService.this.bindService(new Intent(LocalService.this, RemoteService.class), conn, Context.BIND_IMPORTANT);

        }
    }

}
