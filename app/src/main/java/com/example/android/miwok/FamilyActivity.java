package com.example.android.miwok;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.miwok.WordUtilities.getWordArray;
import static com.example.android.miwok.WordUtilities.getWordArrayList;

public class FamilyActivity extends AppCompatActivity {

    String[] familyArray, miwokFamilyArray;
    ArrayList<Word> familyList;

    Integer[] familyImages = {
            R.drawable.family_father, R.drawable.family_mother, R.drawable.family_son,
            R.drawable.family_daughter, R.drawable.family_older_brother, R.drawable.family_younger_brother,
            R.drawable.family_older_sister, R.drawable.family_younger_sister, R.drawable.family_grandfather,
            R.drawable.family_grandmother};

    Integer[] audioResourceIDs = {
            R.raw.family_father, R.raw.family_mother, R.raw.family_son,
            R.raw.family_daughter, R.raw.family_older_brother, R.raw.family_younger_brother,
            R.raw.family_older_sister, R.raw.family_younger_sister, R.raw.family_grandfather,
            R.raw.family_grandmother};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Get the array of family words in {@link String} form, then store it in an {@link ArrayAdapter}
        // Store them in an ArrayList of Word objects
        familyArray = getResources().getStringArray(R.array.family_list);
        miwokFamilyArray = getResources().getStringArray(R.array.miwok_family_list);
        familyList = getWordArrayList(getWordArray(miwokFamilyArray, familyArray, familyImages, audioResourceIDs));

        // Get the background color for this category and pass it to the WordAdapter along with
        // the ArrayList
        int backgroundColor = ContextCompat.getColor(this, R.color.category_family);
        WordAdapter numbersAdapter = new WordAdapter(this, familyList, backgroundColor);

        // Find the {@link ListView} object from the view hierarchy of the {@link Activity} and use
        // the {@link ArrayAdapter} created above to populate the list.
        ListView numbersList = (ListView) findViewById(R.id.word_list);
        numbersList.setAdapter(numbersAdapter);
    }
}
