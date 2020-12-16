package com.example.eva2_17_mysqlite_3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    SQLiteDatabase sqlDB;
    final String nombreDb = "mi_base_datos";
    List<String> nombre = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView = findViewById(R.id.listVDatos);

        sqlDB = openOrCreateDatabase(nombreDb,MODE_PRIVATE,null);

        try{
            sqlDB.execSQL("create table mitabla(id integer primary key autoincrement," +
                    "nombre text," +
                    "apellido text);");
        }catch (SQLiteException e){
            e.printStackTrace();
        }

        //insert
        ContentValues cvDatos= new ContentValues();
        cvDatos.put("nombre", "Ruben Alonso");
        cvDatos.put("apellido", "Hernandez Chavez");
        sqlDB.insert("mitabla",null,cvDatos);
        cvDatos.clear();
        cvDatos.put("nombre", "Isaac Rodolfo");
        cvDatos.put("apellido", "Ceballos Aguilar");
        sqlDB.insert("mitabla",null,cvDatos);
        cvDatos.clear();
        cvDatos.put("nombre", "Pablo Rene");
        cvDatos.put("apellido", "Rojas Arredondo");
        sqlDB.insert("mitabla",null,cvDatos);
        cvDatos.clear();

        long iClave;
        cvDatos.put("nombre", "Juan");
        cvDatos.put("apellido", "Rulfo");
        iClave = sqlDB.insert("mitabla",null,cvDatos);
        Toast.makeText(this, iClave + "", Toast.LENGTH_SHORT).show();

        //update
        cvDatos.clear();
        cvDatos.put("nombre", "Juanelo");
        String[] args={iClave+""};
        sqlDB.update("mitabla",cvDatos,"id = ?",args);

        //delete
        String[] args2 = {"Pablo Rene"};
        sqlDB.delete("mitabla","nombre = ?",args2);

        //Cursor cursor = sqlDB.rawQuery("select * from mitabla;",null);
        String[] columns = {"id", "nombre", "apellido"};
        String[] args3 = {"Juanelo"};
        Cursor cursor = sqlDB.query("mitabla",columns,"nombre like ?",args3,null,null,"apellido");
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            nombre.add(cursor.getString(cursor.getColumnIndex("id"))+ " "+
                    cursor.getString(cursor.getColumnIndex("nombre"))+ " " +
                    cursor.getString(cursor.getColumnIndex("apellido")));
            cursor.moveToNext();
        }
        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,nombre));

    }
}
