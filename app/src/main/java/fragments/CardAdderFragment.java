package fragments;


import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import checker.ticker.bus.basic.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardAdderFragment extends Fragment {

    public CardAdderFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_card_adder, container, false);
        EditText newCardNameEditText = (EditText) view.findViewById(R.id.newCardNameEditText);
        String newCardName = newCardNameEditText.getText().toString();

        return view;
    }

}
