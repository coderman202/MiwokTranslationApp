package com.example.android.miwok;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.miwok.WordUtilities.getWordArray;
import static com.example.android.miwok.WordUtilities.getWordArrayList;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

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
    }
}
