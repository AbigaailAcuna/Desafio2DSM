package com.example.desafio2dsm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class Carrito extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        // Recibir los elementos seleccionados del Intent
        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selectedItems")) {
            ArrayList<MenuItem> selectedItems = (ArrayList<MenuItem>) intent.getSerializableExtra("selectedItems");

            // Ahora puedes trabajar con los objetos MenuItem como desees
            for (MenuItem item : selectedItems) {
                Log.d("Carrito", "Elemento seleccionado: " + item.getId() + ", " + item.getNombre() + ", " + item.getPrecio());
                // Haz lo que necesites con cada elemento seleccionado
            }
        } else {
            Log.d("Carrito", "No se recibieron elementos seleccionados");
        }
    }
}
