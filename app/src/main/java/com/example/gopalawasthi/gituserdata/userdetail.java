package com.example.gopalawasthi.gituserdata;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;


/**
 * A simple {@link Fragment} subclass.
 */
public class userdetail extends Fragment {
    TextView textView;
    ImageView imageView;

    public userdetail() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
            View view =inflater.inflate(R.layout.fragment_userdetail, container, false);
                textView = view.findViewById(R.id.userdetailname);
                imageView = view.findViewById(R.id.userdetailimage);
                Bundle bundle = getArguments();
               textView.setText(bundle.getString("username"));
                String a =bundle.getString("avatar");
        Picasso.get().load(a).into(imageView);

            return view;

    }

}
