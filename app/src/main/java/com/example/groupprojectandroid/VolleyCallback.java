package com.example.groupprojectandroid;

import com.android.volley.VolleyError;

public interface VolleyCallback {
    void onSuccess(Object result);
    void onError(VolleyError error);
}
