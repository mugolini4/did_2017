package com.example.matil.LAMPApp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    LampManager lampManager = LampManager.getInstance();
    RVAdapter adapter;
    BroadcastReceiver updateReceiver;
    IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );

        //intentFilter = new IntentFilter("action_name");

        setContentView( R.layout.activity_main );

        rv = (RecyclerView)findViewById(R.id.recycler_view_lamp_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        adapter = new RVAdapter(lampManager.getLamps());
        rv.setAdapter(adapter);

        //ricerca lampade
        lampManager.discover( new UDPListenerSerivce() );

        /*updateReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                rv.getAdapter().notifyDataSetChanged();
            }
        };*/

        Context context = this;
        int toast_duration = Toast.LENGTH_SHORT;
        String toast_text = "welcome >:|";
        Toast toast = Toast.makeText( context, toast_text, toast_duration );
        toast.show();
        Log.d("!!!debug", "sono in main activity");

        ItemClickSupport.addTo(rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                // useless shit
                Context context = MainActivity.this;
                String text = "ciao " + position;
                Toast toast = Toast.makeText( context, text, Toast.LENGTH_SHORT );
                toast.show();

                Intent detailIntent = new Intent( MainActivity.this, LampDetailActivity.class );
                detailIntent.putExtra( "lamp_name", lampManager.getLamps().get( position ).getLamp_name() );
                detailIntent.putExtra( "lamp_photo_ID", lampManager.getLamps().get( position ).getLamp_image() );
                startActivity( detailIntent );
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        /*lampManager.discover( new UDPListenerSerivce() );
        if(rv.getAdapter() != null && rv != null) {
            rv.getAdapter().notifyDataSetChanged();
        }

        registerReceiver(updateReceiver, intentFilter);*/
    }

    @Override
    protected void onPause() {
        /*if(updateReceiver != null) {
            unregisterReceiver(updateReceiver);
            updateReceiver = null;
        }*/

        super.onPause();
    }
}
