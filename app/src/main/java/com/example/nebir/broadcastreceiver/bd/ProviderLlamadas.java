package com.example.nebir.broadcastreceiver.bd;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.Nullable;

import com.example.nebir.broadcastreceiver.bd.Ayudante;
import com.example.nebir.broadcastreceiver.bd.ContratoLlamadas;

import java.io.IOException;

/**
 * Created by nebir on 18/02/2016.
 */
public class ProviderLlamadas extends ContentProvider {

    public static final UriMatcher uriSwitch;
    public static final int ENTRANTES = 1;
    public static final int SALIENTES = 2;

    static{
        uriSwitch = new UriMatcher(UriMatcher.NO_MATCH);
        uriSwitch.addURI(ContratoLlamadas.TablaSalientes.AUTHORITY, ContratoLlamadas.TablaSalientes.TABLASALIENTES, SALIENTES);
        uriSwitch.addURI(ContratoLlamadas.TablaEntrantes.AUTHORITY, ContratoLlamadas.TablaEntrantes.TABLAENTRANTES, ENTRANTES);
    }

    private Ayudante adb;

    @Override
    public boolean onCreate() {
        adb = new Ayudante(getContext());
        adb.getReadableDatabase();
        return true;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {
        // Obtener base de datos
        SQLiteDatabase db = adb.getReadableDatabase();
        // Comparar Uri
        int match = uriSwitch.match(uri);

        Cursor c;

        switch (match) {
            case ENTRANTES:
                // Consultando todos los registros
                c = db.query(ContratoLlamadas.TablaEntrantes.TABLAENTRANTES, projection, selection, selectionArgs, null, null, sortOrder);
                break;
            case SALIENTES:
                // Consultando todos los registros
                if (sortOrder!="join"){
                    c = db.query(ContratoLlamadas.TablaSalientes.TABLASALIENTES, projection, selection, selectionArgs, null, null, sortOrder);
                }else{
                    String s="Select * from album LEFT JOIN artista ON (album.artist_id = artista._id) order by album";
                    c=db.rawQuery(s,null);
                }
                break;
            default:
                throw new IllegalArgumentException("URI no soportada: " + uri);
        }
        return c;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        switch (uriSwitch.match(uri)){
            case ENTRANTES:
                return ContratoLlamadas.TablaEntrantes.MJLTIPLE_MIME;
            case SALIENTES:
                return ContratoLlamadas.TablaSalientes.MJLTIPLE_MIME;
            default:
                throw new IllegalArgumentException("Tipo de actividad desconocida " + uri);
        }
    }


    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (values == null) {
            throw new IllegalArgumentException("Resultados null ");
        }

        if (uriSwitch.match(uri) != ENTRANTES && uriSwitch.match(uri) != SALIENTES ) {
            throw new IllegalArgumentException("URI desconocida : " + uri + uriSwitch.match(uri) );
        }

        SQLiteDatabase database = adb.getWritableDatabase();

        long idLinea;

        switch (uriSwitch.match(uri)){
            case ENTRANTES:
                idLinea = database.insert(ContratoLlamadas.TablaEntrantes.TABLAENTRANTES, null, values);

                if(idLinea>0){
                    Uri uriActividad = ContentUris.withAppendedId(ContratoLlamadas.TablaEntrantes.CONTENT_URI, idLinea);
                    getContext().getContentResolver().notifyChange(uriActividad, null);

                    return uriActividad;
                }else{
                    try {
                        throw new IOException("Error insertar entrante: "+ uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            case SALIENTES:
                idLinea = database.insert(ContratoLlamadas.TablaSalientes.TABLASALIENTES, null, values);

                if(idLinea>0){
                    Uri uriActividad = ContentUris.withAppendedId(ContratoLlamadas.TablaSalientes.CONTENT_URI, idLinea);
                    getContext().getContentResolver().notifyChange(uriActividad, null);

                    return uriActividad;
                }else{
                    try {
                        throw new IOException("Error insertar saliente: "+ uri);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            
            default:
                return null;
        }
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
