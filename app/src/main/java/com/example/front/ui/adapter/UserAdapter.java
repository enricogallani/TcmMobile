package com.example.front.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.db.entity.User;

import java.util.List;

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> users;
    private OnItemClickListener listener;

    public UserAdapter(List<User> users) {
        this.users = users;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_user, parent, false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final User model = users.get(position);

        holder.tvName.setText(model.getName());
        holder.tvEmail.setText(model.getMail());
    }

    public void updateData(List<User> users) {
        this.users.addAll(users);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return users.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        OnItemClickListener mListener;
        TextView tvName;
        TextView tvEmail;
        ConstraintLayout click;

        ViewHolder(View v, OnItemClickListener listener) {
            super(v);
            tvName = v.findViewById(R.id.name);
            tvEmail = v.findViewById(R.id.email);
            click = v.findViewById(R.id.click);
            mListener = listener;

            click.setOnClickListener(view -> mListener.onClick(view, getAdapterPosition()));
        }
    }
}
