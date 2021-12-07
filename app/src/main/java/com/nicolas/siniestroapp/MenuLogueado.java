package com.nicolas.siniestroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.hardware.lights.LightState;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MenuLogueado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_logueado);
        TextView usuario = (TextView) findViewById(R.id.menu_usuario);
        usuario.setText("BIENVENIDO, "+Tools.UsuarioLogueado.getNombre());
        Button registrar= (Button)findViewById(R.id.menu_nuevo);
        registrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NuevoSiniestro.class);
                startActivity(intent);
            }
        });

        Button listar= (Button)findViewById(R.id.menu_bitacora);
        listar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ListaSiniestros.class);
                startActivity(intent);
            }
        });

        Button cerrar= (Button)findViewById(R.id.menu_cerrar);
        cerrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Tools.UsuarioLogueado=null;
                finish();
            }
        });
    }
}