package com.example.groupprojectandroid;

import android.content.Context;
import android.icu.text.UnicodeSetSpanner;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.groupprojectandroid.Model.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.HashMap;

public class Data {

    static String userAPIURL = "https://peaceful-mountain-14416.herokuapp.com/api/users/email/";

    public static void GetUser(Context context, String email, final VolleyCallback callback) {

        String userReqURL = userAPIURL +   email;
        final User user = new User();

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, userReqURL, null, new Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        User user = new User();

                        try {
                            user.set_id(response.getString("_id"));
                            user.setEmail(response.getString("email"));
                            user.setFirstName(response.getString("firstName"));
                            user.setLastNamel(response.getString("lastName"));
                            user.setPassword(response.getString("password"));

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        callback.onSuccess(user);
                    }
                }, new ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        callback.onError(error);
                    }
                });

        queue.add(jsonObjectRequest);
    }
}
