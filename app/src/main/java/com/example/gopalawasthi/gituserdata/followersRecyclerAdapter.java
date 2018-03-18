package com.example.gopalawasthi.gituserdata;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Gopal Awasthi on 18-03-2018.
 */

public class followersRecyclerAdapter extends RecyclerView.Adapter<followersRecyclerAdapter.userViewHolder>{

   ArrayList<Followers> arrayList ;
   Context context;
    interface  onItemClickListener{
        void onItemClick(int position);
    }

    onItemClickListener listener;

    public followersRecyclerAdapter(ArrayList<Followers> arrayList, Context context, onItemClickListener listener) {
        this.arrayList = arrayList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public userViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater =(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View v = layoutInflater.inflate(R.layout.followers_design,parent,false);
        userViewHolder  holder = new userViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull final userViewHolder holder, int position) {
      Followers followers = arrayList.get(position);
        holder.followername.setText(followers.getName());
        Picasso.get().load(followers.getAvatar_url()).centerCrop().resize(100,100).into(holder.imageView);
       holder.itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               listener.onItemClick(holder.getAdapterPosition());
           }
       });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class userViewHolder extends  RecyclerView.ViewHolder{
        ImageView imageView;
        TextView followername;
        View itemView;
        public userViewHolder(View itemView) {
            super(itemView);
            this.itemView = itemView;
            imageView = itemView.findViewById(R.id.followerimage);
            followername = itemView.findViewById(R.id.followername);
        }


    }

}
