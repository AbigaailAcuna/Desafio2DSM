package com.example.desafio2dsm;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.List;

public class  MenuAdapter extends RecyclerView.Adapter<MenuAdapter.MenuViewHolder> {

    private List<MenuItem> menuItems = new ArrayList<>();

    public void setMenuItems(List<MenuItem> menuItems) {
        this.menuItems = menuItems;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public MenuViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.menu_item, parent, false);
        return new MenuViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MenuViewHolder holder, int position) {
        MenuItem menuItem = menuItems.get(position);
        holder.nombreTextView.setText(menuItem.getNombre());
        holder.precioTextView.setText(String.valueOf(menuItem.getPrecio()));
    }

    @Override
    public int getItemCount() {
        return menuItems.size();
    }

    static class MenuViewHolder extends RecyclerView.ViewHolder {
        TextView nombreTextView;
        TextView platoTextView;
        TextView precioTextView;

        MenuViewHolder(@NonNull View itemView) {
            super(itemView);
            nombreTextView = itemView.findViewById(R.id.nombreTextView);
            platoTextView = itemView.findViewById(R.id.platoTextView);
            precioTextView = itemView.findViewById(R.id.precioTextView);
        }
    }
}
