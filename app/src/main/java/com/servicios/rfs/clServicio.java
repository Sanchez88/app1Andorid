package com.servicios.rfs;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.sql.Date;
import java.util.Calendar;
import java.util.Timer;

/**
 * Created by Ronald on 10/01/15.
 */
public class clServicio extends Service {

    // Binder given to clients
    private final IBinder mBinder = new LocalBinder();
    // Random number generator

    /**
     * Class used for the client Binder.  Because we know this service always
     * runs in the same process as its clients, we don't need to deal with IPC.
     */
    public class LocalBinder extends Binder {
        clServicio getService() {
            // Return this instance of LocalService so clients can call public methods
            return clServicio.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Log.d("Creado","Servio iniciado.");

    }

    @Override
    public int onStartCommand(final Intent intent, int flags, int startId) {

        Toast.makeText(this, "Servicio en Ejecucion ", Toast.LENGTH_SHORT).show();
        adentro = true;
        cerrar();
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        adentro = false;
        super.onDestroy();
        Toast.makeText(this, "Servicio destruido Conteo : " + conteo, Toast.LENGTH_SHORT).show();
    }

    @Override
    public IBinder onBind(Intent arg0) {

        return mBinder;

    }
    private boolean adentro;
    private int conteo = 0;
    public void cerrar(){


            new Thread(new Runnable() {
                public void run() {
                    while (adentro) {
                        try {

                            Thread.sleep(1* 1000);
                            conteo++;
                           // Log.d("msj","Segundos : " + conteo++);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }

                    }
                }
            }).start();



    }
}
