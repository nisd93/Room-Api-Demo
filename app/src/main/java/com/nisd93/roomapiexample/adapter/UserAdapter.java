package com.nisd93.roomapiexample.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nisd93.roomapiexample.R;
import com.nisd93.roomapiexample.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Nisarg W on 10/10/2017.
 */

public class UserAdapter extends RecyclerView.Adapter<UserAdapter.ViewHolder> {

    private List<User> user = new ArrayList<>();
    private final ActionCallback callback;

    public UserAdapter(@NonNull ActionCallback callback) {
        this.callback = callback;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_users, parent, false);
        final ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        User userData = user.get(position);
        holder.tvUser.setText(userData.getName());
        holder.tvAge.setText(""+userData.getAge());
        holder.tvAddress.setText(userData.getAddress());
    }

    @Override
    public int getItemCount() {
        return user.size();
    }

    public User getNote(int position) {
        return user.get(position);
    }

    public void setNotes(@NonNull List<User> user) {
        this.user = user;
        notifyDataSetChanged();
    }

    public interface ActionCallback {
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvUser, tvAge, tvAddress;

        ViewHolder(View itemView) {
            super(itemView);
            tvUser = (TextView) itemView.findViewById(R.id.tvUser);
            tvAge = (TextView) itemView.findViewById(R.id.tvAge);
            tvAddress = (TextView) itemView.findViewById(R.id.tvAddress);
        }
    }
}