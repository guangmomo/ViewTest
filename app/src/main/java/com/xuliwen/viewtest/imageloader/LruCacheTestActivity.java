package com.xuliwen.viewtest.imageloader;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.xuliwen.viewtest.R;
import com.xuliwen.viewtest.utils.L;

public class LruCacheTestActivity extends AppCompatActivity {
    private LruCache<String, Bitmap> mMemroyCache;
    private ImageView lruCacheImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lru_cache_test);

        lruCacheImageView= (ImageView) findViewById(R.id.lru_cache_imageView);

        lruCacheTest();

    }

    private void lruCacheTest(){
        int maxMemory= (int) ((Runtime.getRuntime().maxMemory())/1024);
        int cacheSize=maxMemory/8;
        mMemroyCache=new LruCache<String,Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap value) {
                int size=value.getRowBytes() * value.getHeight()/1024;
                L.log(key+" size: "+size +"KB");
                return size;
            }

            @Override
            protected void entryRemoved(boolean evicted, String key, Bitmap oldValue, Bitmap newValue) {
                L.log("移除旧缓存："+key);
                super.entryRemoved(evicted, key, oldValue, newValue);
            }
        };

    }

    public void addLruCache(View view) {
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.big);
        mMemroyCache.put("缓存1",bitmap);
    }

    public void removeLruCache(View view) {
        mMemroyCache.remove("缓存1");
    }

    public void getLruCache(View view) {
            lruCacheImageView.setImageBitmap(mMemroyCache.get("缓存1"));
    }
}
