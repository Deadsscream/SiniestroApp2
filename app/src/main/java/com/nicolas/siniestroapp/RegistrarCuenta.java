package com.nicolas.siniestroapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrarCuenta extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private EditText correo;
    private EditText contraseña;
    private EditText nombre;
    private Spinner genero;
    private EditText fecha;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar_cuenta);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        correo = (EditText) findViewById(R.id.registrar_correo);
        contraseña = (EditText) findViewById(R.id.registrar_contraseña);
        nombre = (EditText) findViewById(R.id.registrar_nombre);
        genero = (Spinner) findViewById(R.id.registrar_genero);
        fecha = (EditText) findViewById(R.id.registrar_fecha);
        Button limpiar= (Button)findViewById(R.id.registrar_limpiar);
        limpiar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Limpiar();
            }
        });

        Button registrar= (Button)findViewById(R.id.registrar_registrar);
        registrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(HayVacios()){
                    Toast.makeText(getApplicationContext(),"Completar Campos Vacíos",Toast.LENGTH_SHORT).show();
                }
                else{
                    Cuenta u = new Cuenta(correo.getText().toString(), Tools.encriptarMD5(contraseña.getText().toString()), nombre.getText().toString(), genero.getSelectedItem().toString(), fecha.getText().toString());
                    mDatabase.child("Cuentas").child(u.getID()).setValue(u);
                    Toast.makeText(getApplicationContext(),"Cuenta creada exitosamente",Toast.LENGTH_SHORT).show();
                    Limpiar();
                }
            }
        });

        fecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.registrar_fecha:
                        showDatePickerDialog();
                        break;
                }
            }
            private void showDatePickerDialog() {
                DatePickerFragment newFragment = DatePickerFragment.newInstance(new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        // +1 because January is zero
                        final String selectedDate = day + " / " + (month+1) + " / " + year;
                        fecha.setText(selectedDate);
                    }
                });

                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });

    }
    private boolean HayVacios(){
        if(correo.getText().toString().equals("") || Tools.encriptarMD5(contraseña.getText().toString()).equals("") || nombre.getText().toString().equals("") || genero.getSelectedItem().toString().equals("") || fecha.getText().toString().equals("")){
            return true;
        }
        return false;
    }
    private void Limpiar(){
        correo.setText("");
        contraseña.setText("");
        nombre.setText("");
        genero.setSelection(2);
        fecha.setText("");
    }

}