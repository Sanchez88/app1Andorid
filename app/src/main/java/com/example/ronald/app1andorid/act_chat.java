package com.example.ronald.app1andorid;

import android.os.Build;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.funciones.rfs.clMesaje;
/*
import org.java_websocket.WebSocket;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
*/

import de.tavendo.autobahn.WebSocketConnection;
import de.tavendo.autobahn.WebSocketException;
import de.tavendo.autobahn.WebSocketHandler;

import java.net.InetSocketAddress;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.NotYetConnectedException;



public class act_chat extends ActionBarActivity {
      //WebSocketClient mWebSocketClient;

    private final WebSocketConnection mConnection = new WebSocketConnection();

    private void start() {

        final String wsuri = "ws://192.168.0.254/app1/appRealTime.ashx";

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {

                    mConnection.connect(wsuri, new WebSocketHandler() {

                        @Override
                        public void onOpen() {
                            clMesaje.msjC(act_chat.this,"Conectado...");
                            mConnection.sendTextMessage("Hello, world!");
                        }

                        @Override
                        public void onTextMessage(String payload) {
                            final String message = payload;
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    TextView textView = (TextView)findViewById(R.id.twMsj);
                                    textView.setText(textView.getText() + "\n" + message);
                                }
                            });
                        }

                        @Override
                        public void onClose(int code, String reason) {
                            clMesaje.msjC(act_chat.this,"Conexion Cerrada");
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    TextView textView = (TextView)findViewById(R.id.twMsj);
                                    textView.setText("Conexion cerrada.");
                                }
                            });
                        }
                    });
                } catch (WebSocketException e) {

                    clMesaje.msjC(act_chat.this, e.getMessage().toString());
                }
            }
        }).start();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_chat);
        start();
        /*
        URI url = null;

        try{
            url = new URI("ws://192.168.0.254/app1/appRealTime.ashx");
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }


        mWebSocketClient = new WebSocketClient(url) {


                @Override
                public void onOpen(ServerHandshake serverHandshake) {
                    clMesaje.msjC(act_chat.this,"Conectado...");
                   // mWebSocketClient.send("Hello Desde android");
                }

                @Override
                public void onMessage(String s) {
                    clMesaje.msjC(act_chat.this,"OnMessage");
                    final String message = s;
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = (TextView)findViewById(R.id.twMsj);
                            textView.setText(textView.getText() + "\n" + message);
                        }
                    });
                }

                @Override
                public void onClose(int i, String s, boolean b) {
                    clMesaje.msjC(act_chat.this,"Conexion Cerrada");
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            TextView textView = (TextView)findViewById(R.id.twMsj);
                            textView.setText("Conexion cerrada.");
                        }
                    });
                }

                @Override
                public void onError(Exception e) {
                    Log.i("Websocket", "Error " + e.getMessage());
                }
            };

            mWebSocketClient.connect();

            clMesaje.msjC(this,"Estado : " + mWebSocketClient.getReadyState());
            */


    }

    public void sendMessage(View view) {
        EditText editText = (EditText)findViewById(R.id.txtMsjChat);

        //clMesaje.msjC(this,"Estado : " + mWebSocketClient.getReadyState());

       // mWebSocketClient.send(editText.getText().toString());
        editText.setText("");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_chat, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
