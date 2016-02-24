package com.example.nebir.broadcastreceiver;

import android.database.Cursor;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.nebir.broadcastreceiver.bd.ContratoLlamadas;

public class MainActivity extends AppCompatActivity {

    private int[] recibidasCont, salientesCont, cont;
    private Cursor cursor;
    private WebView wv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        wv = (WebView) findViewById(R.id.webView);
        WebSettings webSettings = wv.getSettings();
        webSettings.setJavaScriptEnabled(true);

        final TabLayout tabs = (TabLayout) findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Llamadas recibidas"));
        tabs.addTab(tabs.newTab().setText("Llamadas enviadas"));

        cursor =getContentResolver().query(ContratoLlamadas.TablaEntrantes.CONTENT_URI, null, null, null, null);
        recibidasCont=Funciones.contadorLlamadas(cursor);

        cursor = getContentResolver().query(ContratoLlamadas.TablaSalientes.CONTENT_URI, null, null, null, null);
        salientesCont = Funciones.contadorLlamadas(cursor);
        cont=recibidasCont;
        wv.loadUrl("file:///android_asset/canvas/graficoSemana.html");
        wv.addJavascriptInterface(this, "InterfazAndroid");

        tabs.setTabMode(TabLayout.MODE_FIXED);
        tabs.setOnTabSelectedListener(
                new TabLayout.OnTabSelectedListener() {
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        switch (tabs.getSelectedTabPosition()) {
                            case 0:
                                cont=recibidasCont;
                                wv.reload();
                                break;
                            case 1:
                                cont=salientesCont;
                                wv.reload();
                                break;
                            default:
                                break;
                        }
                    }

                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {

                    }

                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                    }
                }
        );
    }


    @JavascriptInterface
    public int enviarDia(int x){
        return cont[x];
    }

}
