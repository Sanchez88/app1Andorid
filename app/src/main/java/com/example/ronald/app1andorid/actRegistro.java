package com.example.ronald.app1andorid;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.funciones.rfs.ConexionWS;
import com.funciones.rfs.clControlLista;
import com.servicios.rfs.clServicio;

import java.util.ArrayList;
import java.util.List;


public class actRegistro extends ActionBarActivity {

    Spinner spLista = null;
    Button btnRegistro = null;
    EditText txtNombre = null, txtApellido = null, txtTelefono = null, txtMail = null, txtPass = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_registro);

        startService(new Intent(this, clServicio.class));

        spLista = (Spinner)findViewById(R.id.spLista);
        btnRegistro = (Button) findViewById(R.id.btnRegistro);
        txtMail = (EditText) findViewById(R.id.txtMail);
        txtApellido = (EditText) findViewById(R.id.txtApellido);
        txtTelefono = (EditText) findViewById(R.id.txtTelefono);
        txtPass = (EditText) findViewById(R.id.txtPass);
        txtNombre = (EditText) findViewById(R.id.txtNombre);

        //String [] nombre = {"Nombre","Apellido","Direccion","Telefono"};
        String [] genero = {"MASCULINO","FAMENINO"};

        List<String> list = new ArrayList<String>();

        /*ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, nombre);*/

        spLista.setAdapter(clControlLista.getAdapterList(this, genero));

        spLista.setOnItemSelectedListener( new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(actRegistro.this, spLista.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                Toast.makeText(actRegistro.this,"No hay informacion.", Toast.LENGTH_SHORT).show();
            }
        });


        // Evento del Button btnRegistro

        btnRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(txtMail.getText().toString().length() > 0 && txtPass.getText().toString().length() > 0
                        && txtApellido.getText().toString().length() > 0 && txtNombre.getText().toString().length() > 0) {
                    Json con = new Json(txtMail.getText().toString(), txtPass.getText().toString(),
                            txtNombre.getText().toString(), txtApellido.getText().toString(),
                            spLista.getSelectedItem().toString(), txtTelefono.getText().toString());
                    con.execute();
                }
                else{

                    Toast.makeText(actRegistro.this, "Informacion requerida...", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_act_registro, menu);
        return  true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        if(item.getItemId() == R.id.action_Login){
            Intent i = new Intent(this, actLogin.class);
            startActivity(i);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private class Json extends AsyncTask<Void, String, Void> {

        private String js = "";
        ProgressDialog pd = null;

        private String correo, pass, nombre, apellido, genero, telefono;

        public Json(String correo, String pass, String nombre, String apellido, String genero, String telefono){
            this.correo = correo;
            this.pass = pass;
            this.nombre = nombre;
            this.apellido = apellido;
            this.genero = genero;
            this.telefono = telefono;

        }
        //este es el primer metodo que se ejecuta.

        @Override
        protected void onPreExecute()
        {
            pd = new ProgressDialog(actRegistro.this);
            pd.setTitle("Descargando Información...");
            pd.setMessage("Espere un momento...");
            pd.setCancelable(false);
            pd.setIndeterminate(true);
            pd.show();
        }

        @Override
        protected void onPostExecute(Void result) {
            if (pd!=null) {
                pd.dismiss();
            }
        }
        //codigo a ejecutar.
        @Override
        protected Void doInBackground(Void... url) {

            String ca= "";
            try {
                //ca = Conexion.cade-naJson("http://gdata.youtube.com/feeds/api/standardfeeds/most_popular?v=2&alt=json");
                ConexionWS c = new ConexionWS("http://tempuri.org/","http://192.168.0.254/app1/ServiciosWeb/ServicioDemo.asmx"
                        , "addUser","http://tempuri.org/addUser");

                c.addParametro("correo",correo);
                c.addParametro("pass", pass);
                c.addParametro("nombre", nombre);
                c.addParametro("apellido", apellido);
                c.addParametro("genero", genero);
                c.addParametro("telefono", telefono);


                ca = c.EjecutarWS();
                publishProgress(ca);

            } catch (Exception e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values)
        {
            String msj = "";
            if(values[0].equals("false"))
                msj = "Error la informacion es incorrecta.";
            else if(values[0].equals("true")){

                msj = "Registro Completo";

                EditText txtNombre, txtApellido, txtTelefono, txtMail, txtPass;
                Spinner spLista;

                spLista = (Spinner)findViewById(R.id.spLista);
                btnRegistro = (Button) findViewById(R.id.btnRegistro);
                txtMail = (EditText) findViewById(R.id.txtMail);
                txtApellido = (EditText) findViewById(R.id.txtApellido);
                txtTelefono = (EditText) findViewById(R.id.txtTelefono);
                txtPass = (EditText) findViewById(R.id.txtPass);
                txtNombre = (EditText) findViewById(R.id.txtNombre);

                txtApellido.setText("");
                txtMail.setText("");
                txtNombre.setText("");
                txtPass.setText("");
                txtTelefono.setText("");
                spLista.setSelection(0);



            }

            else
                msj = "Conexion no valida...";

            Toast.makeText(actRegistro.this, msj, Toast.LENGTH_SHORT).show();

        }



    }
}
