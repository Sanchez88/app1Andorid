package com.funciones.rfs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
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

            Bitmap bitmap;
            OutputStream output;
            try {

                File directorio = Environment.getExternalStorageDirectory();


                File carpeta = new File(directorio.getAbsolutePath() + "/app1AndroidImg");
                if(!carpeta.exists()){
                    carpeta.mkdir();
                }

                File file = new File(carpeta, this.name + ".jpeg");
                if(file.exists()){

                   return BitmapFactory.decodeFile(file.getAbsolutePath());
                }
                else {
                    bitmap = Conexion.cargarImg(url);
                    //File cache = Environment.getDownloadCacheDirectory();

                    try {
                        output = new FileOutputStream(file);
                        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, output);
                        output.flush();
                        output.close();

                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                return bitmap;
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
