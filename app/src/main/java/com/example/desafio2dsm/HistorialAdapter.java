package com.example.desafio2dsm;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistorialAdapter extends RecyclerView.Adapter<HistorialAdapter.HistorialViewHolder> {

    private Context context;
    private List<HistorialItem> historialItemList;

    public HistorialAdapter(Context context, List<HistorialItem> historialItemList) {
        this.context = context;
        this.historialItemList = historialItemList;
    }

    @NonNull
    @Override
    public HistorialViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_historial_item, parent, false);
        return new HistorialViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HistorialViewHolder holder, int position) {
        HistorialItem item = historialItemList.get(position);
        holder.nombreTextView.setText(item.getNombre());
        holder.precioTextView.setText(String.valueOf(item.getPrecio()));
        holder.fechaTextView.setText(item.getFecha());
    }

    @Override
    public int getItemCount() {
        return historialItemList.size();
    }

    public class HistorialViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView, precioTextView/*, cantidadTextView, subtotalTextView*/, fechaTextView;

        public HistorialViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            precioTextView = itemView.findViewById(R.id.precioTextView);
            fechaTextView = itemView.findViewById(R.id.fechaTextView);
        }
    }
}
