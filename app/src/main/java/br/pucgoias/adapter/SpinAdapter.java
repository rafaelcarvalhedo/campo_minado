package br.pucgoias.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import br.pucgoias.modelo.NivelJogoEnum;

public class SpinAdapter extends ArrayAdapter<NivelJogoEnum> {

    // Your custom values for the spinner (User)
    private final NivelJogoEnum[] values;

    public SpinAdapter(Context context, int textViewResourceId, NivelJogoEnum[] values) {
        super(context, textViewResourceId, NivelJogoEnum.values());
        // Your sent context
        this.values = values;
    }

    @Override
    public int getCount() {
        return values.length;
    }

    @Override
    public NivelJogoEnum getItem(int position) {
        return values[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }


    // And the "magic" goes here
    // This is for the "passive" state of the spinner
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // I created a dynamic TextView here, but you can reference your own  custom layout for each spinner item
        TextView label = (TextView) super.getView(position, convertView, parent);
        // Then you can get the current item using the values array (Users array) and the current position
        // You can NOW reference each method you has created in your bean object (User class)
        label.setText(getContext().getResources().getString(values[position].getStringResource()));

        // And finally return your dynamic (or custom) view for each spinner item
        return label;
    }

    // And here is when the "chooser" is popped up
    // Normally is the same view, but you can customize it if you want
    @Override
    public View getDropDownView(int position, View convertView,
                                ViewGroup parent) {
        TextView label = (TextView) super.getDropDownView(position, convertView, parent);
        label.setText(getContext().getResources().getString(values[position].getStringResource()));

        return label;
    }
}