package com.example.desafio2dsm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Carrito extends AppCompatActivity {

    Button btnFinalizar;
    ArrayList<MenuItem> selectedItems; // Variable para almacenar los elementos seleccionados
    HashMap<String, Integer> itemCountMap; // Mapa para almacenar la cantidad de cada elemento

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        LinearLayout layoutSelectedItems = findViewById(R.id.layout_selected_items);
        DecimalFormat df = new DecimalFormat("#.##");
        TextView totalTextView = findViewById(R.id.text_total); // Obtener referencia al TextView para el total

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selectedItems")) {
            selectedItems = (ArrayList<MenuItem>) intent.getSerializableExtra("selectedItems");
            double total = 0.0; // Inicializar la variable total
            itemCountMap = new HashMap<>(); // Inicializar el mapa de conteo de elementos

            for (MenuItem item : selectedItems) {
                // Crear un LinearLayout horizontal para cada elemento seleccionado
                LinearLayout itemLayout = new LinearLayout(this);
                itemLayout.setLayoutParams(new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
                itemLayout.setOrientation(LinearLayout.HORIZONTAL);

                // TextView para el nombre del artículo
                TextView itemName = new TextView(this);
                itemName.setText(item.getNombre());
                itemName.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        5.0f
                ));

                // Verificar si el elemento ya existe en el mapa de conteo de elementos
                int itemCount = itemCountMap.containsKey(item.getNombre()) ? itemCountMap.get(item.getNombre()) : 0;
                // Incrementar el conteo del elemento
                itemCountMap.put(item.getNombre(), itemCount + 1);

                // TextView para el precio
                TextView itemPrice = new TextView(this);
                itemPrice.setText("$" + item.getPrecio());
                itemPrice.setLayoutParams(new LinearLayout.LayoutParams(
                        0,
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        2.0f
                ));

                itemLayout.addView(itemName);
                itemLayout.addView(itemPrice);

                layoutSelectedItems.addView(itemLayout);

                // Sumar el precio al total
                total += item.getPrecio();
            }

            // Calcular subtotal por elemento y actualizar el texto del TextView para mostrar el total
            totalTextView.setText("Total: $" + df.format(total));
        }

        btnFinalizar = findViewById(R.id.btnFinalizar);

        btnFinalizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Obtener una referencia a la base de datos
                DatabaseReference historialRef = FirebaseDatabase.getInstance().getReference().child("Historial");

                // Crear un nuevo registro en la colección "historial" con los datos de los elementos seleccionados
                String idCompra = historialRef.push().getKey(); // Obtener el ID de la compra
                DatabaseReference idCompraRef = historialRef.child(idCompra); // Crear una referencia al nodo de la compra

                // Agregar los datos de los elementos seleccionados al nodo de la compra
                for (MenuItem item : selectedItems) {
                    String nombre = item.getNombre();
                    double precio = item.getPrecio();
                    int cantidad = itemCountMap.get(nombre);
                    double subtotal = precio * cantidad;

                    // Crear un mapa con los datos del elemento
                    Map<String, Object> itemData = new HashMap<>();
                    itemData.put("nombre", nombre);
                    itemData.put("precio", precio);
                    itemData.put("cantidad", cantidad);
                    itemData.put("subtotal", subtotal);

                    // Obtener la fecha actual y formatearla
                    Date fechaActual = new Date();
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
                    String fechaFormateada = dateFormat.format(fechaActual);

// Agregar la fecha al mapa de datos del elemento
                    itemData.put("fecha", fechaFormateada);

                    // Agregar los datos del elemento al nodo de la compra
                    idCompraRef.push().setValue(itemData);
                }

                // Mostrar un mensaje de éxito o realizar cualquier otra operación después de agregar el registro
                Log.d("Carrito", "Elementos agregados al historial de compras");

                //Navegar a pagina de historial
                startActivity(new Intent(Carrito.this,Finalizar.class));
            }
        });
    }
}
