package com.example.matil.LAMPApp;

import android.util.Log;

import java.io.Console;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * Created by matil on 17/01/2018.
 */

public class UDPListener implements Runnable {

    private static final int MAX_UDP_DATAGRAM_LEN = 64000;
    int LOCAL_PORT = 4096;
    InetAddress remote_address;

    private String lastMessage = "";
    String message;
    byte[] lmessage = new byte[MAX_UDP_DATAGRAM_LEN];

    DatagramPacket packet = new DatagramPacket( lmessage, lmessage.length );
    DatagramSocket socket;

    @Override
    public void run() {
        Log.d("!!!debug", "sono in run");

        while(true) {
            try {
                //riceve il pacchetto
                socket = new DatagramSocket( LOCAL_PORT );
                socket.receive( packet );
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Throwable e) {
                e.printStackTrace();
            }

            //accesso al contenuto del packet
            if (packet != null) {
                remote_address = packet.getAddress();
                message = new String( lmessage, 0, packet.getLength() );
                System.out.print( "!!!messaggio: " + message );
                Log.d( "messaggio ricevuto", "nome lamp: " + message );
                lastMessage = message;
            }
            socket.close();

        }
    }
}
