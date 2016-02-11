package busticketchecker.fragments.adapter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import busticketchecker.database.dao.BusCardDAO;
import checker.ticker.bus.basic.R;

public class CustomList extends ArrayAdapter<BusCardDAO>
{
    private final Activity context;
    private final ArrayList<BusCardDAO> web;
//    private final Integer[] imageId;

    public CustomList(Activity context, ArrayList<BusCardDAO> web)
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

        BusCardDAO card = web.get(position);
        txtTitle.setText(card.getName());

        txtTitle = (TextView) rowView.findViewById(R.id.second_text);
        txtTitle.setText(card.getType());

        return rowView;
    }
}
