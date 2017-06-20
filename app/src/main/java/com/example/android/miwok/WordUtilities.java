package com.example.android.miwok;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A utility class to contain some common methods to be used throughout the project
 */

public final class WordUtilities {

    /**
     * A method to get an array of Word objects from two string arrays of  Miwok and a different
     * language and the int array of audio resource ids
     *
     * @param miwokWordArray          A String array of Miwok words
     * @param defaultTranslationArray A String array of words in another language
     * @param audioResourceArray      An array of audio resource ids
     * @return returns an array of Word objects
     */
    public static Word[] getWordArray(String[] miwokWordArray, String[] defaultTranslationArray, Integer[] audioResourceArray) {
        Word[] wordArray = new Word[miwokWordArray.length];
        for (int i = 0; i < wordArray.length; i++) {
            Log.d(miwokWordArray[i], defaultTranslationArray[i]);
            wordArray[i] = new Word(miwokWordArray[i], defaultTranslationArray[i], audioResourceArray[i]);
        }
        return wordArray;
    }

    /**
     * A method to get an array of Word objects from two string arrays of  Miwok and a different
     * language and the Integer array of image resource ids, along with an int array of audio
     * resource ids
     *
     * @param miwokWordArray          A String array of Miwok words
     * @param defaultTranslationArray A String array of words in another language
     * @param imageResourceArray      An array of image resource ids
     * @param audioResourceArray      An array of audio file resource ids
     * @return returns an array of Word objects
     */
    public static Word[] getWordArray(String[] miwokWordArray, String[] defaultTranslationArray, Integer[] imageResourceArray, Integer[] audioResourceArray) {
        Word[] wordArray = new Word[miwokWordArray.length];
        for (int i = 0; i < wordArray.length; i++) {
            wordArray[i] = new Word(miwokWordArray[i], defaultTranslationArray[i], imageResourceArray[i], audioResourceArray[i]);
        }
        return wordArray;
    }

    /**
     * A quick method to get an ArrayList of Word objects from an array of Word objects
     *
     * @param wordArray Takes in an array of Word objects
     * @return returns an ArrayList of Word objects
     */
    public static ArrayList<Word> getWordArrayList(Word[] wordArray) {
        return new ArrayList<>(Arrays.asList(wordArray));
    }
}
