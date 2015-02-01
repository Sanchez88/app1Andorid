package com.funciones.rfs;

/**
 * Created by Ronald on 20/01/15.
 */
public class clListPer {

    private  String titulo;
    private String descripcion;
    private String src;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getSrc() {
        return src;
    }

    public void setSrc(String src) {
        this.src = src;
    }


    public clListPer(String titulo, String descripcion, String url, String nombre){
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.src = url;
        this.name = nombre;
    }

    public clListPer(){

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
