package com.example.univalle20202;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityManager;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.univalle20202.services.OnlineConnection;

import com.example.univalle20202.databinding.ActivityLoginBinding;

public class ImagesList extends AppCompatActivity {

    Button btnTrayectoria,btnVolver;
    String username, password;
    Bundle dataReceive;

    protected ActivityLoginBinding binding;
    public static final String BroadcastStringForAction = "checkinternet";
    protected IntentFilter mIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this, OnlineConnection.class));
        binding = ActivityLoginBinding.inflate(getLayoutInflater());

        mIntentFilter = new IntentFilter();
        mIntentFilter.addAction(BroadcastStringForAction);

        Intent i = new Intent(getApplicationContext(), OnlineConnection.class);
        startService(i);

        setContentView(R.layout.activity_lista_imagenes);
        btnTrayectoria= findViewById(R.id.btnTrayectoria);
        //btnVolver= findViewById(R.id.btnVolver);

        Bundle dataReceive = getIntent().getExtras();
        username = dataReceive.getString("userName");
        password = dataReceive.getString("passwd");

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

    private BroadcastReceiver myReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent.getAction().equals(BroadcastStringForAction)) {
                if (!intent.getStringExtra("online_status").equals("true")) {

                    ActivityManager am = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
                    ComponentName cn = am.getRunningTasks(1).get(0).topActivity;

                    if (!("class " + cn.getClassName()).equals(Login.class.toString())) {
                        Toast.makeText(getApplicationContext(), "No tienes conexión",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(context, Login.class));
                    }
                }
            }
        }
    };

    @Override
    protected void onRestart() {
        super.onRestart();
        registerReceiver(myReceiver, mIntentFilter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();
        registerReceiver(myReceiver, mIntentFilter);
    }
}