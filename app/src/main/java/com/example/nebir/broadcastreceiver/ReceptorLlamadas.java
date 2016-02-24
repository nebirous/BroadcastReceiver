package com.example.nebir.broadcastreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;

import com.example.nebir.broadcastreceiver.bd.ContratoLlamadas;
import com.example.nebir.broadcastreceiver.pojo.Llamada;

import java.util.Date;

/**
 * Created by nebir on 18/02/2016.
 */
public class ReceptorLlamadas extends BroadcastReceiver {
    private Llamada l;
    private static Date fecha;
    private boolean reg=true;

    @Override
    public void onReceive(final Context context, Intent intent) {
        final Context c = context;
        TelephonyManager tm = (TelephonyManager)
                context.getSystemService(Context.TELEPHONY_SERVICE);
        if (intent.getAction().equals("android.intent.action.NEW_OUTGOING_CALL")) {
            fecha = new Date();
            l = new Llamada(fecha);
            context.getContentResolver().insert(ContratoLlamadas.TablaSalientes.CONTENT_URI, l.getContentValuesSaliente());
        }
        tm.listen(new PhoneStateListener() {
            public void onCallStateChanged(int state, String incomingNumber) {
                switch (state) {
                    case TelephonyManager.CALL_STATE_RINGING:
                        fecha = new Date();
                        l = new Llamada(fecha);
                        if (reg) {
                            context.getContentResolver().insert(ContratoLlamadas.TablaEntrantes.CONTENT_URI, l.getContentValuesSaliente());
                        }
                        reg=false;
                        break;
                }
            }

        }, PhoneStateListener.LISTEN_CALL_STATE);
    }


}
