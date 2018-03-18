package com.example.matil.LAMPApp;


/**
 * Created by matil on 23/11/2017.
 */

public class Lamp {
    private String lamp_name;
    private int lamp_image_ID;
    private boolean lamp_state; //true=ON; false=OFF
    private String lamp_IP; //indirizzo IP della lampada
    private int brightness;

    public Lamp(String lampURL, String lampName, int lampImageID) {
        this.lamp_IP = lampURL;
        this.lamp_name = lampName;
        this.lamp_image_ID = lampImageID;
        this.lamp_state = false;
        this.brightness = 0;
    }

    public String getLampName() {
        return lamp_name;
    }

    public void setLampName(String newName) {
        lamp_name = newName;
    }

    public String getLampIP() {
        return lamp_IP;
    }
    
    public int getLampImage() {
        return lamp_image_ID;
    }

    public void turnOn() { lamp_state = true; }

    public void turnOff() { lamp_state = false; }

    public void switchState() { lamp_state = !lamp_state; }

    public	boolean	isOn() { return lamp_state; }

    public void setState(boolean newState) {
        lamp_state = newState;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }
}
