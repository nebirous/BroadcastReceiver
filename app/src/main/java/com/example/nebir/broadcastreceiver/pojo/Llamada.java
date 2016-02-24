package com.example.nebir.broadcastreceiver.pojo;

import android.content.ContentValues;

import com.example.nebir.broadcastreceiver.bd.ContratoLlamadas;

import java.util.Date;

/**
 * Created by nebir on 20/02/2016.
 */
public class Llamada {

    private Date fecha;

    public Llamada(Date fecha){

        this.fecha = fecha;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public ContentValues getContentValuesEntrante(){
        ContentValues cv = new ContentValues();
        long tmp=fecha.getTime();
        cv.put(ContratoLlamadas.TablaEntrantes.DIA,tmp);
        return cv;
    }

    public ContentValues getContentValuesSaliente(){
        ContentValues cv = new ContentValues();
        long tmp=fecha.getTime();
        cv.put(ContratoLlamadas.TablaSalientes.DIA,tmp);
        return cv;
    }
}
