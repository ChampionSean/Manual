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
    @JavascriptInterface
    public JSONArray consulta2(String b){
        arreglito= new String[5];
        for (int i = 0; i<arreglito.length;i++){
            arreglito[i] = "Marco";
        }

        switch(b){
            case "marca": return arreJson(/*db.consultaMarcas);*/ arreglito);
            case "modelo": return arreJson(/*db.consultaMarcas);*/ arreglito);
            case "version":return arreJson(/*db.consultaMarcas);*/ arreglito);
            case "ano":return arreJson(/*db.consultaMarcas);*/ arreglito);
            case "posicion":return arreJson(/*db.consultaMarcas);*/ arreglito);
            case "carga":return arreJson(/*db.consultaMarcas);*/ arreglito);
            case "ancho": return arreJson(/*db.consultaMarcas);*/ arreglito);
            default: return arreJson(new String[0]);

        }
    }




}