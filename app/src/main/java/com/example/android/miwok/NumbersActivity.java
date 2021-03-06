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

public class NumbersActivity extends AppCompatActivity {

    String[] numbersArray, miwokNumbersArray;
    ArrayList<Word> numbersList;
    Integer[] numbersImages = {
            R.drawable.number_one, R.drawable.number_two, R.drawable.number_three,
            R.drawable.number_four, R.drawable.number_five, R.drawable.number_six,
            R.drawable.number_seven, R.drawable.number_eight, R.drawable.number_nine,
            R.drawable.number_ten};

    Integer[] audioResourceIDs = {
            R.raw.number_one, R.raw.number_two, R.raw.number_three,
            R.raw.number_four, R.raw.number_five, R.raw.number_six,
            R.raw.number_seven, R.raw.number_eight, R.raw.number_nine,
            R.raw.number_ten};

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

        // Get the array of numbers in {@link String} form, then store it in an {@link ArrayAdapter}
        // Store them in an ArrayList of Word objects
        numbersArray = getResources().getStringArray(R.array.numbers_list);
        miwokNumbersArray = getResources().getStringArray(R.array.miwok_numbers_list);
        numbersList = getWordArrayList(getWordArray(miwokNumbersArray, numbersArray, numbersImages, audioResourceIDs));

        // Get the background color for this category and pass it to the WordAdapter along with
        // the ArrayList
        int backgroundColor = ContextCompat.getColor(this, R.color.category_numbers);
        WordAdapter numbersAdapter = new WordAdapter(this, numbersList, backgroundColor);

        // Find the {@link ListView} object from the view hierarchy of the {@link Activity} and use
        // the {@link ArrayAdapter} created above to populate the list.
        ListView numbersListView = (ListView) findViewById(R.id.word_list);
        numbersListView.setAdapter(numbersAdapter);

        // Initialise the AudioManager and ensure it can handle all AudioFocus events
        audioManager = (AudioManager) this.getSystemService(Context.AUDIO_SERVICE);

        // Set an OnItemClickListener to handle all clicks and play the audio files
        // Make sure to also request audio focus when playing any sounds and handle when audio focus
        // is granted
        numbersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
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
