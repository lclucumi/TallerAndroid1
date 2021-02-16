package com.example.univalle20202;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnPlay,btnPause,btnStop,btnSalir,btnVolverAScroll;
    VideoView video;

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
        btnSalir= findViewById(R.id.btnSalir);
        btnVolverAScroll=findViewById(R.id.btnVolverAScroll);

        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
        btnPause.setOnClickListener(this);

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
}