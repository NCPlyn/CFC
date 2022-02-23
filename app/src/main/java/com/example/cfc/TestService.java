package com.example.cfc;

import android.app.Service;
import android.content.Intent;
import android.os.Environment;
import android.os.FileUtils;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class TestService extends Service {
    public TestService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub

        Toast.makeText(getApplicationContext(), "Service Created", Toast.LENGTH_SHORT).show();
        super.onCreate();
    }

    @Override
    public void onDestroy() {
        // TODO Auto-generated method stub
        Toast.makeText(getApplicationContext(), "Service Destroy", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // TODO Auto-generated method stub
        moveIt();
        return super.onStartCommand(intent, flags, startId);
    }

    public void moveIt() {
        String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Camera/";
        File directory = new File(path);
        File[] files = directory.listFiles();
        for (int i = 0; i < files.length; i++)
        {
            if(files[i].getName().length() > 30) {
                moveMaker(files[i].getName());
            }
        }
        Arrays.fill(files, null);
    }

    private void moveMaker(String filename) {
        String desPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/TikTok/";
        File des = new File(desPath, filename);

        String sourcePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Camera/";
        File source = new File(sourcePath, filename);

        try {
            FileUtils.copy(new FileInputStream(source), new FileOutputStream(des));
        }
        catch (IOException fnfe2) {
            Log.e("tag", fnfe2.getMessage());
        }

        source.delete();
    }
}