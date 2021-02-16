package com.example.univalle20202;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ListaImagenes extends AppCompatActivity {

    Button btnTrayectoria,btnVolver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_imagenes);
        btnTrayectoria= findViewById(R.id.btnTrayectoria);
        btnVolver= findViewById(R.id.btnVolver);
    }

    public void trayectoria(View l){
        Intent ir = new Intent(this,MainActivity.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
        Toast.makeText(getApplicationContext(), "Ahora un video que te tranquilizará",Toast.LENGTH_SHORT).show();
    }

    public void volver(View l){
        Intent ir= new Intent(this, Index.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
    }
}