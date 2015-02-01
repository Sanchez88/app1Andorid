package com.funciones.rfs;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ronald.app1andorid.R;

import java.util.List;

/**
 * Created by Ronald on 31/01/15.
 */
public class MyAdater2 extends RecyclerView.Adapter<MyAdater2.ViewHolder> {
    List<clListPer> dato;

    public MyAdater2(List<clListPer> datos){
        this.dato = datos;
    }
    // Provide a reference to the views for each data item
    // Complex data items may need more than one view per item, and
    // you provide access to all the views for a data item in a view holder
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView titulo, descrip;
        public ImageView img;
        public ViewHolder(View v) {
            super(v);
            titulo = (TextView) v.findViewById(R.id.twTitulo);
            descrip = (TextView) v.findViewById(R.id.twTitulo);
            img = (ImageView) v.findViewById(R.id.imgView);


        }
    }


    // Create new views (invoked by the layout manager)
    @Override
    public MyAdater2.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                   int viewType) {
        // create a new view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_personal, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(v);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // - get element from your dataset at this position
        // - replace the contents of the view with that element
        clListPer per = dato.get(position);
        holder.titulo.setText(per.getTitulo());
        holder.descrip.setText(per.getDescripcion());
        new DescargarBitmap(holder.img, per.getSrc()).execute();
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return dato.size();
    }
}
