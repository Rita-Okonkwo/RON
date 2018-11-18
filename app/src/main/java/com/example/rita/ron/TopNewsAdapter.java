package com.example.rita.ron;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class TopNewsAdapter extends ArrayAdapter<TopNews> {

    public TopNewsAdapter(Context context, ArrayList<TopNews> topNews) {
        super(context, 0, topNews);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // Get the data item for this position

        View listItemView = convertView;

        // Check if an existing view is being reused, otherwise inflate the view

        if (listItemView == null) {

            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.main_xml, parent, false);

        }

        TopNews currentTopNewsAdapter = getItem(position);
        TextView title = (TextView) listItemView.findViewById(R.id.title);
        title.setText(currentTopNewsAdapter.getmTitle());

        TextView section = (TextView) listItemView.findViewById(R.id.section);
        section.setText(currentTopNewsAdapter.getmSection());

        TextView date = (TextView) listItemView.findViewById(R.id.date);
        date.setText(currentTopNewsAdapter.getmDate());


        return listItemView;
    }
}
