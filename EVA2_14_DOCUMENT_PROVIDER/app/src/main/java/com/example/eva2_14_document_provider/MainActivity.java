package com.example.eva2_14_document_provider;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.util.Log;
import android.view.View;
import android.widget.EdgeEffect;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText edtTxDatos;
    final int Abrir_Archivo = 100;
    final int Guardar_Archivo = 200;
    Uri uriResu = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        edtTxDatos = findViewById(R.id.edtTxDatos);
    }

    public void Abrir(View v){
        Intent inAbrir = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        inAbrir.addCategory(Intent.CATEGORY_OPENABLE);
        inAbrir.setType("text/plain");//Mimetype
        inAbrir.putExtra(Intent.EXTRA_TITLE,"Prueba.txt");
        inAbrir.putExtra(DocumentsContract.EXTRA_INITIAL_URI,uriResu);//opcional
        startActivityForResult(inAbrir,Abrir_Archivo);
    }

    public void Guardar(View v){
        Intent inGuardar = new Intent(Intent.ACTION_CREATE_DOCUMENT);
        inGuardar.addCategory(Intent.CATEGORY_OPENABLE);
        inGuardar.setType("text/plain");//Mimetype
        inGuardar.putExtra(Intent.EXTRA_TITLE,"Prueba.txt");
        inGuardar.putExtra(DocumentsContract.EXTRA_INITIAL_URI,uriResu);//opcional
        startActivityForResult(inGuardar,Guardar_Archivo);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case Abrir_Archivo:
                if(resultCode== Activity.RESULT_OK){
                    uriResu = data.getData();
                    Log.wtf("URI", uriResu.toString());
                    String sCade;
                    try {
                        InputStream inputStream = getContentResolver().openInputStream(uriResu);
                        InputStreamReader isr = new InputStreamReader(inputStream);
                        BufferedReader br = new BufferedReader(isr);
                        while ((sCade = br.readLine()) != null){
                            edtTxDatos.append(sCade);
                            edtTxDatos.append("\n");
                        }
                        br.close();
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
            case Guardar_Archivo:
                if(resultCode== Activity.RESULT_OK) {
                    uriResu = data.getData();
                    Log.wtf("URI", uriResu.toString());
                    try{
                        String[] aCade = edtTxDatos.getText().toString().split("\n");
                        OutputStream outputStream = getContentResolver().openOutputStream(uriResu);
                        OutputStreamWriter osw = new OutputStreamWriter(outputStream);
                        BufferedWriter bw = new BufferedWriter(osw);
                        for (int i = 0; i<aCade.length;i++){
                            bw.append(aCade[i]);
                            bw.append("\n");
                        }
                        bw.close();
                    }catch (FileNotFoundException e){
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }
}
