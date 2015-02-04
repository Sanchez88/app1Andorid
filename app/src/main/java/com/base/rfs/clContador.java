package com.base.rfs;

import android.content.Context;

/**
 * Created by elite88 on 04/02/2015.
 */
public class clContador {
    private clDBCreacion db;
    public  clContador(Context context){
        db = new clDBCreacion(context);
    }

    public void insert(int valor){
        db.insContador(valor);
    }
}
