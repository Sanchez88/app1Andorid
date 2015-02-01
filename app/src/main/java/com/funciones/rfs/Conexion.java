package com.funciones.rfs;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Ronald on 07/01/15.
 */
public class Conexion {
    public Conexion() {

    }

    public static Bitmap cargarImg(String http) throws MalformedURLException {
        URL url = new URL(http);

        try {
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.connect();
            return BitmapFactory.decodeStream(con.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }

    public static String cadenaJson(String http) throws MalformedURLException {
        URL url = new URL(http);
        URLConnection con= null;

        try {

            con = url.openConnection();
            con.setConnectTimeout(5000);
            con.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));

            String linea;
            String jsonC = "";

            while ((linea = in.readLine()) != null) {
                jsonC += linea;
            }

			/*
			 * Esto cuando ya tengo el objJson.
			Gson json = new Gson();
	          java.lang.reflect.Type tipoObjeto = new TypeToken<ArrayList<Persona>>(){}.getType();
	  		  ArrayList<Persona> per= json.fromJson(jsonC, tipoObjeto);

	  		for(int i=0;i<per.size();i++){
				Persona al = per.get(i);
	  		}*/

            return jsonC;
        } catch (Exception er) {
            return "";
        }
    }
}
