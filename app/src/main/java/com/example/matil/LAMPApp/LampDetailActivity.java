package com.example.matil.LAMPApp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class LampDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_lamp_detail );

        Intent intent = LampDetailActivity.this.getIntent();
        String lampName = "";
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


        TextView tv = (TextView) this.findViewById( R.id.detail_lamp_name );
        tv.setText( lampName );

        ImageView imageView = (ImageView) this.findViewById( R.id.lamp_image_view );
        imageView.setImageResource(lampPhotoID);
    }
}
