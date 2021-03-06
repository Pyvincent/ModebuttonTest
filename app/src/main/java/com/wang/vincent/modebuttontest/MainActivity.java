package com.wang.vincent.modebuttontest;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.wang.vincent.modebuttontest.activity.ActivityCollector;
import com.wang.vincent.modebuttontest.activity.HandlerActivity;
import com.wang.vincent.modebuttontest.activity.SlaveActivity;

import java.util.LinkedList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    static {
        System.loadLibrary("native-lib");
    }

    MyBRReceiver myBRReceiver;
    AllListItem allListItem;
    private List<ParameDev> mData = null;


    private Context mContext;
    private ParameDevAdapter mAdapter = null;
    private ListView list_param;
    private LinearLayout ly_content;

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mContext = MainActivity.this;
        list_param = (ListView) findViewById(R.id.list_param);
        mData = new LinkedList<ParameDev>();

        allListItem = new AllListItem(list_param, mData, mContext);
        mAdapter = new ParameDevAdapter((LinkedList<ParameDev>) mData, mContext);


        list_param.setAdapter(mAdapter);
        list_param.setOnItemClickListener(new MyOnItemClick());

        /**
         * 接受mode按键的广播事件
         */
        myBRReceiver = new MyBRReceiver();
        IntentFilter itFilter = new IntentFilter();
        itFilter.addAction("android.intent.action.ENG_MODE_SWITCH");
        registerReceiver(myBRReceiver, itFilter);

        /**
         *  ATTENTION: This was auto-generated to implement the App Indexing API.
         *  See https://g.co/AppIndexing/AndroidStudio for more information.
         */

        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myBRReceiver);
    }

    public native String stringFromJNI();

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
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
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0x1234 && resultCode == 0x123) {
            Bundle bd = data.getExtras();
            int imgid = bd.getInt("imgid");
           // ImageView imageView= (ImageView) findViewById(R.id.img_icon);
            //imageView.setImageResource(imgid);

            mData.get(0).setaIcon(imgid);
            mAdapter.notifyDataSetChanged();



        }
    }

    class MyOnItemClick implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(mContext, "你点击了第" + position + "项", Toast.LENGTH_SHORT).show();

            if (position == 0) {
                mAdapter.add(1, new ParameDev("新行" + position, "这是一个添加的行", R.mipmap.ic_launcher));
            }
            if (position == 1) {

                Intent it = new Intent(MainActivity.this, SlaveActivity.class);
                //startActivity(it);
                 startActivityForResult(it, 0x1234);
            }
            if (position == mData.size() + 1) {
                mAdapter.remove(mData.size() - 1);
            }
        }

    }

    //杀死所有activity ，杀死app
    public void AppExit(Context context){
        try {
            ActivityCollector.finishAll();
            ActivityManager activityManager= (ActivityManager) context.getSystemService(
                    Context.ACTIVITY_SERVICE
            );
            activityManager.killBackgroundProcesses(context.getPackageName());
            System.exit(0);
        }catch (Exception ignord){

        }
    }

}
