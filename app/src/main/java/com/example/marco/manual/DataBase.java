package excel;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;

public class DataBase extends SQLiteOpenHelper {

    public static final String DATA_BASE = "BASE_DATOS_MANUAL_r4SDTg53c49";
    public static final int VERSION_DB = 1;

    public static final String TEXT = " TEXT,";
    public static final String TEXT_WO = " TEXT ";
    public static final String TAG = "tag";
    public static final String INT_WO = " INT";
    public static final String INT = " INT,";
	
    //Users Table
    public static final String CAR_TABLE="CARROS_ESPECIFICACION_Dw2DEa";
    public static final String BRAND = "MARCA";
    public static final String MODEL ="MODELO";
    public static final String VERSION ="VERSION";
    public static final String YEAR = "ANIO";
    public static final String POSITION = "POSICION";
    public static final String CHARGE_TYPE = "TIPO_DE_CARGA";
    public static final String WIDTH = "ANCHO";
    public static final String RIN_DEFAULT = "ID_RIN";
    public static final String OPTION_1 = "ID_OPCION1";
    public static final String OPTION_2 = "ID_OPCION1";
    public static final String OPTION_3 = "ID_OPCION1";
	
    public static final String RIN_TABLE = "OPCION_RIN_d24DS";
    public static final String SERIE = "SERIE";
    public static final String RIN ="RIN";
    public static final String CHARGE_INDEX = "INDICE_DE_CARGA";
    public static final String RV = "RV";
	
    public static final String PRIMARY_KEY = " PRIMARY KEY,";

    private long rowsRin = 0;
    
    //Create
    public static final String SQL_CREATE_RIN = "CREATE TABLE IF NOT EXISTS " + RIN_TABLE +
            "(ID INTEGER AUTOINCREMENT DEFAULT 1," + SERIE + INT_WO + PRIMARY_KEY + RIN + INT_WO + PRIMARY_KEY +
			CHARGE_INDEX + TEXT_WO + PRIMARY_KEY + RV + TEXT_WO + PRIMARY_KEY + WIDTH + INT_WO + PRIMARY_KEY + ")";
			
	public static final String SQL_CREATE_CAR = "CREATE TABLE IF NOT EXISTS " + CAR_TABLE +
            "(ID INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 1," + BRAND + TEXT + MODEL + TEXT + VERSION +
			TEXT + YEAR + INT + POSITION + TEXT + CHARGE_TYPE + TEXT + WIDTH + INT + RIN_DEFAULT +
			INT + OPTION_1 + INT + OPTION_2 + INT + OPTION_3 + " INT )";

        
    
    public DataBase(Context context){
        super(context, DATA_BASE, null, VERSION_DB);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_RIN);
		db.execSQL(SQL_CREATE_CAR);
    }
	
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }
	
    public int getIdRin(int s,int r,String cI,String rv,int w){
        String query = "SELECT ID FROM " + RIN_TABLE + " WHERE " + SERIE;
        if(s == -1){
            query += " IS NULL AND ";
        }else{
            query += "=" + s + " AND ";
        }
        if(r == -1){
            query += RIN + " IS NULL AND ";
        }else{
            query += RIN + "=" + r + " AND ";
        }
        if(cI == null){
            query += CHARGE_INDEX + " IS NULL AND ";
        }else{
            query += CHARGE_INDEX + "='" + r + "' AND ";
        }
        if(rv == null){
            query += RV + " IS NULL AND ";
        }else{
            query += RV + "='" + r + "' AND ";
        }
        if(w == -1){
            query += WIDTH + " IS NULL";
        }else{
            query += WIDTH + "=" + w;
        }
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        int id = -1;
        if(null != cursor) {
            if (cursor.getCount() > 0) {
                cursor.moveToFirst();
                id = cursor.getInt(0);
            }
        }
        cursor.close();
        return id;
    }

    public String[] getModels(String brand){
        String [] modelsA=new String[0];
        SQLiteDatabase db = this.getWritableDatabase();
        int count = 0;
        Cursor cursor = db.rawQuery("SELECT distinct " + MODEL + " WHERE " +
                BRAND + "='" + brand + "'", null);
        if(null != cursor) {
            if (cursor.moveToFirst()) {
                modelsA = new String[cursor.getCount()];
                do{
                    modelsA[count] = cursor.getString(0);
                }while(cursor.moveToNext());
            }
        }
        return modelsA;
    }
    
    public String[] getVersions(String brand, String model){
        String [] versionsA=new String[0];
        SQLiteDatabase db = this.getWritableDatabase();
        int count = 0;
        Cursor cursor = db.rawQuery("SELECT distinct " + MODEL + " WHERE " +
                BRAND + "='" + brand + "' AND "+ MODEL + "='" + model + "'", null);
        if(null != cursor) {
            if (cursor.moveToFirst()) {
                versionsA = new String[cursor.getCount()];
                do{
                    versionsA[count] = cursor.getString(0);
                }while(cursor.moveToNext());
            }
        }
        return versionsA;
    }
    
    public int[] getYears(String brand, String model,String version){
        int [] yearsA=new int[0];
        SQLiteDatabase db = this.getWritableDatabase();
        int count = 0;
        Cursor cursor = db.rawQuery("SELECT distinct " + MODEL + " WHERE " +
                BRAND + "='" + brand + "' AND "+ MODEL + "='" + model +"' AND " +
                VERSION + "='" + version + "'", null);
        if(null != cursor) {
            if (cursor.moveToFirst()) {
                yearsA = new int[cursor.getCount()];
                do{
                    yearsA[count] = cursor.getInt(0);
                }while(cursor.moveToNext());
            }
        }
        return yearsA;
    }
    
    public Cursor getRins(String brand, String model,String version,int year){
        SQLiteDatabase db = this.getWritableDatabase();
        int count = 0;
        Cursor cursor = db.rawQuery("SELECT distinct " + MODEL + " WHERE " +
                BRAND + "='" + brand + "' AND "+ MODEL + "='" + model +"' AND " +
                VERSION + "='" + version + "' AND "+ YEAR + "=" + year, null);
        if(cursor !=null && cursor.getCount() > 0){
            return cursor;
        }
        return null;
    }

    public long addCar(String b,String m,String v,int y,String p,String cT,int w,int rD,int o1,int o2,int o3){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(BRAND, b);
        contentValues.put(MODEL, m);
        contentValues.put(VERSION, v);
        contentValues.put(YEAR, y);
        contentValues.put(POSITION, p);
        contentValues.put(CHARGE_TYPE, cT);
        contentValues.put(RIN_DEFAULT,rD);
        contentValues.put(OPTION_1,o1);
        contentValues.put(OPTION_2,o2);
        contentValues.put(OPTION_3,o3);
        long insert = db.insert(CAR_TABLE, null, contentValues);
        return insert;
    }
	
    public long addRin(int s,int r,String cI,String rv,int w){
        if(s!=-1 && r!=-1 && cI!=null && rv!=null && w!=-1){
            SQLiteDatabase db = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            if(s!=-1)contentValues.put(SERIE, s);
            if(r!=-1)contentValues.put(RIN, r);
            if(cI!=null)contentValues.put(CHARGE_INDEX, cI);
            if(rv!=null)contentValues.put(RV, rv);
            if(w!=-1)contentValues.put(WIDTH, w);
            long insert = db.insert(RIN_TABLE, null, contentValues);
            if(insert != -1){
                return ++rowsRin;
            }
        }
        return -1;
    }
}