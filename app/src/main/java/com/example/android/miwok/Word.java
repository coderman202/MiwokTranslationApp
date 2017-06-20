package com.example.android.miwok;

/**
 * Word class which has attributes of a miwok word and a translation in English
 */
class Word {

    private String miwokWord, defaultTranslation;
    private int imageResourceID, audioResourceID;

    /**
     * Instantiates a new Word with the miwok word, the translation and an image.
     *
     * @param miwokWord          the miwok word
     * @param defaultTranslation the default translation
     * @param imageResourceID    the image resource id
     * @param audioResourceID    the audio resource id
     */
    Word(String miwokWord, String defaultTranslation, int imageResourceID, int audioResourceID) {
        this.miwokWord = miwokWord;
        this.defaultTranslation = defaultTranslation;
        this.imageResourceID = imageResourceID;
        this.audioResourceID = audioResourceID;
    }

    /**
     * Instantiates a new Word with the miwok word, the translation and an audio resource id.
     *
     * @param miwokWord          the miwok word
     * @param defaultTranslation the default translation
     * @param audioResourceID    the audio resource id
     */
    Word(String miwokWord, String defaultTranslation, int audioResourceID) {
        this.miwokWord = miwokWord;
        this.defaultTranslation = defaultTranslation;
        this.audioResourceID = audioResourceID;
    }

    /**
     * Instantiates a new Word.
     *
     * @param miwokWord          the miwok word
     * @param defaultTranslation the default translation
     */
    Word(String miwokWord, String defaultTranslation) {
        this.miwokWord = miwokWord;
        this.defaultTranslation = defaultTranslation;
    }

    /**
     * Gets default translation.
     *
     * @return the default translation
     */
    String getDefaultTranslation() {
        return defaultTranslation;
    }

    /**
     * Gets miwok word.
     *
     * @return the miwok word
     */
    String getMiwokWord() {
        return miwokWord;
    }

    /**
     * Sets default translation.
     *
     * @param defaultTranslation the default translation
     */
    public void setDefaultTranslation(String defaultTranslation) {
        this.defaultTranslation = defaultTranslation;
    }

    /**
     * Sets miwok word.
     *
     * @param miwokWord the miwok word
     */
    public void setMiwokWord(String miwokWord) {
        this.miwokWord = miwokWord;
    }

    /**
     * Gets image resource id.
     *
     * @return the image resource id
     */
    int getImageResourceID() {
        return imageResourceID;
    }

    /**
     * Sets image.
     *
     * @param imageResourceID the image resource id
     */
    public void setImageResourceID(int imageResourceID) {
        this.imageResourceID = imageResourceID;
    }

    /**
     * Gets audio resource id.
     *
     * @return the audio resource id
     */
    public int getAudioResourceID() {
        return audioResourceID;
    }

    /**
     * Sets audio resource id.
     *
     * @param audioResourceID the audio resource id
     */
    public void setAudioResourceID(int audioResourceID) {
        this.audioResourceID = audioResourceID;
    }

    /**
     * Has image boolean.
     *
     * @return the boolean
     */
    public boolean hasImage() {
        return (imageResourceID != 0);
    }
}
