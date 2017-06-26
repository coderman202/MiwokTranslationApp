package com.example.android.miwok;

import android.media.MediaPlayer;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * A utility class to contain some common methods to be used throughout the project
 */

final class WordUtilities {

    /**
     * A method to get an array of Word objects from two string arrays of  Miwok and a different
     * language and the int array of audio resource ids
     *
     * @param miwokWordArray          A String array of Miwok words
     * @param defaultTranslationArray A String array of words in another language
     * @param audioResourceArray      An array of audio resource ids
     * @return returns an array of Word objects
     */
    static Word[] getWordArray(String[] miwokWordArray, String[] defaultTranslationArray, Integer[] audioResourceArray) {
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
    static Word[] getWordArray(String[] miwokWordArray, String[] defaultTranslationArray, Integer[] imageResourceArray, Integer[] audioResourceArray) {
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
    static ArrayList<Word> getWordArrayList(Word[] wordArray) {
        return new ArrayList<>(Arrays.asList(wordArray));
    }

    /**
     * Clean up the media player by releasing its resources.
     */
    static void releaseMediaPlayer(MediaPlayer mp) {
        // If the media player is not null, then it may be currently playing a sound.
        if (mp != null) {
            // Regardless of the current state of the media player, release its resources
            // because we no longer need it.
            mp.release();

            // Set the media player back to null. For our code, we've decided that
            // setting the media player to null is an easy way to tell that the media player
            // is not configured to play an audio file at the moment.
            mp = null;
        }
    }
}
