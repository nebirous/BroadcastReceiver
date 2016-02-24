package com.example.nebir.broadcastreceiver;

import android.database.Cursor;

import java.sql.Date;

/**
 * Created by nebir on 20/02/2016.
 */
public class Funciones {
    public static int[] contadorLlamadas(Cursor c) {
        int Domingo=0, Lunes=0, Martes=0, Miercoles=0, Jueves=0, Viernes=0, Sabado = 0;

        while (c.moveToNext()) {
            long fecha = c.getLong(c.getColumnIndex("dia"));
            int caso;
            Date cap=new Date(fecha);
            caso=cap.getDay();

            switch (caso) {
                case 0:
                    Domingo++;
                    break;

                case 1:
                    Lunes++;
                    break;

                case 2:
                    Martes++;
                    break;

                case 3:
                    Miercoles++;
                    break;

                case 4:
                    Jueves++;
                    break;

                case 5:
                    Viernes++;
                    break;

                case 6:
                    Sabado++;
                    break;

                default:
                    break;
            }
        }
        int[] result = {Domingo, Lunes, Martes, Miercoles, Jueves, Viernes, Sabado};
        return result;
    }
}
