package com.example.matil.LAMPApp;

import android.util.Log;

import java.util.ArrayList;

/**
 * Created by matil on 30/11/2017.
 */

public class LampManager {

    private static final LampManager instance = new LampManager();
    private ArrayList<Lamp> lampList;

    //private constructor to avoid client applications to use constructor
    private LampManager() {
    }

    public static LampManager getInstance() {
        return instance;
    }

    public ArrayList<Lamp> getLamps() {
        lampList = new ArrayList<>();

        lampList.add(new Lamp( "Lame Lamp" , R.drawable.lamp1));
        lampList.add( new Lamp( "Hot Lamp", R.drawable.lamp2));
        lampList.add( new Lamp( "Cat Lamp", R.drawable.cat_lamp ));
        return lampList;
    }

    public void discover(Runnable done) {
        Log.d("!!!debug", "sono in discover");

        done.run();
    }

}
