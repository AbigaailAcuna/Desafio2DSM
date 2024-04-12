package com.example.desafio2dsm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Menu extends AppCompatActivity {
    private RecyclerView menuRecyclerView;
    private MenuAdapter menuAdapter;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        menuRecyclerView = findViewById(R.id.menuRecyclerView);
        menuRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        menuAdapter = new MenuAdapter();
        menuRecyclerView.setAdapter(menuAdapter);

        databaseReference = FirebaseDatabase.getInstance().getReference().child("Menu");

        loadMenuData();

    }

    private void loadMenuData() {
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<MenuItem> menuItems = new ArrayList<>();
                for (DataSnapshot categorySnapshot : dataSnapshot.getChildren()) {
                    for (DataSnapshot itemSnapshot : categorySnapshot.getChildren()) {
                        String id = itemSnapshot.child("id").getValue(String.class);
                        String nombre = itemSnapshot.child("nombre").getValue(String.class);
                        double precio = itemSnapshot.child("precio").getValue(Double.class);
                            menuItems.add(new MenuItem(id, nombre, precio));

                    }
                }
                menuAdapter.setMenuItems(menuItems);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });
    }

    public void onInicioButtonClick(View view) {
        // Ya esta en pantalla inicio, no hace nada
    }
    public void onCarritoButtonClick(View view) {
        // Obtener los elementos seleccionados del adaptador
        List<MenuItem> selectedItems = menuAdapter.getSelectedItems();


        // Convertir el conjunto de elementos seleccionados a un ArrayList
        ArrayList<MenuItem> selectedItemsList = new ArrayList<>(selectedItems);

        // Crear un Intent para abrir la actividad Carrito
        Intent intent = new Intent(Menu.this, Carrito.class);

        // Agregar el ArrayList de elementos seleccionados como dato extra en el Intent
        intent.putExtra("selectedItems", selectedItemsList);

        // Iniciar la actividad Carrito
        startActivity(intent);
    }

    public void onHistorialButtonClick(View view) {
        // Abrir la actividad Historial
        startActivity(new Intent(Menu.this, Historial.class));
    }

}