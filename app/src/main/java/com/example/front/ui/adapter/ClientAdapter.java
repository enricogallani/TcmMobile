package com.example.front.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.db.entity.Client;

import java.util.List;

public class ClientAdapter extends RecyclerView.Adapter<ClientAdapter.ViewHolder> {

    private List<Client> clients;
    private OnItemClickListener listener;

    public ClientAdapter(List<Client> clients) {
        this.clients = clients;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_client, parent, false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Client model = clients.get(position);

        holder.tvName.setText(model.getName());
        holder.tvEmail.setText(model.getMail());
        holder.tvCnpj.setText(model.getCnpj());
    }

    public void updateData(List<Client> clients) {
        this.clients.addAll(clients);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return clients.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        OnItemClickListener mListener;
        TextView tvName;
        TextView tvEmail;
        TextView tvCnpj;
        ConstraintLayout click;

        ViewHolder(View v, OnItemClickListener listener) {
            super(v);
            tvName = v.findViewById(R.id.name);
            tvEmail = v.findViewById(R.id.email);
            tvCnpj= v.findViewById(R.id.cnpj);
            click = v.findViewById(R.id.click);
            mListener = listener;

            click.setOnClickListener(view -> mListener.onClick(view, getAdapterPosition()));
        }
    }
}
