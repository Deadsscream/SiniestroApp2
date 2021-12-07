package com.nicolas.siniestroapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class IniciarSesion extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private EditText correo;
    private EditText contraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iniciar_sesion);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        correo = (EditText) findViewById(R.id.login_correo);
        contraseña = (EditText) findViewById(R.id.login_contraseña);
        Button login= (Button)findViewById(R.id.login_iniciar);
        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                mDatabase.child("Cuentas").get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DataSnapshot> task) {
                        if (!task.isSuccessful()) {
                            String a = "Error getting data: "+task.getException();
                            Toast.makeText(getApplicationContext(),a,Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Iterable<DataSnapshot> datos = task.getResult().getChildren();
                            boolean encontrado = false;
                            for (DataSnapshot d : datos){
                                Cuenta c = d.getValue(Cuenta.class);
                                if (c.getCorreo().equals(correo.getText().toString())){
                                    if(c.getContraseña().equals(Tools.encriptarMD5(contraseña.getText().toString()))){
                                        encontrado = true;
                                        Tools.UsuarioLogueado = c;
                                        Intent intent = new Intent(getApplicationContext(), MenuLogueado.class);
                                        startActivity(intent);
                                    }
                                    else{
                                        encontrado = true;
                                        Toast.makeText(getApplicationContext(),"Contraseña incorrecta",Toast.LENGTH_SHORT).show();
                                    }
                                }
                            }
                            if(!encontrado){
                                Toast.makeText(getApplicationContext(),"Cuenta no existe",Toast.LENGTH_SHORT).show();
                            }
                        }
                    }
                });
            }
        });
        if(Tools.UsuarioLogueado!=null){
            Intent intent = new Intent(getApplicationContext(), MenuLogueado.class);
            startActivity(intent);
        }
    }
}