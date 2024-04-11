package com.example.desafio2dsm;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Historial extends AppCompatActivity {

    private static final String TAG = "Historial";

    private DatabaseReference historialRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);

        // Obtener una referencia a la base de datos
        historialRef = FirebaseDatabase.getInstance().getReference().child("historial");

        // Agregar un listener para escuchar cambios en los datos de la base de datos
        historialRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                // Limpiar la tabla antes de agregar nuevos datos
                TableLayout tableLayout = findViewById(R.id.tableLayout);
                tableLayout.removeAllViews();

                // Recorrer los datos obtenidos de Firebase
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    // Obtener los valores de fecha, id y el arreglo de nombres y precios
                    String fecha = snapshot.child("fecha").getValue(String.class);
                    String id = snapshot.child("id").getValue(String.class);
                    int cantidad = snapshot.child("cantidad").getValue(Integer.class);
                    double subtotal = snapshot.child("subtotal").getValue(Double.class);

                    // Crear una fila de tabla para mostrar los datos
                    TableRow row = new TableRow(Historial.this);

                    // Agregar celdas para fecha, id, cantidad, subtotal
                    TextView fechaTextView = new TextView(Historial.this);
                    fechaTextView.setText(fecha);
                    row.addView(fechaTextView);

                    TextView idTextView = new TextView(Historial.this);
                    idTextView.setText(id);
                    row.addView(idTextView);

                    TextView cantidadTextView = new TextView(Historial.this);
                    cantidadTextView.setText(String.valueOf(cantidad));
                    row.addView(cantidadTextView);

                    TextView subtotalTextView = new TextView(Historial.this);
                    subtotalTextView.setText(String.format("%.2f", subtotal));
                    row.addView(subtotalTextView);

                    // Agregar la fila a la tabla
                    tableLayout.addView(row);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Manejar errores de base de datos
                Log.e(TAG, "Error al obtener datos", databaseError.toException());
            }
        });
    }
}
