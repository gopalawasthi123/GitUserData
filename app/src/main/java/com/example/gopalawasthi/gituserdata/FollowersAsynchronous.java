package com.example.gopalawasthi.gituserdata;

import android.os.AsyncTask;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.jar.JarException;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Gopal Awasthi on 15-03-2018.
 */

public class FollowersAsynchronous extends AsyncTask<String,Void,ArrayList<Followers>> {

    public  interface followersinterface{
        void onfollowerscomplete(ArrayList<Followers>arrayList);
    }

    followersinterface mlistener;

    public FollowersAsynchronous(followersinterface mlistener) {
        this.mlistener = mlistener;
    }

    @Override
    protected ArrayList<Followers> doInBackground(String... strings) {
        String followersurl = strings[0];
        try {
            URL url = new URL(followersurl);
            HttpsURLConnection httpsURLConnection =(HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.connect();
            InputStream inputStream = httpsURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
            String result = "";
            while (scanner.hasNext()){
                result = result.concat(scanner.next());
            }
            ArrayList<Followers> arrayList = parsedata(result);
            httpsURLConnection.disconnect();
            return arrayList;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return null;
    }

    private ArrayList<Followers> parsedata(String result) throws JSONException {
        ArrayList<Followers > arrayList = new ArrayList<>();
        JSONArray jsonArray = new JSONArray(result);
        for(int i =0 ; i < jsonArray.length() ; i++){
         JSONObject jsonObject  =jsonArray.getJSONObject(i);
         String name = jsonObject.getString("login");
         String image = jsonObject.getString("avatar_url");
         Followers followers = new Followers(name,image);
         arrayList.add(followers);
        }
        return arrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<Followers> followers) {
        super.onPostExecute(followers);
        mlistener.onfollowerscomplete(followers);
    }
}
