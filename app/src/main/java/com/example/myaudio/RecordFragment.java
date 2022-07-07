package com.example.myaudio;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class RecordFragment extends Fragment implements View.OnClickListener {

    private NavController navController;

    private ImageView listBtn;

    private ImageView recordBtn;

    private boolean isRecording=false;

    private String recordPermission = Manifest.permission.RECORD_AUDIO;

    private MediaRecorder mediaRecorder;

    private String recordFile;


    public RecordFragment() {
        // Required empty public constructor
    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_record, container, false);
    }

    //transizione tra activity
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        navController= Navigation.findNavController(view);
        listBtn=view.findViewById(R.id.record_list_btn);
        recordBtn = view.findViewById(R.id.record_button);
        recordBtn.setOnClickListener(this);

        listBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        //controlla i click
        switch (v.getId()){
            case R.id.record_list_btn:
                //naviga tra le activity
                navController.navigate(R.id.action_recordFragment_to_audioListFragment);
                break;
            case R.id.record_button:
                //isRecording=(isRecording==false)?true:false;
                if (isRecording){
                    //stop recording
                    stopRecording();
                    recordBtn.setImageDrawable(getResources().getDrawable(R.drawable.microphone, null));
                    isRecording=false;

                }else {
                    //start recording,
                    if(checkPermission()) {
                        startRecording();
                        recordBtn.setImageDrawable(getResources().getDrawable(R.drawable.microphone2, null));
                        isRecording = true;
                    }
                }
                break;
        }
    }

    private void startRecording()  {
        String filePath = getActivity().getExternalFilesDir("/").getAbsolutePath();
        SimpleDateFormat dataFormat= new SimpleDateFormat("yyyy_MM_dd_hh_mm_ss", Locale.ITALY);
        Date now = new Date();
        recordFile = "Rec_"+dataFormat.format(now)+".mp3";
        mediaRecorder = new MediaRecorder();
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        mediaRecorder.setOutputFile(filePath+"/"+recordFile);
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaRecorder.start();

    }

    private void stopRecording() {
        mediaRecorder.stop();
        mediaRecorder.release();
        mediaRecorder = null;
    }

    //controllo dei permessi per la registrazione
    private boolean checkPermission() {
        if( ActivityCompat.checkSelfPermission(getContext(), recordPermission)== PackageManager.PERMISSION_GRANTED){
            return true;
        }else {
            ActivityCompat.requestPermissions(getActivity(),new String[]{recordPermission}, 10);
        }

        return false;
    }
}