package com.example.android.miwok;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

import static com.example.android.miwok.WordUtilities.getWordArray;
import static com.example.android.miwok.WordUtilities.getWordArrayList;

public class ColorsActivity extends AppCompatActivity {

    String[] colorsArray, miworkColorsArray;
    ArrayList<Word> colorsList;

    Integer[] colorsImages = {
            R.drawable.color_red, R.drawable.color_green, R.drawable.color_brown,
            R.drawable.color_gray, R.drawable.color_black, R.drawable.color_white,
            R.drawable.color_dusty_yellow, R.drawable.color_mustard_yellow};

    Integer[] audioFiles = {
            R.raw.color_red, R.raw.color_green, R.raw.color_brown,
            R.raw.color_gray, R.raw.color_black, R.raw.color_white,
            R.raw.color_dusty_yellow, R.raw.color_mustard_yellow};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_list);

        // Get the array of colors in {@link String} form, then store it in an {@link ArrayAdapter}
        // Store them in an ArrayList of Word objects
        colorsArray = getResources().getStringArray(R.array.colors_list);
        miworkColorsArray = getResources().getStringArray(R.array.miwok_colors_list);
        colorsList = getWordArrayList(getWordArray(miworkColorsArray, colorsArray, colorsImages, audioFiles));

        // Get the background color for this category and pass it to the WordAdapter along with
        // the ArrayList
        int backgroundColor = ContextCompat.getColor(this, R.color.category_colors);
        WordAdapter colorsAdapter = new WordAdapter(this, colorsList, backgroundColor);

        // Find the {@link ListView} object from the view hierarchy of the {@link Activity} and use
        // the {@link ArrayAdapter} created above to populate the list.
        ListView colorsListView = (ListView) findViewById(R.id.word_list);
        colorsListView.setAdapter(colorsAdapter);
    }
}
