package com.example.univalle20202;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.example.univalle20202.R;
import com.example.univalle20202.databinding.ActivityLoginBinding;
import com.example.univalle20202.services.CheckConection;

public class Login extends AppCompatActivity implements View.OnClickListener {

    Button btnIniciar;
    EditText etUserName, etPasswd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

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

        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://run.mocky.io/v3/1aaf3907-9707-4ddf-94d5-dc1d24afb383";

        // Request a string response from the provided URL.
        JsonObjectRequest stringRequest = new JsonObjectRequest (url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                        try {
                            JSONArray jsonArray = response.getJSONArray("users");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject user = jsonArray.getJSONObject(i);
                                String userName = user.getString("usernamer");
                                int password = user.getInt("passwd");

                                if(etUserName.getText().toString().equals(userName) && etPasswd.getText().toString().equals(String.valueOf(password))){

                                    Toast.makeText(getApplicationContext(),"Redireccionando...",Toast.LENGTH_SHORT).show();
                                    data.putString("userName",etUserName.getText().toString());
                                    data.putString("passwd",etPasswd.getText().toString());
                                    ir.putExtras(data);
                                    ir.addFlags(ir.FLAG_ACTIVITY_CLEAR_TOP | ir.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(ir);
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                etUserName.setText("That didn't work!");
            }
        });
        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}