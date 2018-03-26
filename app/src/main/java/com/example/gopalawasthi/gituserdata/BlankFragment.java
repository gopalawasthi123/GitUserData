package com.example.gopalawasthi.gituserdata;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class BlankFragment extends Fragment implements followersRecyclerAdapter.onItemClickListener {

    ArrayList<Followers> arrayList;
    //    FollowersAdapter followersAdapter;
    RecyclerView recyclerView;
    followersRecyclerAdapter adapter;
    ProgressBar progressBar;
    forfollowerCallback callback;
    public interface forfollowerCallback {
        void onfollowerselected(Followers followers);
    }


    public BlankFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
        callback =(forfollowerCallback)context;
        }
        catch (ClassCastException e){
            throw  new ClassCastException("Activity should implement UserSelectedCallback");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.fragment_blank, container, false);
        recyclerView = view.findViewById(R.id.followerslist);
        arrayList = new ArrayList<>();
        progressBar = view.findViewById(R.id.progressbar);
//        followersAdapter = new FollowersAdapter(arrayList, this);
//        listView.setAdapter(followersAdapter);
        adapter = new followersRecyclerAdapter(arrayList,getContext(),this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        // item touch helper for touch event handling
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(ItemTouchHelper.UP|ItemTouchHelper.DOWN,ItemTouchHelper.RIGHT|ItemTouchHelper.LEFT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                int from = viewHolder.getAdapterPosition();
                int to = target.getAdapterPosition();
                Collections.swap(arrayList,from,to);
                adapter.notifyItemMoved(from,to);
                return true;
            }

            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int from  = viewHolder.getAdapterPosition();
                arrayList.remove(from);
                adapter.notifyItemRemoved(from);
            }

        });
        itemTouchHelper.attachToRecyclerView(recyclerView);

        Intent intent = getActivity().getIntent();


        String name = intent.getStringExtra(MainActivity.USER_NAME);
//        String url = name  ;
//        listView.setVisibility(View.GONE);
        recyclerView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
//        FollowersAsynchronous followersAsynchronous = new FollowersAsynchronous(new FollowersAsynchronous.followersinterface() {
//            @Override
//            public void onfollowerscomplete(ArrayList<Followers> followersArrayList) {
//                if(followersArrayList!=null){
//                    for (int i =0 ;i <followersArrayList.size() ;i++){
//                    Followers followers = followersArrayList.get(i);
//                        String name = followers.getName();
//                        String image = followers.getImage();
//                        Followers followers1  = new Followers(name , image);
//                        arrayList.add(followers1);
//
//                    }
//                    followersAdapter.notifyDataSetChanged();
//
//                } else
//                    Toast.makeText(FollowersList.this,"tryagain!",Toast.LENGTH_SHORT).show();
//                listView.setVisibility(View.VISIBLE);
//                progressBar.setVisibility(View.GONE);
//            }
//        });
//        followersAsynchronous.execute(url);
//

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        MyInterface myInterface = retrofit.create(MyInterface.class);
        final Call<ArrayList<Followers>> arrayListCall = myInterface.getfollowers(name);
        arrayListCall.enqueue(new Callback<ArrayList<Followers>>() {
            @Override
            public void onResponse(Call<ArrayList<Followers>> call, Response<ArrayList<Followers>> response) {
                if(response!=null){
                    arrayList.clear();
                    arrayList.addAll(response.body());

                    }
                    //followersAdapter.notifyDataSetChanged();
                    adapter.notifyDataSetChanged();
//                    listView.setVisibility(View.VISIBLE);
                    recyclerView.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.GONE);
                }


            @Override
            public void onFailure(Call<ArrayList<Followers>> call, Throwable t) {

            }
        });
        return view;
    }


    @Override
    public void onItemClick(int position) {
        Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
       Followers followers = arrayList.get(position);
       callback.onfollowerselected(followers);


    }
}


