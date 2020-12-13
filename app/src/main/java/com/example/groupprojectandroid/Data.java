package com.example.groupprojectandroid;

import android.content.Context;
import android.icu.text.UnicodeSetSpanner;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.Response.Listener;
import com.android.volley.Response.ErrorListener;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.groupprojectandroid.Model.User;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Data {

    private static String apiURL = "https://party-store-android-api.herokuapp.com/";
    private static String userAPIURL = apiURL + "api/users";
    private static String inventoryAPIURL = apiURL + "api/inventory";
    private static String loginURl = apiURL + "login/user";
    private static String token;
    public static String name;

    public static void GetInventories(final Context context, final VolleyCallback callback) {

        RequestQueue queue = Volley.newRequestQueue(context);
        final HashMap<String, String> inventories = new HashMap<>();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, inventoryAPIURL, null,
                new Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {

                            for (int i = 0; i < response.length(); i++) {
                                JSONObject employee = response.getJSONObject(i);
                                String name = employee.getString("firstName");
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Log.i("Get", "Error in response");
                        }
                    }
                }, new ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                error.printStackTrace();
                Toast.makeText(context, "Error in Req", Toast.LENGTH_SHORT).show();
                Log.i("Get", "Error in request");
            }
        });
    }

    public static void LoginUser(final Context context, String email, String password, final VolleyCallback callback) {

        final User user = new User();

        JSONObject postData = new JSONObject();

        try {
            postData.put("email", email);
            postData.put("password", password);

        } catch (JSONException e) {

            e.printStackTrace();
        }

        RequestQueue queue = Volley.newRequestQueue(context);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, loginURl, postData, new Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        User user = new User();
                        Object res = response;

                        try {
                            user.set_id(response.getJSONObject("user").getString("_id"));
                            user.setEmail(response.getJSONObject("user").getString("email"));
                            user.setFirstName(response.getJSONObject("user").getString("firstName"));
                            user.setLastName(response.getJSONObject("user").getString("lastName"));
                            user.setPassword(response.getJSONObject("user").getString("password"));

                            Data.name = user.getFirstName() + " " + user.getLastName();
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

    public static void CreateUser(Context context, User user, final VolleyCallback callback) {

        RequestQueue queue = Volley.newRequestQueue(context);
        JSONObject postData = new JSONObject();

        try {

            postData.put("firstName", user.getFirstName());
            postData.put("lastName", user.getLastName());
            postData.put("email", user.getEmail());
            postData.put("password", user.getPassword());
            postData.put("role", user.getRole());
        } catch (JSONException e) {

            e.printStackTrace();
        }

        JsonObjectRequest postRequest = new JsonObjectRequest
                (Request.Method.POST, userAPIURL, postData, new Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        callback.onSuccess(response);
                    }
                }, new ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                        error.printStackTrace();
                        callback.onError(error);
                    }
                });
        queue.add(postRequest);
    }
}
