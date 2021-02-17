package com.example.univalle20202;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ListaImagenes extends AppCompatActivity {

    Button btnTrayectoria,btnVolver;
    String username, password;
    Bundle dataReceive;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_imagenes);
        btnTrayectoria= findViewById(R.id.btnTrayectoria);
        //btnVolver= findViewById(R.id.btnVolver);

        Bundle dataReceive = getIntent().getExtras();
        username = dataReceive.getString("userName");
        password = dataReceive.getString("passwd");
        Toast.makeText(this, username, Toast.LENGTH_SHORT).show();
    }

    public void trayectoria(View l){
        Bundle data = new Bundle();
        Intent irMultimediaVideos = new Intent(this, MainActivity.class);
        irMultimediaVideos.addFlags(irMultimediaVideos.FLAG_ACTIVITY_CLEAR_TOP | irMultimediaVideos.FLAG_ACTIVITY_CLEAR_TASK);
        data.putString("userName",username);
        data.putString("passwd", password);
        irMultimediaVideos.putExtras(data);
        startActivity(irMultimediaVideos);
        Toast.makeText(getApplicationContext(), "Ahora un video que te tranquilizará",Toast.LENGTH_SHORT).show();
    }

    public void volver(View l){
        Intent ir= new Intent(this, Index.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.lista_imagenes_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Bundle data = new Bundle();
        switch (item.getItemId()) {
            case R.id.menuCerrarSesion:
                Intent irLogin = new Intent(getApplicationContext(), Login.class);
                irLogin.addFlags(irLogin.FLAG_ACTIVITY_CLEAR_TOP | irLogin.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(irLogin);
                return true;
            case R.id.menuVolver:
                Intent irIndex = new Intent(getApplicationContext(), Index.class);
                irIndex.addFlags(irIndex.FLAG_ACTIVITY_CLEAR_TOP | irIndex.FLAG_ACTIVITY_CLEAR_TASK);
                data.putString("userName",username);
                data.putString("passwd", password);
                irIndex.putExtras(data);
                startActivity(irIndex);
                return true;
            case R.id.menuMultimediaVideos:
                Intent irMultimediaVideos = new Intent(this, MainActivity.class);
                irMultimediaVideos.addFlags(irMultimediaVideos.FLAG_ACTIVITY_CLEAR_TOP | irMultimediaVideos.FLAG_ACTIVITY_CLEAR_TASK);
                data.putString("userName",username);
                data.putString("passwd", password);
                irMultimediaVideos.putExtras(data);
                startActivity(irMultimediaVideos);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}