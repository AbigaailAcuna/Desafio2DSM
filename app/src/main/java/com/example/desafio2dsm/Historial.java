package com.example.desafio2dsm;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.widget.Button;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Historial extends AppCompatActivity {

    private RecyclerView recyclerView;
    private HistorialAdapter adapter;
    private List<HistorialItem> historialItemList;

    private DatabaseReference historialRef;

    Button btnRegresar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);


        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        historialItemList = new ArrayList<>();
        adapter = new HistorialAdapter(this, historialItemList);
        recyclerView.setAdapter(adapter);

        historialRef = FirebaseDatabase.getInstance().getReference().child("Historial");

        historialRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                historialItemList.clear();
                for (DataSnapshot historialSnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot itemSnapshot : historialSnapshot.getChildren()) {
                        String nombre = itemSnapshot.child("nombre").getValue(String.class);
                        double precio = itemSnapshot.child("precio").getValue(Double.class);
                        //int cantidad = itemSnapshot.child("cantidad").getValue(Integer.class);
                        double subtotal = itemSnapshot.child("subtotal").getValue(Double.class);
                        String fecha = itemSnapshot.child("fecha").getValue(String.class);

                        //HistorialItem item = new HistorialItem(nombre, precio, cantidad, subtotal, fecha);
                        HistorialItem item = new HistorialItem(nombre, precio, subtotal, fecha);
                        historialItemList.add(item);
                    }
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar errores de base de datos
            }
        });

        btnRegresar = findViewById(R.id.btnRegresar);

        btnRegresar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Historial.this, Menu.class));
            }
        });
    }

    public void onCarritoButtonClick(View view) {
        // Abrir la actividad Menu
        startActivity(new Intent(Historial.this, Carrito.class));

    }

    public void onInicioButtonClick(View view) {
        // Abrir la actividad Historial
        startActivity(new Intent(Historial.this, Menu.class));
    }

    public void onHistorialButtonClick(View view) {
        // Ya esta en historial, no hace nada
    }
}
