package com.example.desafio2dsm;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
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
                        String nombre = itemSnapshot.child("nombre").getValue(String.class);
                        double precio = itemSnapshot.child("precio").getValue(Double.class);
                        // Check if the current category is "Bebida"
                        if (categorySnapshot.getKey().equals("Bebida")) {
                            menuItems.add(new MenuItem(nombre, precio));
                        } else if (categorySnapshot.getKey().equals("Comida")) {
                            // Access the "plato" field for the "Comida" collection
                            String plato = itemSnapshot.child("plato").getValue(String.class);
                            menuItems.add(new MenuItem(plato, precio));
                        }
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

}