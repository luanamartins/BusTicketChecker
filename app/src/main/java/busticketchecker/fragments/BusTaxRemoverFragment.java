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

import java.util.ArrayList;
import java.util.HashMap;

import busticketchecker.database.SQLite.repository.TaxRepo;
import busticketchecker.database.dao.BusCardDAO;
import checker.ticker.bus.basic.R;

public class BusTaxRemoverFragment extends Fragment
{
    private ArrayList<String> listOfTaxesNames;
    private TaxRepo taxRepository;
    private View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        // Inflate the layout for this fragment
        this.rootView = inflater.inflate(R.layout.fragment_main_drawer, container, false);
        this.setListOfTaxes(rootView);

        return rootView;
    }

    private void setListOfTaxes(View rootView){
        final ListView list = (ListView) rootView.findViewById(R.id.listView2);

        this.taxRepository = new TaxRepo(rootView.getContext());
        ArrayList<HashMap<String, String>> listOfTaxes = taxRepository.getTaxList();
        this.listOfTaxesNames = new ArrayList<>();

        int numberOfTaxesOnDatabase = listOfTaxes.size();

        for (int i = 0; i < numberOfTaxesOnDatabase; i++)
        {
            listOfTaxesNames.add(listOfTaxes.get(i).get(BusCardDAO.KEY_NAME));
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(rootView.getContext(), android.R.layout.simple_list_item_1, android.R.id.text1, listOfTaxesNames);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {
                String remove = getString(R.string.remove);
                String tax = getString(R.string.bus_tax).toLowerCase();

                String taxName = listOfTaxesNames.get(position);

                String title = remove + " " + tax;
                String message = remove + " " + taxName + "?";

                showDialog(title, message, taxName);
            }
        });
    }

    private void showDialog(String title, String message, String taxName)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setMessage(message);
        String yes = getString(R.string.yes);
        String no = getString(R.string.no);
        final String taxNameUnchangeable = taxName;
        builder.setPositiveButton(yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id)
            {
                taxRepository.delete(taxNameUnchangeable);
                setListOfTaxes(rootView);
            }
        });
        builder.setNegativeButton(no, null);
        builder.show();
    }

}
