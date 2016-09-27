package com.wang.vincent.modebuttontest.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.PointF;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.wang.vincent.modebuttontest.R;
import com.wang.vincent.modebuttontest.jumpingbeans.JumpingBeans;

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
    private JumpingBeans jumpingBeans1,jumpingBeans2;


    int imgid[] = new int[]{
            R.mipmap.i2,
            R.mipmap.i3,
            R.mipmap.i4,
            R.mipmap.i5,
            R.mipmap.i6,
            R.mipmap.i7,
            R.mipmap.i1,
    };
    ImageView imgchange;
    TextView other_icon;
    int imgstart = 0;
    /*
    final Handler myHandler = new Handler() {
        //重写handleMessage方法,根据msg中what的值判断是否执行后续操作
        public void handleMessage(Message msg) {
            if (msg.what == 0x123) {
                imgchange.setImageResource(imgid[imgstart++ % 7]);
                // mData.getFirst().setId(imgid[imgstart++ % 7]);
            }
        }
    };
    */
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.activity_handler);
        mContext = HandlerActivity.this;
        other_icon = (TextView) findViewById(R.id.other_icon);




        imgchange = (ImageView) findViewById(R.id.imgchange);


        Intent it2 = getIntent();
        Bundle bd = it2.getExtras();
        int img = bd.getInt("img");
        String name = bd.getString("name");
        imgchange.setImageResource(img);
        other_icon.setText(name);


        //摇摆
        TranslateAnimation alphaAnimation2 = new TranslateAnimation(150f, 350f, 50, 50);
        alphaAnimation2.setDuration(1000);
        alphaAnimation2.setRepeatCount(Animation.INFINITE);
        alphaAnimation2.setRepeatMode(Animation.REVERSE);
        imgchange.setAnimation(alphaAnimation2);
        alphaAnimation2.start();

         /*
        //闪烁
        AlphaAnimation alphaAnimation1 = new AlphaAnimation(0.1f, 1.0f);
        alphaAnimation1.setDuration(3000);
        alphaAnimation1.setRepeatCount(Animation.INFINITE);
        alphaAnimation1.setRepeatMode(Animation.REVERSE);
        imgchange.setAnimation(alphaAnimation1);
        alphaAnimation1.start();

         jumpingBeans2 = JumpingBeans.with(other_icon)
                .makeTextJump(0, other_icon.getText().toString().indexOf(' '))
                .setIsWave(false)
                .setLoopDuration(1000)
                .build();
         */



        jumpingBeans1 = JumpingBeans.with(other_icon)
                .appendJumpingDots()
                .build();


        /*

        //使用定时器,每隔200毫秒让handler发送一个空信息
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                myHandler.sendEmptyMessage(0x123);

            }
        }, 0, 1000);

        */
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Handler Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    protected void onPause() {
        super.onPause();
        jumpingBeans1.stopJumping();
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    class MyonTouch implements View.OnTouchListener {

        @Override
        public boolean onTouch(View v, MotionEvent event) {
            ImageView view = (ImageView) v;
            switch (event.getAction() & MotionEvent.ACTION_MASK) {
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
        double v = Math.sqrt(x * x + y + y);
        return v;
    }

    private PointF middle(MotionEvent event) {
        float x = event.getX(0) + event.getX(1);
        float y = event.getY(0) + event.getY(1);
        return new PointF(x / 2, y / 2);
    }
}
/**
 * intent传递复杂数据。
 * 传递复杂些的参数
 * Map<String, Object> map1 = new HashMap<String, Object>();
 * map1.put("key1", "value1");
 * map1.put("key2", "value2");
 * List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
 * list.add(map1);
 * <p>
 * Intent intent = new Intent();
 * intent.setClass(MainActivity.this,ComplexActivity.class);
 * Bundle bundle = new Bundle();
 * <p>
 * //须定义一个list用于在budnle中传递需要传递的ArrayList<Object>,这个是必须要的
 * ArrayList bundlelist = new ArrayList();
 * bundlelist.add(list);
 * bundle.putParcelableArrayList("list",bundlelist);
 * intent.putExtras(bundle);
 * startActivity(intent);
 * <p>
 * 1）List<基本数据类型或String>
 * 写入集合：
 * intent.putStringArrayListExtra(name, value)
 * intent.putIntegerArrayListExtra(name, value)
 * 读取集合：
 * intent.getStringArrayListExtra(name)
 * intent.getIntegerArrayListExtra(name)
 * <p>
 * 2）List< Object>
 * 将list强转成Serializable类型,然后传入(可用Bundle做媒介)
 * 写入集合：
 * putExtras(key, (Serializable)list)
 * 读取集合：
 * (List<Object>) getIntent().getSerializable(key)
 * PS:Object类需要实现Serializable接口
 * <p>
 * 写入数组：
 * bd.putStringArray("StringArray", new String[]{"呵呵","哈哈"});
 * //可把StringArray换成其他数据类型,比如int,float等等...
 * 读取数组：
 * String[] str = bd.getStringArray("StringArray")
 * <p>
 * <p>
 * Bitmap bitmap = null;
 * Intent intent = new Intent();
 * Bundle bundle = new Bundle();
 * bundle.putParcelable("bitmap", bitmap);
 * intent.putExtra("bundle", bundle);
 */
