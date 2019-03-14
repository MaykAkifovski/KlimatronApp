package com.example.myapplication.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.myapplication.R;
import com.example.myapplication.model.IdAndBoardName;

import java.util.List;

public class ThreeColumnListAdapter extends ArrayAdapter<IdAndBoardName> {

    private LayoutInflater mInflater;
    private List<IdAndBoardName> idAndBoardNames;
    private int mViewResourceId;

    public ThreeColumnListAdapter(Context context, int textViewResourceId, List<IdAndBoardName> idAndBoardNames) {
        super(context, textViewResourceId, idAndBoardNames);
        this.idAndBoardNames = idAndBoardNames;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        mViewResourceId = textViewResourceId;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = mInflater.inflate(mViewResourceId, null);

        IdAndBoardName idAndBoardName = idAndBoardNames.get(position);

        if (idAndBoardName != null) {
            TextView id = convertView.findViewById(R.id.textId);
            TextView boardName = convertView.findViewById(R.id.textBoardName);
            if (id != null) {
                id.setText(idAndBoardName.getId());
            }
            if (boardName != null) {
                boardName.setText((idAndBoardName.getBoardName()));
            }
        }

        return convertView;
    }
}
