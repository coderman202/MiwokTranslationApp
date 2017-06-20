package com.example.android.miwok;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.miwok.WordUtilities.getWordArray;
import static com.example.android.miwok.WordUtilities.getWordArrayList;

public class PhrasesActivity extends AppCompatActivity {

    String[] phrasesArray, miwokPhrasesArray;
    ArrayList<Word> phrasesList;

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
        phrasesList = getWordArrayList(getWordArray(miwokPhrasesArray, phrasesArray, audioResourceIDs));

        // Get the background color for this category and pass it to the WordAdapter along with
        // the ArrayList
        int backgroundColor = ContextCompat.getColor(this, R.color.category_phrases);
        WordAdapter numbersAdapter = new WordAdapter(this, phrasesList, backgroundColor);

        // Find the {@link ListView} object from the view hierarchy of the {@link Activity} and use
        // the {@link ArrayAdapter} created above to populate the list.
        ListView numbersList = (ListView) findViewById(R.id.word_list);
        numbersList.setAdapter(numbersAdapter);
    }
}
