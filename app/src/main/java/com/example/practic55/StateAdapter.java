package com.example.practic55;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class StateAdapter extends RecyclerView.Adapter<StateAdapter.ViewHolder> {

    private final LayoutInflater inflater;
    private final List<State> states;
    private final OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(int id); // Возвращаем только ID элемента
    }

    StateAdapter(Context context, List<State> states, OnItemClickListener listener) {
        this.states = states;
        this.inflater = LayoutInflater.from(context);
        this.listener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        State state = states.get(position);

        // Устанавливаем данные элемента
        holder.nameView.setText(state.getState_name());
        holder.capitalView.setText(state.getStateDescription());

        // Обрабатываем клик
        holder.itemView.setOnClickListener(v -> listener.onItemClick(state.getID_State()));
    }

    @Override
    public int getItemCount() {
        return states.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView nameView, capitalView;

        ViewHolder(View view) {
            super(view);
            nameView = view.findViewById(R.id.name);
            capitalView = view.findViewById(R.id.description);
        }
    }
}
