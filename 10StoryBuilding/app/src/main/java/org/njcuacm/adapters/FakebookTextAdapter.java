package org.njcuacm.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.njcuacm.tenstory.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saurabh on 9/12/2015.
 */
public class FakebookTextAdapter extends ArrayAdapter<FakebookDisplayTextAdapter> {
    private TextView countryName;
    private TextView speaker;
    private List<FakebookDisplayTextAdapter> countries = new ArrayList<FakebookDisplayTextAdapter>();
    private LinearLayout wrapper;
    private ImageView imageView;

    @Override
    public void add(FakebookDisplayTextAdapter object) {
        countries.add(object);
        super.add(object);
    }

    public FakebookTextAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public int getCount() {
        return this.countries.size();
    }

    public FakebookDisplayTextAdapter getItem(int index) {
        return this.countries.get(index);
    }

    public void removeItem(int index) {
        this.countries.remove(countries.get(index - 1));
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.fakebook_comment_list, parent, false);
        }

        wrapper = (LinearLayout) row.findViewById(R.id.wrapper_dialog);

        FakebookDisplayTextAdapter textAdapter = getItem(position);

        countryName = (TextView) row.findViewById(R.id.comment);
        speaker = (TextView) row.findViewById(R.id.speaker);
        imageView = (ImageView) row.findViewById(R.id.profile_pic);

        countryName.setText(textAdapter.comment);
        speaker.setText(textAdapter.speaker);
        imageView.setImageResource(textAdapter.profile_pic_resource);

        //countryName.setBackgroundResource(coment.left ? R.drawable.bubble_yellow : R.drawable.bubble_green);
        wrapper.setGravity(textAdapter.left ? Gravity.LEFT : Gravity.RIGHT);

        row.setBackgroundResource(textAdapter.row_bg_color);

        return row;
    }
}
