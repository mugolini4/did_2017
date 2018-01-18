package com.example.matil.LAMPApp;

import android.content.Context;
import android.content.Intent;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );

        final ArrayList<Lamp> lampList = LampManager.getInstance().getLamps();

        /*ArrayList<String> names_list = new ArrayList<String>();
        for (int i=0; i<lampList.size(); i++) {
            names_list.add( lampList.get( i ).getLamp_name() );
            //System.out.println(lampList.get( i ).getLamp_name());
        }*/

        RecyclerView rv = (RecyclerView)findViewById(R.id.recycler_view_lamp_list);
        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        RVAdapter adapter = new RVAdapter(lampList);
        rv.setAdapter(adapter);

        /*ArrayAdapter<String> ad = new ArrayAdapter<String>(
                this,
                R.layout.list_item_lamp,
                R.id.text_view_lamp,
                names_list);

        ListView listView = (ListView) this.findViewById( R.id.list_view_lamp );
        listView.setAdapter( ad );*/

        Context context = this;
        int toast_duration = Toast.LENGTH_SHORT;
        String toast_text = "welcome >:|";
        Toast toast = Toast.makeText( context, toast_text, toast_duration );
        toast.show();
        Log.d("!!!debug", "sono in main activity");
        LampManager.getInstance().discover( new UDPListener() );

        ItemClickSupport.addTo(rv).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                // useless shit
                Context context = MainActivity.this;
                String text = "ciao " + position;
                Toast toast = Toast.makeText( context, text, Toast.LENGTH_SHORT );
                toast.show();

                /*Intent detailIntent = new Intent( MainActivity.this, LampDetailActivity.class );
                detailIntent.putExtra( "lamp_name", lampList.get( position ).getLamp_name() );
                detailIntent.putExtra( "lamp_photo_ID", lampList.get( position ).getLamp_image() );
                startActivity( detailIntent );*/
            }
        });

    }
}
