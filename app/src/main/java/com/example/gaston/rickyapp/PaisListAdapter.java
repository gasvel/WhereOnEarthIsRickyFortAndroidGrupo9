package com.example.gaston.rickyapp;


import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.example.gaston.rickyapp.model.PaisNombre;

import java.util.List;


public class PaisListAdapter extends ArrayAdapter<PaisNombre> {
    private Context context;
    private List<PaisNombre> values;


    public PaisListAdapter(Context context,int textViewResourceId, List<PaisNombre> values) {
        super(context, textViewResourceId, values);
        this.context = context;
        this.values = values;

    }

    @Override
    public int getCount() {
        return values.size();
    }

    @Override
    public PaisNombre getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView label = new TextView(context);
        label.setTextColor(Color.BLACK);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(values.get(position).getNombre());

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }
}