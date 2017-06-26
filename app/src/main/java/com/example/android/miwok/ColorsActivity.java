package com.example.android.miwok;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.miwok.WordUtilities.getWordArray;
import static com.example.android.miwok.WordUtilities.getWordArrayList;
import static com.example.android.miwok.WordUtilities.releaseMediaPlayer;

public class ColorsActivity extends AppCompatActivity {

    String[] colorsArray, miworkColorsArray;
    ArrayList<Word> colorsList;

    Integer[] colorsImages = {
            R.drawable.color_red, R.drawable.color_green, R.drawable.color_brown,
            R.drawable.color_gray, R.drawable.color_black, R.drawable.color_white,
            R.drawable.color_dusty_yellow, R.drawable.color_mustard_yellow};

    Integer[] audioResourceIDs = {
            R.raw.color_red, R.raw.color_green, R.raw.color_brown,
            R.raw.color_gray, R.raw.color_black, R.raw.color_white,
            R.raw.color_dusty_yellow, R.raw.color_mustard_yellow};

    static MediaPlayer mediaPlayer =  null;

    // Declare an instance of  AudioManager for managing any audio focus events
    static AudioManager audioManager;

    /**
     * Create a listener.
     * This listener is called whenever the audio focus changes
     */
    private AudioManager.OnAudioFocusChangeListener audioFocusChangeListener = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            if(focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT ||
                    focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK){
                mediaPlayer.pause();
                mediaPlayer.seekTo(0);
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_GAIN){
                mediaPlayer.start();
            }
            else if(focusChange == AudioManager.AUDIOFOCUS_LOSS){
                releaseMediaPlayer(mediaPlayer);
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Setting an up button on the app bar to allow user to navigate back to the home screen
        getSupportActionBar().setHomeButtonEnabled(true);

        // Get the array of colors in {@link String} form, then store it in an {@link ArrayAdapter}
        // Store them in an ArrayList of Word objects
        colorsArray = getResources().getStringArray(R.array.colors_list);
        miworkColorsArray = getResources().getStringArray(R.array.miwok_colors_list);
        colorsList = getWordArrayList(getWordArray(miworkColorsArray, colorsArray, colorsImages, audioResourceIDs));

        // Get the background color for this category and pass it to the WordAdapter along with
        // the ArrayList
        int backgroundColor = ContextCompat.getColor(this, R.color.category_colors);
        WordAdapter colorsAdapter = new WordAdapter(this, colorsList, backgroundColor);

        // Find the {@link ListView} object from the view hierarchy of the {@link Activity} and use
        // the {@link ArrayAdapter} created above to populate the list.
        ListView colorsListView = (ListView) findViewById(R.id.word_list);
        colorsListView.setAdapter(colorsAdapter);

        // Initialise the AudioManager and ensure it can handle all AudioFocus events
        audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        // Set an OnItemClickListener to handle all clicks and play the audio files
        // Make sure to also request audio focus when playing any sounds and handle when audio focus
        // is granted
        colorsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                releaseMediaPlayer(mediaPlayer);
                int result = audioManager.requestAudioFocus(audioFocusChangeListener,
                        AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if(result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED){
                    mediaPlayer = MediaPlayer.create(getApplicationContext(), audioResourceIDs[position]);
                    mediaPlayer.start();

                    // Set an OnCompletionListener to ensure the MediaPlayer object is released after use
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mp.reset();
                            releaseMediaPlayer(mp);
                        }
                    });
                }
            }
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseMediaPlayer(mediaPlayer);
        audioManager.abandonAudioFocus(audioFocusChangeListener);
    }
}
