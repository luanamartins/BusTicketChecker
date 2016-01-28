package fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import checker.ticker.bus.basic.R;

public class BusTaxRemoverFragment extends Fragment
{

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.fragment_card_adder, container, false);
        EditText newCardNameEditText = (EditText) view.findViewById(R.id.newCardNameEditText);
        String newCardName = newCardNameEditText.getText().toString();

        return view;
    }

}
