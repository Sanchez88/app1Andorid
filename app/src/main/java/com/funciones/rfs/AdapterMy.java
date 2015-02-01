package com.funciones.rfs;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ronald.app1andorid.R;
import com.example.ronald.app1andorid.actDetalle;

//import org.java_websocket.util.Base64;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Objects;
import java.util.zip.Inflater;

/**
 * Created by Ronald on 19/01/15.
 */
public class AdapterMy extends BaseAdapter {
    Context ctx;
    Button btnLike, btnNLike;
    List<clListPer> dato;
    public AdapterMy(Context context, List<clListPer> datos){
        super();
        this.ctx = context;
        this.dato = datos;
    }

    @Override
    public int getCount() {
        return dato.size();
    }

    @Override
    public Object getItem(int position) {
        return dato.get(position);
    }

    @Override
    public long getItemId(int position) {
        return  0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view;
        if(convertView == null){
            LayoutInflater inflar = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflar.inflate(R.layout.lista_personal,null );

        }
        else{
            view = convertView;
        }

        TextView titulo = (TextView) view.findViewById(R.id.twTitulo);
        titulo.setText(dato.get(position).getTitulo());

        TextView descrip = (TextView) view.findViewById(R.id.twDescripcion);
        descrip.setText(dato.get(position).getDescripcion());

        ImageView img = (ImageView) view.findViewById(R.id.imgView);

       // btnLike = (Button) view.findViewById(R.id.btnItem);
       // btnNLike = (Button) view.findViewById(R.id.btnItemN);


        /*
        btnLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,dato.get(position).getTitulo() + " Like!!!", Toast.LENGTH_SHORT ).show();
            }
        });

        btnNLike.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ctx,dato.get(position).getTitulo() + " Not Like!!!", Toast.LENGTH_SHORT ).show();
            }
        });*/

        new DescargarBitmap(img,dato.get(position).getSrc(),dato.get(position).getName()).execute();

        return  view;
    }


}
