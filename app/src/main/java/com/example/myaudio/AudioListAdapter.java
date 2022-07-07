package com.example.myaudio;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;

//usata per popolare la lista audio_list_view
public class AudioListAdapter extends RecyclerView.Adapter<AudioListAdapter.AudioViewHolder> {

    private File[] fileList;

    public AudioListAdapter(File[] fileList){
        this.fileList=fileList;
    }

    @NonNull
    @Override
    public AudioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_list_item,parent, false);
        return new AudioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AudioViewHolder holder, int position) {
        holder.audio_title.setText(fileList[position].getName());
        //lastModified() da la data come long
        holder.audio_date.setText(fileList[position].lastModified()+"");
    }

    @Override
    public int getItemCount() {
        return fileList.length;
    }

    public class AudioViewHolder extends RecyclerView.ViewHolder{
        private ImageView audio_image;
        private TextView audio_title;
        private TextView audio_date;


        public AudioViewHolder(@NonNull View itemView) {
            super(itemView);
            audio_image = itemView.findViewById(R.id.audio_list_view);
            audio_title = itemView.findViewById(R.id.audio_title);
            audio_date = itemView.findViewById(R.id.audio_date);

        }
    }
}
