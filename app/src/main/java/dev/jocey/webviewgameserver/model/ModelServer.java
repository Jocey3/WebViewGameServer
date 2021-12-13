package dev.jocey.webviewgameserver.model;

import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

import io.reactivex.rxjava3.core.Single;

public class ModelServer {
    private Socket clientSocket;
    private BufferedReader reader;

    public Single<String> getAnswer() {
        return Single.fromCallable(() -> {
            try {
                clientSocket = new Socket("192.168.0.103", 4004);
                Log.d("myLog", "ClientSocket is connected: " + clientSocket.isConnected());
                reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                Log.d("myLog", "BufferReader is ready: " + reader.ready());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return reader.readLine();
        });
    }


}
