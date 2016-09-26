package com.wang.vincent.modebuttontest.activity;

import android.content.Context;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.wang.vincent.modebuttontest.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by vincent on 16-9-25.
 */

public class HandlerActivity extends AppCompatActivity {

    private Context mContext;

    //缩放控制
    private Matrix matrix = new Matrix();
    private Matrix savedMatrix = new Matrix();

    // 不同状态的表示：
    private static final int NONE = 0;
    private static final int DRAG = 1;
    private static final int ZOOM = 2;
    private int mode = NONE;

    // 定义第一个按下的点，两只接触点的重点，以及出事的两指按下的距离：
    private PointF startPoint = new PointF();
    private PointF midPoint = new PointF();
    private double oriDis = 1f;


    int imgid[] = new int[]{
            R.mipmap.i1,
            R.mipmap.i2,
            R.mipmap.i3,
            R.mipmap.i4,
            R.mipmap.i5,
            R.mipmap.i6,
            R.mipmap.i7,
    };
    ImageView imgchange;
    int imgstart = 0;
    final Handler myHandler = new Handler() {
        //重写handleMessage方法,根据msg中what的值判断是否执行后续操作
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                imgchange.setImageResource(imgid[imgstart++ % 7]);
                // mData.getFirst().setId(imgid[imgstart++ % 7]);
            }
        }
    };


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_handler);
        mContext = HandlerActivity.this;
        imgchange = (ImageView) findViewById(R.id.imgchange);

        //使用定时器,每隔200毫秒让handler发送一个空信息
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                myHandler.sendEmptyMessage(0x123);

            }
        }, 0, 1000);
        imgchange.setOnTouchListener(new MyonTouch());
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    class MyonTouch implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ImageView view= (ImageView) v;
            switch (event.getAction()&MotionEvent.ACTION_MASK){
                // 单指
                case MotionEvent.ACTION_DOWN:
                    matrix.set(view.getImageMatrix());
                    savedMatrix.set(matrix);
                    startPoint.set(event.getX(), event.getY());
                    mode = DRAG;
                    break;
                // 双指
                case MotionEvent.ACTION_POINTER_DOWN:
                    oriDis = distance(event);
                    if (oriDis > 10f) {
                        savedMatrix.set(matrix);
                        midPoint = middle(event);
                        mode = ZOOM;
                    }
                    break;
                // 手指放开
                case MotionEvent.ACTION_UP:
                case MotionEvent.ACTION_POINTER_UP:
                    mode = NONE;
                    break;
                // 单指滑动事件
                case MotionEvent.ACTION_MOVE:
                    if (mode == DRAG) {
                        // 是一个手指拖动
                        matrix.set(savedMatrix);
                        matrix.postTranslate(event.getX() - startPoint.x, event.getY() - startPoint.y);
                    } else if (mode == ZOOM) {
                        // 两个手指滑动
                        double newDist = distance(event);
                        if (newDist > 10f) {
                            matrix.set(savedMatrix);
                            double scale = newDist / oriDis;
                            matrix.postScale((float) scale, (float) scale, midPoint.x, midPoint.y);
                        }
                    }
                    break;
            }

            view.setImageMatrix(matrix);
            return true;
        }
    }

    //计算两点之间的距离
    private double distance(MotionEvent event) {
        float x = event.getX(0) - event.getX(1);
        float y = event.getY(0) - event.getY(1);
        double v=Math.sqrt(x * x + y + y);
        return v;
    }

    private PointF middle(MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        return new PointF(x / 2, y / 2);
    }
}
