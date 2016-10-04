package com.example.patrick.background_sensor_servidor;

import android.app.Service;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.location.LocationManager;
import android.os.Environment;
import android.os.Handler;
import android.os.IBinder;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;


public class MyServiceSemThread extends Service{

    private TextView textBatteryLevel = null;
    private String batteryLevelInfo = "Battery Level", nulidade, tempo = "";
    private String eixoX = "", eixoY = "", eixoZ = "";
    private String giroX = "", giroY = "", giroZ = "";
    private SensorManager manager;
    private Sensor sensorA, sensorG;
    private LocationManager locationManager;
    private int qtde = 0, i = 0;
    private List<Sensor> lista;
    private boolean pegouDado = false;
    private Calendar rightNow = Calendar.getInstance();

    final Handler handler = new Handler();
    final Bateria info = new Bateria(this);
    final EditText editTextAddress1 = MainActivity.editTextAddress;
    final EditText editTextPort1 = MainActivity.editTextPort;
    final TextView response1 = MainActivity.response;

    Runnable runnableCode;

    @Override
    public IBinder onBind(Intent arg0) {
        return null;
    }



    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "Service Started", LENGTH_LONG).show();

        runnableCode = new Runnable() {

            private int contador = 0;
            @Override
            public void run() {

                EditText editTextAddress = editTextAddress1;
                EditText editTextPort = editTextPort1;
                TextView response = response1;

                Client myClient = new Client(editTextAddress.getText().toString(), Integer.parseInt(editTextPort.getText().toString()), response, info);
                myClient.execute();

                if(contador++<10) handler.postDelayed(this, 2000);
                else onDestroy();
            }
        };

        handler.post(runnableCode);

        return START_STICKY;
    }

    @Override
    public void onDestroy() {

        super.onDestroy();
        handler.removeCallbacks(runnableCode);
        Toast.makeText(this, "Service Destroyed", LENGTH_LONG).show();

    }

}