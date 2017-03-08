package com.zhouwei.easyblur;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhouwei.blurlibrary.EasyBlur;

public class MainActivity extends AppCompatActivity {
    private ImageView mImageBg;
    private TextView view;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      //  final ImageView imageView = (ImageView) findViewById(R.id.blur_image);
        mImageBg = (ImageView) findViewById(R.id.image_bg);
        view = (TextView) findViewById(R.id.blur_text);
        final Bitmap bitmap = BitmapFactory.decodeResource(getResources(),R.mipmap.test);

      //  imageView.setImageBitmap(bitmap);

        long startTime = System.currentTimeMillis();

        //Bitmap finalBitmap = EasyBlur.fastBlur(bitmap,scale,20);

        long endTime = System.currentTimeMillis();

        Log.i("zhouwei","cost Time:"+(endTime - startTime));

        mImageBg.setImageBitmap(bitmap);

        mImageBg.getViewTreeObserver().addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {
                mImageBg.getViewTreeObserver().removeOnPreDrawListener(this);
                mImageBg.buildDrawingCache();
                Bitmap bmp = mImageBg.getDrawingCache();
                Bitmap overlay = Bitmap.createBitmap((int) (view.getMeasuredWidth()),
                        (int) (view.getMeasuredHeight()), Bitmap.Config.ARGB_8888);

                Canvas canvas = new Canvas(overlay);

                canvas.translate(-view.getLeft(), -view.getTop());
                canvas.drawBitmap(bmp, 0, 0, null);

                Bitmap finalBitmap = EasyBlur.with(MainActivity.this)
                        .bitmap(overlay) //要模糊的图片
                        .radius(10)//模糊半径
                        .blur();

                view.setBackground(new BitmapDrawable(
                        getResources(), finalBitmap));
                return true;
            }
        });


    }
}
