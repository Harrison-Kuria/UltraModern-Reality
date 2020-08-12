package ultramodern.activity.besha;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.PixelFormat;
import android.hardware.Camera;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.Toast;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.L;
import com.google.android.gms.vision.Tracker;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.FaceDetector;
import com.google.android.gms.vision.face.LargestFaceFocusingProcessor;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.opencv.android.BaseLoaderCallback;
import org.opencv.android.CameraBridgeViewBase;
import org.opencv.android.JavaCameraView;
import org.opencv.android.OpenCVLoader;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.imgproc.Imgproc;

import java.io.IOException;


public class Add_Card extends AppCompatActivity implements CameraBridgeViewBase.CvCameraViewListener2 {
    private static final String TAG="TRACK";
    private static int REQUEST_CODE_CAMERA_PERMISSION = 101;

    CameraBridgeViewBase javaCameraView;
    Mat mRGBA,mRGBAT;
    SurfaceView mSurfaceView;
    SurfaceHolder mSurfaceHolder;

    BaseLoaderCallback baseLoaderCallback ;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__card);

        permission();

        javaCameraView = findViewById(R.id.my_camera);
        javaCameraView.setVisibility(SurfaceView.VISIBLE);
        javaCameraView.setCvCameraViewListener(this);
        javaCameraView.setCameraPermissionGranted();
        //setting surfaceView translucent
        baseLoaderCallback= new BaseLoaderCallback(this) {
            @Override
            public void onManagerConnected(int status) {
                switch (status)
                {
                    case BaseLoaderCallback.SUCCESS:
                    {
                        javaCameraView.enableView();
                        break;
                    }
                    default:
                    {
                        super.onManagerConnected(status);
                    }

                }

            }
        };
    }
    private boolean permission(){
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]
                            {Manifest.permission.CAMERA},
                    50);
        }else{
            return true;
        }
        return false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (javaCameraView !=null){
            javaCameraView.disableView();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (javaCameraView !=null){
            javaCameraView.disableView();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (OpenCVLoader.initDebug()){
            Log.d(TAG,"OPENCV IS CONFIGURED SUCCESSFULLY");
            baseLoaderCallback.onManagerConnected(BaseLoaderCallback.SUCCESS);

        }
        else {
            Log.d(TAG,"OPENCV IS HAVING ISSUES");
            OpenCVLoader.initAsync(OpenCVLoader.OPENCV_VERSION_2_4_3,this,baseLoaderCallback);
        }
    }

    @Override
    public void onCameraViewStarted(int width, int height) {
        mRGBA = new Mat(width,height, CvType.CV_8UC4);
    }

    @Override
    public void onCameraViewStopped() {
        mRGBA.release();
    }

    @Override
    public Mat onCameraFrame(CameraBridgeViewBase.CvCameraViewFrame inputFrame) {
        mRGBA=inputFrame.rgba();
        mRGBAT = mRGBA.t();
        Core.flip(mRGBA.t(),mRGBAT,1);
        Imgproc.resize(mRGBAT,mRGBAT,mRGBA.size());
        return mRGBAT;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}