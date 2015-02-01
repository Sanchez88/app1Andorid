package com.funciones.rfs;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by Ronald on 25/01/15.
 */
public class clMesaje {
    public static void msjC(Context ctx, String mensjae){
        Toast.makeText(ctx, mensjae,Toast.LENGTH_SHORT).show();
    }
    public static void msjL(Context ctx, String mensjae){
        Toast.makeText(ctx, mensjae,Toast.LENGTH_LONG).show();
    }

}
