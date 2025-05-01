package com.cst338.lootcrate.viewHolders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cst338.lootcrate.R;
import com.cst338.lootcrate.database.entities.GameAnalytics;

import java.util.List;

public class AnalyticsAdapter extends RecyclerView.Adapter<AnalyticsAdapter.AnalyticsViewHolder> {
    private final List<GameAnalytics> gameAnalyticsList;
    private final Context context;

    public AnalyticsAdapter(Context context, List<GameAnalytics> gameAnalyticsList) {
        this.context = context;
        this.gameAnalyticsList = gameAnalyticsList;
    }

    @NonNull
    @Override
    public AnalyticsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.analytics_row, parent, false);
        return new AnalyticsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AnalyticsViewHolder holder, int position) {
        GameAnalytics data = gameAnalyticsList.get(position);
        holder.title.setText(data.title);
        holder.likes.setText("Likes: " + data.likeCount);
        holder.dislikes.setText("Dislikes: " + data.dislikeCount);
    }

    @Override
    public int getItemCount() {
        return gameAnalyticsList.size();
    }

    static class AnalyticsViewHolder extends RecyclerView.ViewHolder {
        TextView title, likes, dislikes;

        public AnalyticsViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.analytics_game_title);
            likes = itemView.findViewById(R.id.analytics_likes);
            dislikes = itemView.findViewById(R.id.analytics_dislikes);
        }
    }
}