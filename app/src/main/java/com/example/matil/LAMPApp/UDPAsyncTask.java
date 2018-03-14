package com.example.matil.LAMPApp;

import android.os.AsyncTask;
import android.util.Log;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * Created by matil on 14/03/2018.
 */

public class UDPAsyncTask extends AsyncTask<Object, String, Integer> {

    private Runnable updateUI;

    public UDPAsyncTask(Runnable updateUI) {
        this.updateUI = updateUI;
    }

    @Override
    protected void onProgressUpdate(String... progress) {

    }

    @Override
    protected Integer doInBackground(Object... nothing) {

        boolean keepListening = true;
        DatagramSocket socket = null;
        byte[] recvBuf = new byte[15000];
        DatagramPacket packet = new DatagramPacket(recvBuf, recvBuf.length);

        try {
            if (socket == null || socket.isClosed()) {
                socket = new DatagramSocket(4096);
            }
            socket.setSoTimeout(5000);
        } catch (SocketException e) {
            e.printStackTrace();
        }

        while(keepListening) {
            Log.e("UDP", "Waiting for UDP broadcast");

            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }

            String senderIP = packet.getAddress().getHostAddress().trim();
            String lamp_name = new String(packet.getData()).trim();

            Log.e("UDP", "ricevuto broadcast UDP da: " + senderIP + ", lamp_name: " + lamp_name);

            LampManager.getInstance().addLamp( senderIP, lamp_name );
        }

        return 0;
    }
}
