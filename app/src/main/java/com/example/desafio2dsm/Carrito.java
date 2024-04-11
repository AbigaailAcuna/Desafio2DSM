package com.example.desafio2dsm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class Carrito extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carrito);

        LinearLayout layoutSelectedItems = findViewById(R.id.layout_selected_items);
        DecimalFormat df = new DecimalFormat("#.##");
        TextView totalTextView = findViewById(R.id.text_total); // Obtener referencia al TextView para el total

        Intent intent = getIntent();
        if (intent != null && intent.hasExtra("selectedItems")) {
            ArrayList<MenuItem> selectedItems = (ArrayList<MenuItem>) intent.getSerializableExtra("selectedItems");
            double total = 0.0; // Inicializar la variable total

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

            // Actualizar el texto del TextView para mostrar el total
            totalTextView.setText("Total: $" + df.format(total));
        }
    }


}