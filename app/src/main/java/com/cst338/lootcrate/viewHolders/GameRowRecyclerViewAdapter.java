package com.cst338.lootcrate.viewHolders;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cst338.lootcrate.R;

import java.util.ArrayList;

public class GameRowRecyclerViewAdapter extends RecyclerView.Adapter<GameRowRecyclerViewAdapter.MyViewHolder> {
    private final GameRowRecyclerViewInterface gameRowRecyclerViewInterface;
    Context context;
    ArrayList<GameRowModel> gameModels;
    public GameRowRecyclerViewAdapter(Context context, ArrayList<GameRowModel> gameModels, GameRowRecyclerViewInterface gameRowRecyclerViewInterface) {
        this.context = context;
        this.gameModels = gameModels;
        this.gameRowRecyclerViewInterface = gameRowRecyclerViewInterface;
    }

    @NonNull
    @Override
    public GameRowRecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.game_row, parent, false);
        return new GameRowRecyclerViewAdapter.MyViewHolder(view, gameRowRecyclerViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull GameRowRecyclerViewAdapter.MyViewHolder holder, int position) {
        holder.gameTitle.setText(gameModels.get(position).getTitle());
        holder.rowText.setText(gameModels.get(position).getButtonText());
        holder.rowButton.setImageResource(gameModels.get(position).getButtonImage());

        Glide.with(context)
                .load(gameModels.get(position).getImage())
                .into(holder.gameImage);

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
        LinearLayout gameRowButton;

        public MyViewHolder(@NonNull View itemView, GameRowRecyclerViewInterface gameRowRecyclerViewInterface) {
            super(itemView);

            rowText = itemView.findViewById(R.id.rowText);
            rowButton = itemView.findViewById(R.id.rowButton);
            gameTitle = itemView.findViewById(R.id.gameTitleText);
            gameImage = itemView.findViewById(R.id.gameBackgroundImage);
            gameRowButton = itemView.findViewById(R.id.gameRowButton);

            gameImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (gameRowRecyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            gameRowRecyclerViewInterface.onCardClick(pos);
                        }
                    }
                }
            });

            gameRowButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (gameRowRecyclerViewInterface != null) {
                        int pos = getAdapterPosition();

                        if (pos != RecyclerView.NO_POSITION) {
                            gameRowRecyclerViewInterface.onButtonClick(pos);
                        }
                    }
                }
            });

        }
    }
}
