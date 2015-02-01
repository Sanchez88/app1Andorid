package com.example.ronald.app1andorid;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.view.MenuItemCompat;
import android.os.Bundle;

import android.support.v7.app.ActionBarActivity;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import com.funciones.rfs.ConexionWS;
import com.funciones.rfs.clMesaje;

import org.w3c.dom.Text;

import java.util.ArrayList;

import static android.view.View.OnClickListener;


public class actLogin extends ActionBarActivity {
    EditText txtMail= null, txtPass = null;
    Button btnLogin = null;
    ArrayList<String> selList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act_login);
        final String [] nombre = {"Editar", "Eliminar"};
        final boolean [] ck = new boolean[nombre.length];

        selList = new ArrayList();


        AlertDialog.Builder dialogo = new AlertDialog.Builder(actLogin.this);
        dialogo.setTitle("Titulo del dialogo");
        dialogo.setSingleChoiceItems(nombre, -1, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                clMesaje.msjC(actLogin.this,nombre[which]);

            }
        });
        dialogo.setCancelable(false);
        dialogo.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String sl = "";
                for(String n : selList)
                    sl += " " + n;

                clMesaje.msjC(getApplicationContext(),sl);
            }
        });

        dialogo.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Toast.makeText(getApplicationContext(), "Filtro Cancelado.",Toast.LENGTH_SHORT).show();
            }
        });

        dialogo.show();


        txtMail = (EditText) findViewById(R.id.txtMailLog);
        txtPass = (EditText) findViewById(R.id.txtPassLog);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        //Boton de inicio de session.

        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if (txtPass.getText().toString().length() > 0 && txtMail.getText().toString().length() > 0) {
                    clLogin con = new clLogin(txtMail.getText().toString(),txtPass.getText().toString());
                    con.execute();

                } else {
                    Toast.makeText(actLogin.this, "Informacion Requerida...", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.


        getMenuInflater().inflate(R.menu.menu_act_login, menu);


        final SearchView searchView = (SearchView) menu.findItem(R.id.action_Contacto).getActionView();

        searchView.setQueryHint("Buscar:");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {



                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });



        return true;
    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        //noinspection SimplifiableIfStatement
        if(item.getItemId() == R.id.action_amigos){
            Intent i = new Intent(this, act_amigos.class);
            startActivity(i);
            return true;

        }

        if(item.getItemId() == R.id.action_New){
            Intent i = new Intent(this, actRegistro.class);
            startActivity(i);
            return true;

        }

        if(item.getItemId() == R.id.action_chat){
            Intent i = new Intent(this, act_chat.class);
            startActivity(i);
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private class clLogin extends AsyncTask<Void, String, Void> {


        ProgressDialog pd = null;

        private String correo = "", pass = "";

        public clLogin(String mail, String pass){
            this.correo = mail;
            this.pass = pass;

        }
        //este es el primer metodo que se ejecuta.

        @Override
        protected void onPreExecute()
        {
            pd = new ProgressDialog(actLogin.this);
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
                ConexionWS c = new ConexionWS("http://tempuri.org/","http://192.168.0.254/app1/ServiciosWeb/ServicioDemo.asmx", "validarUsuario", "http://tempuri.org/validarUsuario");

                c.addParametro("mail",correo);
                c.addParametro("pass", pass);

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
            String msj = "", res = "";
            res = values[0].toString();
            /*if(!res.equals("")){
                msj = "Usuario valido";

                EditText txtMail, txtPass;

                txtMail = (EditText) findViewById(R.id.txtMail);
                txtPass = (EditText) findViewById(R.id.txtPass);

                txtMail.setText("");
                txtPass.setText("");



            }

            else
                msj = "Conexion no valida...";*/

            Toast.makeText(actLogin.this, res, Toast.LENGTH_SHORT).show();

        }



    }
}
