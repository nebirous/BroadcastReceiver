package com.example.nebir.broadcastreceiver.bd;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by nebir on 18/02/2016.
 */
public class ContratoLlamadas {

    private ContratoLlamadas() {
    }


    public static abstract class TablaEntrantes implements BaseColumns {
        public static final String  TABLAENTRANTES = "Entrantes";

        public static final String  DIA = "dia";

        public final static String AUTHORITY = "com.example.nebir.broadcastreceiver.bd.ProviderLlamadas";

        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLAENTRANTES);
        public final static String SINGLE_MIME =
                "vnd.android.cursor.item/vnd." + AUTHORITY + TABLAENTRANTES;
        public final static String MJLTIPLE_MIME =
                "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLAENTRANTES;
    }

    public static abstract class TablaSalientes implements BaseColumns {
        public static final String  TABLASALIENTES= "Salientes";

        public static final String  DIA = "dia";


        public final static String AUTHORITY = "com.example.nebir.broadcastreceiver.bd.ProviderLlamadas";

        public final static Uri CONTENT_URI =
                Uri.parse("content://" + AUTHORITY + "/" + TABLASALIENTES);
        public final static String SINGLE_MIME =
                "vnd.android.cursor.item/vnd." + AUTHORITY + TABLASALIENTES;
        public final static String MJLTIPLE_MIME =
                "vnd.android.cursor.dir/vnd." + AUTHORITY + TABLASALIENTES;

    }
}
