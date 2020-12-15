package com.example.groupprojectandroid;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

public class AddReviewActivity extends AppCompatActivity {


    private ImageView imageView;
    private static final int REQUEST_IMAGE_CAPTURE = 101;


    // Define the pic id
//    private static final int pic_id = 123;

    // Define the button and imageview type variable
//    Button addImagebtn;
//    ImageView cameraimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);
        imageView = findViewById(R.id.takepicture);
    }

    public void takepicture(View view) {
        Intent camera_intent
                = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (camera_intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(camera_intent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            imageView.setImageBitmap(imageBitmap);
        }
    }
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
