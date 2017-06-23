package com.example.android.miwok;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Custom Adapter class for printing words to the screen along with their translation
 */
public class WordAdapter extends ArrayAdapter<Word> {


    private int backgroundColor;

    /**
     * Instantiates a new {@link WordAdapter}.
     *
     * @param context       the context
     * @param wordArrayList the word array list
     * @param backgroundColor  the background color for each list item in this adapter
     */
    WordAdapter(Context context, ArrayList<Word> wordArrayList, int backgroundColor) {
        super(context, 0, wordArrayList);
        this.backgroundColor = backgroundColor;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final Word currentWord = getItem(position);

        // Get the LinearLayout with the TextViews to set the correct background. Also set the play
        // button to the correct background.
        LinearLayout textViews = (LinearLayout) listItemView.findViewById(R.id.text_container);
        textViews.setBackgroundColor(backgroundColor);
        ImageView playButton = (ImageView) listItemView.findViewById(R.id.play_button);
        playButton.setBackgroundColor(backgroundColor);

        // Get the three other views in the list item by their id, and set them to the appropriate
        // text and image.
        TextView translationView = (TextView) listItemView.findViewById(R.id.list_item_translation);
        TextView miwokView = (TextView) listItemView.findViewById(R.id.list_item_miwok);
        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);

        imageView.setBackgroundResource(currentWord.getImageResourceID());
        imageView.setVisibility(View.VISIBLE);

        // Set the textviews to the miwok word and the translation
        translationView.setText(currentWord.getDefaultTranslation());
        miwokView.setText(currentWord.getMiwokWord());

        return listItemView;
    }
}
