package com.example.matil.LAMPApp;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class LampDetailActivity extends AppCompatActivity {

    private LampManager lampManager = LampManager.getInstance();
    private String lampIP = "";
    private Lamp selectedLamp;
    private TextView lampNameView;
    private ImageView imageView;
    private SeekBar lumSeekBar;
    private Switch switchOnOff;
    private TCPClient tcpClient;
    private ConnectTask connectTask;

    //default commands
    private final String turnOn = "turnOn";
    private final String turnOff = "turnOff";
    private final String switchState = "switchState";
    private final String setLum = "setLum";
    private final String setColor = "setColor";
    //private final int MIN_LUM = 5;
    //private final int MAX_LUM = 255;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lamp_detail );

        final Context appContext = getApplicationContext();

        Intent intent = LampDetailActivity.this.getIntent();

        int lampPhotoID = 0;

        if(intent.hasExtra( "lamp_photo_ID" )) {
            lampPhotoID = intent.getIntExtra("lamp_photo_ID", 0);
        } else {
            System.out.println("no photo id");
        }

        if(intent.hasExtra( "lamp_ip" )) {
            lampIP = intent.getStringExtra("lamp_ip");
        } else {
            System.out.println("no lamp_ip");
        }

        //trova la lampada che si vuole utilizzare
        for (Lamp lamp : lampManager.getLamps()) {
            if(lamp.getLampIP().equals( lampIP )) {
                selectedLamp = lamp;
                break;
            }
        }

        //TEXTVIEW PER NOME LAMPADA
        lampNameView = (TextView) this.findViewById( R.id.selected_lamp_name );
        lampNameView.setText( selectedLamp.getLampName() );

        //IMAGE VIEW PER FOTO LAMPADA
        imageView = (ImageView) this.findViewById( R.id.selected_lamp_img );
        imageView.setImageResource(lampPhotoID);

        //CONNECTION TCP
        tcpClient = new TCPClient(new TCPClient.OnMessageReceived() {
            @Override
            //here the messageReceived method is implemented
            public void messageReceived(final String message) {
                //this method calls the onProgressUpdate
                // Get a handler that can be used to post to the main thread
                Handler mainHandler = new Handler(getApplicationContext().getMainLooper());
                Runnable myRunnable = new Runnable() {
                    @Override
                    public void run() {

                        //Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
                        Log.d("message: ", message);

                        String[] cmd_rcv = message.split("$");
                        switch (cmd_rcv[0]) {

                            case turnOn:
                                selectedLamp.turnOn();
                                switchOnOff.setChecked(true);
                                break;

                            case turnOff:
                                selectedLamp.turnOff();
                                switchOnOff.setChecked(false);
                                break;

                            case setLum:
                                if(cmd_rcv.length > 1) {
                                    Toast.makeText(appContext, cmd_rcv[1], Toast.LENGTH_SHORT).show();
                                    selectedLamp.setBrightness(Integer.parseInt(cmd_rcv[1]));
                                    lumSeekBar.setProgress(selectedLamp.getBrightness());
                                }
                                break;
                        }

                    } // This is your code
                };
                mainHandler.post(myRunnable);

            }
        }, selectedLamp.getLampIP());

        //CONNECT TASK, non bloccante
        connectTask = new ConnectTask(getApplicationContext(), this);
        connectTask.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, tcpClient);

        //SWITCH ON-OFF
        switchOnOff = (Switch) this.findViewById(R.id.selected_lamp_switch);
        switchOnOff.setChecked( selectedLamp.isOn() );
        switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                selectedLamp.setState( switchOnOff.isChecked() );
                if(switchOnOff.isChecked())
                    tcpClient.setMessage(turnOn);
                else
                    tcpClient.setMessage(turnOff);

            }
        });

        //SEEKBAR PER LUMINOSITA'
        lumSeekBar = (SeekBar) this.findViewById( R.id.lum_seekbar );
        //lumSeekBar.setMax( MAX_LUM );
        lumSeekBar.setProgress( selectedLamp.getBrightness() );

        lumSeekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //setta la luminosità
                if(progress == 0)
                    progress = 5;
                selectedLamp.setBrightness( progress );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d("Brightness", "Seekbar luminosità cambiata");
                if(selectedLamp.isOn())
                    tcpClient.setMessage( setLum + "$" + selectedLamp.getBrightness());
            }
        } );
    }
}
