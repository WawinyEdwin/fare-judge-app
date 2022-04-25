package com.example.booklist;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.service.autofill.TextValueSanitizer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    private Context context;
    Activity activity;
    private ArrayList review_id, user_id, establishment_name, establishment_type, food_served, location, review;

    Animation translate_anim;

    CustomAdapter (Activity activity,
                   Context context,
                   ArrayList review_id,
                   ArrayList user_id,
                   ArrayList establishment_name,
                   ArrayList establishment_type,
                   ArrayList food_served,
                   ArrayList location,
                   ArrayList review){
        this.activity = activity;
        this.context = context;
        this.review_id = review_id;
        this.user_id = user_id;
        this.establishment_name = establishment_name;
        this.establishment_type = establishment_type;
        this.food_served= food_served;
        this.location = location;
        this.review = review;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") final int position) {
        holder.review_id_text.setText(String.valueOf(review_id.get(position)));
        holder.user_id_text.setText(String.valueOf(user_id.get(position)));
        holder.establishment_name_text.setText(String.valueOf(establishment_name.get(position)));
        holder.establishment_type_text.setText(String.valueOf(establishment_type.get(position)));
        holder.food_served_text.setText(String.valueOf(food_served.get(position)));
        holder.location_text.setText(String.valueOf(location.get(position)));
        holder.review_text.setText(String.valueOf(review.get(position)));

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(context, UpdateActivity.class);
                i.putExtra("id", String.valueOf(review_id.get(position)));
                i.putExtra("user_id", String.valueOf(user_id.get(position)));
                i.putExtra("establishment_name", String.valueOf(establishment_name.get(position)));
                i.putExtra("establishment_type", String.valueOf(establishment_type.get(position)));
                i.putExtra("food_served", String.valueOf(food_served.get(position)));
                i.putExtra("location", String.valueOf(location.get(position)));
                i.putExtra("review", String.valueOf(review.get(position)));
                activity.startActivityForResult(i, 1);
            }
        });

    }

    @Override
    public int getItemCount() {
        return review_id.size();
    }

    public class MyViewHolder  extends RecyclerView.ViewHolder {

        TextView review_id_text, user_id_text, establishment_name_text, establishment_type_text, food_served_text, location_text, review_text;
        LinearLayout mainLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            user_id_text = itemView.findViewById(R.id.user_id_text);
            review_id_text = itemView.findViewById(R.id.review_id_text);
            establishment_name_text = itemView.findViewById(R.id.establishment_name_text);
            establishment_type_text = itemView.findViewById(R.id.establishment_type_text);
            food_served_text = itemView.findViewById(R.id.food_served_text);
            location_text = itemView.findViewById(R.id.location_text);
            review_text = itemView.findViewById(R.id.review_text);
            mainLayout = itemView.findViewById(R.id.mainLayout);
            translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }
    }
}
