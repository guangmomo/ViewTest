package com.xuliwen.viewtest.bitmap;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;


import com.xuliwen.viewtest.R;
import com.xuliwen.viewtest.utils.L;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class BitmapActivity extends AppCompatActivity {
    private ImageView mBitmapTestImageView;
    private String sdPath=Environment.getExternalStorageDirectory().getAbsolutePath();
    private String imagePath=sdPath+File.separator+"bitmap_test.png";
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bitmap);

        mBitmapTestImageView= (ImageView) findViewById(R.id.bitmap_test_imageView);

    }







    public void decodeFromRes(View view) {
        bitmap=BitmapUtil.decodeResource(R.drawable.bitmap_test);
        L.log("decodeFromRes  width: "+bitmap.getWidth());
        L.log("decodeFromRes  height: "+bitmap.getHeight());
        L.log("decodeFromRes: "+String.valueOf(bitmap.getByteCount()/1024/1024));
        mBitmapTestImageView.setImageBitmap(bitmap);
    }

    public void decodeFromInputStream(View view) {
        bitmap=BitmapUtil.decodeStream(imagePath);
        L.log("decodeFromInputStream  width: "+bitmap.getWidth());
        L.log("decodeFromInputStream  height: "+bitmap.getHeight());
        L.log("decodeFromInputStream: "+String.valueOf(bitmap.getByteCount()/1024/1024));
        mBitmapTestImageView.setImageBitmap(bitmap);
    }

    public void decodeFromFile(View view) {
        bitmap=BitmapUtil.decodeFile(imagePath);
        L.log("decodeFromFile: "+String.valueOf(bitmap.getByteCount()/1024/1024));
        mBitmapTestImageView.setImageBitmap(bitmap);
    }

    public void decodeFromByteArray(View view) {
        bitmap=BitmapUtil.decodeByteArray(imagePath);
        L.log("decodeFromByteArray: "+String.valueOf(bitmap.getByteCount()/1024/1024));
        mBitmapTestImageView.setImageBitmap(bitmap);
    }

    public void decodeFromFD(View view) {
        bitmap=BitmapUtil.decodeFD(imagePath);
        L.log("decodeFromFD: "+String.valueOf(bitmap.getByteCount()/1024/1024));
        mBitmapTestImageView.setImageBitmap(bitmap);
    }

    public void decodeSampleFromFD(View view) {
        mBitmapTestImageView.post(new Runnable() {
            @Override
            public void run() {
                int width=mBitmapTestImageView.getMeasuredWidth();
                int height=mBitmapTestImageView.getMeasuredHeight();
                FileInputStream fileInputStream=null;
                FileDescriptor fileDescriptor=null;
                try {
                    fileInputStream=new FileInputStream(sdPath+File.separator+"bitmap_test.png");
                    fileDescriptor=fileInputStream.getFD();
                    bitmap=BitmapUtil.decodeSampledBitmapFromFileDescriptor(fileDescriptor,width,height);
                } catch (IOException e) {
                    e.printStackTrace();
                }finally {
                    if(fileInputStream!=null){
                        try {
                            fileInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
                L.log("decodeSampleFromFD: "+String.valueOf(bitmap.getByteCount()/1024/1024));
                mBitmapTestImageView.setImageBitmap(bitmap);
            }
        });
    }

    public void decodeSampleFromRes(View view) {
        mBitmapTestImageView.post(new Runnable() {
            @Override
            public void run() {
                int width=mBitmapTestImageView.getMeasuredWidth();
                int height=mBitmapTestImageView.getMeasuredHeight();
                bitmap=BitmapUtil.decodeSampledBitmapFromResource(getResources(),R.drawable.bitmap_test,width,height);
                L.log("decodeSampleFromRes: "+String.valueOf(bitmap.getByteCount()/1024/1024));
                mBitmapTestImageView.setImageBitmap(bitmap);
            }
        });
    }

    public void decodeSampleFromFile(View view) {
        mBitmapTestImageView.post(new Runnable() {
            @Override
            public void run() {
                int width=mBitmapTestImageView.getMeasuredWidth();
                int height=mBitmapTestImageView.getMeasuredHeight();
                bitmap=BitmapUtil.decodeSampledBitmapFromFile(imagePath,width,height);
                L.log("decodeSampleFromFile: "+String.valueOf(bitmap.getByteCount()/1024/1024));
                mBitmapTestImageView.setImageBitmap(bitmap);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        BitmapUtil.recycle(bitmap);
    }

    public void decodeSampleFromByteArray(View view) {
        mBitmapTestImageView.post(new Runnable() {
            @Override
            public void run() {
                int width=mBitmapTestImageView.getMeasuredWidth();
                int height=mBitmapTestImageView.getMeasuredHeight();
                FileInputStream fileInputStream= null;
                try {
                    fileInputStream = new FileInputStream(imagePath);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
                byte[] buffer=new byte[1024];
                int len=-1;
                ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
                try {
                    while((len=fileInputStream.read(buffer))!=-1){
                        byteArrayOutputStream.write(buffer,0,len);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    byteArrayOutputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                byte[] data=byteArrayOutputStream.toByteArray();
                bitmap=BitmapUtil.decodeSampledBitmapFromBitArray(data,width,height);
                L.log("decodeSampleFromByteArray: "+String.valueOf(bitmap.getByteCount()/1024/1024));
                mBitmapTestImageView.setImageBitmap(bitmap);
            }
        });
    }

    public void recycleBitmap(View view) {
        BitmapUtil.recycle(bitmap);
    }

    public void printDisplayMetrics(View view) {
        DisplayMetrics metric = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metric);

        int width = metric.widthPixels;  // 屏幕宽度（像素）
        int height = metric.heightPixels;  // 屏幕高度（像素）
        float density = metric.density;  // 屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        L.log("屏幕宽度： "+width);
        L.log("屏幕高度： "+height);
        L.log("屏幕密度： "+density);
        L.log("dpi: "+densityDpi);

    }
}
