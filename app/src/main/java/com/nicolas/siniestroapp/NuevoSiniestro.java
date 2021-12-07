package com.nicolas.siniestroapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NuevoSiniestro extends AppCompatActivity {
    private DatabaseReference mDatabase;
    private EditText titulo;
    private EditText descripcion;
    private EditText ubi_latitud;
    private EditText ubi_longitud;
    private ImageView foto;
    private EditText fecha;
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo_siniestro);
        mDatabase = FirebaseDatabase.getInstance().getReference();
        titulo = (EditText) findViewById(R.id.nuevo_titulo);
        descripcion = (EditText) findViewById(R.id.nuevo_descripcion);
        ubi_latitud = (EditText) findViewById(R.id.nuevo_latitud);
        ubi_longitud = (EditText) findViewById(R.id.nuevo_longitud);
        foto = (ImageView) findViewById(R.id.nuevo_imagen);
        fecha = (EditText) findViewById(R.id.nuevo_fecha);
        fecha.setEnabled(false);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        String currentDateandTime = sdf.format(new Date());
        fecha.setText(currentDateandTime);
        Button photoButton = (Button) this.findViewById(R.id.nuevo_foto);
        photoButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if (checkSelfPermission(Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }
            }
        });

        Button limpiar = (Button)findViewById(R.id.nuevo_limpiar);
        limpiar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
            }
        });

        Button registrar = (Button)findViewById(R.id.nuevo_registrar);
        registrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if(HayVacios()){
                    Toast.makeText(getApplicationContext(),"Completar Campos Vacíos",Toast.LENGTH_SHORT).show();
                }
                else{
                    Drawable photo = foto.getDrawable();
                    Siniestro s = new Siniestro(Tools.UsuarioLogueado.getID(), titulo.getText().toString(), descripcion.getText().toString(), ubi_latitud.getText().toString(), ubi_longitud.getText().toString(), fecha.getText().toString());
                    mDatabase.child("Siniestros").child(s.getID()).setValue(s);
                    Toast.makeText(getApplicationContext(),"Siniestro Registrado",Toast.LENGTH_SHORT).show();
                    Limpiar();
                }
            }
        });
    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(this, "Permisos de cámara otorgados", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(this, "Permisos de cámara denegados, intente denuevo", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            foto.setImageBitmap(photo);
        }
    }
    private boolean HayVacios(){
        if(titulo.getText().toString().equals("") || descripcion.getText().toString().equals("") || ubi_latitud.getText().toString().equals("") || ubi_longitud.getText().toString().equals("")){
            return true;
        }
        return false;
    }
    private void Limpiar(){
        titulo.setText("");
        descripcion.setText("");
        ubi_latitud.setText("");
        ubi_longitud.setText("");
        fecha.setText("");
    }
}