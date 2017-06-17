package com.xuliwen.viewtest.imageloader;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.xuliwen.viewtest.R;
import com.xuliwen.viewtest.utils.T;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class DiskLruCacheActivity extends AppCompatActivity {

    private static final long DISK_CACHE_SIZE=1024*1024*50; //MB
    private DiskLruCache mDiskLruCache;
    private ImageView mDiskCacheImageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_disk_lru_cache);

        mDiskCacheImageView= (ImageView) findViewById(R.id.disk_cache_imageView);


        createDisLruCache();
    }

    private void createDisLruCache(){
        File diskCacheDir= getDiskCacheDir(this,"bitmap");
        try {
            mDiskLruCache=DiskLruCache.open(diskCacheDir,getAppVersion(this),1,DISK_CACHE_SIZE);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public File getDiskCacheDir(Context context, String uniqueName) {
        String cachePath;
        if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())
                || !Environment.isExternalStorageRemovable()) {
            cachePath = context.getExternalCacheDir().getPath();//外部SD卡缓存
        } else {
            cachePath = context.getCacheDir().getPath();//内部SD卡缓存
        }

        File file= new File(cachePath + File.separator + uniqueName);
        if(!file.exists()){
            file.mkdirs();
        }
        return file;
    }


    public int getAppVersion(Context context) {
        try {
            PackageInfo info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return info.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 1;
    }




    private boolean downloadUrlToStream(String urlString, OutputStream outputStream) {
        HttpURLConnection urlConnection = null;
        BufferedOutputStream out = null;
        BufferedInputStream in = null;
        try {
            final URL url = new URL(urlString);
            urlConnection = (HttpURLConnection) url.openConnection();
            in = new BufferedInputStream(urlConnection.getInputStream(), 8 * 1024);
            out = new BufferedOutputStream(outputStream, 8 * 1024);
            byte[] byteArray=new byte[1024]; //一次读取 1KB
            int length;
            while ((length = in.read(byteArray)) != -1) {
                out.write(byteArray,0,length);
            }
            return true;// return true;应该写在这里，保证正常下载完后会return true; 如果写在方法的最后面，那么不管是否正常下载完，都会return true;显然不符合逻辑
        } catch (final IOException e) {
            e.printStackTrace();
            return false;//当出现异常的时候 return false
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            try {
                if (out != null) {
                    out.close();
                }
                if (in != null) {
                    in.close();
                }
            } catch (final IOException e) {
                e.printStackTrace();
            }
        }
      //  return false; 也可以将return false 放在这里
    }


    public String hashKeyForDisk(String key) {
        String cacheKey;
        try {
            final MessageDigest mDigest = MessageDigest.getInstance("MD5");
            mDigest.update(key.getBytes());
            cacheKey = bytesToHexString(mDigest.digest());
        } catch (NoSuchAlgorithmException e) {
            cacheKey = String.valueOf(key.hashCode());
        }
        return cacheKey;
    }

    private String bytesToHexString(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < bytes.length; i++) {
            String hex = Integer.toHexString(0xFF & bytes[i]);
            if (hex.length() == 1) {
                sb.append('0');
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public void downloadToDiskCache(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
                    String key = hashKeyForDisk(imageUrl);
                    DiskLruCache.Editor editor = mDiskLruCache.edit(key);
                    if (editor != null) {//如果一个缓存对象正在被编辑，那么editor将返回null
                        OutputStream outputStream = editor.newOutputStream(0);//通过editor去获取一个输出流
                        if (downloadUrlToStream(imageUrl, outputStream)) {
                            editor.commit();//如果下载成功，就提交更改
                        } else {
                            editor.abort();//如果下载失败，就撤销更改
                        }
                    }
                    mDiskLruCache.flush();//将缓存刷新到文件系统中
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void showDiskCache(View view) {
        try {
            String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
            String key = hashKeyForDisk(imageUrl);
            DiskLruCache.Snapshot snapShot = mDiskLruCache.get(key);
            if (snapShot != null) {
                InputStream is = snapShot.getInputStream(0);
                Bitmap bitmap = BitmapFactory.decodeStream(is);
                mDiskCacheImageView.setImageBitmap(bitmap);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void removeADiskCache(View view) {
        try {
            String imageUrl = "http://img.my.csdn.net/uploads/201309/01/1378037235_7476.jpg";
            String key = hashKeyForDisk(imageUrl);
            mDiskLruCache.remove(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getDiskCacheUseSize(View view) {
        long useSize= mDiskLruCache.size()/1024;
        T.toast("缓存目录已使用大小是 ："+useSize+"KB");
    }

    @Override
    protected void onPause() {
        super.onPause();
        try {
            mDiskLruCache.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        try {
            mDiskLruCache.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void cleanDiskCache(View view) {
        try {
            mDiskLruCache.delete();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
