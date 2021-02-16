package com.example.univalle20202;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class Index extends AppCompatActivity {

    EditText edData,etCount;
    Button colorear,btnContar;
    RadioGroup grupoRadio;
    RadioButton rbtnContar,rbtnColorear,rbtnActividades;
    int contador=0;
    Colorear objColorear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index);

        //Lanzamiento
        //editText
        edData= findViewById(R.id.etData);
        edData.setEnabled(false);
        etCount=findViewById(R.id.etCount);
        etCount.setVisibility(View.INVISIBLE);

        //Botones
        colorear=findViewById(R.id.btnColorear);
        colorear.setVisibility(View.INVISIBLE);
        btnContar=findViewById(R.id.btnContar);
        btnContar.setVisibility(View.INVISIBLE);

        //RadioGroup
        grupoRadio = findViewById(R.id.grupoRadio);

        //radioButtons
        rbtnContar= findViewById(R.id.rbtnContar);
        rbtnColorear= findViewById(R.id.rbtnColorear);
        rbtnActividades=findViewById(R.id.rbtnActividades);

        Bundle dataReceive = getIntent().getExtras();
        edData.setText("Bienvenida "+dataReceive.getString("userName")+" "+
                dataReceive.getString("passwd"));

        //Se indica que inicialmente no esté seleccionado ningún RadioButton
        grupoRadio.clearCheck();

        //Comprobamos que RadioButton ha sido seleccionado mediante el evento que nos indica el cambio de estado
        grupoRadio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            public void onCheckedChanged(RadioGroup group, int checkedId){
                if(rbtnContar.isChecked())
                {
                    colorear.setVisibility(View.INVISIBLE);
                    etCount.setVisibility(View.VISIBLE);
                    btnContar.setVisibility(View.VISIBLE);
                }
                else if(rbtnColorear.isChecked())
                {
                    etCount.setVisibility(View.INVISIBLE);
                    btnContar.setVisibility(View.INVISIBLE);
                    colorear.setVisibility(View.VISIBLE);
                }
                else if(rbtnActividades.isChecked())
                {
                    etCount.setVisibility(View.VISIBLE);
                    btnContar.setVisibility(View.VISIBLE);
                    colorear.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    public void explorar(View l){
        Intent ir = new Intent(this,ListaImagenes.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
    }

    public void volver(View l){
        Intent ir = new Intent(this,Login.class);
        ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(ir);
    }

    public void contar(View r){ //Recibe un objeto de la clase View porque se llama desde la UI
        contador=contador + 1;
        etCount.setText(""+contador);
    }

    public void colorear(View r){
        //Opción 1 :(
        /*for(int i=0; i<10; i++){
            colorear.setBackgroundColor(Color.rgb(aleatorio(),aleatorio(),aleatorio()));
            Thread.sleep(1000);
        }*/

        //Opción 2 :) Hilo independiente en línea
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<10; i++){
                    try {
                        Thread.sleep(1000);
                        colorear.setBackgroundColor(Color.rgb(aleatorio(),aleatorio(),aleatorio()));
                        //colorear.setText("Colorear: "+i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
            public int aleatorio(){
                return (int) (Math.random()*255) + 1;
            }
        }).start();*/

        //Opción 3 por medio de AsyncTask
        objColorear= new Colorear();
        objColorear.execute();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("contador",contador);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        contador= savedInstanceState.getInt("contador");
    }

    class Colorear extends AsyncTask<Void,Void,Void>{ // Parametros, Progreso, Resultados
        @Override
        protected Void doInBackground(Void... voids) {
            //colorear.setBackgroundColor(Color.rgb(aleatorio(),aleatorio(),aleatorio()));
            for(int i=0; i<10; i++){
                try {
                    Thread.sleep(1000);
                    publishProgress(); // <-- se llama al método onProgressUpdate
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        //Ctrl+O
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
            colorear.setBackgroundColor(Color.rgb(aleatorio(),aleatorio(),aleatorio()));
        }

        public int aleatorio(){
            return (int) (Math.random()*255) + 1;
        }
    }
}