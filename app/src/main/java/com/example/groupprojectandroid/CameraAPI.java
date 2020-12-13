package com.example.groupprojectandroid;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.*;
//import android.graphics.Camera;

public class CameraAPI {

    public static boolean CheckCameraAccess(Context context){

        if(context.getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA))
            return true;

        return false;
    }

    public void GetCameraInstance(){

       // Camera c;
        try{
           // c = Camera.open();
        }catch (Exception e){

            e.printStackTrace();
        }
    }


}
