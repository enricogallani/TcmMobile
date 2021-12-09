package com.example.front.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.front.R;
import com.example.front.db.entity.Activity;
import com.example.front.db.entity.User;

import java.util.List;

public class ActivityAdapter extends RecyclerView.Adapter<ActivityAdapter.ViewHolder> {

    private List<Activity> activities;
    private OnItemClickListener listener;

    public ActivityAdapter(List<Activity> activities) {
        this.activities = activities;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_activity, parent, false);
        return new ViewHolder(v, listener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Activity model = activities.get(position);

        holder.tvName.setText(model.getName());
        holder.tvUser.setText(String.format("Consultor: %s", model.getUser().getName()));
    }

    public void updateData(List<Activity> activities) {
        this.activities.addAll(activities);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return activities.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        OnItemClickListener mListener;
        TextView tvName;
        TextView tvUser;
        ConstraintLayout click;

        ViewHolder(View v, OnItemClickListener listener) {
            super(v);
            tvName = v.findViewById(R.id.name);
            tvUser = v.findViewById(R.id.user);
            click = v.findViewById(R.id.click);
            mListener = listener;

            click.setOnClickListener(view -> mListener.onClick(view, getAdapterPosition()));
        }
    }
}
