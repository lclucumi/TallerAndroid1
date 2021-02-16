package com.example.univalle20202;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btnIniciar;
    EditText etUserName, etPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Lanzamiento
        btnIniciar=findViewById(R.id.btnIniciar);
        etUserName=findViewById(R.id.etUserName);
        etPasswd=findViewById(R.id.etPasswd);

        btnIniciar.setOnClickListener(this);
        btnIniciar.setBackgroundColor(0xFF009688);
    }

    @Override
    public void onClick(View v) {
        Intent ir= new Intent(this, Index.class);
        Bundle data = new Bundle();

        //Validación de campos usuario y contraseña
        if(etUserName.getText().toString().equals("Luz") && etPasswd.getText().toString().equals("Carime")) {
            Toast.makeText(getApplicationContext(),"Redireccionando...",Toast.LENGTH_SHORT).show();
            data.putString("userName",etUserName.getText().toString());
            data.putString("passwd",etPasswd.getText().toString());
            ir.putExtras(data);
            ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(ir);
        }
        //Usuario incorrecto
        else if(etUserName.getText().toString()!="Luz" && etPasswd.getText().toString().equals("Carime")){
            Toast.makeText(getApplicationContext(), "Usuario incorrecto",Toast.LENGTH_SHORT).show();
        }
        //Contraseña incorrecta
        else if(etUserName.getText().toString().equals("Luz") && etPasswd.getText().toString()!="admin123"){
            Toast.makeText(getApplicationContext(), "Contraseña incorrecta",Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(getApplicationContext(), "Verifica los datos por favor",Toast.LENGTH_SHORT).show();
        }
        data.putString("userName",etUserName.getText().toString());
        data.putString("passwd",etPasswd.getText().toString());
    }
}