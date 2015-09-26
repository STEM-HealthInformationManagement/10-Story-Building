package org.njcuacm.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
 * Created by Saurabh on 6/3/2015.
 */
public class DialogViewTextAdapter extends ArrayAdapter<DialogDisplayListAdapter> {
    //private TextView countryName;
    private TextView speaker;
    private List<DialogDisplayListAdapter> listItems = new ArrayList<DialogDisplayListAdapter>();
    private LinearLayout wrapper;

    @Override
    public void add(DialogDisplayListAdapter object) {
        listItems.add(object);
        super.add(object);
    }

    public DialogViewTextAdapter(Context context, int textViewResourceId) {
        super(context, textViewResourceId);
    }

    public int getCount() {
        return this.listItems.size();
    }

    public DialogDisplayListAdapter getItem(int index) {
        return this.listItems.get(index);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View row = convertView;
        if (row == null) {
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row = inflater.inflate(R.layout.dialog_view_list, parent, false);
        }

        wrapper = (LinearLayout) row.findViewById(R.id.wrapper_dialog);

        DialogDisplayListAdapter textAdapter = getItem(position);

        speaker = (TextView) row.findViewById(R.id.itemOfList);

        speaker.setText(textAdapter.speaker);

        //countryName.setBackgroundResource(comment.left ? R.drawable.bubble_yellow : R.drawable.bubble_green);
        //wrapper.setGravity(textAdapter.left ? Gravity.LEFT : Gravity.RIGHT);

        return row;
    }

    public Bitmap decodeToBitmap(byte[] decodedByte) {
        return BitmapFactory.decodeByteArray(decodedByte, 0, decodedByte.length);
    }
}
