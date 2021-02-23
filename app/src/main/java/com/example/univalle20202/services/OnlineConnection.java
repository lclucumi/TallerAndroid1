package com.example.univalle20202.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.os.Handler;
import android.os.IBinder;
import android.os.SystemClock;

import com.example.univalle20202.ImagesList;

public class OnlineConnection extends Service{
    Handler handler = new Handler();

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        handler.post(periodicUpdate);
        return START_STICKY;
    }

    //Método de devolución de llamada
    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    //isOnline se encarga de validar la conexión a la red, mediante ConnectivityManager
    //se notifica a la aplicación cuando cambia la conectividad de la red
    public boolean isOnline(Context c) {
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        boolean isWifiConn = false;
        boolean isMobileConn = false;

        //el getActiveNetworkInfo() muestra una instancia de NetworkInfo, que representa
        //la primera interfaz de red conectada que puede encontrar, o null si ninguna de
        //las interfaces está conectada
        for (Network network : connMgr.getAllNetworks()) {
            NetworkInfo networkInfo = connMgr.getNetworkInfo(network);
            if (networkInfo.getType() == ConnectivityManager.TYPE_WIFI) {
                isWifiConn |= networkInfo.isConnected();
            }
            if (networkInfo.getType() == ConnectivityManager.TYPE_MOBILE) {
                isMobileConn |= networkInfo.isConnected();
            }
        }

        return isWifiConn || isMobileConn;
    }

    //Función para la actualización dinámica de estado de conexión
    private final Runnable periodicUpdate = new Runnable() {
        @Override
        public void run() {
            //Intervalo dinámico
            handler.postDelayed(periodicUpdate, 1000 - SystemClock.elapsedRealtime() % 1000);
            Intent broadcastIntent = new Intent();
            broadcastIntent.setAction(ImagesList.BroadcastStringForAction);
            broadcastIntent.putExtra("online_status",""+isOnline(OnlineConnection.this));

            sendBroadcast(broadcastIntent);
        }
    };
}
