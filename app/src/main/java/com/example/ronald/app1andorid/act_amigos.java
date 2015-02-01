package com.example.ronald.app1andorid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.funciones.rfs.AdapterMy;
import com.funciones.rfs.MyAdater2;
import com.funciones.rfs.clListPer;

import java.util.ArrayList;
import java.util.List;


public class act_amigos extends ActionBarActivity {
    ListView myList = null;
    Spinner spListaSp = null;
    List<clListPer> lista = null;

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_amigos);

         lista = new ArrayList<clListPer>();


        lista.add(new clListPer("Primer Titulo","Primera Descricion", "http://www.androidparatodos.es/wp-content/uploads/2012/07/Android-roto.jpeg","img1"));
        lista.add(new clListPer("Segundo Imd","Segunda Descricion","http://cdn.mos.techradar.com/art/mobile_phones/Android/KitKat/Android_KitKat-970-80.jpg","img2"));
        lista.add(new clListPer("Primer dff","Primera Descricion", "http://square.github.io/picasso/static/sample.png", "img3"));
        lista.add(new clListPer("Segundo Tidfftulo","Segunda Descricion","http://i.imgur.com/DvpvklR.png","img4"));
        lista.add(new clListPer("Primer Titulo","Primera Descricion", "http://www.androidparatodos.es/wp-content/uploads/2012/07/Android-roto.jpeg","img5"));
        lista.add(new clListPer("Segundo Imd","Segunda Descricion","http://cdn.mos.techradar.com/art/mobile_phones/Android/KitKat/Android_KitKat-970-80.jpg","img6"));
        lista.add(new clListPer("Primer dff","Primera Descricion", "http://square.github.io/picasso/static/sample.png","img7"));
        lista.add(new clListPer("Segundo Tidfftulo","Segunda Descricion","http://i.imgur.com/DvpvklR.png","img8"));

        mRecyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView

        mRecyclerView.setHasFixedSize(true);

        // use a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        mAdapter = new MyAdater2(lista);
        //mRecyclerView.setAdapter(mAdapter);

        /*
        AdapterMy my = new AdapterMy(getApplicationContext(),lista);

        myList = (ListView) findViewById(R.id.lwLista);

        myList.setAdapter(my);

        myList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), lista.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent i = new Intent(getApplicationContext(), actDetalle.class);
                i.putExtra("titulo",lista.get(position).getTitulo());
                i.putExtra("descripcion",lista.get(position).getDescripcion());
                i.putExtra("url",lista.get(position).getSrc());
                startActivity(i);




            }
        });
        */

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_amigos, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        return super.onOptionsItemSelected(item);
    }
}
