package com.xuliwen.viewtest.notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RemoteViews;

import com.xuliwen.viewtest.MainActivity;
import com.xuliwen.viewtest.R;
import com.xuliwen.viewtest.utils.T;

public class NotificationTestActivity extends AppCompatActivity {

    private NotificationCompat.Builder builder;
    private String bigText="bigTextbigTextbigTextbigTextbigTextbigTextbigTextbigText";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_test);

        toastIntentExtra();
    }

    private void toastIntentExtra(){
        Intent intent=getIntent();
        if(intent.getStringExtra("pendingIntentTest")!=null){
            T.toast(intent.getStringExtra("pendingIntentTest"));
        }
    }

    public void commonNotification(View view) {
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("我是普通通知的标题")
                .setContentText("我是普通通知的内容");
        notificationManager.notify(1,builder.build());//id标志一个通知，使用相同的id就可以更新通知
    }


    public void updateNotification(View view) {
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("我是更新的通知的标题")
                .setContentText("我是更新的通知的内容");
        notificationManager.notify("2",1,builder.build());//和notificationManager.notify(1,builder.build());不冲突，因为含有tag
    }



    public void intentNotification(View view) {
        Intent intent=new Intent(this,NotificationTestActivity.class);
        PendingIntent pendingIntent=PendingIntent.getActivity(this,0,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("我是intent通知的标题")
                .setContentText("我是intent通知的内容")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent);
        notificationManager.notify(1,builder.build());//id标志一个通知，使用相同的id就可以更新通知
    }

    public void deleteNotification(View view) {
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        //notificationManager.cancelAll();
        //notificationManager.cancel(1);
        notificationManager.cancel("2",2);
    }

    public void customNotification(View view) {
        PendingIntent pendingIntent2NotificationTestActivity=PendingIntent.getActivity(this,0,new Intent(this,NotificationTestActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent2MainActivity=PendingIntent.getActivity(this,0,new Intent(this, MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);

        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        RemoteViews remoteViews=new RemoteViews(getPackageName(),R.layout.layout_custom_notification);
        remoteViews.setTextViewText(R.id.notify_textView,"我是自定义通知");
        remoteViews.setImageViewResource(R.id.notify_imageView,R.drawable.ic_chrome);
        remoteViews.setOnClickPendingIntent(R.id.open_activity,pendingIntent2NotificationTestActivity);

        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setContent(remoteViews)
                .setContentIntent(pendingIntent2MainActivity)
                .setSmallIcon(R.mipmap.ic_launcher);

        notificationManager.notify(3,builder.build());

    }

    public void ensureProgressBarNotification(View view) {
        final NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setOngoing(true);

        new Thread(){
            @Override
            public void run() {
               for(int i=0;i<=100;i++){
                   i+=10;
                   builder.setProgress(100,i,false);
                   try {
                       Thread.sleep(500);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                   notificationManager.notify(4,builder.build());
               }
                builder.setContentText("下载完成")
                .setProgress(0,0,false)
                . setOngoing(false);  //移除进度条
                notificationManager.notify(4,builder.build());
            }
        }.start();
    }

    public void notEnsureProgressBarNotification(View view) {
        final NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setContentTitle("Picture Download")
                .setContentText("Download in progress")
                .setSmallIcon(R.mipmap.ic_launcher);

        new Thread(){
            @Override
            public void run() {
                for(int i=0;i<=100;i++){
                    i+=10;
                    builder.setProgress(0,0,true);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    notificationManager.notify(5,builder.build());
                }
                builder.setContentText("下载完成")
                        .setProgress(0,0,false);  //移除进度条
                notificationManager.notify(5,builder.build());
            }
        }.start();
    }

    public void floatNotification(View view) {
         NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
       PendingIntent pendingIntent2NotificationTestActivity=PendingIntent.getActivity(this,0,new Intent(this,NotificationTestActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
         NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setContentTitle("我是浮动通知标题")
                .setContentText("我是浮动通知")
                .setFullScreenIntent(pendingIntent2NotificationTestActivity,false)
                .setSmallIcon(R.mipmap.ic_launcher);

        notificationManager.notify(6,builder.build());
    }

    public void foldingNotification(View view) {
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        RemoteViews remoteViews=new RemoteViews(getPackageName(),R.layout.layout_folding_notification);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setContentTitle("我是浮动通知标题")
                .setContentText("我是浮动通知")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setCustomBigContentView(remoteViews);
        notificationManager.notify(7,builder.build());
    }

    public void lockNotification(View view) {
        final NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        final NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setContentTitle("我是锁屏or非锁屏通知标题")
                .setContentText("我是锁屏or非锁屏通知")
                .setSmallIcon(R.mipmap.ic_launcher)
                //.setVisibility(NotificationCompat.VISIBILITY_PRIVATE);
                .setVisibility(NotificationCompat.VISIBILITY_PUBLIC);
        new Thread(){
            @Override
            public void run() {
                try {
                    Thread.sleep(4000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                notificationManager.notify(8,builder.build());
            }
        }.start();

    }

    public void bigShowNotification(View view) {
        PendingIntent pendingIntent2NotificationTestActivity=PendingIntent.getActivity(this,0,new Intent(this,NotificationTestActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);
        PendingIntent pendingIntent2MainActivity=PendingIntent.getActivity(this,0,new Intent(this, MainActivity.class),PendingIntent.FLAG_UPDATE_CURRENT);

         NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
         NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setContentTitle("我是大视图通知标题")
                .setContentText("我是大视图通知")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setStyle(new NotificationCompat.BigTextStyle().bigText(bigText))
                .addAction(R.drawable.xunlei,"2notify",pendingIntent2NotificationTestActivity)
                .addAction(R.mipmap.ic_launcher,"2main",pendingIntent2MainActivity);
        notificationManager.notify(9,builder.build());

    }

    public void testSoundNotification(View view) {
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setContentTitle("测试通知的震动，声音")
                .setContentText("测试通知的震动，声音")
                .setVibrate(new long[]{100, 300, 400, 800})
               // .setSound(Uri.parse("android.resource://" + getPackageName() + "/" +R.raw.notification))
                .setLights(0xff00ff00,0,2000)
                .setDefaults(NotificationCompat.FLAG_SHOW_LIGHTS)
                .setSmallIcon(R.mipmap.ic_launcher);
        Notification notification=builder.build();
        notification.flags=NotificationCompat.FLAG_SHOW_LIGHTS;
        notificationManager.notify(10,builder.build());
    }

    public void pendingIntentTest1(View view) {
        Intent intent2NotificationTestActivity=new Intent(this,NotificationTestActivity.class);
        intent2NotificationTestActivity.putExtra("pendingIntentTest","pendingIntentTest1");
        PendingIntent pendingIntent2NotificationTestActivity=PendingIntent.getActivity(this,0,intent2NotificationTestActivity,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setContentTitle("我是pendingIntentTest1通知标题")
                .setContentText("我是pendingIntentTest1通知")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent2NotificationTestActivity);
        notificationManager.notify(11,builder.build());
    }

    public void pendingIntentTest2(View view) {
        Intent intent2NotificationTestActivity=new Intent(this,NotificationTestActivity.class);
        intent2NotificationTestActivity.putExtra("pendingIntentTest","pendingIntentTest2");
        PendingIntent pendingIntent2NotificationTestActivity=PendingIntent.getActivity(this,0,intent2NotificationTestActivity,PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationManager notificationManager= (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder builder=new NotificationCompat.Builder(this)
                .setContentTitle("我是pendingIntentTest2通知标题")
                .setContentText("我是pendingIntentTest2通知")
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(pendingIntent2NotificationTestActivity);
        notificationManager.notify(12,builder.build());
    }
}
