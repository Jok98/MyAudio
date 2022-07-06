package com.example.myaudio;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


public class RecordFragment extends Fragment implements View.OnClickListener {

    private NavController navController;

    private ImageView listBtn;

    private ImageView recordBtn;

    private boolean isRecording=false;

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
                    recordBtn.setImageDrawable(getResources().getDrawable(R.drawable.microphone, null));
                    isRecording=false;

                }else {
                    //start recording
                    recordBtn.setImageDrawable(getResources().getDrawable(R.drawable.microphone2, null));
                    isRecording=true;
                }
                break;
        }
    }
}