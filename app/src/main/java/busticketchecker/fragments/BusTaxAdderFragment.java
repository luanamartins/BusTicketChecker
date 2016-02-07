package busticketchecker.fragments;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import busticketchecker.database.SQLite.repository.TaxRepo;
import busticketchecker.database.dao.BusTaxDAO;
import checker.ticker.bus.basic.R;

public class BusTaxAdderFragment extends Fragment implements View.OnClickListener
{
    private View view;

    public BusTaxAdderFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        view = inflater.inflate(R.layout.fragment_bus_tax_adder, container, false);

        Button button = (Button) view.findViewById(R.id.addNewBusTaxButton);
        button.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v)
    {
        EditText newBusTaxEditText = (EditText) view.findViewById(R.id.newBusTaxEditText);
        String newTaxName = newBusTaxEditText.getText().toString();

        EditText newBusTaxValueEditText = (EditText) view.findViewById(R.id.newBusTaxValueEditText);
        String newTaxValue = newBusTaxValueEditText.getText().toString();

        Context context = view.getContext();
        TaxRepo repo = new TaxRepo(context);
        BusTaxDAO tax = new BusTaxDAO(newTaxName, Float.valueOf(newTaxValue));
        repo.insert(tax);

        Toast.makeText(context, "OK", Toast.LENGTH_SHORT);

        FragmentManager fragmentManager = getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = new CardListerFragment();

        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }

}
