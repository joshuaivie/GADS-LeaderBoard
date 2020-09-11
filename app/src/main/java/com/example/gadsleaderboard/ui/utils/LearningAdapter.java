package com.example.gadsleaderboard.ui.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.gadsleaderboard.R;
import com.example.gadsleaderboard.ui.data.LearningDataModel;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LearningAdapter extends RecyclerView.Adapter<LearningAdapter.MyViewHolder> {
    List<LearningDataModel> learningLeadersDataList;
    Context context;

    public LearningAdapter(List<LearningDataModel> learningLeadersDataList, Context context) {
        this.learningLeadersDataList = learningLeadersDataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.learning_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(v);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.name.setText(learningLeadersDataList.get(position).getName());
        holder.hours.setText(String.valueOf(learningLeadersDataList.get(position).getHours()) + " Learning Hours, ");
        holder.country.setText(learningLeadersDataList.get(position).getCountry());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(learningLeadersDataList.get(position).getBadgeUrl())
                .into(holder.imageView);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Clicked" + learningLeadersDataList.get(position).getName(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return learningLeadersDataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name;
        TextView hours;
        TextView country;
        CardView cardView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.imageLearning);
            name = (TextView) itemView.findViewById(R.id.name);
            hours = (TextView) itemView.findViewById(R.id.hours);
            country = (TextView) itemView.findViewById(R.id.country);
            cardView = (CardView) itemView.findViewById(R.id.cardLearning);
        }
    }
}
