package com.funciones.rfs;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

/**
 * Created by Ronald on 08/01/15.
 */
public class ConexionWS {
    SoapObject request;

    private String namespace, url, method_name,soap_action;

    public ConexionWS(String namespace, String url,
                      String method_name, String soap_action){
        this.namespace = namespace;
        this.url = url;
        this.method_name = method_name;
        this.soap_action = soap_action;
        request = new SoapObject(namespace,method_name);
    }

    public void addParametro(String nombre, String value){
        request.addProperty(nombre,value);
    }


    public String EjecutarWS(){


        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet = true;

        envelope.setOutputSoapObject(request);

        HttpTransportSE transporte = new HttpTransportSE(url);
        try{
            transporte.call(soap_action, envelope);
            SoapPrimitive resultado = (SoapPrimitive) envelope.getResponse();
            return resultado.toString();
        }
        catch (Exception e){
            e.printStackTrace();
        }

        return "";
    }
}
