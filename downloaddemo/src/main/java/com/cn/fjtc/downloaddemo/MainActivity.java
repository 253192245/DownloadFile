package com.cn.fjtc.downloaddemo;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "PlayActivity";
    private SurfaceView surfaceView;
    private CameraUtils cameraUtils;
    private String path, name;
    private ImageView btn;
    private Button sc_btn;
    int x = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);
        sc_btn=findViewById(R.id.sc_btn);

        String yyyyMMdd_hHmmss = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        Log.e("TAG",yyyyMMdd_hHmmss);
        surfaceView=findViewById(R.id.surfaceView);
        cameraUtils = new CameraUtils();
        cameraUtils.create(surfaceView, this);
        path = Environment.getExternalStorageDirectory().getAbsolutePath();
        //name = yyyyMMdd_hHmmss+"video";
        name = "video";
        btn.setOnClickListener(this);
        sc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cameraUtils.deleteFolderFile(name+".mp4",true);
            }
        });
    }

    @Override
    public void onClick(View v) {

        if (x == 0) {
            cameraUtils.startRecord(path, name);
            btn.setImageResource(R.drawable.tz);
            x = 1;
        } else if (x == 1) {
            cameraUtils.stopRecord();
            btn.setImageResource(R.drawable.ks);
            x=0;
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
        cameraUtils.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
        cameraUtils.destroy();
    }
}
