package com.example.android.miwok;


import android.annotation.TargetApi;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    // global mediaplayer;
    private static MediaPlayer mediaPlayer;
    //sets background color of the view
    private AudioManager audioManager = (AudioManager) getContext().getSystemService(getContext().AUDIO_SERVICE);
    private int mBackgroundColor;
    private AudioManager.OnAudioFocusChangeListener afistener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {

            //Stopping sound if there is interruption
            if (focusChange == AudioManager.AUDIOFOCUS_LOSS || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                releaseMediaPlayer();
            } else if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mediaPlayer.start();
            }
        }
    };


    public WordAdapter(Context context, ArrayList<Word> words, int background) {
        super(context, 0, words);
        mBackgroundColor = background;

    }

    @TargetApi(Build.VERSION_CODES.O)
    public void releaseMediaPlayer() {
        if (mediaPlayer != null) {
            mediaPlayer.release();
            audioManager.abandonAudioFocus(afistener);
            mediaPlayer = null;
        }
    }

    @TargetApi(Build.VERSION_CODES.O)
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        //implementing audiofocuschangelistener
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build();
        final AudioFocusRequest focusRequest = new AudioFocusRequest.Builder(AudioManager.AUDIOFOCUS_GAIN_TRANSIENT_EXCLUSIVE)
                .setAudioAttributes(audioAttributes)
                .setAcceptsDelayedFocusGain(true)
                .setOnAudioFocusChangeListener(afistener).build();


        final Word words = getItem(position);

        View list_item = convertView;

        if (list_item == null)
            list_item = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);

        list_item.setBackgroundColor(list_item.getResources().getColor(mBackgroundColor));

        TextView miwok = list_item.findViewById(R.id.miwok_text_view);
        miwok.setText(words.getMiwokTranslation());

        TextView def = list_item.findViewById(R.id.default_text_view);
        def.setText(words.getDefaultTranslation());

        final LinearLayout audio_play = list_item.findViewById(R.id.test);

        audio_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                releaseMediaPlayer();

                //Checking if android gives permission to play the translation
                int res = audioManager.requestAudioFocus(focusRequest);
                if (res == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {
                    mediaPlayer = MediaPlayer.create(getContext(), words.getAudioID());
                    mediaPlayer.start();
                }
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        releaseMediaPlayer();
                    }
                });

            }
        });


        ImageView image = list_item.findViewById(R.id.image_view);
        if (!words.hasImage()) {
            image.setVisibility(View.GONE);
        } else {
            image.setImageResource(words.getImageID());
            image.setVisibility(View.VISIBLE);
        }


        return list_item;


    }
}
