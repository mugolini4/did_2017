package com.example.matil.LAMPApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

public class LampDetailActivity extends AppCompatActivity {

    private TextView tv;
    private ImageView imageView;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lamp_detail );

        Intent intent = LampDetailActivity.this.getIntent();
        String lampName = "";
        String lampIP = "";

        int lampPhotoID = 0;
        if(intent.hasExtra( "lamp_name" )) {
            lampName = intent.getStringExtra( "lamp_name" );
        } else {
            System.out.println("no lamp_name");
        }

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

        tv = (TextView) this.findViewById( R.id.detail_lamp_name );
        tv.setText( lampName );

        imageView = (ImageView) this.findViewById( R.id.lamp_image_view );
        imageView.setImageResource(lampPhotoID);
    }
}
