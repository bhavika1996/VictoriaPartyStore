package com.example.groupprojectandroid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CheckoutActivity extends AppCompatActivity {
    EditText fname,useraddress,useraddress1,town,postalcode,phone,email,cardnumber,expdate,cvvnumber;
    Button paynow;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_checkout);

        fname = (EditText)findViewById(R.id.personName);
        useraddress = (EditText)findViewById(R.id.userAddress);
        useraddress1 = (EditText)findViewById(R.id.userAddress1);
        town = (EditText)findViewById(R.id.userTown);
        postalcode = (EditText)findViewById(R.id.userPostalCode);
        phone = (EditText)findViewById(R.id.userNumber);
        email = (EditText)findViewById(R.id.userEmail);
        cardnumber = (EditText)findViewById(R.id.CardNumber);
        expdate = (EditText)findViewById(R.id.ExpDate);
        cvvnumber = (EditText)findViewById(R.id.CvvNumber);
        paynow = (Button) findViewById(R.id.paynowBtn);
        paynow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String name=fname.getText().toString();
                final String address=useraddress.getText().toString();
                final String city=town.getText().toString();
                final String postcode=postalcode.getText().toString();
                final String mobile=phone.getText().toString();
                final String emailaddress=email.getText().toString().trim();
                final String cardno=cardnumber.getText().toString();
                final String expiry=expdate.getText().toString();
                final String cvv=cvvnumber.getText().toString();
                if(name.length()==0)
                {
                    fname.requestFocus();
                    fname.setError("FIELD CANNOT BE EMPTY");
                }
                else if(!name.matches("[a-zA-Z ]+"))
                {
                    fname.requestFocus();
                    fname.setError("ENTER ONLY ALPHABETICAL CHARACTER");
                }
                else if(address.length()==0)
                {
                    useraddress.requestFocus();
                    useraddress.setError("FIELD CANNOT BE EMPTY");
                }
                else if(city.length()==0){
                    town.requestFocus();
                    town.setError("FIELD CANNOT BE EMPTY");
                }
                else if(mobile.matches("^\\+[0-9]{10,13}$")){
                    phone.requestFocus();
                    phone.setError("Enter Valid Phone Number");
                }
                else if(emailaddress.matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")){
                    email.requestFocus();
                    email.setError("Must Be Valid Email Address");
                }
                else if(postcode.length()==0 ){
                    postalcode.requestFocus();
                    postalcode.setError("Enter Valid Postal Code");
                }
                else if(cardno.length()==16){
                    cardnumber.requestFocus();
                    cardnumber.setError("Enter Valid Card Number");
                }
                else if(expiry.length()==0){
                    expdate.requestFocus();
                    expdate.setError("FIELD CANNOT BE EMPTY");
                }
                else if(cvv.length()==0){
                    cvvnumber.requestFocus();
                    cardnumber.setError("FIELD CANNOT BE EMPTY");
                }
                else
                {
                    Toast.makeText(CheckoutActivity.this,"Your Order Has been Placed Successfully",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}