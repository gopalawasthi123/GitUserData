package com.example.gopalawasthi.gituserdata;

import android.content.Context;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Gopal Awasthi on 15-03-2018.
 */

public class FollowersAdapter extends BaseAdapter {
    ArrayList<Followers> arrayList = new ArrayList<>();
    Context context;

    public FollowersAdapter(ArrayList<Followers> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Followers getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view1 = convertView;

        if(view1==null){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view1 = layoutInflater.inflate(R.layout.followers_design,  parent,false);
            Holder holder = new Holder();
            holder.followername = view1.findViewById(R.id.followername);
            holder.imageView = view1.findViewById(R.id.followerimage);
            view1.setTag(holder);
        }

        Holder holder = (Holder) view1.getTag();
        Followers myfollowers =(Followers)getItem(position);
        String url = myfollowers.getAvatar_url();
        holder.followername.setText(myfollowers.getName());
        Picasso.get().load(url).centerCrop().resize(100,100).into(holder.imageView);
        return view1;
    }

    class Holder {
        ImageView imageView;
        TextView followername;
    }
}
