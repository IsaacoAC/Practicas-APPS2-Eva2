package com.example.eva2_13_archivos_espacio_externo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {


    EditText editText;
    String rutaSDApp, rutaSD;
    boolean blnEscribirArchivo = false;
    final static  int permisoEscr = 0;
    final static  String ARCHIVO = "prueba.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.edtTxtDatos);
        rutaSD = Environment.getExternalStorageDirectory().getAbsolutePath();
        rutaSDApp = getExternalFilesDir(null).getAbsolutePath();
        Log.wtf("rutaSd ", rutaSD);
        Log.wtf("rutaSDApp ", rutaSDApp);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){//que version de android tenemos
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)
            != PackageManager.PERMISSION_GRANTED){
                ActivityCompat.requestPermissions(
                        this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        permisoEscr);
            }else{
                blnEscribirArchivo = true;
            }
        }else{
            blnEscribirArchivo = true;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == permisoEscr){
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
                blnEscribirArchivo = true;
        }

    }

    public void Guardar(View v){
        if(blnEscribirArchivo){
            String[] aCadenas = editText.getText().toString().split("\n");
            try {
                FileOutputStream fos = new FileOutputStream(rutaSD+"/"+ARCHIVO);
                OutputStreamWriter osw = new OutputStreamWriter(fos);
                BufferedWriter bw = new BufferedWriter(osw);

                for (int i = 0 ; i<aCadenas.length;i++){
                    bw.append(aCadenas[i]);
                    bw.append("\n");
                }
                bw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }catch (IOException e){
                e.printStackTrace();
            }
        }
    }

    public void Leer(View v){
        if (blnEscribirArchivo){
            String Cade;

            try {
                FileInputStream fis = new FileInputStream(rutaSD+"/"+ARCHIVO);
                InputStreamReader isr = new InputStreamReader(fis);
                BufferedReader br = new BufferedReader(isr);
                while ((Cade = br.readLine()) != null){
                    editText.append(Cade);
                    editText.append("\n");
                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
