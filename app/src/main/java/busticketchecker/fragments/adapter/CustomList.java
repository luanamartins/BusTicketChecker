package busticketchecker.fragments.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import checker.ticker.bus.basic.R;

public class CustomList extends ArrayAdapter<String>
{
    private final Activity context;
    private final ArrayList<String> web;
//    private final Integer[] imageId;

    public CustomList(Activity context, ArrayList<String> web)
    {
        super(context, R.layout.list_single, web);
        this.context = context;
        this.web = web;
//        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.first_text);

        txtTitle.setText(web.get(position));

        txtTitle = (TextView) rowView.findViewById(R.id.second_text);
        txtTitle.setText(web.get(position));

        return rowView;
    }
}
