package com.funciones.rfs;
import android.content.Context;
import android.widget.ArrayAdapter;
/**
 * Created by Ronald on 07/01/15.
 */
public class clControlLista {

    public static ArrayAdapter getAdapterList(Context context, String[] array){
        return new ArrayAdapter<String>(context,  android.R.layout.simple_dropdown_item_1line, array);
    }

}
