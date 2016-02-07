package busticketchecker.fragments;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import checker.ticker.bus.basic.R;

public class BusTaxRemoverFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_main_drawer, container, false);
        final ListView list = (ListView) rootView.findViewById(R.id.listView2);
        final String[] values = new String[]{"Tarifa 1", "Tarifa 2",};

        final Context context = getActivity().getBaseContext();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, android.R.id.text1, values);
        list.setAdapter(adapter);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String remove = getString(R.string.remove);
                String busTax = getString(R.string.bus_tax).toLowerCase();

                String title = remove + " " + busTax;
                String message = remove + " " + values[position] + "?";

                showDialog(title, message);
            }
        });
        return rootView;
    }

    private void showDialog(String title, String message, String taxName)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        String yes = getString(R.string.yes);
        String no = getString(R.string.no);
        builder.setPositiveButton(yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                // TODO
            }
        });
        builder.setNegativeButton(no, null);
        builder.show();
    }

}
