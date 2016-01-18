package checker.ticker.bus.busticketchecker.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import checker.ticker.bus.busticketchecker.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardInserterFragment extends Fragment {


    public CardInserterFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_card_inserter, container, false);
    }

}
