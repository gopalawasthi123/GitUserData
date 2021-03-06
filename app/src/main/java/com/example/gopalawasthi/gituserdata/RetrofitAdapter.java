package com.example.gopalawasthi.gituserdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Gopal Awasthi on 17-03-2018.
 */

public class RetrofitAdapter extends BaseAdapter {
    ArrayList<UserResponse > arrayList = new ArrayList<>();
    Context context ;

    public RetrofitAdapter(ArrayList<UserResponse> arrayList, Context context) {
        this.arrayList = arrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view1 = convertView;
        if(view1 == null ){
            LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view1 = layoutInflater.inflate(R.layout.customlistview,parent,false);
            Holder holder = new Holder();
            holder.imageView = view1.findViewById(R.id.imageView);
            holder.username = view1.findViewById(R.id.username);
            holder.userlocation = view1.findViewById(R.id.location);
            holder.followersCount = view1.findViewById(R.id.followercount);
            holder.followingCount = view1.findViewById(R.id.followingcount);
            view1.setTag(holder);
        }

        Holder  holder = (Holder)view1.getTag();
       UserResponse user_attr =(UserResponse) getItem(position);
        // Toast.makeText(context,user_attr.getUsername(), Toast.LENGTH_SHORT).show();
        holder.username.setText(user_attr.name);
        holder.userlocation.setText(user_attr.getLocation());
        String url = ((UserResponse) getItem(position)).getAvatar_url();
        Picasso.get().load(url).into(holder.imageView);
        holder.followersCount.setText(user_attr.followers);
        holder.followingCount.setText(user_attr.following);

        return view1;
    }

    class Holder{
        ImageView imageView;
        TextView username;
        TextView userlocation;
        TextView followersCount;
        TextView followingCount;
    }
    }

