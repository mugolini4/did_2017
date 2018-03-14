package com.example.matil.LAMPApp;

import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.RunnableFuture;

/**
 * Created by matil on 30/11/2017.
 */

public class LampManager {

    private static final LampManager instance = new LampManager();
    private List<Lamp> lampList;

    //private constructor to avoid client applications to use constructor
    private LampManager() {
        lampList = Collections.synchronizedList(new ArrayList<Lamp>());
    }

    public static LampManager getInstance() {
        return instance;
    }

    public List<Lamp> getLamps() {
        //lampList = new ArrayList<>();

        /*lampList.add(new Lamp( "Lame Lamp" , R.drawable.lamp1));
        lampList.add( new Lamp( "Hot Lamp", R.drawable.lamp2));
        lampList.add( new Lamp( "Cat Lamp", R.drawable.cat_lamp ));*/
        return lampList;
    }

    public void addLamp(String lamp_ip, String lamp_name) {
        for (int i = 0; i < lampList.size(); i++) {
            if(lampList.get( i ).getLamp_name().equals( lamp_ip )) {
                return;
            }
        }
        lampList.add( new Lamp(lamp_ip, lamp_name,  R.drawable.cat_lamp) );
    }

    public void discover(final UDPListenerSerivce done) {
        Log.d("!!!debug", "sono in discover");

        //lampList.clear();

        done.startListenForUDPBroadcast();
    }

}
