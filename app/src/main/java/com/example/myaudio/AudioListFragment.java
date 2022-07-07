package com.example.myaudio;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;


public class AudioListFragment extends Fragment {

    private RecyclerView audioList;
    private File[] fileList;
    private AudioListAdapter audioListAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_audio_list, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        audioList = view.findViewById(R.id.audio_list_view);


        String path = getActivity().getExternalFilesDir("/").getAbsolutePath();
        File dir = new File(path);
        fileList = dir.listFiles();

        audioListAdapter = new AudioListAdapter(fileList);
        //ottimizza lo spazio della lista
        audioList.setHasFixedSize(true);
        audioList.setLayoutManager(new LinearLayoutManager(getContext()));
        audioList.setAdapter(audioListAdapter);

    }
}