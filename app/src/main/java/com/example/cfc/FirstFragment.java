package com.example.cfc;

import android.os.Bundle;
import android.os.Environment;
import android.os.FileUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.cfc.databinding.FragmentFirstBinding;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;

public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    private void moveMaker(String filename) {
        String desPath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES).getAbsolutePath() + "/TikTok/";
        File des = new File(desPath, filename);

        String sourcePath = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM).getAbsolutePath() + "/Camera/";
        File source = new File(sourcePath, filename);

        try {
            copyFile(source, des);
        }
        catch (IOException fnfe2) {
            Log.e("tag", fnfe2.getMessage());
            Toast myToastErr = Toast.makeText(getActivity(), fnfe2.getMessage(), Toast.LENGTH_LONG);
            myToastErr.show();
        }

        source.delete();
    }

    public void copyFile(File source, File destination) throws IOException {
        FileUtils.copy(new FileInputStream(source), new FileOutputStream(destination));
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.buttonFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
                Toast myToast = Toast.makeText(getActivity(), "Done!", Toast.LENGTH_SHORT);
                myToast.show();
            }
        });


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}