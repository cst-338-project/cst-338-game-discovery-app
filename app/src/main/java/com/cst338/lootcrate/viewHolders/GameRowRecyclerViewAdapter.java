package com.cst338.lootcrate.viewHolders;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cst338.lootcrate.R;

import java.util.ArrayList;

public class GameRowRecyclerViewAdapter extends RecyclerView.Adapter<GameRowRecyclerViewAdapter.MyViewHolder> {
    Context context;
    ArrayList<GameRowModel> gameModels;
    public GameRowRecyclerViewAdapter(Context context, ArrayList<GameRowModel> gameModels) {
        this.context = context;
        this.gameModels = gameModels;
    }

    @NonNull
    @Override
    public GameRowRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull GameRowRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.gameTitle.setText(gameModels.get(position).getTitle());
        holder.rowText.setText(gameModels.get(position).getButtonText());

        holder.gameImage.setImageResource(gameModels.get(position).getImage());


    }

    @Override
    public int getItemCount() {
        return gameModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView gameImage;
        ImageView rowButton;
        TextView gameTitle;
        TextView rowText;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            rowText = itemView.findViewById(R.id.rowText);
            rowButton = itemView.findViewById(R.id.rowButton);
            gameTitle = itemView.findViewById(R.id.gameTitleText);
            gameImage = itemView.findViewById(R.id.gameBackgroundImage);


        }
    }
}
