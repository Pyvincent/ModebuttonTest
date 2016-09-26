package com.wang.vincent.modebuttontest.activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import com.wang.vincent.modebuttontest.R;
import com.wang.vincent.modebuttontest.adapter.MyAdapter;
import com.wang.vincent.modebuttontest.databean.Vend;

import java.util.LinkedList;


/**
 * Created by vincent on 16-9-24.
 */

public class SlaveActivity extends AppCompatActivity {

    private Context mContext;
    private GridView gridView;
    private BaseAdapter mAdapter = null;
    private LinkedList<Vend> mData = null;



    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (this.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            setContentView(R.layout.activity_slave_land);
        }

        else if (this.getResources().getConfiguration().orientation ==Configuration.ORIENTATION_PORTRAIT) {
            setContentView(R.layout.activity_slave_portrait);
        }

        mContext = SlaveActivity.this;
        gridView = (GridView) findViewById(R.id.grid_vend);

        mData = new LinkedList<Vend>();
        mData.add(new Vend(R.mipmap.i1, getResources().getResourceName(R.mipmap.i1).split("/")[1]));
        mData.add(new Vend(R.mipmap.i2, getResources().getResourceName(R.mipmap.i2).split("/")[1]));
        mData.add(new Vend(R.mipmap.i3, getResources().getResourceName(R.mipmap.i3).split("/")[1]));
        mData.add(new Vend(R.mipmap.i4, getResources().getResourceName(R.mipmap.i4).split("/")[1]));
        mData.add(new Vend(R.mipmap.i5, getResources().getResourceName(R.mipmap.i5).split("/")[1]));
        mData.add(new Vend(R.mipmap.i6, getResources().getResourceName(R.mipmap.i6).split("/")[1]));
        mData.add(new Vend(R.mipmap.i7, getResources().getResourceName(R.mipmap.i7).split("/")[1]));
        mData.add(new Vend(R.mipmap.i8, getResources().getResourceName(R.mipmap.i8).split("/")[1]));
        mData.add(new Vend(R.mipmap.i9, getResources().getResourceName(R.mipmap.i9).split("/")[1]));
        mData.add(new Vend(R.mipmap.i10,getResources().getResourceName(R.mipmap.i10).split("/")[1]));
        mData.add(new Vend(R.mipmap.i11,getResources().getResourceName(R.mipmap.i11).split("/")[1]));
        mData.add(new Vend(R.mipmap.i12,getResources().getResourceName(R.mipmap.i12).split("/")[1]));
        mData.add(new Vend(R.mipmap.i13,getResources().getResourceName(R.mipmap.i13).split("/")[1]));


        mAdapter = new MyAdapter<Vend>(mData, R.layout.item_grid_icon) {
            @Override
            public void bindView(ViewHolder viewHolder, Vend obj) {

                viewHolder.setImageResource(R.id.img_vend, obj.getId());
                viewHolder.setText(R.id.txt_icon, obj.getVendName());
            }
        };

        gridView.setAdapter(mAdapter);
        gridView.setOnItemClickListener(new MyOnItemClickListener());



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
                .setName("Slave Page") // TODO: Define a title for the content shown.
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

    class MyOnItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Toast.makeText(mContext, "你点击了~" + position + "~项", Toast.LENGTH_SHORT).show();
           // if(position==0){
                Intent it1=new Intent(SlaveActivity.this, HandlerActivity.class);
                Bundle bd=new Bundle();
                bd.putInt("img",mData.get(position).getId());
                bd.putString("name",mData.get(position).getVendName());
                it1.putExtras(bd);
               // startActivity(new Intent(SlaveActivity.this, HandlerActivity.class));
                startActivity(it1);
                overridePendingTransition(R.anim.fade_in,R.anim.fade_out);
          //  }
        }
    }
}
