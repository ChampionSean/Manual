package com.example.marco.manual2;

import android.content.Context;
import android.webkit.JavascriptInterface;
import android.widget.Toast;

import org.json.JSONArray;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * Created by Marco on 14/10/2016.
 */

public class WebAppInterface {
    Context mContext;
    WebAppInterface(Context c) {
        mContext = c;
    }

    private DataBase db;
    private String [] arreglito;

    /** Show a toast from the web page */
    @JavascriptInterface
    public void showToast(String toast) {
        Toast.makeText(mContext, toast, Toast.LENGTH_SHORT).show();
    }


    public JSONArray arreJson(String [] a){

        return new JSONArray(Arrays.asList(a));

    }

    //b = valor, elemeto = marca/modelo/...

    @JavascriptInterface
    public String consulta2(String valor, String elemento){
        arreglito= new String[5];
        for (int i = 0; i<arreglito.length;i++){
            arreglito[i] = "Marco";
        }

        switch(elemento){
            case "marca": return arreJson(/*db.consultaMarcas);*/ arreglito).toString();
            case "modelo": return arreJson(/*db.consultaMarcas);*/ arreglito).toString();
            case "version":return arreJson(/*db.consultaMarcas);*/ arreglito).toString();
            case "ano":return arreJson(/*db.consultaMarcas);*/ arreglito).toString();
            case "posicion":return arreJson(/*db.consultaMarcas);*/ arreglito).toString();
            case "carga":return arreJson(/*db.consultaMarcas);*/ arreglito).toString();
            case "ancho": return arreJson(/*db.consultaMarcas);*/ arreglito).toString();
            default: return arreJson(new String[0]).toString();

        }
    }




}
