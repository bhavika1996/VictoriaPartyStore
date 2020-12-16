package com.example.groupprojectandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.VolleyError;

public class AddReviewActivity extends AppCompatActivity {


    private ImageView imageView;
    private static final int REQUEST_IMAGE_CAPTURE = 101;


    // Define the pic id
//    private static final int pic_id = 123;

    // Define the button and imageview type variable
//    Button addImagebtn;
//    ImageView cameraimage;

    EditText reviewDescription;
    Button saveReview;
    UserDetailsSingleton userDetailsSingleton = UserDetailsSingleton.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        reviewDescription = findViewById(R.id.addReviewDescription);
        saveReview = findViewById(R.id.saveReview);

        Intent intent = getIntent();
        Bundle extra = intent.getExtras();

        final String inventoryId = extra.get("inventoryId").toString();

        Toast.makeText(AddReviewActivity.this, inventoryId,  Toast.LENGTH_SHORT).show();

        saveReview.setOnClickListener(new View.OnClickListener() {

            //LoadingDialog dialog = new LoadingDialog(AddReviewActivity.this);
            @Override
            public void onClick(View v) {

                String desc = reviewDescription.getText().toString();

                if (desc != null && desc.length() > 0 && desc != " ") {

                    Data.AddReview(AddReviewActivity.this, inventoryId, reviewDescription.getText().toString(), userDetailsSingleton.userDetails.get("userId"), new VolleyCallback() {

                        @Override
                        public void onSuccess(Object result) {

                            Intent i = new Intent(AddReviewActivity.this, ItemDetailActivity.class);
                            i.putExtra("inventoryId", inventoryId);
                            i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                            startActivity(i);
                        }

                        @Override
                        public void onError(VolleyError error) {

                            Toast.makeText(AddReviewActivity.this, "Error! Try Again!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }

//    public void takepicture(View view) {
//        Intent camera_intent
//                = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//
//        if (camera_intent.resolveActivity(getPackageManager()) != null) {
//            startActivityForResult(camera_intent, REQUEST_IMAGE_CAPTURE);
//        }
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
//            Bundle extras = data.getExtras();
//            Bitmap imageBitmap = (Bitmap) extras.get("data");
//            imageView.setImageBitmap(imageBitmap);
//        }
//    }
}

//        addImagebtn = (Button) findViewById(R.id.addImageBtn);
//        cameraimage = (ImageView) findViewById(R.id.cameraimage);
//
//        addImagebtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent camera_intent
//                        = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
//                startActivityForResult(camera_intent, pic_id);
//            }
//        });
//    }
//
//    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//
//        // Match the request 'pic id with requestCode
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == pic_id) {
//
//            // BitMap is data structure of image file
//            // which stor the image in memory
//            Bitmap photo = (Bitmap) data.getExtras()
//                    .get("data");
//
//            // Set the image in imageview for display
//            cameraimage.setImageBitmap(photo);
//        }
//    }
