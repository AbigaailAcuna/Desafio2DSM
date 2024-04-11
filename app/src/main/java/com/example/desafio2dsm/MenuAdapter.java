package com.example.desafio2dsm;

import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import android.content.Intent;
import android.view.View;

import java.io.Serializable;
public class  MenuAdapter extends RecyclerView.Adapter<MenuAdapter.ViewHolder> implements Serializable {

    private List<MenuItem> menuItems = new ArrayList<>();
    private Set<MenuItem> selectedItems;

    public Set<MenuItem> getSelectedItems() {
        return selectedItems;
    }
    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
        this.selectedItems = new HashSet<>();
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);
        holder.nombreTextView.setText(menuItem.getNombre());
        holder.platoTextView.setText("Plato"); // Puedes establecer el valor adecuado aquí
        holder.precioTextView.setText(String.valueOf(menuItem.getPrecio())); // Puedes ajustar el formato según necesites

        // Establecer onClickListener para el botón Agregar
        holder.agregarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mostrar un Toast con el ID del item al que se hizo clic
                Toast.makeText(v.getContext(), "ID del item: " + menuItem.getId(), Toast.LENGTH_SHORT).show();

                // Agregar el elemento al conjunto de elementos seleccionados
                selectedItems.add(menuItem);

                // Log para verificar los elementos seleccionados
                Log.d("MenuAdapter", "Elementos seleccionados:");

                // Construir la representación detallada de cada elemento
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("[");
                for (MenuItem item : selectedItems) {
                    stringBuilder.append("[")
                            .append(item.getId()).append(",")
                            .append(item.getNombre()).append(",")
                            .append(item.getPrecio())
                            .append("],");
                }
                // Eliminar la coma extra al final y cerrar el corchete
                if (!selectedItems.isEmpty()) {
                    stringBuilder.deleteCharAt(stringBuilder.length() - 1);
                }
                stringBuilder.append("]");

                // Imprimir la representación detallada en el log
                Log.d("MenuAdapter", "Elementos seleccionados: " + stringBuilder.toString());
            }




        });
    }




    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView;
        TextView platoTextView;
        TextView precioTextView;
        Button agregarButton;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            platoTextView = itemView.findViewById(R.id.platoTextView);
            precioTextView = itemView.findViewById(R.id.precioTextView);
            agregarButton = itemView.findViewById(R.id.agregarButton);
        }
    }
}
