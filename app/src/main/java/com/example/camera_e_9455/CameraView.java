package com.example.camera_e_9455;

import android.content.Context;

import android.hardware.Camera;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import java.io.IOException;

public class CameraView extends SurfaceView implements SurfaceHolder.Callback {
    private SurfaceHolder mHolder;
    private Camera mCamera;

    public CameraView(Context context, Camera camera){
        super(context);
        mCamera= camera;
        mCamera.setDisplayOrientation(90);
        mHolder = getHolder();
        mHolder.addCallback(this);
       mHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder){
        try{
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        }catch(IOException e){
            Log.d("Error","Camera error on SurfaceCreated"+e.getMessage());
        }
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2){
        if(mHolder.getSurface()== null)
            return;
        try{
            mCamera.setPreviewDisplay(mHolder);
            mCamera.startPreview();
        }catch (IOException e){
            Log.d("Error","Camera error on SurfaceCreated"+ e.getMessage());
        }

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder){
        mCamera.stopPreview();
        mCamera.release();
    }
}
