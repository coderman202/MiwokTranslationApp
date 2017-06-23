package com.example.android.miwok;

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

public class PhrasesActivity extends AppCompatActivity {

    String[] phrasesArray, miwokPhrasesArray;
    ArrayList<Word> phrasesList;

    Integer[] imageResourceIDs = {
            R.drawable.speech_bubble_red, R.drawable.speech_bubble_blue,
            R.drawable.speech_bubble_brown, R.drawable.speech_bubble_green,
            R.drawable.speech_bubble_light_blue, R.drawable.speech_bubble_purple,
            R.drawable.speech_bubble_yellow, R.drawable.speech_bubble_pink,
            R.drawable.speech_bubble_grey, R.drawable.speech_bubble_orange};

    Integer[] audioResourceIDs = {
            R.raw.phrase_where_are_you_going, R.raw.phrase_what_is_your_name,
            R.raw.phrase_my_name_is, R.raw.phrase_how_are_you_feeling,
            R.raw.phrase_im_feeling_good, R.raw.phrase_are_you_coming,
            R.raw.phrase_yes_im_coming, R.raw.phrase_im_coming,
            R.raw.phrase_lets_go, R.raw.phrase_come_here};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Get the array of phrases in {@link String} form, then store it in an {@link ArrayAdapter}
        // Store them in an ArrayList of Word objects
        phrasesArray = getResources().getStringArray(R.array.phrases_list);
        miwokPhrasesArray = getResources().getStringArray(R.array.miwok_phrases_list);
        phrasesList = getWordArrayList(getWordArray(miwokPhrasesArray, phrasesArray, imageResourceIDs, audioResourceIDs));

        // Get the background color for this category and pass it to the WordAdapter along with
        // the ArrayList
        int backgroundColor = ContextCompat.getColor(this, R.color.category_phrases);
        WordAdapter numbersAdapter = new WordAdapter(this, phrasesList, backgroundColor);

        // Find the {@link ListView} object from the view hierarchy of the {@link Activity} and use
        // the {@link ArrayAdapter} created above to populate the list.
        ListView phrasesListView = (ListView) findViewById(R.id.word_list);
        phrasesListView.setAdapter(numbersAdapter);

        // Set an OnItemClickListener to handle all clicks and play the audio files
        phrasesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                final MediaPlayer mediaPlayer = MediaPlayer.create(getApplicationContext(), audioResourceIDs[position]);
                mediaPlayer.start();

                // Set an OnCompletionListener to ensure the MediaPlayer object is released after use
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.reset();
                        mp.release();
                    }
                });
            }
        });
    }
}
