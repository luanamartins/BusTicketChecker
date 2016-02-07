package busticketchecker.fragments;


import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import checker.ticker.bus.basic.R;

public class BusTaxAdderFragment extends Fragment implements View.OnClickListener
{
    public BusTaxAdderFragment()
    {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_bus_tax_adder, container, false);
        EditText newBusTaxEditText = (EditText) view.findViewById(R.id.newBusTaxEditText);
        String taxName = newBusTaxEditText.getText().toString();
        EditText newBusTaxValueEditText = (EditText) view.findViewById(R.id.newBusTaxValueEditText);

        ((Button) view.findViewById(R.id.addNewBusTaxButton)).setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View v)
    {

    }

}
