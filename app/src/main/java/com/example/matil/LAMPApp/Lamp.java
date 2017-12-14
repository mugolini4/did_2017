package com.example.matil.LAMPApp;

import android.widget.Switch;


/**
 * Created by matil on 23/11/2017.
 */

public class Lamp {
    private String lamp_name;
    private int lamp_image_ID;
    private boolean lamp_state; //true=ON; false=OFF
    private String lamp_URL;

    public Lamp(String lampURL) {
        this.lamp_URL = lampURL;
    }


    public Lamp(String lampName, int lampImageID) {
        this.lamp_name = lampName;
        this.lamp_image_ID = lampImageID;
    }

    public String getLamp_name() {
        return lamp_name;
    }

    public void setLamp_name(String newNAme) {
        lamp_name = newNAme;
    }

    public int getLamp_image() {
        return lamp_image_ID;
    }

    public void turnOn() { lamp_state = true; }

    public void turnOff() { lamp_state = false; }

    public void switchState() { lamp_state = !lamp_state; }

    public	boolean	isOn() { return lamp_state; }

}
