package com.example.gopalawasthi.gituserdata;

import android.os.AsyncTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by Gopal Awasthi on 14-03-2018.
 */

public class UserAsynchronous extends AsyncTask<String,Void,ArrayList<User_attr>> {

    public  interface myinterface {
        void ondownloadcomplete(ArrayList<User_attr> user_attrs);
    }

    myinterface mlistener;

    public UserAsynchronous(myinterface mlistener) {
        this.mlistener = mlistener;
    }

    @Override
    protected ArrayList<User_attr> doInBackground(String... strings) {
        String urlstring = strings[0];
        try {
            URL url = new URL(urlstring);
            HttpsURLConnection httpsURLConnection =(HttpsURLConnection) url.openConnection();
            httpsURLConnection.setRequestMethod("GET");
            httpsURLConnection.connect();
            InputStream inputStream = httpsURLConnection.getInputStream();
            Scanner scanner = new Scanner(inputStream);
           String result = "";
            while(scanner.hasNext()){
                result = result.concat(scanner.next());

            }
            ArrayList<User_attr> arrayList = parseruser(result);
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

    private ArrayList<User_attr> parseruser(String result) throws JSONException {
        ArrayList<User_attr> arrayList = new ArrayList<>();
        JSONObject jsonObject = new JSONObject(result);

       String username = jsonObject.getString("name");
        int user_id = jsonObject.getInt("id");
        String userLocation = jsonObject.getString("location");
        String userFollowers =jsonObject.getString("followers");
        String userFollowing = jsonObject.getString("following");
        String userDp = jsonObject.getString("avatar_url");
        User_attr user_attr = new User_attr(username,user_id,userLocation,userDp,userFollowers,userFollowing);
        arrayList.add(user_attr);
        return arrayList;
    }

    @Override
    protected void onPostExecute(ArrayList<User_attr> user_attrs) {
        super.onPostExecute(user_attrs);
        mlistener.ondownloadcomplete(user_attrs);
    }
}
