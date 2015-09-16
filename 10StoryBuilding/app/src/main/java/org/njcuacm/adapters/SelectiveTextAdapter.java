package org.njcuacm.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.njcuacm.tenstory.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Saurabh on 9/13/2015.
 */
public class SelectiveTextAdapter extends ArrayAdapter<SelectiveDisplayTextAdapter> {
    private TextView countryName;
    private TextView speaker;
    private List<SelectiveDisplayTextAdapter> countries = new ArrayList<SelectiveDisplayTextAdapter>();
    private LinearLayout wrapper;

    @Override
    public void add(SelectiveDisplayTextAdapter object) {
        countries.add(object);
        super.add(object);
    }

    public SelectiveTextAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public int getCount() {
        return this.countries.size();
    }

    public SelectiveDisplayTextAdapter getItem(int index) {
        return this.countries.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.story_list, parent, false);
        }

        wrapper = (LinearLayout) row.findViewById(R.id.wrapper_dialog);

        SelectiveDisplayTextAdapter textAdapter = getItem(position);

        countryName = (TextView) row.findViewById(R.id.comment);
        speaker = (TextView) row.findViewById(R.id.speaker);

        countryName.setText(textAdapter.comment);
        speaker.setText(textAdapter.speaker);

        //countryName.setBackgroundResource(coment.left ? R.drawable.bubble_yellow : R.drawable.bubble_green);
        wrapper.setGravity(textAdapter.left ? Gravity.LEFT : Gravity.RIGHT);

        row.setBackgroundResource(textAdapter.background_res_color);

        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
