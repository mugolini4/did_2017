package com.example.matil.LAMPApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

public class LampDetailActivity extends AppCompatActivity {

    private LampManager lampManager = LampManager.getInstance();
    private String lampIP = "";
    private Lamp selectedLamp;
    private TextView lampNameView;
    private ImageView imageView;
    private SeekBar lumSeekBar;
    private Switch switchOnOff;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lamp_detail );

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

        //SWITCH ON-OFF
        switchOnOff = (Switch) this.findViewById(R.id.selected_lamp_switch);
        switchOnOff.setChecked( selectedLamp.isOn() );
        switchOnOff.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                selectedLamp.setState( switchOnOff.isChecked() );
            }
        });

        //SEEKBAR PER LUMINOSITA'
        lumSeekBar = (SeekBar) this.findViewById( R.id.lum_seekbar );
        lumSeekBar.setProgress( selectedLamp.getBrightness() );
        lumSeekBar.setOnSeekBarChangeListener( new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                //setta la luminosità
                selectedLamp.setBrightness( progress );
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { Log.d("Brightness", "Seekbar luminosità cambiata"); }
        } );
    }
}
