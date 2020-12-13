package com.example.groupprojectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.groupprojectandroid.Model.User;

public class LoginActivity extends AppCompatActivity {

    EditText loginEmail;
    EditText loginPassword;
    TextView signUpText;

    Button loginButton;
    LoadingDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.username);
        loginPassword = findViewById(R.id.password);
        signUpText = findViewById(R.id.signUpText);

        loginButton = findViewById(R.id.signInButton);
        dialog = new LoadingDialog(LoginActivity.this);

        if(loginButton == null){

            Toast.makeText(LoginActivity.this, "error login", Toast.LENGTH_SHORT).show();
        }


        loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                dialog.startLoadingDialog();
                final String email = loginEmail.getText().toString();
                final String password = loginPassword.getText().toString();

                if (email.equals(null)  || email.equals("")) {

                    Toast.makeText(LoginActivity.this, "Email can not be empty!!", Toast.LENGTH_SHORT).show();
                    loginEmail.requestFocus();
                    dialog.dismissLoadingDialog();
                    return;
                } else if (password.equals(null)  || password.equals("")) {

                    Toast.makeText(LoginActivity.this, "Password can not be empty!!", Toast.LENGTH_SHORT).show();
                    loginPassword.requestFocus();
                    dialog.dismissLoadingDialog();
                    return;
                }

                Data.LoginUser(LoginActivity.this, email, password,new VolleyCallback() {
                    @Override
                    public void onSuccess(Object result) {

                        User user = (User) result;

                            Intent i = new Intent(LoginActivity.this, HomePage.class);
                            i.putExtra("email", email);
                            startActivity(i);
                            dialog.dismissLoadingDialog();
                    }

                    @Override
                    public void onError(VolleyError error) {

                        loginPassword.setText("");
                        Toast.makeText(LoginActivity.this, "Error..Try Again!!", Toast.LENGTH_LONG).show();
                        error.printStackTrace();
                        dialog.dismissLoadingDialog();
                    }
                });
            }
        });

        signUpText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(i);
            }
        });

    }
}