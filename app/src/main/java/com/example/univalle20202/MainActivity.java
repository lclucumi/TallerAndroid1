package com.example.univalle20202;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPlay,btnPause,btnStop,btnSalir,btnVolverAScroll;
    VideoView video;
    String username, password;
    Bundle dataReceive;
     Snackbar mySnackbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Inicialización de la clase VideoView
        video=(VideoView) findViewById(R.id.videoView);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.mi_bendicion;
        video.setVideoURI(Uri.parse(path));

        //Lanzamiento
        btnPlay= findViewById(R.id.btnPlay);
        btnStop= findViewById(R.id.btnStop);
        btnPause= findViewById(R.id.btnPause);

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnPause.setOnClickListener(this);

        Bundle dataReceive = getIntent().getExtras();
        username = dataReceive.getString("userName");
        password = dataReceive.getString("passwd");

    }

    //De nuevo a Login porque acaba trayectoria
    public void salir(View l){
        Intent ir = new Intent(this,Login.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
        Toast.makeText(getApplicationContext(), "Gracias por utilizar la App",Toast.LENGTH_SHORT).show();
    }

    public void volver(View l){
        Intent ir = new Intent(this,ListaImagenes.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnPlay:
                video.start();
                break;
            case R.id.btnPause:
                video.pause();
                break;
            case R.id.btnStop:
                video.stopPlayback();
                video.resume();
                break;
            default:
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.multimedia_videos_menu, menu);
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
                Intent irListaImagenes = new Intent(getApplicationContext(), ListaImagenes.class);
                irListaImagenes.addFlags(irListaImagenes.FLAG_ACTIVITY_CLEAR_TOP | irListaImagenes.FLAG_ACTIVITY_CLEAR_TASK);
                data.putString("userName",username);
                data.putString("passwd", password);
                irListaImagenes.putExtras(data);
                startActivity(irListaImagenes);
                return true;
            case R.id.menuMultimediaVideos:
                Intent irMultimediaVideos = new Intent(this, MainActivity.class);
                irMultimediaVideos.addFlags(irMultimediaVideos.FLAG_ACTIVITY_CLEAR_TOP | irMultimediaVideos.FLAG_ACTIVITY_CLEAR_TASK);
                data.putString("userName", dataReceive.getString("userName"));
                data.putString("passwd",dataReceive.getString("passwd"));
                irMultimediaVideos.putExtras(data);
                startActivity(irMultimediaVideos);
                return true;
            case R.id.menuAcercaDe:
                Toast.makeText(getApplicationContext(),"Próximamente", Toast.LENGTH_SHORT).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}