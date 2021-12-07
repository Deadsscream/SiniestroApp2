package com.nicolas.siniestroapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListaSiniestros extends AppCompatActivity {

    ArrayList<Siniestro> siniestros;

    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_siniestros);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        RecyclerView re = (RecyclerView) findViewById(R.id.listado);
        re.setHasFixedSize(true);
        siniestros = new ArrayList<>();

        SiniestroAdapter adapter = new SiniestroAdapter(siniestros);
        re.setAdapter(adapter);

        re.setLayoutManager(new LinearLayoutManager(this));
        adapter.notifyDataSetChanged();
        Button recuperar= (Button) findViewById(R.id.lista_recuperar);
        recuperar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference referenciaMensaje = FirebaseDatabase.getInstance().getReference("Siniestros");
                referenciaMensaje.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Iterable<DataSnapshot> data = snapshot.getChildren();
                        for(DataSnapshot d : data){
                            Siniestro s = d.getValue(Siniestro.class);
                            if(s.getUUIDPropietario().equals(Tools.UsuarioLogueado.getID())){
                                siniestros.add(s);
                            }

                            adapter.notifyDataSetChanged();
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });
            }
        });

    }
}