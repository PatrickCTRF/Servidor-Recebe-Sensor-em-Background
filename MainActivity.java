package com.example.patrick.background_sensor_servidor;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    public static EditText editTextAddress, editTextPort;
    public static TextView response;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle savedInstanceState2 = savedInstanceState;
        setContentView(R.layout.activity_main);

        editTextAddress = (EditText) findViewById(R.id.addressEditText);
        editTextPort = (EditText) findViewById(R.id.portEditText);
        response = (TextView) findViewById(R.id.responseTextView);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.activity_main, menu);
        return false;//Estou retornando False para o menu nao ser exibido. VErificar se vai dar certo!!
    }

    // Método que dará início ao servico de background.
    public void startService(View view) {
        startService(new Intent(getBaseContext(), MyServiceSemThread.class));//Como aki eu invoco um metodo q nao foi implementado??? Ele pertence a Context.
    }

    // Metodo que parara o servico
    public void stopService(View view) {
        stopService(new Intent(getBaseContext(), MyServiceSemThread.class));
    }
}
