package com.funciones.rfs;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.widget.ImageView;

import java.net.MalformedURLException;

/**
 * Created by Ronald on 22/01/15.
 */
public class DescargarBitmap  extends AsyncTask<Void, Void, Bitmap> {
        private ImageView img;
        private String url, name;

        public DescargarBitmap(ImageView img, String http, String nombre){
            super();
            this.img = img;
            this.url = http;
            this.name = nombre;
        }

    public DescargarBitmap(ImageView img, String http){
        super();
        this.img = img;
        this.url = http;

    }

        /** The system calls this to perform work in a worker thread and
         * delivers it the parameters given to AsyncTask.execute() */
        protected Bitmap doInBackground(Void... urls) {


            try {
                return Conexion.cargarImg(url);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            return  null;
        }

        /** The system calls this to perform work in the UI thread and delivers
         * the result from doInBackground() */
        protected void onPostExecute(Bitmap result) {
            img.setImageBitmap(result);
        }

}
