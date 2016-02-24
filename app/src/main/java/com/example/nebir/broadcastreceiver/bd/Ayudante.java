package com.example.nebir.broadcastreceiver.bd;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nebir on 18/02/2016.
 */
public class Ayudante extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="llamadas.sqlite";
    public static final int DATABASE_VERSION = 1;

    public Ayudante(Context context) {
        super(context, DATABASE_NAME, null,DATABASE_VERSION);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion,
                          int newVersion) {
        String sql="drop table if exists "
                + ContratoLlamadas.TablaEntrantes.TABLAENTRANTES;
        db.execSQL(sql);
        String sq2="drop table if exists "
                + ContratoLlamadas.TablaSalientes.TABLASALIENTES;
        db.execSQL(sq2);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//Cuando se baja la aplicación y se crea por primera vez(no hay versión previa de la aplicación)-
        String sql;
        sql="create table "+ ContratoLlamadas.TablaEntrantes.TABLAENTRANTES+ " ("+
                ContratoLlamadas.TablaEntrantes._ID+ " integer primary key autoincrement, "+
                ContratoLlamadas.TablaEntrantes.DIA+" text) ";
        db.execSQL(sql);
        String sq2;
        sq2="create table "+ ContratoLlamadas.TablaSalientes.TABLASALIENTES+ " ("+
                ContratoLlamadas.TablaSalientes._ID+ " integer primary key autoincrement, "+
                ContratoLlamadas.TablaSalientes.DIA+" text)";
        db.execSQL(sq2);
    }
}
