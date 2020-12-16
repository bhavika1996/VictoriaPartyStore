package com.example.groupprojectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.example.groupprojectandroid.Model.User;

public class SignUpActivity extends AppCompatActivity {

    EditText nameET;
    EditText emailET;
    EditText passwordET;
    EditText confirmPasswordET;
    LoadingDialog loadingDialog;

    Button signUpBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameET = findViewById(R.id.personName);
        emailET = findViewById(R.id.editTextTextEmailAddress);
        passwordET = findViewById(R.id.editTextTextPassword2);
        confirmPasswordET = findViewById(R.id.editTextTextPassword3);

        loadingDialog = new LoadingDialog(SignUpActivity.this);
        signUpBtn = findViewById(R.id.signUpBtn);

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameET.getText().toString();
                String email = emailET.getText().toString();
                String password = passwordET.getText().toString();
                String confirmPassword = confirmPasswordET.getText().toString();

                if (name.equals("") || email.equals("") || password.equals("") || confirmPassword.equals("")) {

                    Toast.makeText(SignUpActivity.this, "Please fill up every details.", Toast.LENGTH_SHORT).show();
                    return;
                } else if (!password.equals(confirmPassword) && password.length() > 6) {

                    Toast.makeText(SignUpActivity.this, "Password doesn't match. \n Or password shoud contain more than 6 charactors", Toast.LENGTH_SHORT).show();
                    return;
                } else {

                    loadingDialog.startLoadingDialog();
                    User user = new User(name, " ", email, password, "Customer");
                    postUser(user);
                }
            }
        };

        signUpBtn.setOnClickListener(clickListener);
    }

    void postUser(User user) {

        Data.CreateUser(SignUpActivity.this, user, new VolleyCallback() {
            @Override
            public void onSuccess(Object result) {

                //User newUser = (User) result;

                //Toast.makeText(SignUpActivity.this, "onSuccess",Toast.LENGTH_SHORT ).show();

                Intent i = new Intent(SignUpActivity.this, LoginActivity.class);
                startActivity(i);
                loadingDialog.dismissLoadingDialog();
            }

            @Override
            public void onError(VolleyError error) {

                Toast.makeText(SignUpActivity.this, "Error...Try Again!", Toast.LENGTH_SHORT).show();
                loadingDialog.dismissLoadingDialog();
                error.printStackTrace();
            }
        });
    }
}