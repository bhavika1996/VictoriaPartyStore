package com.example.groupprojectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.groupprojectandroid.Model.User;

public class LoginActivity extends AppCompatActivity {

    TextView loginEmail;
    TextView loginPassword;

    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.username);
        loginPassword = findViewById(R.id.password);

        loginButton = findViewById(R.id.signInButton);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final String email = loginEmail.getText().toString();
                final String password = loginPassword.getText().toString();

                Data.GetUser(LoginActivity.this, email, new VolleyCallback() {
                    @Override
                    public void onSuccess(Object result) {

                        User user = (User) result;

                        if (password.equals(user.getPassword())) {

                            Intent i = new Intent(LoginActivity.this, HomePage.class);
                            i.putExtra("email", email);
                            startActivity(i);
                        } else {

                            Toast.makeText(LoginActivity.this, "User not found!!", Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onError(VolleyError error) {

                        loginPassword.setText("");
                        Toast.makeText(LoginActivity.this, "Error..Try Again!!", Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                    }
                });
            }
        });

    }
}