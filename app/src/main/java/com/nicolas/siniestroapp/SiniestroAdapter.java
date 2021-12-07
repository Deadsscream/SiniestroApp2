package com.nicolas.siniestroapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class SiniestroAdapter extends RecyclerView.Adapter<SiniestroAdapter.ViewHolder> {

    // Provide a direct reference to each of the views within a data item
    // Used to cache the views within the item layout for fast access
    public class ViewHolder extends RecyclerView.ViewHolder {
        // Your holder should contain a member variable
        // for any view that will be set as you render a row
        public TextView mtitulo;
        public TextView mdescripcion;
        public TextView mubicacion;
        public TextView mfecha;
        public Button mMapa;


        // We also create a constructor that accepts the entire item row
        // and does the view lookups to find each subview
        public ViewHolder(View itemView) {
            // Stores the itemView in a public final member variable that can be used
            // to access the context from any ViewHolder instance.
            super(itemView);

            mtitulo = (TextView) itemView.findViewById(R.id.item_titulo);
            mdescripcion = (TextView) itemView.findViewById(R.id.item_descripcion);
            mubicacion = (TextView) itemView.findViewById(R.id.item_ubicacion);
            mfecha = (TextView) itemView.findViewById(R.id.item_fecha);
            mMapa = (Button) itemView.findViewById(R.id.item_mapa);
        }
    }

    private ArrayList<Siniestro> mSiniestros;

    // Pass in the contact array into the constructor
    public SiniestroAdapter(ArrayList<Siniestro> siniestros) {
        mSiniestros = siniestros;
    }
    // Usually involves inflating a layout from XML and returning the holder
    @Override
    public SiniestroAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.layout_siniestro, parent, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    // Involves populating data into the item through holder
    @Override
    public void onBindViewHolder(SiniestroAdapter.ViewHolder holder, int position) {
        // Get the data model based on position
        Siniestro sin = mSiniestros.get(position);

        // Set item views based on your views and data model
        TextView stitulo = holder.mtitulo;
        stitulo.setText(sin.getTitulo());
        TextView sdescripcion = holder.mdescripcion;
        sdescripcion.setText(sin.getDescripcion());
        TextView subicacion = holder.mubicacion;
        subicacion.setText("LAT "+sin.getLatitud()+", LON "+sin.getLongitud());
        Button sMapa = holder.mMapa;
        sMapa.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), MapsActivity.class);
                intent.putExtra("lat",sin.getLatitud());
                intent.putExtra("lon",sin.getLongitud());
                intent.putExtra("tit",sin.getTitulo());
                view.getContext().startActivity(intent);
            }
        });
        TextView sfecha = holder.mfecha;
        sfecha.setText(sin.getFecha());

    }

    // Returns the total count of items in the list
    @Override
    public int getItemCount() {
        return mSiniestros.size();
    }
}
